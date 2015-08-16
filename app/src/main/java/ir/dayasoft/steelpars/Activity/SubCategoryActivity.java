package ir.dayasoft.steelpars.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;

import ir.dayasoft.steelpars.Adapter.SubCategoryAdapter;
import ir.dayasoft.steelpars.Class.Category;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.R;


public class SubCategoryActivity extends BaseActivity {

    ListView myListView;
    SubCategoryAdapter myAdapter;
    List<Category> mList;
    Long parentId;
    Context context;
    Boolean myReceiverIsRegistered = false;

    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            List<Category> myList = Category.GetCategoryByParent(SubCategoryActivity.this, parentId);
            myAdapter.setData(myList);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "SubCategoryActivity"); //todo log
        parentId = AppConfig.GetSubCategoryId(this);
        context = this;

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_sub_category, null, false);
        // add the custom layout of this activity to frame layout.
        frameLayout.addView(activityView);


        //    setContentView(R.layout.fragment_sub_category);

        //categories = Category.GetCategoryByParent(this,parentId);

        myListView = (ListView) findViewById(R.id.category_listView);




        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Long objectId = myAdapter.getItem(i).getCategoryId();
                Category item = myAdapter.getItem(i);

               // if (item.getIsSubCategory()) {
                    boolean flag = Category.IsSubCategory(SubCategoryActivity.this, objectId);
                    //long FK_categoryId = myAdapter.getItem(i).getCategoryId();

                    if(flag){
                    List<Category> myList = Category.GetCategoryByParent(context, objectId);
                    myAdapter.setData(myList);
                    AppConfig.SetSubCategoryId(SubCategoryActivity.this, objectId);
                    } else {

                        AppConfig.SetFK_categoryId(view.getContext(), objectId);

                        Intent goIntent = new Intent(view.getContext(), ProductActivity.class);

                        startActivity(goIntent);

                        SubCategoryActivity.this.overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);
                    }

            }
        });

    }

    @Override
    public void onBackPressed() {

        boolean flag = Category.IsFirstSubCategory(this);
        if (flag) {
            super.onBackPressed();
        } else {
            long categoryId = Category.FindGrandFather(this);
            AppConfig.SetSubCategoryId(this, categoryId);
            List<Category> categoryList = Category.GetCategoryByParent(SubCategoryActivity.this, categoryId);
            myAdapter.setData(mList);

        }
    }

    @Override
    public void onResume() {
        if (!myReceiverIsRegistered) {
            context.registerReceiver(broadcastReceiver, new IntentFilter(AppIntent.BroadcastProduct));
            myReceiverIsRegistered = true;
        }

        mList = Category.GetCategoryByParent(context, parentId);


        myAdapter = new SubCategoryAdapter(this, mList);
        myListView.setAdapter(myAdapter);

        myAdapter = new SubCategoryAdapter(context, mList);
        myListView.setAdapter(myAdapter);

        super.onResume();
    }


    @Override
    public void onPause() {

        if (myReceiverIsRegistered) {
            context.unregisterReceiver(broadcastReceiver);
            myReceiverIsRegistered = false;
        }

        super.onPause();
    }
}


//getsuportedfragmentmanager.getBackstackentry