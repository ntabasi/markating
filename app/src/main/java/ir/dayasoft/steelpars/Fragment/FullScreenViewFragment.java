package ir.dayasoft.steelpars.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.dayasoft.steelpars.Class.TouchImageView;
import ir.dayasoft.steelpars.Communication.BitmapWorkerTask;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.R;


public class FullScreenViewFragment extends Fragment {

    Context context;
    TouchImageView mImageView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("page","FullScreenViewFragment");

        View rootView = inflater.inflate(R.layout.full_screen_image_fragment, container, false);
        context = getActivity();
        mImageView = (TouchImageView) rootView.findViewById(R.id.fullScreenImage_image_imageView);

        String imageString =  getArguments().getString(AppIntent.ImageString);

                //setSingletonInstance(com.squareup.picasso.Picasso)
        /*Picasso.with(context).
                load(Constants.WebImageDirectoryPath + imageString).
                into(mImageView);*/

        if (imageString.lastIndexOf("/")>0) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(mImageView , R.drawable.detail_pic, getActivity());
            bitmapWorkerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, imageString.substring(imageString.lastIndexOf("/") + 1));
        }


        return rootView;
    }

}