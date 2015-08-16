package ir.dayasoft.steelpars.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Fragment.AccountFragment;
import ir.dayasoft.steelpars.Fragment.ProfileFragment;
import ir.dayasoft.steelpars.Fragment.WaitingForConfirmFragment;
import ir.dayasoft.steelpars.R;


public class AccountActivity extends BaseActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "AccountActivity"); //todo log
        context = this;


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue_background));

        if (savedInstanceState == null) {




            if (AppConfig.GetIsLogin(context) && !AppConfig.IsConfirm(context) )
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new WaitingForConfirmFragment())
                        .commit();
            }
            else if (!AppConfig.GetIsLogin(context))
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new AccountFragment())
                        .commit();

            }else
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ProfileFragment())
                        .commit();
            }


        }
    }
}


