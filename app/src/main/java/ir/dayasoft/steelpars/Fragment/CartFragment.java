package ir.dayasoft.steelpars.Fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import ir.dayasoft.steelpars.Activity.CartActivity;
import ir.dayasoft.steelpars.Adapter.CartAdapter;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.CreateDialog;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;
import swipetodismiss.SwipeDismissList;


public class CartFragment extends Fragment {


    ListView myListView;
    List<Cart> carts = new ArrayList<>();
    CartAdapter myAdapter;
    ImageView submitImageView;
    TextView sumPriceTextView;
    Boolean myReceiverIsRegistered = false;


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            double sumPriceDouble = Cart.GetCartSum(getActivity());
            if (sumPriceDouble == -1) {
                Intent goIntent = new Intent(getActivity(), CartActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goIntent);
            } else
                sumPriceTextView.setText(Core.Converter.DoubleToString(sumPriceDouble * 1000) + " ریال");
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        Log.i("page", "CartFragment"); //todo log

        myListView = (ListView) rootView.findViewById(R.id.cart_listView);

        Typeface tf= Farsi.GetFarsiFont(getActivity());

        SwipeDismissList.OnDismissCallback callback = new SwipeDismissList.OnDismissCallback() {
            // Gets called whenever the user deletes an item.
            public SwipeDismissList.Undoable onDismiss(AbsListView listView, final int position) {
                // Get your item from the adapter (mAdapter being an adapter for MyItem objects)

                if (position == 0)
                    return null;
                final Cart deletedItem = myAdapter.getItem(position - 1);
                // Delete item from adapter
                //myAdapter.remove(deletedItem);
                Cart.DeleteCart(getActivity(), deletedItem.getCartId());
                myAdapter.remove(position);


                return null;
                // Return an Undoable implementing every method
                /*return new SwipeDismissList.Undoable() {

                    // Method is called when user undoes this deletion
                    public void undo() {
                        // Reinsert item to list
                        myAdapter.insert(deletedItem, position);
                    }

                    // Return an undo message for that item
                    public String getTitle() {
                        return deletedItem.getName() + " deleted";
                    }

                    // Called when user cannot undo the action anymore
                    public void discard() {
                        // Use this place to e.g. delete the item from database
                        finallyDeleteFromSomeStorage(deletedItem);
                    }
                };*/
            }
        };

        SwipeDismissList.UndoMode mode = SwipeDismissList.UndoMode.SINGLE_UNDO;
        SwipeDismissList swipeList = new SwipeDismissList(myListView, callback, mode);


        View headerView = ((LayoutInflater) getActivity()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.list_view_cart_header, null);


        TextView textViewPrice=(TextView)headerView.findViewById(R.id.cart_header_price_textView);
       // textViewPrice.setTypeface(tf);

        TextView textViewCount=(TextView)headerView.findViewById(R.id.cart_header_count_textView);
        textViewCount.setTypeface(tf);

        TextView textViewTitle=(TextView)headerView.findViewById(R.id.cart_header_title_textView);
        textViewTitle.setTypeface(tf);

        myListView.addHeaderView(headerView);

        carts = Cart.GetAllCart(getActivity());


        submitImageView = (ImageView) rootView.findViewById(R.id.cart_item_accept_imageView);
        sumPriceTextView = (TextView) rootView.findViewById(R.id.cart_item_sumPrice_textView);
        double sumPriceDouble = Cart.GetCartSum(getActivity());
        sumPriceTextView.setText(Core.Converter.DoubleToString(sumPriceDouble) + " ریال");
        submitImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AppConfig.IsConfirm(getActivity())) {
                    CreateDialog.CreateConfirmCartDialog(getActivity());
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ForbiddenAccountPageFragment())
                            .addToBackStack(null)
                            .commit();
                }

            }
        });

  //     sumPriceTextView.setTypeface(tf);
        TextView cart_footer_attention_textView=(TextView)rootView.findViewById(R.id.cart_footer_attention_textView);
        cart_footer_attention_textView.setTypeface(tf);

        return rootView;
    }

    @Override
    public void onResume() {
        myAdapter = new CartAdapter(getActivity(), carts);
        myListView.setAdapter(myAdapter);

        /** Listener */
        if (!myReceiverIsRegistered) {
            getActivity().registerReceiver(broadcastReceiver, new IntentFilter(AppIntent.ListenerUpdateSumPrice));
            myReceiverIsRegistered = true;
        }
        super.onResume();
    }

    @Override
    public void onPause() {

        /** Listener */
        if (myReceiverIsRegistered) {
            getActivity().unregisterReceiver(broadcastReceiver);
            myReceiverIsRegistered = false;
        }
        super.onPause();
    }

}