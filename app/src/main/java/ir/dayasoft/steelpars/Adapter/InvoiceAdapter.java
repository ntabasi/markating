package ir.dayasoft.steelpars.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.R;


public class InvoiceAdapter extends BaseAdapter {

    List<Invoice> data;
    Context MyContext;

    public InvoiceAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public InvoiceAdapter(Context _MyContext, List<Invoice> _data) {
        MyContext = _MyContext;
        data = _data;
    }

    public void setData(List<Invoice> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public Invoice getItem(int position) {
        if (data == null)
            return null;
        try {
            Invoice element = (Invoice) data.get(position);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public long getItemId(int position) {
        if (data == null)
            return -1;
        try {
            Invoice element = (Invoice) data.get(position);
            return position;
            //return element.getStringId();
        } catch (Exception e) {
            return -1;
        }

    }


    static class ViewHolder {

        TextView counterTextView;
        TextView priceTextView;
        TextView dateTextView;
        TextView stateTextView;
        TextView invoiceNumberTextView;
        int position;
    }
    public View getView(int Position, View convertView, ViewGroup parent) {

        final int position = Position;
        final Invoice node = data.get(Position);
        View rowView = convertView;
        ViewHolder holder;
        int i = 0;

        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.list_item_invoice,
                    null);
            holder = new ViewHolder();

            //default
            holder.counterTextView = (TextView) rowView.findViewById(R.id.invoice_item_counter_textView);
            holder.priceTextView = (TextView) rowView.findViewById(R.id.invoice_item_invoicePrice_textView);
            holder.dateTextView = (TextView) rowView.findViewById(R.id.invoice_item_date_textView);
            holder.stateTextView = (TextView) rowView.findViewById(R.id.invoice_item_state_textView);
            holder.invoiceNumberTextView = (TextView) rowView.findViewById(R.id.invoice_item_invoiceNumber_textView);

            holder.position = Position;
            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Activity activity = (MyContext instanceof Activity) ? (Activity) MyContext : null;
        holder.counterTextView.setText(String.valueOf(position+1));
        holder.priceTextView.setText(Core.Converter.DoubleToString(node.getPrice()*1000));


        String shamsiDate;
        shamsiDate = String.valueOf(Core.Dates.ConvertMiladiToShamsi(node.getCreateDate(),false));
        //year = year.substring(2);


        if(node.getServerInvoiceId() != 0)
            holder.invoiceNumberTextView.setText(String.valueOf(node.getServerInvoiceId()));



        holder.dateTextView.setText(String.valueOf(Core.Dates.GetYear(shamsiDate)).substring(2)+"\n"+
                String.valueOf(Core.Dates.GetMonthOfYear(shamsiDate)+1)+"\n"+
                String.valueOf(Core.Dates.GetDay(shamsiDate)));



        switch (node.getStatus()) {
            case Invoice.Status_Confirm:
                rowView.setBackgroundResource(R.drawable.selector_item_listview_green);
                holder.stateTextView.setText("تایید شده");
                break;
            case Invoice.Status_Deliver:
                rowView.setBackgroundResource(R.drawable.selector_item_listview_yellow);
                holder.stateTextView.setText("منتظر پاسخ");
                break;
            case Invoice.Status_Reject:
                rowView.setBackgroundResource(R.drawable.selector_item_listview_red);
                holder.stateTextView.setText("کنسل شده");
                break;
            case -1:
               return null;// rowView.setBackgroundResource(R.drawable.selector_item_listview_gray);

            default:
                break;
        }
        return rowView;

    }
}
