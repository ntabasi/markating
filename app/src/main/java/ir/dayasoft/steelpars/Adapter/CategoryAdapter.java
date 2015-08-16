package ir.dayasoft.steelpars.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Class.Category;
import ir.dayasoft.steelpars.Communication.BitmapWorkerTask;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;


public class CategoryAdapter extends BaseAdapter {

    List<Category> data;
    Context MyContext;

    public CategoryAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public CategoryAdapter(Context _MyContext, List<Category> _data) {
        MyContext = _MyContext;
        data = _data;
    }

    public void setData(List<Category> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public Category getItem(int position) {
        if (data == null)
            return null;
        try {
            Category element = (Category) data.get(position);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public long getItemId(int position) {
        if (data == null)
            return -1;
        try {
            Category element = (Category) data.get(position);
            return position;
            //return element.getStringId();
        } catch (Exception e) {
            return -1;
        }

    }


    static class ViewHolder {

        TextView titleTextView;
        ImageView iconImageView;
        ImageView imageImageView;
        View backgroundView;
        int position;
    }
    public View getView(int Position, View convertView, ViewGroup parent) {

        final int position = Position;
        final Category node = data.get(Position);
        Activity activity = (MyContext instanceof Activity) ? (Activity) MyContext : null;
        View rowView = convertView;
        ViewHolder holder;
        int i=0;

        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.list_item_category,
                    null);
            holder = new ViewHolder();

            //default
            holder.titleTextView = (TextView) rowView.findViewById(R.id.category_item_title_textView);
            holder.iconImageView = (ImageView) rowView.findViewById(R.id.category_item_icon_imageView);
            holder.imageImageView = (ImageView) rowView.findViewById(R.id.category_item_image_imageView);
            holder.backgroundView = (View) rowView.findViewById(R.id.category_item_background_view);
            Typeface tf= Farsi.GetFarsiFont(MyContext);



            holder.titleTextView.setTypeface(tf);

            holder.position = Position;
            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }


        try {
            //holder.backgroundView.setBackgroundColor(Color.parseColor(node.getColor()));
            holder.backgroundView.setBackgroundColor(Color.parseColor("#32afff"));
        } catch (Exception e) {

        }

        if (node.getImageUrl().lastIndexOf("/")>0) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(holder.imageImageView, R.drawable.sample_category, MyContext);
            bitmapWorkerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, node.getImageUrl().substring(node.getImageUrl().lastIndexOf("/") + 1));
        }
   /*     Picasso.with(activity).
                load(Constants.WebImageDirectoryPath + node.getImageUrl()).
                into(holder.imageImageView) ;
*/



        holder.titleTextView.setText(node.getName());



        return rowView;
    }


}
