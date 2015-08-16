package ir.dayasoft.steelpars.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ir.dayasoft.steelpars.R;


public class ForbiddenAccountPageFragment extends Fragment {


    Button EnterCategoryButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forbidden_account, container, false);
        Log.i("page", "ForbiddenAccountPageFragment"); //todo log



        return rootView;
    }




}