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


public class EmptyCartFragment extends Fragment {


    Button EnterCategoryButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_empty_cart, container, false);
        Log.i("page", "EmptyCartFragment"); //todo log


        EnterCategoryButton = (Button) rootView.findViewById(R.id.empty_cart_enter_category);
        EnterCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goIntent = new Intent(getActivity(), CategoryActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);
            }
        });

        return rootView;
    }




}