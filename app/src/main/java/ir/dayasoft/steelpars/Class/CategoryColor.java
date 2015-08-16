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
import ir.dayasoft.steelpars.DataBase.CategoryColorDataSource;


public class CategoryColor {

    private Long CategoryColorId;
    private int FK_categoryId;
    private int FK_colorId ;
    private int Order ;


    public static final String Tag = "database.CategoryColor";

    public Long getCategoryColorId() {
        return CategoryColorId;
    }
    public void setCategoryColorId(Long categoryColorId) {
        CategoryColorId = categoryColorId;
    }
    public int getFK_categoryId() {
        return FK_categoryId;
    }
    public void setFK_categoryId(int fK_categoryId) {
        this.FK_categoryId = fK_categoryId;
    }
    public int getFK_colorId() {
        return FK_colorId;
    }
    public void setFK_colorId(int fK_colorId) {
        FK_colorId = fK_colorId;
    }
    public int getOrder() {
        return Order;
    }
    public void setOrder(int order) {
        this.Order = order;
    }


    static public CategoryColor GetSingleCategoryColor (Context context, long categoryColorId) {
        CategoryColor returnValue  =new CategoryColor();
        CategoryColorDataSource categoryColorDataSource = new CategoryColorDataSource(context);
        try {
            categoryColorDataSource.open();
            returnValue = categoryColorDataSource.GetSingleCategoryColor(categoryColorId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleCategoryColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryColorDataSource.close();
        }
        return returnValue;
    }
    static public List<CategoryColor> GetAllCategoryColor (Context context) {
        List<CategoryColor> returnValue =new ArrayList<>();
        CategoryColorDataSource categoryColorDataSource = new CategoryColorDataSource(context);
        try {
            categoryColorDataSource.open();
            returnValue = categoryColorDataSource.GetAllCategoryColor();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllCategoryColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryColorDataSource.close();
        }
        return returnValue;
    }
    static public int InsertCategoryColor (Context context, List<CategoryColor> categoryColors) {
        int returnValue = 0;
        CategoryColorDataSource categoryColorDataSource = new CategoryColorDataSource(context);

        try {
            categoryColorDataSource.open();
            returnValue = categoryColorDataSource.InsertCategoryColor(categoryColors);
            if(returnValue != categoryColors.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertCategoryColor");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert CategoryColor array. sizeOfColors:" + categoryColors.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCategoryColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryColorDataSource.close();
            return returnValue;
        }
    }
    static public void ClearCategoryColor (Context context) {
        CategoryColorDataSource categoryColorDataSource = new CategoryColorDataSource(context);
        try {
            categoryColorDataSource.open();
            categoryColorDataSource.ClearCategoryColor();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCategoryColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            categoryColorDataSource.close();
        }
    }
    static public void DeleteCategoryColor (Context context, long colorId) {
        CategoryColorDataSource categoryColorDataSource = new CategoryColorDataSource(context);
        try {
            categoryColorDataSource.open();
            categoryColorDataSource.DeleteColor(colorId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteCategoryColor");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            categoryColorDataSource.close();
        }
    }

    static public Integer SyncWithServer(Context context)
    {
        WebService ws = new WebService();



        String dec = AppConfig.GetDec(context);
        Boolean flag = false;

        String res = ws.GetCategoryColor(dec, Constants.Phrase);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {

            List<CategoryColor> categoryColorList = CommandHandler.DecodeCommand.GetCategoryColor(res);

            CategoryColor.ClearCategoryColor(context);
            CategoryColor.InsertCategoryColor(context, categoryColorList);
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;

    }

}
