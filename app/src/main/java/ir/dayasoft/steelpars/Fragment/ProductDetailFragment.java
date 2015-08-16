package ir.dayasoft.steelpars.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ir.dayasoft.steelpars.Activity.FullScreenViewActivity;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Class.Color;
import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Communication.BitmapWorkerTask;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;


public class ProductDetailFragment extends Fragment {

    TextView titleTextView;
    TextView priceTextView;
    TextView contentTextView;
    ImageView imageView;
    RelativeLayout imageZoomRelativeLayout;
    Product product;
    Cart thisCart;

    int c1;
    int c2;
    int c3;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        Log.i("page", "ProductDetailFragment"); //todo log
        final Long productId = AppConfig.GetProductId(getActivity());

        Typeface typeface= Farsi.GetFarsiFont(getActivity());

        //define
        thisCart = Cart.GetCartByFK_ProductId(getActivity(), productId);
        product = Product.GetSingleProduct(getActivity(), productId);
        titleTextView = (TextView) rootView.findViewById(R.id.product_detail_name_textView);
        priceTextView = (TextView) rootView.findViewById(R.id.product_detail_price_textView);
        contentTextView = (TextView) rootView.findViewById(R.id.product_detail_content_textView);
        imageView = (ImageView) rootView.findViewById(R.id.product_detail_image_imageView);
        imageZoomRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.detail_zoom_layout);

        TextView textView_detail_textView=(TextView)rootView.findViewById(R.id.product_detail_detail_textView);
        textView_detail_textView.setTypeface(typeface);

        TextView detail_zoom_fullScreen_textView=(TextView)rootView.findViewById(R.id.detail_zoom_fullScreen_textView);
        detail_zoom_fullScreen_textView.setTypeface(typeface);


      //  titleTextView.setTypeface(typeface);
       // priceTextView.setTypeface(typeface);
        Long categoryId = AppConfig.GetFK_categoryId(getActivity());

        List<Color> colorList = Color.GetColorByCategoryId(getActivity(), categoryId);

      /*c1 = android.graphics.Color.parseColor(colorList.get(0).getCode());
        c2 = android.graphics.Color.parseColor(colorList.get(1).getCode());
        c3 = android.graphics.Color.parseColor(colorList.get(2).getCode());*/

        c1 = android.graphics.Color.parseColor("#48b2ca");
        c2 = android.graphics.Color.parseColor("#ff4dbfd9");
        c3 = android.graphics.Color.parseColor("#32afff");

        rootView.findViewById(R.id.product_detail_colorBar_view).setBackgroundColor(c1);
        rootView.findViewById(R.id.product_detail_name_textView).setBackgroundColor(c2);
        rootView.findViewById(R.id.product_detail_colorBar2_view).setBackgroundColor(c2);
        ((TextView) rootView.findViewById(R.id.product_detail_detail_textView)).setTextColor(c2);


        ImageView addCart = (ImageView) rootView.findViewById(R.id.product_detail_addCart_imageView);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thisCart = Cart.GetCartByFK_ProductId(getActivity(), productId);
                if (thisCart.getCartId() == -1) {

                    ImageView myImage = (ImageView) rootView.findViewById(R.id.product_detail_addCart_imageView);
                    myImage.setImageDrawable(getResources().getDrawable(R.drawable.icn_added_to_cart));

                    int order = Cart.GetOrder(getActivity());

                    Cart cart = new Cart();
                    cart.setCount(1.0);
                    cart.setCreateDate(Core.Dates.GetCurentDate());
                    cart.setOrder(order + 1);
                    cart.setFK_productId(productId);

                    Cart.InsertCart(getActivity(), cart);

                    Intent intent = new Intent(AppIntent.ListenerUpdateCart);
                    getActivity().sendBroadcast(intent);


                } else {


                    ImageView myImage = (ImageView) rootView.findViewById(R.id.product_detail_addCart_imageView);
                    myImage.setImageDrawable(getResources().getDrawable(R.drawable.icn_add_to_cart));

                    Cart.DeleteCart(getActivity(), thisCart.getCartId());

                    Intent intent = new Intent(AppIntent.ListenerUpdateCart);
                    getActivity().sendBroadcast(intent);
                }


            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FullScreenViewActivity.class);
                intent.putExtra(AppIntent.FullScreenViewActivity, productId);
                getActivity().startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {


        thisCart = Cart.GetCartByFK_ProductId(getActivity(), thisCart.getFK_productId());

        titleTextView.setText(product.getName());
        if (AppConfig.IsConfirm(getActivity()))
            priceTextView.setText(Core.Converter.DoubleToString(product.getPrice() * 1000) + " ریال");
        else
            priceTextView.setText("-");

        if (!product.getDescription().equals("-1"))
            contentTextView.setText(product.getDescription());
        else
            contentTextView.setText("");

       /* Picasso.with(getActivity()).
                load(Constants.WebImageDirectoryPath + product.getImageUrl()).
                placeholder(R.drawable.detail_pic).
                into(imageView);*/

        if (product.getImageUrl().lastIndexOf("/") > 0) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(imageView, R.drawable.detail_pic, getActivity());
            bitmapWorkerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, product.getImageUrl().substring(product.getImageUrl().lastIndexOf("/") + 1));
        }


        if (thisCart.getCartId() != -1) {
            ImageView myImage = (ImageView) rootView.findViewById(R.id.product_detail_addCart_imageView);
            myImage.setImageDrawable(getResources().getDrawable(R.drawable.icn_added_to_cart));
        } else {
            ImageView myImage = (ImageView) rootView.findViewById(R.id.product_detail_addCart_imageView);
            myImage.setImageDrawable(getResources().getDrawable(R.drawable.icn_add_to_cart));
        }
        super.onResume();
    }

}