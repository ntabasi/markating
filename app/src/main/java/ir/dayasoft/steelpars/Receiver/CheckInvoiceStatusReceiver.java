package ir.dayasoft.steelpars.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ir.dayasoft.steelpars.Service.CheckInvoiceStatusService;


/**
 * Created by navid on 05/10/2015.
 */
public class CheckInvoiceStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {


        Intent intent1 = new Intent(context, CheckInvoiceStatusService.class);
        context.startService(intent1);




    }

}