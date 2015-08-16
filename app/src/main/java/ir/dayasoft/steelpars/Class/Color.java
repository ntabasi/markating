package ir.dayasoft.steelpars.Class;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.ColorDataSource;


public class Color {

    private Long ColorId;
    private String Code;


    public static final String Tag = "database.Color";

    public Long getColorId() {
        return ColorId;
    }
    public void setColorId(Long colorId) {
        ColorId = colorId;
    }
    public String getCode() {
        return Code;
    }
    public void setCode(String code) {
        Code = code;
    }



/*
    static public List<Color> FillSampleColor() {
        List<Color> returnValue= new ArrayList<>();

        Color tmp = new Color();
        tmp.setColorId(Long.valueOf(1));
        tmp.setCodeFirst("#48b2ca");
        tmp.setCodeSecond("#4dbfd9");
        tmp.setCodeThird("#cc9667");
        tmp.setCategory(1);
        returnValue.add(tmp);


        tmp = new Color();
        tmp.setColorId(Long.valueOf(2));
        tmp.setCodeFirst("#9362a4");
        tmp.setCodeSecond("#9e69af");
        tmp.setCodeThird("#a9cc66");
        tmp.setCategory(2);
        returnValue.add(tmp);

        tmp = new Color();
        tmp.setColorId(Long.valueOf(3));
        tmp.setCodeFirst("#ee9e2f");
        tmp.setCodeSecond("#ffaa33");
        tmp.setCodeThird("#32afff");
        tmp.setCategory(3);
        returnValue.add(tmp);

        tmp = new Color();
        tmp.setColorId(Long.valueOf(4));
        tmp.setCodeFirst("#84b563");
        tmp.setCodeSecond("#8dc36b");
        tmp.setCodeThird("#c866cb");
        tmp.setCategory(4);
        returnValue.add(tmp);


        tmp = new Color();
        tmp.setColorId(Long.valueOf(5));
        tmp.setCodeFirst("#2fa970");
        tmp.setCodeSecond("#34b67a");
        tmp.setCodeThird("#cc7267");
        tmp.setCategory(5);
        returnValue.add(tmp);


        return returnValue;
    }
*/


    static public Color GetSingleColor (Context context, long colorId) {
        Color returnValue  =new Color();
        ColorDataSource colorDataSource = new ColorDataSource(context);
        try {
            colorDataSource.open();
            returnValue = colorDataSource.GetSingleColor(colorId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            colorDataSource.close();
        }
        return returnValue;
    }
    static public List<Color> GetColor (Context context) {
        List<Color> returnValue =new ArrayList<>();
        ColorDataSource colorDataSource = new ColorDataSource(context);
        try {
            colorDataSource.open();
            returnValue = colorDataSource.GetAllColor();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            colorDataSource.close();
        }
        return returnValue;
    }
    static public List<Color> GetColorByCategoryId(Context context, Long categoryId) {
        List<Color> returnValue =new ArrayList<>();
        ColorDataSource colorDataSource = new ColorDataSource(context);
        try {
            colorDataSource.open();
            returnValue = colorDataSource.GetColorByCategoryId(categoryId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetColorByCategoryId");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            colorDataSource.close();
        }
        return returnValue;
    }
    static public int InsertColor (Context context, List<Color> colorList) {
        int returnValue = 0;
        ColorDataSource colorDataSource = new ColorDataSource(context);

        try {
            colorDataSource.open();
            returnValue = colorDataSource.InsertColor(colorList);
            if(returnValue != colorList.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertColor");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Category array. sizeOfColors:" + colorList.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            colorDataSource.close();
            return returnValue;
        }
    }
    static public void ClearColor (Context context) {
        ColorDataSource colorDataSource = new ColorDataSource(context);
        try {
            colorDataSource.open();
            colorDataSource.ClearColor();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            colorDataSource.close();
        }
    }
    static public void DeleteColor (Context context, long colorId) {
        ColorDataSource colorDataSource = new ColorDataSource(context);
        try {
            colorDataSource.open();
            colorDataSource.DeleteColor(colorId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            colorDataSource.close();
        }
    }

 static public Integer SyncWithServer(Context context)
 {
     WebService ws = new WebService();

     String dec = AppConfig.GetDec(context);
     Boolean flag = false;

     String res = ws.GetColor(dec, Constants.Phrase);

      flag = CommandHandler.CommandValidation(res);

     if (flag) {

         List<Color> colorList = CommandHandler.DecodeCommand.GetColor(res);
         Color.ClearColor(context);
         Color.InsertColor(context, colorList);
         return CommandHandler.errorType_NoError;
     }
     else
         return CommandHandler.errorType_ServerAccess;
 }



}
