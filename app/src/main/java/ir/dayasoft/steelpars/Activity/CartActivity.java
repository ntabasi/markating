package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Fragment.CartFragment;
import ir.dayasoft.steelpars.Fragment.EmptyCartFragment;
import ir.dayasoft.steelpars.R;


public class CartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "CartActivity"); //todo log

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setBackgroundColor(getResources().getColor(R.color.grass_green_background));

        int cnt = Cart.GetCartCounter(this);
        if (cnt == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new EmptyCartFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CartFragment())
                    .commit();

        }

    }
}
