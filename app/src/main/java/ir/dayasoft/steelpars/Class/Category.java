package ir.dayasoft.steelpars.Class;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.CategoryDataSource;


public class Category {

    Long CategoryId;
    Long ParentId;
    Integer Level;
    String Name;
    String ImageUrl;
    Integer Status;
    String UpdateDate;
    Long FK_color;
    String Icon;
    private Integer Order;

     String Color;



    public static final String Tag = "database.Category";


    public static final Integer Active = 1;
    public static final Integer Deactive = 0;


    public Long getCategoryId() {
        return CategoryId;
    }
    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }
    public Long getParentId() {
        return ParentId;
    }
    public void setParentId(Long parentId) {
        ParentId = parentId;
    }
    public Integer getLevel() {
        return Level;
    }
    public void setLevel(Integer level) {
        Level = level;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;
    }
    public String getUpdateDate() {
        return UpdateDate;
    }
    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }
    public Long getFK_color() {
        return FK_color;
    }
    public void setFK_color(Long FK_color) {
        this.FK_color = FK_color;
    }
    public String getIcon() {
        return Icon;
    }
    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        this.Color = color;
    }


    static public List<Category> FillSampleCategory() {
        List<Category> categoryList = new ArrayList<>();

        Category tmp = new Category();
        tmp.setCategoryId(Long.valueOf("1"));
        tmp.setParentId(Long.valueOf("-1"));
        tmp.setLevel(1);
        tmp.setName("تخت");
        tmp.setImageUrl("");
        tmp.setStatus(Active);
        tmp.setFK_color(Long.valueOf(1));
        categoryList.add(tmp);


        tmp = new Category();
        tmp.setCategoryId(Long.valueOf("2"));
        tmp.setParentId(Long.valueOf("-1"));
        tmp.setLevel(1);
        tmp.setName("مبل راحتی");
        tmp.setImageUrl("");
        tmp.setStatus(Active);
        tmp.setFK_color(Long.valueOf(2));
        categoryList.add(tmp);

        tmp = new Category();
        tmp.setCategoryId(Long.valueOf("3"));
        tmp.setParentId(Long.valueOf("-1"));
        tmp.setLevel(1);
        tmp.setName("مبل ناراحتی");
        tmp.setImageUrl("");
        tmp.setStatus(Active);
        tmp.setFK_color(Long.valueOf(3));
        categoryList.add(tmp);

        tmp = new Category();
        tmp.setCategoryId(Long.valueOf("4"));
        tmp.setParentId(Long.valueOf("-1"));
        tmp.setLevel(1);
        tmp.setName("دراور");
        tmp.setImageUrl("");
        tmp.setStatus(Active);
        tmp.setFK_color(Long.valueOf(4));
        categoryList.add(tmp);

        tmp = new Category();
        tmp.setCategoryId(Long.valueOf("5"));
        tmp.setParentId(Long.valueOf("-1"));
        tmp.setLevel(1);
        tmp.setName("میز عسلی");
        tmp.setFK_color(Long.valueOf(5));
        tmp.setImageUrl("");
        tmp.setStatus(Active);
        categoryList.add(tmp);


        return categoryList;
    }


    static public List<Category> GetCategoryByParent (Context context,long categoryId) {
        List<Category> categoryList  =new ArrayList<Category>();
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);

        try {
            categoryDataSource.open();
            categoryList = categoryDataSource.GetCategoryByParent(categoryId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCategoryByParent");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
        }
        return categoryList;
    }
    static public List<Category> GetAllCategoryWithColor (Context context) {
        List<Category> categoryList  =new ArrayList<Category>();
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);

        try {
            categoryDataSource.open();
            categoryList = categoryDataSource.GetAllCategoryWithColor();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCategoryByParent");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
        }
        return categoryList;
    }
    static public Category GetCategoryId (Context context, long categoryId) {
        Category returnValue  =new Category();
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);
        try {
            categoryDataSource.open();
            returnValue = categoryDataSource.GetSingleCategory(categoryId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCategoryId");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
        }
        return returnValue;
    }
    static public boolean IsSubCategory (Context context, long categoryId) {
        boolean returnValue = false;
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);
        try {
            categoryDataSource.open();
            List<Category> categoryList = categoryDataSource.GetCategoryByParent(categoryId);
            if(categoryList.size()>0)
                returnValue = true;
            else
                returnValue = false;
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCategoryId");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
        }
        return returnValue;
    }
    static public int InsertCategory(Context context, List<Category> categories) {
        int returnValue = 0;
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);

        try {
            categoryDataSource.open();
            returnValue = categoryDataSource.InsertCategory(categories);
            if(returnValue != categories.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertCategory");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Category array. sizeOfCategory:" + categories.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllClassStudent");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
            return returnValue;
        }
    }
    static public void ClearCategory (Context context) {
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);
        try {
            categoryDataSource.open();
            categoryDataSource.ClearCategory();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCategory");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            categoryDataSource.close();
        }
    }
    static public void DeleteCategory (Context context, long categoryId) {
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);
        try {
            categoryDataSource.open();
            categoryDataSource.DeleteCategory(categoryId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCategory");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            categoryDataSource.close();
        }
    }
    static public Long FindGrandFather(Context context) {
        long returnValue = 0;
        long parent = AppConfig.GetSubCategoryId(context);
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);

        try {
            categoryDataSource.open();
            Category category = categoryDataSource.GetSingleCategory(Integer.valueOf(String.valueOf(parent)));
            returnValue = category.getParentId();
        }
        catch (Exception e )
        {
            LogError logError = new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "FindGrandFather");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
            return returnValue;
        }
    }
    static public boolean IsFirstSubCategory (Context context) {
        boolean returnValue = false;
        long parent = AppConfig.GetSubCategoryId(context);
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);

        try {
            categoryDataSource.open();
            Category category = categoryDataSource.GetSingleCategory(Integer.valueOf(String.valueOf(parent)));
            Long categoryId = category.getParentId();
            if(categoryId==-1)
                returnValue = true;
            else
                returnValue = false;
        }
        catch (Exception e )
        {
            LogError logError = new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "FindGrandFather");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
            return returnValue;
        }
    }

    static public List<String> GetAllImagesUrl(Context context)
    {
        List<String> returnValue  =new ArrayList<>();
        List<Category> categories=new ArrayList<>();

        CategoryDataSource categoryDataSource = new CategoryDataSource(context);

        try {

            categoryDataSource.open();

            categories = categoryDataSource.GetAllCategory();


            for (Category tmp : categories )
                returnValue.add(tmp.ImageUrl);


        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllImagesUrl");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            categoryDataSource.close();
        }
        return returnValue;
    }


    static public Integer SyncWithServer(Context context)
    {
        WebService ws = new WebService();


        String dec = AppConfig.GetDec(context);
        Boolean flag = false;

        String res = ws.GetCategory(dec, Constants.Phrase );


        flag = CommandHandler.CommandValidation(res);
        if (flag) {

            List<Category> categoryList = CommandHandler.DecodeCommand.GetCategory(res);


            Category.ClearCategory(context);
            Category.InsertCategory(context, categoryList);

            Intent intent = new Intent(AppIntent.ListenerCategory);
            context.sendBroadcast(intent);
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;


    }


    public Integer getOrder() {
        return Order;
    }

    public void setOrder(Integer order) {
        this.Order = order;
    }
}