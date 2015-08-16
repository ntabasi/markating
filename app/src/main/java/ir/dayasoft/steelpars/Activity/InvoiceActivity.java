package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.util.Log;

import ir.dayasoft.steelpars.Fragment.InvoiceFragment;
import ir.dayasoft.steelpars.R;


public class InvoiceActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "InvoiceActivity"); //todo log

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
     //   toolbar.setBackgroundColor(getResources().getColor(R.color.light_green_background));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new InvoiceFragment())
                    .commit();
        }
    }



}
