package ir.dayasoft.steelpars.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Fragment.AboutFragment;
import ir.dayasoft.steelpars.R;


public class AboutActivity extends BaseActivity {

    CollectionPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "AboutActivity"); //todo log

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setBackgroundColor(getResources().getColor(R.color.dirty_green_background));

        android.support.v4.view.PagerTitleStrip pagerTitleStrip = (android.support.v4.view.PagerTitleStrip) findViewById(R.id.pager_title_strip);
        pagerTitleStrip.setVisibility(View.VISIBLE);

        mCustomPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.activity_base_pager);
        mViewPager.setAdapter(mCustomPagerAdapter);



        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_about, null, false);

        frameLayout.addView(activityView);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("ashkan" , "1");

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("ashkan" , "2");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("ashkan" , "3");
            }
        });

    }

    public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();

            if (i==0)
                bundle.putInt(AppIntent.AboutFragment, 0);
            else if(i == 1)
                bundle.putInt(AppIntent.AboutFragment, 1);
            else
                bundle.putInt(AppIntent.AboutFragment, 2);


            AboutFragment fragment=new AboutFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (position==0)
                return "درباره ما";
            else if (position == 1)
                return "خدمات ما";
            else
                return "اهداف ما";


        }
    }


}


