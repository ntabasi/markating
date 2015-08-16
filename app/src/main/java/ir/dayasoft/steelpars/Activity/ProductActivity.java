package ir.dayasoft.steelpars.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Adapter.ProductAdapter;
import ir.dayasoft.steelpars.Class.Color;
import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Fragment.ProductDetailFragment;
import ir.dayasoft.steelpars.R;


public class ProductActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("page", "ProductActivity"); //todo log

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        GridView myGridView;
        List<Product> productList = new ArrayList<>();
        ProductAdapter myAdapter;
        View backgroundView;
        int c1;
        int c2;
        int c3;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product, container, false);
            Long categoryId = AppConfig.GetFK_categoryId(getActivity());

            List<Color> colorList = Color.GetColorByCategoryId(getActivity(), categoryId);

            /*c1 = android.graphics.Color.parseColor(colorList.get(0).getCode());
            c2 = android.graphics.Color.parseColor(colorList.get(1).getCode());
            c3 = android.graphics.Color.parseColor(colorList.get(2).getCode());*/
            c1 = android.graphics.Color.parseColor("#48b2ca");
            c2 = android.graphics.Color.parseColor("#ff4dbfd9");
            c3 = android.graphics.Color.parseColor("#32afff");




            productList = Product.GetProductByCategoryIdWithLeftJoin(getActivity(), categoryId);



            Toolbar toolbar = (Toolbar) ((BaseActivity) getActivity()).findViewById(R.id.toolbar);
          //  toolbar.setBackgroundColor(c2);




            myGridView = (GridView) rootView.findViewById(R.id.product_gridView);
            myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    long id = myAdapter.getItem(i).getProductId();
                    AppConfig.SetProductId(getActivity(),id);

                    ProductDetailFragment fragment = new ProductDetailFragment();
                    //fragment.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit();

                    getActivity().overridePendingTransition(R.animator.animation_part_one, R.animator.animation_part_tow);

                }
            });

            return rootView;
        }

        @Override
        public void onResume() {
            myAdapter = new ProductAdapter(getActivity(), productList);
            myGridView.setAdapter(myAdapter);
            super.onResume();
        }

    }

}


