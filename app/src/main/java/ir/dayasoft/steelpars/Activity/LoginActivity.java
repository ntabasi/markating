package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.util.Log;

import ir.dayasoft.steelpars.Fragment.SignUpFragment;
import ir.dayasoft.steelpars.R;


public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "LoginActivity"); //todo log

        Bundle extras;
        extras = getIntent().getExtras();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .commit();


    }

}
