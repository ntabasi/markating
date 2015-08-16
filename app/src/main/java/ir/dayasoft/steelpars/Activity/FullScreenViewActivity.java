package ir.dayasoft.steelpars.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Fragment.FullScreenViewFragment;
import ir.dayasoft.steelpars.R;


public class FullScreenViewActivity extends BaseActivity {

    Long ProductId;
    CollectionPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    Context context;
   // List<ProductImage> productImageList;

    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        Bundle extras;
        extras = getIntent().getExtras();
        ProductId= extras.getLong(AppIntent.FullScreenViewActivity);

//        productImageList = ProductImage.GetProductImageByProductId(context, ProductId);

        imageUrl= Product.GetSingleProduct(context,ProductId).getImageUrl();

        mCustomPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.activity_base_pager);
        mViewPager.setAdapter(mCustomPagerAdapter);


        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_full_screen_view, null, false);
    }

    public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();
            bundle.putString(AppIntent.ImageString, imageUrl);
            //bundle.putString(AppIntent.ImageString, productImageList.get(i).getImageUrl());

            FullScreenViewFragment fragment = new FullScreenViewFragment();
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {

            return 1;
            //return productImageList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }

}
