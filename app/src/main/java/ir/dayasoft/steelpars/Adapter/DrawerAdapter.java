package ir.dayasoft.steelpars.Adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Class.Drawer;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;


public class DrawerAdapter extends BaseAdapter {

    List<Drawer> data;
    Context MyContext;


    public DrawerAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public DrawerAdapter(Context _MyContext, List<Drawer> _data) {
        MyContext = _MyContext;
        data = _data;
    }

    public void setData(List<Drawer> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public Drawer getItem(int position) {
        if (data == null)
            return null;

        try {
            Drawer element = (Drawer) data.get(position);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stubif(data==null)
        if (data == null)
            return -1;
        try {
            Drawer element = (Drawer) data.get(position);
            return position;
        } catch (Exception e) {
            return -1;
        }

    }

    static class ViewHolder {
        TextView title;
        ImageView icon;
        int position;
    }

    public View getView(int Position, View convertView, ViewGroup parent) {
        final Drawer node = data.get(Position);

        View rowView = convertView;
        ViewHolder holder;


        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.drawer_item,
                    null);

            holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.drawer_title_imageView);
            holder.icon = (ImageView) rowView.findViewById(R.id.drawer_icon_imageView);

            holder.position = Position;
            rowView.setTag(holder);
        } else {

            holder = (ViewHolder) rowView.getTag();
        }
        Typeface typeface= Farsi.GetFarsiFont(MyContext);
        holder.title.setTypeface(typeface);
        holder.title.setText(node.getName());
        holder.icon.setImageResource(node.getIcon());


        return rowView;
    }
}
