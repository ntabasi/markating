package ir.dayasoft.steelpars.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Adapter.DrawerAdapter;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Class.Drawer;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.R;
import ir.dayasoft.steelpars.Service.IntentServicesGetAllData;


public class BaseActivity extends ActionBarActivity {

    TextView cartCounter;
    FrameLayout cartValue;
    RelativeLayout cartRelativeLayout;

    public void ChangeToolbarColor (int id) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue_background));
       // toolbar.setBackgroundColor(getResources().getColor(id));
    }

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Boolean myReceiverIsRegistered = false;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            cartCounter = (TextView) findViewById(R.id.activity_base_cart_counter_textView);
            int cnt  = Cart.GetCartCounter(context);
            cartCounter.setText(String.valueOf(cnt));

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Cart.ClearCart(BaseActivity.this);


        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor();
        final ImageView holder_drawer = (ImageView) findViewById(R.id.toolbar_menu_imageView);

        cartCounter = (TextView) findViewById(R.id.activity_base_cart_counter_textView);
        cartRelativeLayout = (RelativeLayout) findViewById(R.id.activity_base_cart_relativeLayout);
        cartValue = (FrameLayout) findViewById(R.id.activity_base_frameLayout);
        int cnt  = Cart.GetCartCounter(this);
        cartCounter.setText(String.valueOf(cnt));

        cartRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppConfig.IsConfirm(view.getContext())) {
                    Intent goIntent;
                    goIntent = new Intent(BaseActivity.this, CartActivity.class);
                    goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goIntent);

                    overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);
                }
                else
                {
                    Toast.makeText(view.getContext(),"شما کاربر تایید شده نمی باشید",Toast.LENGTH_LONG).show();
                }
            }
        });

        holder_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                else
                    mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        //////////////drawerMenu
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        List<Drawer> drawerList = new ArrayList<>();



        Drawer tmp = new Drawer(R.drawable.menu_account,getResources().getString(R.string.account));
        drawerList.add(tmp);

        tmp = new Drawer(R.drawable.menu_product,getResources().getString(R.string.product));
        drawerList.add(tmp);

        tmp = new Drawer(R.drawable.menu_basket,getResources().getString(R.string.basket));
        drawerList.add(tmp);

        tmp = new Drawer(R.drawable.menu_invoice,getResources().getString(R.string.factors));
        drawerList.add(tmp);

      /*  tmp = new Drawer(R.drawable.menu_warranty,getResources().getString(R.string.warranty));
        drawerList.add(tmp);*/

        /*tmp = new Drawer(R.drawable.menu_activation,getResources().getString(R.string.activation));
        drawerList.add(tmp);*/

    //    tmp = new Drawer(R.drawable.menu_brand,getResources().getString(R.string.brands));
     //   drawerList.add(tmp);

        tmp = new Drawer(R.drawable.menu_agents,getResources().getString(R.string.about_us));
        drawerList.add(tmp);

        tmp = new Drawer(R.drawable.menu_refresh,getResources().getString(R.string.refresh));
        drawerList.add(tmp);


        // Set the adapter for the list view
        DrawerAdapter myAdapter = new DrawerAdapter(this, drawerList);
        mDrawerList.setAdapter(myAdapter);

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());




        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                holder_drawer.setImageResource(R.drawable.icn_toolbar_arrow);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                holder_drawer.setImageResource(R.drawable.icn_toolbar_menu);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        mDrawerList.setVerticalScrollBarEnabled(false);
        mDrawerList.setHorizontalScrollBarEnabled(false);
    }


    /////////drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position

        Bundle args = new Bundle();
        Intent goIntent;

        switch(position) {
            case 0:

                goIntent = new Intent(this, AccountActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);

                break;
            case 1:
                goIntent = new Intent(this, CategoryActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);

                break;
            case 2:
                if (AppConfig.IsConfirm(this)) {
                    goIntent = new Intent(this, CartActivity.class);
                    goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goIntent);
                }
                else
                Toast.makeText(this,"شما کاربر تایید شده نمی باشید",Toast.LENGTH_LONG).show();
                break;
            case 3:

                if (AppConfig.IsConfirm(this)) {
                    goIntent = new Intent(this, InvoiceActivity.class);
                    goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goIntent);
                }
                else
                    Toast.makeText(this,"شما کاربر تایید شده نمی باشید",Toast.LENGTH_LONG).show();




                break;
           /* case 4:
                goIntent = new Intent(this, GuaranteeActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);
                break;
            case 5:
                goIntent = new Intent(this, GuaranteeActivatorActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);

                break;*/
    //        case 4:
    //            goIntent = new Intent(this, AboutActivity.class);
    //            goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //            startActivity(goIntent);

   //             break;
            case 4:
                goIntent = new Intent(this, AboutDaya.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);

                break;

            case 5:
                Intent intent=new Intent(this, IntentServicesGetAllData.class);
                startService(intent);
                break;

            default:
                break;
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void onResume() {
        /** Listener */
        if (!myReceiverIsRegistered) {
            this.registerReceiver(broadcastReceiver, new IntentFilter(AppIntent.ListenerUpdateCart));
            myReceiverIsRegistered = true;
        }
        cartCounter.setText(String.valueOf(Cart.GetCartCounter(this)));
        super.onResume();
    }

    @Override
    public void onPause() {

        /** Listener */
        if (myReceiverIsRegistered) {
            this.unregisterReceiver(broadcastReceiver);
            myReceiverIsRegistered = false;
        }
        super.onPause();
    }


}
