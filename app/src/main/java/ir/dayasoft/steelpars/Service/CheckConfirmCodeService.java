package ir.dayasoft.steelpars.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;

import ir.dayasoft.steelpars.Activity.CategoryActivity;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Notification;


/**
 * Created by navid on 05/08/2015.
 */
public class CheckConfirmCodeService extends IntentService {
    String TAG="CheckConfirmCodeService";
    public CheckConfirmCodeService() {

        super("CheckConfirmCodeService");

    }

    @Override
    protected void onHandleIntent(Intent workIntent) {


        if (workIntent.equals(null))
            return;


        Log.i(TAG,"1");
        WebService ws = new WebService();

        String res = ws.GetConfirm(AppConfig.GetUserId(this), AppConfig.GetDec(this), Constants.Phrase);
        Log.i(TAG,res);
        Boolean flag = CommandHandler.CommandValidation(res);
        if (flag) {

            if (res.length() > 7) {//2 taa code hade aghal 6 ta pass
                AppConfig.SetSecurityCode(this, res.substring(2));
                AppConfig.StopCheckConfirmCodeReceiver(this);
                HashMap<String, String> hashMap = new HashMap<String, String>();

            //    hashMap.put(AppIntent.InvoiceId, String.valueOf(invoice.getInvoiceId()));

                Notification notification = new Notification();
                notification.NotificationRegisterResult(this,
                        CategoryActivity.class, "",
                        "ثبت نام شما تایید گردید", hashMap);

            }

        }


    }




}


