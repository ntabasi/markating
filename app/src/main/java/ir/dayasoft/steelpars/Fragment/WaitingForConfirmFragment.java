package ir.dayasoft.steelpars.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.dayasoft.steelpars.R;

/**
 * Created by navid on 05/16/2015.
 */
public class WaitingForConfirmFragment extends Fragment {


    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wait_for_confirm , container, false);
        context = getActivity();


        return rootView;
    }

}