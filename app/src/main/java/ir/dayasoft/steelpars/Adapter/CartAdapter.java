package ir.dayasoft.steelpars.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Communication.BitmapWorkerTask;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;
import it.sephiroth.android.library.picasso.Picasso;

public class CartAdapter extends BaseAdapter {

    List<Cart> data;
    Context MyContext;

    public CartAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public CartAdapter(Context _MyContext, List<Cart> _data) {
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

    public void remove(int position){
        data.remove(data.get(position-1));
        setData(data);
    }

    static class ViewHolder {

        TextView title_textView;
        TextView price_textView;
        Spinner counter_spinner;
        ImageView icon_imageView;
        LinearLayout row_linearLayout;
        int position;
    }
    public View getView(int Position, final View convertView, ViewGroup parent) {

        final int position = Position;
        final Cart node = data.get(Position);
        View rowView = convertView;
        ViewHolder holder;
        int i=0;

        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.list_item_cart,
                    null);
            holder = new ViewHolder();

            //define
            holder.title_textView = (TextView) rowView.findViewById(R.id.cart_item_title_textView);
            holder.price_textView = (TextView) rowView.findViewById(R.id.cart_item_price_textView);
            holder.counter_spinner = (Spinner) rowView.findViewById(R.id.cart_item_counter_spinner);
            holder.row_linearLayout = (LinearLayout) rowView.findViewById(R.id.cart_item_linearLayout);
            holder.icon_imageView = (ImageView) rowView.findViewById(R.id.cart_item_image_imageView);



            final TextView priceTextView = (TextView) rowView.findViewById(R.id.cart_item_price_textView);
            holder.counter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int count = i+1;
                    Activity activity = (MyContext instanceof Activity) ? (Activity) MyContext : null;
                    final int position = (int) adapterView.getTag();
                    long cartId = data.get(position).getCartId();
                    Cart cart = Cart.GetSingleCart(activity,cartId);

                    Cart.DeleteCart(activity, Long.valueOf(cartId));
                    cart.setCount(Double.valueOf(count));
                    Cart.InsertCart(activity,cart);
                    Product product = Product.GetSingleProduct(activity, cart.getFK_productId());

                    double priceDouble = product.getPrice() * cart.getCount() ;
                    priceTextView.setText(Core.Converter.DoubleToString(priceDouble*1000));

                    Intent intent = new Intent(AppIntent.ListenerUpdateSumPrice);
                    activity.sendBroadcast(intent);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



            holder.position = Position;
            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Typeface tf= Farsi.GetFarsiFont(MyContext);

      //  holder.price_textView.setTypeface(tf);
        holder.title_textView.setTypeface(tf);

        Activity activity = (MyContext instanceof Activity) ? (Activity) MyContext : null;


        String[] counter = activity.getResources().getStringArray(R.array.counter);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item,
                counter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.counter_spinner.setAdapter(dataAdapter);

        holder.counter_spinner.setSelection(Core.Converter.DoubleToInt(node.getCount())-1);
        holder.counter_spinner.setTag(position);

        double sumPriceDouble = Double.valueOf(node.getProductPrice()) * node.getCount();
        holder.title_textView.setText(node.getProductName());
        holder.price_textView.setText(Core.Converter.DoubleToString(sumPriceDouble));
        if(position%2 == 0)
            holder.row_linearLayout.setBackgroundColor(activity.getResources().getColor(R.color.table_gray_1));
        else
            holder.row_linearLayout.setBackgroundColor(activity.getResources().getColor(R.color.table_gray_2));

        Picasso.with(activity).
                load(Constants.WebImageDirectoryPath+node.getProductImage()).
                into(holder.icon_imageView);

        if (node.getProductImage().lastIndexOf("/")>0) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(holder.icon_imageView, R.drawable.detail_pic, parent.getContext());
            bitmapWorkerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,node.getProductImage().substring(node.getProductImage().lastIndexOf("/") + 1));
        }


        return rowView;

    }





}
