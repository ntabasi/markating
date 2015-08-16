package ir.dayasoft.steelpars.Fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Activity.ProductActivity;
import ir.dayasoft.steelpars.Activity.SubCategoryActivity;
import ir.dayasoft.steelpars.Adapter.CategoryAdapter;
import ir.dayasoft.steelpars.Class.Category;
import ir.dayasoft.steelpars.Class.CategoryColor;
import ir.dayasoft.steelpars.Class.Color;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.R;


public class CategoryFragment extends Fragment {

    ListView myListView;
    List<Category> categoryList = new ArrayList<>();
    CategoryAdapter myAdapter;
    Boolean myReceiverIsRegistered = false;

    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            categoryList = Category.GetCategoryByParent(getActivity(), -1);
            myAdapter.setData(categoryList);

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_category, container, false);

        //Color.ClearColor(getActivity());
        //Color.InsertColor(getActivity(), colorList);

        List<Color> colorList = new ArrayList<>();
        List<CategoryColor> categoryColors = new ArrayList<>();

        colorList = Color.GetColor(getActivity());
        categoryColors = CategoryColor.GetAllCategoryColor(getActivity());

        categoryList = Category.GetAllCategoryWithColor(getActivity());


        myListView = (ListView) rootView.findViewById(R.id.category_listView);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                long categoryId = myAdapter.getItem(i).getCategoryId();
                boolean flag = Category.IsSubCategory(getActivity(), categoryId);
//                List<Category> categoryList = Category.GetCategoryByParent(getActivity(),categoryId);
                // flag = true;
                long id = myAdapter.getItem(i).getCategoryId();
                if (flag) {

                    AppConfig.SetSubCategoryId(getActivity(), id);

                    Intent goIntent = new Intent(getActivity(), SubCategoryActivity.class);
                    startActivity(goIntent);

                    getActivity().overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);

                } else {

                    AppConfig.SetFK_categoryId(getActivity(), id);
                    Intent goIntent = new Intent(getActivity(), ProductActivity.class);
                    startActivity(goIntent);

                    getActivity().overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);

                }
            }
        });

        myListView.setVerticalScrollBarEnabled(false);
        myListView.setHorizontalScrollBarEnabled(false);

        return rootView;
    }

    @Override
    public void onResume() {
        categoryList=Category.GetCategoryByParent(getActivity(),-1l);
        myAdapter = new CategoryAdapter(getActivity(), categoryList);
        myListView.setAdapter(myAdapter);

        if (!myReceiverIsRegistered) {
            getActivity().registerReceiver(broadcastReceiver, new IntentFilter(AppIntent.ListenerCategory));
            myReceiverIsRegistered = true;
        }

        super.onResume();
    }

    @Override
    public void onPause() {
        if (myReceiverIsRegistered) {
            getActivity().unregisterReceiver(broadcastReceiver);
            myReceiverIsRegistered = false;
        }

        super.onPause();
    }
}