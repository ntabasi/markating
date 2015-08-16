package ir.dayasoft.steelpars.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Adapter.ReceiveCartAdapter;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.CreateDialog;
import ir.dayasoft.steelpars.R;


public class PreInvoiceFragment extends Fragment {

    ListView myListView;
    List<Cart> cartList = new ArrayList<>();
    ReceiveCartAdapter myAdapter;
    Context context;

    TextView rowCounterTextView;
    TextView sumPriceTextView;
    TextView deliveryCost;
    ImageView cancelImageView;
    ImageView okImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_receive_cart, container, false);
        context = getActivity();
        Log.i("page", "PreInvoiceFragment"); //todo log

        myListView = (ListView) rootView.findViewById(R.id.invoiceDetail_listView);


        View footerView = ((LayoutInflater) getActivity()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.list_view_receive_cart_footer, null);
        myListView.addFooterView(footerView);

        View headerView = ((LayoutInflater) getActivity()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.list_view_receive_cart_header, null);
        myListView.addHeaderView(headerView);


        cartList = Cart.GetAllCart(getActivity());
        Double cartSum = Cart.GetCartSum(getActivity());

        footerView.setClickable(false);
        //define
        rowCounterTextView = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_rowCounter_textView);
        sumPriceTextView = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_sumPrice_textView);
        deliveryCost = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_deliveryCost_textView);

        cancelImageView = (ImageView) rootView.findViewById(R.id.receiveCart_cancel_imageView);
        okImageView = (ImageView) rootView.findViewById(R.id.receiveCart_ok_imageView);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new CartFragment())
                        .commit();
            }
        });
        okImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateDialog.CreatePreInvoiceDialog(context);

            }
        });


        rowCounterTextView.setText("تعداد کل اقلام: "+ String.valueOf(cartList.size())+ " عدد ");
        sumPriceTextView.setText("جمع کل فاکتور: "+ Core.Converter.DoubleToString(cartSum*1000) + " ریال ");



        myListView.setVerticalScrollBarEnabled(false);
        myListView.setHorizontalScrollBarEnabled(false);


        return rootView;
    }

    @Override
    public void onResume() {

        myAdapter = new ReceiveCartAdapter (getActivity(), cartList);
        myListView.setAdapter(myAdapter);

        super.onResume();
    }
}