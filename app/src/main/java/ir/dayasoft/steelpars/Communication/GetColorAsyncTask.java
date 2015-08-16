package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Class.Color;


public class GetColorAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;


    public GetColorAsyncTask(Context contextMain) {
        this.context = contextMain;
    }

    @Override
    protected Integer doInBackground(String... urls) {

    return Color.SyncWithServer(context);

    }

    @Override
    protected void onPostExecute(Integer returnValue) {

        super.onPostExecute(returnValue);
    }
}

