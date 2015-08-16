package ir.dayasoft.steelpars.Core;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import ir.dayasoft.steelpars.Receiver.CheckConfirmCodeReceiver;
import ir.dayasoft.steelpars.Receiver.CheckInvoiceStatusReceiver;
import ir.dayasoft.steelpars.Receiver.CheckUpdateReceiver;

public class AppConfig {

    static public Boolean GetFirstTime (Context context) {
        Boolean returnValue;
        returnValue = PropertiesHandler.GetIsFirstTime(context);
        return returnValue;
    }
    static public void SetFirstTime (Context context, boolean flag) {
        PropertiesHandler.SetIsFirstTime(context,flag);
    }

    static public String GetDeviceSN(Context context) {

        return Core.PhoneInfo.GetDeviceId(context);
        //return "";
    }

    static public String GetDec(Context context) {

        String userId=PropertiesHandler.GetUserId(context);
        String date=Core.Dates.GetCurrentDate3();
        String securityCode=PropertiesHandler.GetSecurityCode(context);
        String rollIId= String.valueOf(3);
        String serialNumber=Core.PhoneInfo.GetSimSerialNumber(context);


        String decRes=userId + Constants.DecSep+rollIId + Constants.DecSep + serialNumber + Constants.DecSep + date ;

        decRes = Core.Decript.Decrypt(decRes,securityCode);

        return decRes;

    }

    static public Boolean IsConfirm (Context context) {

        if (GetSecurityCode(context).length()>5)
            return true;
        return false;
    }

    static public Boolean IsLogin (Context context) {
        Boolean returnValue;
        returnValue = PropertiesHandler.GetIsLogin(context);
        return returnValue;
    }

    static public String GetSimSerialNumber (Context context) {
        String returnValue;

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        returnValue = telephonyManager.getSimSerialNumber();


        return returnValue;
    }

    static public String GetUserId (Context context) {
        String returnValue = PropertiesHandler.GetUserId(context);

        return returnValue;
    }
    static public void SetUserId (Context context, String userId) {
        PropertiesHandler.SetUserId(context, userId);
    }

    static public boolean IsSimChange (Context context) {
        boolean returnValue;

        String simSerialNumber = GetSimSerialNumber(context);
        if(simSerialNumber.equals(PropertiesHandler.GetSimSerialNumber(context))) {
            returnValue = false;
        } else {
            returnValue = true;
        }

        return returnValue;
    }

    static public void AfterSignUp (Context context) {

        String simSerialNumber = GetSimSerialNumber(context);
        PropertiesHandler.SetSimSerialNumber(context,simSerialNumber);
        PropertiesHandler.SetIsConfirm(context, true);
        //todo ashkan sendSms to server

    }

    static public Long GetSubCategoryId (Context context) {
        Long returnValue = PropertiesHandler.GetSubCategoryId(context);

        return returnValue;
    }
    static public void SetSubCategoryId (Context context, Long categoryId) {
        PropertiesHandler.SetSubCategoryId(context, categoryId);
    }

    static public Long GetFK_categoryId (Context context) {
        Long returnValue = PropertiesHandler.GetFK_categoryId(context);

        return returnValue;
    }
    static public void SetFK_categoryId (Context context, Long FK_categoryId) {
        PropertiesHandler.SetFK_categoryId(context, FK_categoryId);
    }

    static public Long GetColorId (Context context) {
        Long returnValue = PropertiesHandler.GetColorId(context);

        return returnValue;
    }
    static public void SetColorId (Context context, Long colorId) {
        PropertiesHandler.SetColorId(context, colorId);
    }

    static public Long GetProductId (Context context) {
        Long returnValue = PropertiesHandler.GetProductId(context);

        return returnValue;
    }
    static public void SetProductId (Context context, Long productId) {
        PropertiesHandler.SetProductId(context, productId);
    }


    static private String GetSecurityCode(Context context) {

        return PropertiesHandler.GetSecurityCode(context);

    }

    static public Boolean SetSecurityCode(Context context,String securityCode) {

        PropertiesHandler.SetSecurityCode(context, securityCode);
        return  true;
    }



    static public Boolean StartCheckConfirmCodeReceiver(Context context)
    {
        Intent alarmIntent = new Intent(context, CheckConfirmCodeReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        long interval = 300000;

        //  manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  pendingIntent);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        // Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();

        return true;
    }


    static public Boolean StopCheckConfirmCodeReceiver(Context context)
    {
        Intent alarmIntent = new Intent(context, CheckConfirmCodeReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);

        return true;
    }



    static public Boolean StartCheckInvoiceStatusReceiver(Context context)
    {
        Intent alarmIntent = new Intent(context, CheckInvoiceStatusReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        long interval = 300000;

        //  manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  pendingIntent);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        // Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();

        return true;
    }


    static public Boolean StopCheckInvoiceStatusReceiver(Context context)
    {
        Intent alarmIntent = new Intent(context, CheckInvoiceStatusReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);

        return true;
    }


    static public Boolean StartCheckUpdateReceiver(Context context)
    {
        Intent alarmIntent = new Intent(context, CheckUpdateReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        long interval = AlarmManager.INTERVAL_HALF_DAY;

        //  manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  pendingIntent);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        // Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();

        return true;
    }


    static public Boolean StopCheckUpdateReceiver(Context context)
    {
        Intent alarmIntent = new Intent(context, CheckUpdateReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);

        return true;
    }


    static  public  String ValidationMobileNumber (String MobileNumber)
    {
        String res="0";

        MobileNumber=MobileNumber.replace(" ","");
        MobileNumber=MobileNumber.replace("-","");
        MobileNumber=MobileNumber.replace("(","");
        MobileNumber=MobileNumber.replace(")","");

        if (MobileNumber.length()>=11)
        {
            res= "0" + MobileNumber.substring(MobileNumber.length()-10);
        }
        else if (MobileNumber.length()==10)
        {
            res= "0" + MobileNumber;
        }

        return res;

    }



    static  public  Boolean IsValidMobileNumber (String MobileNumber)
    {
        String res="0";

        String validChar="0123456789+";

        if (MobileNumber.length()!=11)
            return false;
        if (MobileNumber.substring(1).contains("+"))
            return false;
        for (char c : MobileNumber.toCharArray())
        {
            if (!validChar.contains(String.valueOf(c)))
                return false;

        }

        return true;

    }




    static public long GetGroups (Context context) {
        long returnValue = PropertiesHandler.GetGroups(context);

        return returnValue;
    }
    static public void SetGroups (Context context, int groupsId) {
        PropertiesHandler.SetGroups(context, groupsId);
    }



    static public Boolean GetIsLogin (Context context) {
        boolean returnValue = PropertiesHandler.GetIsLogin(context);

        return returnValue;
    }
    static public void SetIsLogin (Context context, boolean isLogin) {
        PropertiesHandler.SetIsLogin(context, isLogin);
    }

    static public String GetUsername (Context context) {
        String returnValue = PropertiesHandler.GetUserName(context);

        return returnValue;
    }
    static public void SetUsername (Context context, String username) {
        PropertiesHandler.SetUserName(context, username);
    }


    static public int GetPaymentType (Context context) {
        int returnValue = PropertiesHandler.GetPaymentType(context);

        return returnValue;
    }
    static public void SetPaymentType (Context context, int paymentType) {
        PropertiesHandler.SetPaymentType(context, paymentType);
    }

    static public long GetCityId (Context context) {
        long returnValue = PropertiesHandler.GetCityId(context);

        return returnValue;
    }
    static public void SetCityId (Context context, long cityId) {
        PropertiesHandler.SetCityId(context, cityId);
    }


}
