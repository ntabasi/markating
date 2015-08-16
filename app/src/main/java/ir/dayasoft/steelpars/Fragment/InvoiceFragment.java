package ir.dayasoft.steelpars.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Adapter.InvoiceAdapter;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.R;


public class InvoiceFragment extends Fragment {

    ListView myListView;
    List<Invoice> invoices = new ArrayList<>();
    InvoiceAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_invoice, container, false);

        invoices = Invoice.GetAllInvoice(getActivity());
        myListView = (ListView) rootView.findViewById(R.id.invoice_listView);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long invoiceId = myAdapter.getItem(i).getInvoiceId();


                Bundle bundle = new Bundle();
                bundle.putLong(AppIntent.InvoiceDetailFragment, invoiceId);

                InvoiceDetailFragment fragment=new InvoiceDetailFragment();
                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        myListView.setVerticalScrollBarEnabled(false);
        myListView.setHorizontalScrollBarEnabled(false);

        return rootView;
    }

    @Override
    public void onResume() {
        myAdapter = new InvoiceAdapter(getActivity(), invoices);
        myListView.setAdapter(myAdapter);
        super.onResume();
    }

}