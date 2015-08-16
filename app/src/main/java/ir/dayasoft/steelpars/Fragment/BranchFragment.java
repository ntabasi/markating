package ir.dayasoft.steelpars.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Activity.BaseActivity;
import ir.dayasoft.steelpars.Adapter.BranchAdapter;
import ir.dayasoft.steelpars.Class.Branch;
import ir.dayasoft.steelpars.R;


public class BranchFragment extends Fragment {


    ListView myListView;
    List<Branch> branches = new ArrayList<>();
    BranchAdapter myAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_branch, container, false);

      //  ((BaseActivity) getActivity()).ChangeToolbarColor(R.color.dirty_green_background);


        branches = Branch.GetBranch(getActivity());

        myListView = (ListView) rootView.findViewById(R.id.branch_listView);

        return rootView;
    }

    @Override
    public void onResume() {
        myAdapter = new BranchAdapter(getActivity(), branches);
        myListView.setAdapter(myAdapter);
        super.onResume();
    }
}