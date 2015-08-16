package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Core.LogError;


public class LogErrorAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String username;
    String password;

    LogError logError;
    Boolean flag;


    public LogErrorAsyncTask(Context contextMain, LogError logError) {
        this.context = contextMain;
        /*this.username = User.GetUsername(context);
        this.password = User.GetPassword(context);*/
        this.logError = logError;
    }

    @Override
    protected Integer doInBackground(String... urls) {

        /*WebServiceCentral ws = new WebServiceCentral();


        String dec=CommandHandler.EncodeCommand.SignUp(password, Core.PhoneInfo.GetDeviceId(context),Core.PhoneInfo.GetSimSerialNumber(context));
        flag = false;
        this.res = ws.xxxLogError(username, logError, dec);*/



        if(res.equals("1")) {
            logError.setSenT(1);
            logError.UpdateSent(context);
        }

        return 1;
    }



}