package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import ir.dayasoft.steelpars.Fragment.PreInvoiceFragment;
import ir.dayasoft.steelpars.R;


public class PreInvoiceActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "PreInvoiceActivity"); //todo log

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setBackgroundColor(getResources().getColor(R.color.grass_green_background));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PreInvoiceFragment())
                    .commit();
        }
    }

}


