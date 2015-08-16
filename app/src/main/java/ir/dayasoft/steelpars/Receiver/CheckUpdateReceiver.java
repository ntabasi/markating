package ir.dayasoft.steelpars.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ir.dayasoft.steelpars.Service.CheckUpdateService;


public class CheckUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {


        Intent intent1 = new Intent(context, CheckUpdateService.class);
        context.startService(intent1);




    }

}