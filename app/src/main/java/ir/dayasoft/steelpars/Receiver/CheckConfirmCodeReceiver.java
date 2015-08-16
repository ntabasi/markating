package ir.dayasoft.steelpars.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ir.dayasoft.steelpars.Service.CheckConfirmCodeService;


public class CheckConfirmCodeReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {


        Intent intent1 = new Intent(context, CheckConfirmCodeService.class);
        context.startService(intent1);




    }

}