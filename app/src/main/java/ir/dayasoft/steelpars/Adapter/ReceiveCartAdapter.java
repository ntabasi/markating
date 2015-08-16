package ir.dayasoft.steelpars.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.R;


public class ReceiveCartAdapter extends BaseAdapter {

    List<Cart> data;
    Context MyContext;

    public ReceiveCartAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public ReceiveCartAdapter(Context _MyContext, List<Cart> _data) {
        MyContext = _MyContext;
        data = _data;
    }

    public void setData(List<Cart> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public Cart getItem(int position) {
        if (data == null)
            return null;
        try {
            Cart element = (Cart) data.get(position);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public long getItemId(int position) {
        if (data == null)
            return -1;
        try {
            Cart element = (Cart) data.get(position);
            return position;
            //return element.getStringId();
        } catch (Exception e) {
            return -1;
        }

    }


    static class ViewHolder {

        TextView productNameTextView;
        TextView counterTextView;
        TextView priceTextView;
        LinearLayout row_linearLayout;

        int position;
    }
    public View getView(int Position, View convertView, ViewGroup parent) {

        final int position = Position;
        final Cart node = data.get(Position);
        View rowView = convertView;
        ViewHolder holder;
        int i = 0;

        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.list_item_invoice_detail,
                    null);
            holder = new ViewHolder();


            holder.productNameTextView = (TextView) rowView.findViewById(R.id.invoiceDetail_item_productName_textView);
            holder.counterTextView = (TextView) rowView.findViewById(R.id.invoiceDetail_item_counter_textView);
            holder.priceTextView = (TextView) rowView.findViewById(R.id.invoiceDetail_item_price_textView);
            holder.row_linearLayout = (LinearLayout) rowView.findViewById(R.id.cart_item_linearLayout);

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        Activity activity = (MyContext instanceof Activity) ? (Activity) MyContext : null;

        holder.productNameTextView.setText(node.getProductName());
        holder.counterTextView.setText(Core.Converter.DoubleToString(node.getCount()));
        holder.priceTextView.setText(Core.Converter.DoubleToString(Double.valueOf(node.getProductPrice()) * node.getCount()*1000));


        if(position%2 == 0)
            holder.row_linearLayout.setBackgroundColor(activity.getResources().getColor(R.color.table_gray_1));
        else
            holder.row_linearLayout.setBackgroundColor(activity.getResources().getColor(R.color.table_gray_2));
        return rowView;

    }
}
