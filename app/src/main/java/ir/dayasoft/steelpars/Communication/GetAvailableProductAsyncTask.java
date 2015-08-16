package ir.dayasoft.steelpars.Communication;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Activity.PreInvoiceActivity;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.CreateDialog;


public class GetAvailableProductAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;
    List<Cart> CartList;
    ProgressDialog progressDialog;

    public GetAvailableProductAsyncTask(Context contextMain, List<Cart> cartList) {
        this.context = contextMain;
        this.CartList = cartList;

        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage("درحال برقراری ارتباط");
        progressDialog.show();

    }

    @Override
    protected Integer doInBackground(String... urls) {

        WebService ws = new WebService();


        phrase = Constants.Phrase;
        String dec = AppConfig.GetDec(context);
        flag = false;


        String command = CommandHandler.EncodeCommand.EncodeAvailableProduct(CartList);

        this.res = ws.GetAvailableProduct(command, dec, phrase);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;

    }

    @Override
    protected void onPostExecute(Integer returnValue) {
        progressDialog.dismiss();

        if(returnValue == CommandHandler.errorType_NoError) {

            List<Cart> resCart = CommandHandler.DecodeCommand.GetAvailableProduct(this.res);
            List<String> st = new ArrayList<>();
            boolean enterFlag = false;

            for(int i=0; i<CartList.size(); i++) {
                if(resCart.get(i).getCount() < CartList.get(i).getCount()) {
                    st.add(CartList.get(i).getProductName());
                    enterFlag = true;
                }
            }

            if(enterFlag) {
                CreateDialog.CreateAvailableDialog(context, st);
            } else {
                Intent intent=new Intent(context, PreInvoiceActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);

            }

        }



        super.onPostExecute(returnValue);
    }

}

