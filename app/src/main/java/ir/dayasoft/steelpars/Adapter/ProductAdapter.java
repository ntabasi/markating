package ir.dayasoft.steelpars.Adapter;

import android.content.Context;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Communication.BitmapWorkerTask;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;
import ir.dayasoft.steelpars.Class.Color;

public class ProductAdapter extends BaseAdapter {

    List<Product> data;
    Context MyContext;
    Boolean showPrice;

    public ProductAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public ProductAdapter(Context _MyContext, List<Product> _data) {
        MyContext = _MyContext;
        data = _data;
        showPrice=AppConfig.IsConfirm(_MyContext);
    }

    public void setData(List<Product> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public Product getItem(int position) {
        if (data == null)
            return null;
        try {
            Product element = (Product) data.get(position);
            return element;
        } catch (Exception e) {
            return null;
        }
    }
    public long getItemId(int position) {
        if (data == null)
            return -1;
        try {
            Product element = (Product) data.get(position);
            return  position;
            //return element.getProductId();
        } catch (Exception e) {
            return -1;
        }

    }

    static class ViewHolder {

        View colorView;
        TextView titleTextView;
        TextView priceTextView;
        ImageView imageImageView;

        int position;
    }

    public View getView(int Position, View convertView, ViewGroup parent) {

        final int position = Position;
        final Product node = data.get(position);
        View rowView = convertView;
        ViewHolder holder;
        int c1;
        int c2;
        int c3;

        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.grid_item_product, null);
            holder = new ViewHolder();

            holder.titleTextView = (TextView) rowView.findViewById(R.id.product_item_title_textView);
            holder.priceTextView = (TextView) rowView.findViewById(R.id.product_item_price_textView);
            holder.imageImageView = (ImageView) rowView.findViewById(R.id.product_item_image_imageView);

            holder.colorView = rowView.findViewById(R.id.product_item_colorBar_view);


            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }



        Typeface tf= Farsi.GetFarsiFont(MyContext);
        if (node.getImageUrl().lastIndexOf("/")>0) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(holder.imageImageView, R.drawable.detail_pic, parent.getContext());
            bitmapWorkerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, node.getImageUrl().substring(node.getImageUrl().lastIndexOf("/") + 1));
        }

        Long categoryId = AppConfig.GetFK_categoryId(parent.getContext());
        List<Color> colorList = Color.GetColorByCategoryId(parent.getContext(), categoryId);


        c1 = android.graphics.Color.parseColor("#48b2ca");
        c2 = android.graphics.Color.parseColor("#ff4dbfd9");
        c3 = android.graphics.Color.parseColor("#32afff");


        holder.colorView.setBackgroundColor(c1);
        holder.titleTextView.setBackgroundColor(c2);
        holder.priceTextView.setBackgroundColor(c3);


        holder.titleTextView.setTypeface(tf);
      //  holder.priceTextView.setTypeface(tf);

        if (showPrice)
        holder.priceTextView.setText(Core.Converter.DoubleToString(node.getPrice() * 1000));
        else
        holder.priceTextView.setText("-");


        holder.titleTextView.setText(node.getName());

       /* Picasso.with(parent.getContext()).
                load(Constants.WebImageDirectoryPath + node.getImageUrl()).
                placeholder(R.drawable.detail_pic).
                into(holder.imageImageView);*/



        return rowView;
    }


}
