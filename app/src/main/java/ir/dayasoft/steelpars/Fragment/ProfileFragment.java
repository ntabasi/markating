package ir.dayasoft.steelpars.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ir.dayasoft.steelpars.Activity.AccountActivity;
import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.R;


public class ProfileFragment extends Fragment {

    ImageView exitImageView;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity();

        exitImageView = (ImageView) rootView.findViewById(R.id.profile_exit_imageView);
        exitImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer.SignOutCustomer(context);

                Intent intent=new Intent(getActivity(), AccountActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(intent);

            }
        });

        return rootView;
    }

}