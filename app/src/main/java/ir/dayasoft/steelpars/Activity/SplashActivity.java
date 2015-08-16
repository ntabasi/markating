package ir.dayasoft.steelpars.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.dayasoft.steelpars.Communication.DataLoaderAsyncTask;
import ir.dayasoft.steelpars.Core.PropertiesHandler;
import ir.dayasoft.steelpars.R;
import ir.dayasoft.steelpars.Service.IntentServicesGetAllData;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.blank);

        Intent intent=new Intent(this, IntentServicesGetAllData.class);
        startService(intent);

        if (!PropertiesHandler.GetIsLoadData(this))
        {
            DataLoaderAsyncTask dataLoaderAsyncTask=new DataLoaderAsyncTask(this,false);
            dataLoaderAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        Log.i("page", "SplashActivity"); //todo log

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable, 2000);

    }

    private Runnable mMyRunnable = new Runnable() {
        @Override
        public void run() {


            Intent intent=new Intent(SplashActivity.this, CategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);

        }
    };


    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_splash, container, false);
            Context mContext = getActivity();




          /*  GetBranchAsyncTask branchAsyncTask = new GetBranchAsyncTask(mContext);
            branchAsyncTask.execute();

            GetColorAsyncTask colorAsyncTask = new GetColorAsyncTask(mContext);
            colorAsyncTask.execute();

            GetCategoryColorAsyncTask categoryColorAsyncTask= new GetCategoryColorAsyncTask(mContext);
            categoryColorAsyncTask.execute();

            GetCategoryAsyncTask categoryAsyncTask = new GetCategoryAsyncTask(mContext);
            categoryAsyncTask.execute();

            GetProductAsyncTask productAsyncTask = new GetProductAsyncTask(mContext);
            productAsyncTask.execute();

            GetProductImageAsyncTask productImageAsyncTask = new GetProductImageAsyncTask(mContext , "-1");
            productImageAsyncTask.execute();

            GetCityAsyncTask cityAsyncTask = new GetCityAsyncTask(mContext);
            cityAsyncTask.execute();

            Branch.ClearBranche(getActivity());
            Branch.InsertBranch(getActivity(), Branch.FillSampleBranch());*/

           /* GetJSONCity getJSONCity = new GetJSONCity(getActivity());
            getJSONCity.execute();*/



            return rootView;
        }


    }


}
