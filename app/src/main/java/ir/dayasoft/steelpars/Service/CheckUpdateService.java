package ir.dayasoft.steelpars.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import ir.dayasoft.steelpars.Class.Message;
import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;


/**
 * Created by navid on 05/11/2015.
 */
public class CheckUpdateService extends IntentService {

    String TAG = "CheckUpdateService";

    public CheckUpdateService() {

        super("CheckUpdateService");

    }

    @Override
    protected void onHandleIntent(Intent workIntent) {

        if (workIntent.equals(null))
            return;


        Log.i(TAG, "1");


        WebService ws = new WebService();


        String dec = AppConfig.GetDec(this);
        Boolean flag = false;


        String res = ws.GetMessage(dec, Constants.Phrase, AppConfig.GetUserId(this));
        Log.i(TAG, res);
        flag = CommandHandler.CommandValidation(res);
        if (flag) {

            List<Message> messageList = CommandHandler.DecodeCommand.GetMessage(res);
            Message.InsertMessage(this, messageList);

            Intent intent = new Intent(AppIntent.BroadCastMessage);
            sendBroadcast(intent);

        } else
        {
            //Error
        }



    }



}


