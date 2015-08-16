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
import ir.dayasoft.steelpars.DataBase.ProductImageDataSource;


public class ProductImage {

    Long ProductImageId;
    Long FK_productId;
    String ImageUrl;
    Long FK_imageId;

    public static final String Tag = "database.ProductImage";

    public Long getProductImageId() {
        return ProductImageId;
    }
    public void setProductImageId(Long productImageId) {
        ProductImageId = productImageId;
    }
    public Long getFK_productId() {
        return FK_productId;
    }
    public void setFK_productId(Long fK_productId) {
        FK_productId = fK_productId;
    }
    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
    public Long getFK_imageId() {
        return FK_imageId;
    }
    public void setFK_imageId(Long fK_imageId) {
        FK_imageId = fK_imageId;
    }

    public static List<ProductImage> FillSampleProductImage (Long productId) {
        List<ProductImage> productImageList = new ArrayList<>();

        ProductImage tmp = new ProductImage();
        tmp.setImageUrl("img/takhtelogo.png");
        tmp.setFK_productId(productId);
        productImageList.add(tmp);

        for(int i=0; i<4; i++) {
            tmp = new ProductImage();
            tmp.setImageUrl("img/takhtelogo.png");
            tmp.setFK_productId(productId);
            productImageList.add(tmp);
        }

        return productImageList;
    }

    static public List<ProductImage> GetProductImageByProductId (Context context, long productId) {
        List<ProductImage> returnValue  = new ArrayList<>();
        ProductImageDataSource productImageDataSource = new ProductImageDataSource(context);
        try {
            productImageDataSource.open();
            returnValue = productImageDataSource.GetProductImageByProductId(productId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleProductImage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productImageDataSource.close();
        }
        return returnValue;
    }
    static public ProductImage GetSingleProductImage (Context context, long productImageId) {
        ProductImage returnValue  =new ProductImage();
        ProductImageDataSource productImageDataSource = new ProductImageDataSource(context);
        try {
            productImageDataSource.open();
            returnValue = productImageDataSource.GetSingleProductImage(productImageId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleProductImage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productImageDataSource.close();
        }
        return returnValue;
    }
    static public List<ProductImage> GetAllProductImage (Context context) {
        List<ProductImage> invoices  =new ArrayList<ProductImage>();
        ProductImageDataSource productImageDataSource = new ProductImageDataSource(context);
        try {
            productImageDataSource.open();
            invoices = productImageDataSource.GetAllProductImage();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllProductImage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productImageDataSource.close();
        }
        return invoices;
    }
    static public int InsertProductImage(Context context, List<ProductImage> invoices) {
        int returnValue = 0;
        ProductImageDataSource productImageDataSource = new ProductImageDataSource(context);

        try {
            productImageDataSource.open();
            returnValue = productImageDataSource.InsertProductImage(invoices);
            if(returnValue != invoices.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertCustomer");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert ProductImage array. sizeOfProductImage:" + invoices.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productImageDataSource.close();
            return returnValue;
        }
    }
    static public void ClearProductImage (Context context) {
        ProductImageDataSource productImageDataSource = new ProductImageDataSource(context);
        try {
            productImageDataSource.open();
            productImageDataSource.ClearProductImage();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearProductImage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            productImageDataSource.close();
        }
    }
    static public void DeleteProductImage (Context context, long productImageId) {
        ProductImageDataSource productImageDataSource = new ProductImageDataSource(context);
        try {
            productImageDataSource.open();
            productImageDataSource.DeleteProductImage(productImageId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteProductImage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            productImageDataSource.close();
        }
    }

    static public Integer SyncWithServer(Context context, String productId) {
        WebService ws = new WebService();

        String dec = AppConfig.GetDec(context);
        Boolean flag = false;


        String res = ws.GetProductImages(dec, Constants.Phrase , productId);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {

            List<ProductImage> productImageList = CommandHandler.DecodeCommand.GetProductImage(res);

            ProductImage.ClearProductImage(context);
            ProductImage.InsertProductImage(context, productImageList);
            Intent intent = new Intent(AppIntent.ListenerProductImage);
            context.sendBroadcast(intent);
            return CommandHandler.errorType_NoError;

        }
        else
            return CommandHandler.errorType_ServerAccess;

    }









}