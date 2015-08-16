package ir.dayasoft.steelpars.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ir.dayasoft.steelpars.Activity.CategoryActivity;
import ir.dayasoft.steelpars.R;


public class IntroFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);

        Log.i("page","IntroFragment"); //todo log

        //define
        Button showVideo = (Button) rootView.findViewById(R.id.intro_video_imageView);
        showVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);
            }
        });

        return rootView;
    }



}