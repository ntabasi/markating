package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import ir.dayasoft.steelpars.Class.Branch;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.R;


public class GuaranteeActivatorActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "GuaranteeActivatorActivity"); //todo log

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        Spinner branchSpinner;
        EditText invoiceNumberEditText;
        EditText invoiceDateDayEditText;
        EditText invoiceDateMonthEditText;
        EditText invoiceDateYearEditText;
        ImageView submitImageView;



        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_guarantee_activator, container, false);
            Long categoryId = AppConfig.GetFK_categoryId(getActivity());

            ((BaseActivity) getActivity()).ChangeToolbarColor(R.color.violet_background);

            //define
            invoiceNumberEditText = (EditText)rootView.findViewById(R.id.guarantee_activator_invoiceNumber_editText);
            invoiceDateDayEditText = (EditText)rootView.findViewById(R.id.guarantee_activator_invoiceDate_day_editText);
            invoiceDateMonthEditText = (EditText)rootView.findViewById(R.id.guarantee_activator_invoiceDate_month_editText);
            invoiceDateYearEditText = (EditText)rootView.findViewById(R.id.guarantee_activator_invoiceDate_year_editText);
            submitImageView = (ImageView)rootView.findViewById(R.id.guarantee_activator_submit_imageView);
            branchSpinner = (Spinner) rootView.findViewById(R.id.guarantee_activator_branch_spinner);

            List<String> branches = Branch.GetBranchString(getActivity());
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item,
                    branches);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            branchSpinner.setAdapter(dataAdapter);


            submitImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),"submit text", Toast.LENGTH_SHORT).show();
                }
            });




            return rootView;
        }

        @Override
        public void onResume() {

            super.onResume();
        }

    }
}


