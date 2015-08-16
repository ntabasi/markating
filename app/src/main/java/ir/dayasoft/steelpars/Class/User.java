package ir.dayasoft.steelpars.Class;

import android.content.Context;

import ir.dayasoft.steelpars.Core.AppConfig;


public class User {


    private static final String Tag = "database.User";
    public static String getTag() {
        return Tag;
    }



    static public void SetLoginInfo(Context context, String username, String userId) {
        try {
            AppConfig.SetUsername(context, username);
            AppConfig.SetIsLogin(context, true);
            AppConfig.SetUserId(context, userId);

        } catch (Exception e) {
        }
    }

    static public String GetUserId(Context context) {
        try {
            String returnValue;
            returnValue = AppConfig.GetUserId(context);
            return returnValue;
        } catch (Exception e) {
        }
        return "-1";
    }

    /** check response of server - delete first 2 char and return rest of them */
    static public Boolean GetRequestValidation(String command) {
        try {
            command = command.substring(2);
            if (!command.equals("0")) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    static public Boolean GetIsZero(String command) {
        boolean returnValue = false;
        try {
            if (!command.equals("0")) {
                returnValue = true;
            }
        } catch (Exception e) {
            returnValue = false;
        }
        return returnValue;
    }

}