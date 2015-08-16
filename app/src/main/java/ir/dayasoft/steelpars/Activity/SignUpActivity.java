package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.util.Log;
 import ir.dayasoft.steelpars.Fragment.SignUpFragment;
import ir.dayasoft.steelpars.R;

public class SignUpActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "SignUpActivity"); //todo log

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
    //    toolbar.setBackgroundColor(getResources().getColor(R.color.blue_background));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SignUpFragment())
                    .commit();
        }
    }
}


