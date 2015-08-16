package ir.dayasoft.steelpars.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Class.Branch;
import ir.dayasoft.steelpars.R;


public class BranchAdapter extends BaseAdapter {

    List<Branch> data;
    Context MyContext;

    public BranchAdapter(Context _MyContext) {
        MyContext = _MyContext;
    }

    public BranchAdapter(Context _MyContext, List<Branch> _data) {
        MyContext = _MyContext;
        data = _data;
    }

    public void setData(List<Branch> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public Branch getItem(int position) {
        if (data == null)
            return null;
        try {
            Branch element = (Branch) data.get(position);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public long getItemId(int position) {
        if (data == null)
            return -1;
        try {
            Branch element = (Branch) data.get(position);
            return position;
            //return element.getStringId();
        } catch (Exception e) {
            return -1;
        }

    }


    static class ViewHolder {

        TextView title_textView;
        TextView address_textView;
        TextView phone_textView;

        ImageView image_imageView;


        LinearLayout row_linearLayout;
        int position;
    }
    public View getView(int Position, final View convertView, ViewGroup parent) {

        final int position = Position;
        final Branch node = data.get(Position);
        View rowView = convertView;
        ViewHolder holder;
        int i=0;

        if (rowView == null) {

            LayoutInflater li = (LayoutInflater) MyContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = (ViewGroup) li.inflate(R.layout.list_item_branch,
                    null);
            holder = new ViewHolder();

            //define
            holder.title_textView = (TextView) rowView.findViewById(R.id.branchItem_title_textView);
            holder.phone_textView = (TextView) rowView.findViewById(R.id.branchItem_tell_textView);
            holder.address_textView = (TextView) rowView.findViewById(R.id.branchItem_location_textView);


            holder.position = Position;
            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.title_textView.setText(node.getName());
        holder.phone_textView.setText(node.getPhone());
        holder.address_textView.setText(node.getAddress());


        return rowView;

    }
}
