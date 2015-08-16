package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.util.Log;

import ir.dayasoft.steelpars.Fragment.BranchFragment;
import ir.dayasoft.steelpars.R;


public class BranchActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "BranchActivity"); //todo log

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new BranchFragment())
                    .commit();
        }
    }


}


