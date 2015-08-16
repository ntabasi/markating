package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Class.Branch;


public class GetBranchAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";

    String phrase;
    Boolean flag;


    public GetBranchAsyncTask(Context contextMain) {
        this.context = contextMain;
    }

    @Override
    protected Integer doInBackground(String... urls) {


      return  Branch.SyncWithServer(context);

    }

}

