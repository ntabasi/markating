package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Class.City;


public class GetCityAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;

    public GetCityAsyncTask(Context contextMain) {
        this.context = contextMain;

    }

    @Override
    protected Integer doInBackground(String... urls) {

      return City.SyncWithServer(context);

    }

    @Override
    protected void onPostExecute(Integer returnValue) {


        super.onPostExecute(returnValue);
    }

}

