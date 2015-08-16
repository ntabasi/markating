package ir.dayasoft.steelpars.Core;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import ir.dayasoft.steelpars.Activity.CategoryActivity;
import ir.dayasoft.steelpars.R;


public class Notification {

    public void NotificationInvoiceResult(Context context, Class<?> cls,String Title, String Content, HashMap<String, String> hashMap) {

        Intent resultIntent = new Intent(context, cls);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Iterator<Entry<String, String>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> pairs = (Entry<String, String>) it
                    .next();
            resultIntent.putExtra((String) pairs.getKey(),
                    (String) pairs.getValue());


            it.remove(); // avoids a ConcurrentModificationException
        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addNextIntent(new Intent(context, CategoryActivity.class));
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        // Actions are just fake
        Uri alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.ic_notify)
                .setContentTitle(Title).setContentIntent(resultPendingIntent)
                .setContentText(Content).setAutoCancel(true)
                .setSound(alarmSound);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());

    }


    public void NotificationRegisterResult(Context context, Class<?> cls,String Title, String Content, HashMap<String, String> hashMap) {

        Intent resultIntent = new Intent(context, cls);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Iterator<Entry<String, String>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> pairs = (Entry<String, String>) it
                    .next();
            resultIntent.putExtra((String) pairs.getKey(),
                    (String) pairs.getValue());


            it.remove(); // avoids a ConcurrentModificationException
        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
     //   stackBuilder.addNextIntent(new Intent(context, CategoryActivity.class));
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        // Actions are just fake
        Uri alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.ic_notify)
                .setContentTitle(Title).setContentIntent(resultPendingIntent)
                .setContentText(Content).setAutoCancel(true)
                .setSound(alarmSound);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());

    }



}

