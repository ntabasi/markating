package ir.dayasoft.steelpars.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.R;


public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        int i = getArguments().getInt(AppIntent.AboutFragment, 0);

        if (i == 0) {
            rootView = inflater.inflate(R.layout.fragment_about, container, false);
           /* ImageView mImageView = (ImageView) rootView.findViewById(R.id.about_button_textView);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), BranchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getActivity().startActivity(intent);

                }
            });*/
        }
        else if (i == 1)
            rootView = inflater.inflate(R.layout.fragment_about2, container, false);
        else
            rootView = inflater.inflate(R.layout.fragment_about3, container, false);




        return rootView;
    }
}