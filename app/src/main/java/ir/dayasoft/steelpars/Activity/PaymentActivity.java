package ir.dayasoft.steelpars.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Fragment.PaymentFragment;
import ir.dayasoft.steelpars.R;


public class PaymentActivity extends BaseActivity {

    Context context;
    Long invoiceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "PaymentActivity"); //todo log
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setBackgroundColor(getResources().getColor(R.color.grass_green_background));


        Bundle extras = getIntent().getExtras();
       invoiceId= extras.getLong(AppIntent.InvoiceId);


        if (savedInstanceState == null) {

            Bundle bundle = new Bundle();
            bundle.putLong(AppIntent.InvoiceId, invoiceId);

            PaymentFragment fragment=new PaymentFragment();
            fragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(context, CategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        super.onBackPressed();
    }
}
