package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Class.Product;


public class GetProductAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;
    String categoryId ;
    String updateDate;

    public GetProductAsyncTask(Context contextMain) {
        this.context = contextMain;
        categoryId="-1";
        updateDate="-1";
    }

    @Override
    protected Integer doInBackground(String... urls) {


        return Product.SyncWithServer(context,updateDate,categoryId);

    }

    @Override
    protected void onPostExecute(Integer returnValue) {

        if(returnValue == CommandHandler.errorType_NoError) {


        }



        super.onPostExecute(returnValue);
    }

}

