package ir.dayasoft.steelpars.Fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Adapter.InvoiceDetailAdapter;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Class.InvoiceProduct;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;


public class InvoiceDetailFragment extends Fragment {

    ListView myListView;
    List<InvoiceProduct> invoiceProductList = new ArrayList<>();
    InvoiceDetailAdapter myAdapter;
    Invoice invoice;

    TextView rowCounterTextView;
    TextView sumPriceTextView;
    TextView remainTextView;
    TextView paymentTextView;
    TextView paymentCode;
    TextView deliveryCost;
    TextView status;

    TextView invoiceDate;
    TextView invoiceNum;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_invoice_detail, container, false);
        Long invoiceId;
        invoiceId = getArguments().getLong(AppIntent.InvoiceDetailFragment, Long.valueOf("0"));


        Typeface tf= Farsi.GetFarsiFont(getActivity());

        Log.i("page", "InvoiceDetailFragment"); //todo log

        myListView = (ListView) rootView.findViewById(R.id.invoiceDetail_listView);


        View footerView = ((LayoutInflater) getActivity()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.list_view_invoice_detail_footer, null);
        myListView.addFooterView(footerView);

        View headerView = ((LayoutInflater) getActivity()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.list_view_invoice_detail_header, null);

        TextView textViewPrice=(TextView)headerView.findViewById(R.id.invoiceDetail_header_price_textView);
        TextView textViewCount=(TextView)headerView.findViewById(R.id.invoiceDetail_header_count_textView);
        TextView textViewTitle=(TextView)headerView.findViewById(R.id.invoiceDetail_header_title_textView);

     //   textViewPrice.setTypeface(tf);
       // textViewCount.setTypeface(tf);
        textViewTitle.setTypeface(tf);

        myListView.addHeaderView(headerView);


        invoiceProductList = InvoiceProduct.GetAllInvoiceProductByFK_invoiceId(getActivity(), invoiceId);
        invoice = Invoice.GetSingleInvoice(getActivity(), invoiceId);

        //define
        rowCounterTextView = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_rowCounter_textView);
        sumPriceTextView = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_sumPrice_textView);
        remainTextView = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_remain_textView);
        paymentTextView = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_payment_textView);
        paymentCode = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_code_textView);
        deliveryCost = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_deliveryCost_textView);
        status = (TextView) footerView.findViewById(R.id.invoiceDetail_footer_status_textView);

        invoiceNum = (TextView) headerView.findViewById(R.id.invoiceDetail_header_invoiceNumber_textView);
        invoiceDate = (TextView) headerView.findViewById(R.id.invoiceDetail_header_date_textView);

        rowCounterTextView.setTypeface(tf);
       // sumPriceTextView.setTypeface(tf);
       //  remainTextView.setTypeface(tf);
       //  paymentTextView.setTypeface(tf);
       //  paymentCode.setTypeface(tf);
       // deliveryCost.setTypeface(tf);
        status.setTypeface(tf);

        invoiceNum.setTypeface(tf);
        invoiceDate.setTypeface(tf);

        status.setText(Invoice.GetStatusText(invoice.getStatus()));
        status.setTextColor(Color.parseColor( Invoice.GetStatusColor(invoice.getStatus())));


        if (invoice.getServerInvoiceId() == 0)
            invoiceNum.setText("شماره فاکتور: ثبت نشده");
        else
            invoiceNum.setText("شماره فاکتور :" +  String.valueOf(invoice.getServerInvoiceId()));
        String shamsiDate = String.valueOf(Core.Dates.ConvertMiladiToShamsi(invoice.getCreateDate(), false));
        invoiceDate.setText(String.valueOf(Core.Dates.GetYear(shamsiDate)).substring(2) + "/" +
                String.valueOf(Core.Dates.GetMonthOfYear(shamsiDate)) + "/" +
                String.valueOf(Core.Dates.GetDay(shamsiDate)));


        if (invoice.getPaymentType() == Invoice.PaymentTypeFull) {
            remainTextView.setVisibility(View.GONE);

            paymentTextView.setText("فاکتور کاملاً پرداخت شده");
            paymentTextView.setTextColor(getResources().getColor(R.color.invoice_detail_green));
        } else {
            paymentTextView.setText("بیعانه پرداخت شده: " + Core.Converter.DoubleToString(invoice.getDeposit() * 1000) + " ریال");
            remainTextView.setText("مانده: " + Core.Converter.DoubleToString(invoice.getPrice() * 1000 - invoice.getDeposit() * 1000) + " ریال ");
        }
       /* if (invoice.getPaymentCode().equals("null"))
            paymentCode.setText("شناسه پرداخت: ثبت نشده");
        else
            paymentCode.setText("شناسه پرداخت: " + invoice.getPaymentCode());*/

        deliveryCost.setText("هزینه ارسال: " + Core.Converter.DoubleToString(invoice.getDeliveryCost() * 1000));
        rowCounterTextView.setText("تعداد کل اقلام: " + String.valueOf(invoiceProductList.size()) + " عدد ");
        sumPriceTextView.setText("جمع کل فاکتور: " + Core.Converter.DoubleToString(invoice.getPrice() * 1000) + " ریال ");


        myListView.setVerticalScrollBarEnabled(false);
        myListView.setHorizontalScrollBarEnabled(false);


        return rootView;
    }

    @Override
    public void onResume() {

        myAdapter = new InvoiceDetailAdapter(getActivity(), invoiceProductList);
        myListView.setAdapter(myAdapter);

        super.onResume();
    }
}