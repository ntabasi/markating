package ir.dayasoft.steelpars.Core;

import android.content.Context;
import android.content.SharedPreferences;

import ir.dayasoft.steelpars.Class.Customer;


public class PropertiesHandler {


    static private SharedPreferences shopPrefs;
    static private String prefName = "furnitureShp[";

    private static final String SMSWebService = "SMSWebService"; // string
    private static final String WebServiceaddress = "WebServiceNumber"; // string

    //#1450 start  daya framework Auto Generation
    private static final String IsFirstTime="IsFirstTime";// boolean
    private static final String IsLoadData="IsLoadData";// boolean
    private static final String IsLogin="IsLogin";// boolean
    private static final String IsConfirm="IsConfirm";// boolean
    private static final String IsFirstEnter="IsFirstEnter";// boolean
    private static final String IsNeedPassword="IsNeedPassword";// boolean
    private static final String IsChangeSim="IsChangeSim";// boolean
    private static final String Groups ="Groups";// integer
    private static final String PaymentType ="PaymentType";// integer


    private static final String UserId = "UserId"; // string
    private static final String SecurityCode="SecurityCode"; // string
    private static final String MobileNumber = "MobileNumber"; // string
    private static final String SimSerialNumber = "SimSerialNumber"; // string


    private static final String WorkTimeText = "WorkTimeText"; // string
    private static final String ConfirmSendOrderText = "ConfirmSendOrderText"; // string
    private static final String SpecialOrderText = "SpecialOrderText"; // string
    private static final String ConfirmSpecialOrderText = "ConfirmSpecialOrderText"; // string


    private static final String ShopPhoneNumber = "ShopPhoneNumber"; // string


    private static final String UserPassword="UserPassword"; // string
    private static final String UserName="UserName"; // string
    private static final String CityName ="CityName"; // int


    private static final String UpdateDate="UpdateDate"; // string
    private static final String LastOrderTime="LastOrderTime"; // string


    private static final String FontFlag="FontFlag";// boolean


    private static final String SubCategoryId="SubCategoryId";// Long
    private static final String Fk_categoryId="Fk_categoryId";// Long
    private static final String ProductId="ProductId";// Long
    private static final String Balance="Balance";// Double
    private static final String Source="Source";// String
    private static final String ColorId="ColorId";// Long






