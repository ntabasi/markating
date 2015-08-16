package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Fragment.CategoryFragment;
import ir.dayasoft.steelpars.R;


public class CategoryActivity extends BaseActivity {

    boolean exit = false;
    long timeFirst, timeSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "CategoryActivity"); //todo log

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
     //   toolbar.setBackgroundColor(getResources().getColor(R.color.dark_green_background));


        Cart.GetLastCart(this);
     /*   List<City> cities=new ArrayList<>();
        City city=new City();
        city.setCityId(23l);
        city.setName("mashhad");
        city.setCost(1235000l);

        cities.add(city);

        city=new City();
        city.setCityId(24l);
        city.setName("tehran");
        city.setCost(122000l);
        cities.add(city);

        City.toJSon(city);

        GetJSONCity getJSONCity=new GetJSONCity(this);
        getJSONCity.execute();*/

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CategoryFragment())
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            if (!exit) {
                exit = true;
                timeFirst = System.currentTimeMillis();
                Toast.makeText(this, "برای خروج مجدداً کلید برگشت را بزنید", Toast.LENGTH_SHORT).show();
            } else {
                timeSecond = System.currentTimeMillis();
                if (timeFirst + 5000 > timeSecond)
                    super.onBackPressed();
                else {
                    Toast.makeText(this, "برای خروج مجدداً کلید برگشت را بزنید", Toast.LENGTH_SHORT).show();
                    timeFirst = timeSecond;
                    exit = false;
                }
            }
        }
    }
}
