package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Class.ProductImage;


public class GetProductImageAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;
    String ProductId;



    public GetProductImageAsyncTask(Context contextMain, String productId) {
        this.context = contextMain;
        this.ProductId = productId;
    }

    @Override
    protected Integer doInBackground(String... urls) {

      return ProductImage.SyncWithServer(context,ProductId);

    }

    @Override
    protected void onPostExecute(Integer returnValue) {

        super.onPostExecute(returnValue);
    }

}