    //ashkan function
    static public void SetIsFirstTime (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsFirstTime, flag);
        editor.commit();
    }
    static public boolean GetIsFirstTime (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsFirstTime, true);
        return res;
    }

    static public void SetIsLoadData (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsLoadData, flag);
        editor.commit();
    }
    static public boolean GetIsLoadData (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsLoadData, false);
        return res;
    }

    static public void SetIsLogin (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsLogin, flag);
        editor.commit();
    }
    static public boolean GetIsLogin (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsLogin, false);
        return res;
    }

    static public void SetIsConfirm (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsConfirm, flag);
        editor.commit();
    }
    static public boolean GetIsConfirm (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsConfirm, false);
        return res;
    }

    static public void SetIsFirstEnter (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsFirstEnter, flag);
        editor.commit();
    }
    static public boolean GetIsFirstEnter (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsFirstEnter, false);
        return res;
    }

    static public void SetIsNeedPassword (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsNeedPassword, flag);
        editor.commit();
    }
    static public boolean GetIsNeedPassword (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsNeedPassword, false);
        return res;
    }

    static public void SetIsChangeSim (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.IsChangeSim, flag);
        editor.commit();
    }
    static public boolean GetIsChangeSim (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.IsChangeSim, false);
        return res;
    }

    static public void SetGroups (Context context, int value) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putInt(PropertiesHandler.Groups, value);
        editor.commit();
    }
    static public int GetGroups (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        int res = shopPrefs.getInt(PropertiesHandler.Groups, Customer.GroupPerson);
        return res;
    }

    static public void SetPaymentType (Context context, int value) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putInt(PropertiesHandler.PaymentType, value);
        editor.commit();
    }
    static public int GetPaymentType (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        int res = shopPrefs.getInt(PropertiesHandler.PaymentType, Customer.GroupPerson);
        return res;
    }

    static public void SetCityId (Context context, long value) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putLong(PropertiesHandler.CityName, value);
        editor.commit();
    }
    static public long GetCityId (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        long res = shopPrefs.getLong(PropertiesHandler.CityName, Customer.GroupPerson);
        return res;
    }



    static public void SetUserId (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.UserId, string);
        editor.commit();
    }
    static public String GetUserId (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.UserId,"-1");
        return res;
    }



    static public void SetSecurityCode (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.SecurityCode, string);
        editor.commit();
    }
    static public String GetSecurityCode (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.SecurityCode,"-1");
        return res;
    }

    static public void SetMobileNumber (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.MobileNumber, string);
        editor.commit();
    }
    static public String GetMobileNumber (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.MobileNumber,"-1");
        return res;
    }

    static public void SetSimSerialNumber (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.SimSerialNumber, string);
        editor.commit();
    }
    static public String GetSimSerialNumber (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.SimSerialNumber,"-1");
        return res;
    }



    static public void SetWorkTimeText (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.WorkTimeText, string);
        editor.commit();
    }
    static public String GetWorkTimeText (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.WorkTimeText, "---");
        return res;
    }

    static public void SetConfirmSendOrderText (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.ConfirmSendOrderText, string);
        editor.commit();
    }
    static public String GetConfirmSendOrderText (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.ConfirmSendOrderText,"-1");
        return res;
    }

    static public void SetSpecialOrderText (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.SpecialOrderText, string);
        editor.commit();
    }
    static public String GetSpecialOrderText (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.SpecialOrderText,"---");
        return res;
    }

    static public void SetConfirmSpecialOrderText (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.ConfirmSpecialOrderText, string);
        editor.commit();
    }
    static public String GetConfirmSpecialOrderText (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.ConfirmSpecialOrderText,"-1");
        return res;
    }



    static public void SetShopPhoneNumber (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.ShopPhoneNumber, string);
        editor.commit();
    }
    static public String GetShopPhoneNumber (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.ShopPhoneNumber,"-1");
        return res;
    }



    static public void SetUserPassword (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.UserPassword, string);
        editor.commit();
    }
    static public String GetUserPassword (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.UserPassword,"-1");
        return res;
    }

    static public void SetUserName (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.UserName, string);
        editor.commit();
    }
    static public String GetUserName (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.UserName,"-1");
        return res;
    }



    static public void SetUpdateDate (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.UpdateDate, string);
        editor.commit();
    }
    static public String GetUpdateDate (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.UpdateDate,"-1");
        return res;
    }

    static public void SetLastOrderTime (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.LastOrderTime, string);
        editor.commit();
    }
    static public String GetLastOrderTime (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.LastOrderTime,"-1");
        return res;
    }



    static public void SetFontFlag (Context context, Boolean flag) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putBoolean(PropertiesHandler.FontFlag, flag);
        editor.commit();
    }
    static public boolean GetFontFlag (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        boolean res = shopPrefs.getBoolean(PropertiesHandler.FontFlag, false);
        return res;
    }



    static public void SetSubCategoryId (Context context,Long subCategoryId) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putLong(PropertiesHandler.SubCategoryId, subCategoryId);
        editor.commit();
    }
    static public Long GetSubCategoryId (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        Long res= shopPrefs.getLong(PropertiesHandler.SubCategoryId,-1);
        return res;
    }

    static public void SetFK_categoryId (Context context,Long FK_categoryId) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putLong(PropertiesHandler.Fk_categoryId, FK_categoryId);
        editor.commit();
    }
    static public Long GetFK_categoryId (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        Long res= shopPrefs.getLong(PropertiesHandler.Fk_categoryId,-1);
        return res;
    }

    static public void SetColorId (Context context,Long colorId) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putLong(PropertiesHandler.ColorId, colorId);
        editor.commit();
    }
    static public Long GetColorId (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        Long res= shopPrefs.getLong(PropertiesHandler.ColorId,-1);
        return res;
    }

    static public void SetProductId (Context context,Long productId) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putLong(PropertiesHandler.ProductId, productId);
        editor.commit();
    }
    static public Long GetProductId (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        Long res= shopPrefs.getLong(PropertiesHandler.ProductId,-1);
        return res;
    }

    static public void SetBalance (Context context,Double balance) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putFloat(PropertiesHandler.Balance,Float.valueOf(String.valueOf(balance)));
        editor.commit();
    }
    static public Double GetBalance (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        Float res= shopPrefs.getFloat(PropertiesHandler.Balance,0);
        double returnValue = Double.valueOf(String.valueOf(res));
        return returnValue;
    }

    static public void SetSource (Context context,String string) {
        shopPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shopPrefs.edit();
        editor.putString(PropertiesHandler.Source, string);
        editor.commit();
    }
    static public String GetSource (Context context) {
        shopPrefs =context.getSharedPreferences(prefName , Context.MODE_PRIVATE );
        String res= shopPrefs.getString(PropertiesHandler.Source,"");
        return res;
    }

}