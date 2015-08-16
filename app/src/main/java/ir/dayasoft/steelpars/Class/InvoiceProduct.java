package ir.dayasoft.steelpars.Class;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.InvoiceDataSource;
import ir.dayasoft.steelpars.DataBase.InvoiceProductDataSource;
import ir.dayasoft.steelpars.DataBase.ProductDataSource;


public class InvoiceProduct
{

    Long  InvoiceProductId;
    Long FK_invoiceId;
    Long FK_productId;
    Double  Price;
    Double  Count;
    Integer Order;
    String ProductName;
    String ProductUnitName;

    public static final String Tag = "database.InvoiceProduct";

    public Long  getInvoiceProductId() {
    return InvoiceProductId;}
    public void setInvoiceProductId(Long  invoiceProductId)  {
        InvoiceProductId=invoiceProductId;
    }
    public Long  getFK_invoiceId() {
        return FK_invoiceId;}
    public void setFK_invoiceId(Long  fk_invoiceId)  {
        FK_invoiceId=fk_invoiceId;
    }
    public Long  getFK_productId() {
    return FK_productId;}
    public void setFK_productId(Long  fK_productId)  {
        FK_productId=fK_productId;
    }
    public Double  getPrice() {
    return Price;}
    public void setPrice(Double  price)  {
        Price=price;
    }
    public Double  getCount() {
    return Count;}
    public void setCount(Double  count)  {
        Count=count;
    }
    public  Integer getOrder() {
    return Order;}
    public void setOrder( Integer order)  {
        Order=order;
    }
    public String getProductName() {
        return ProductName;
    }
    public void setProductName(String productName) {
        ProductName = productName;
    }
    public String getProductUnitName() {
        return ProductUnitName;
    }
    public void setProductUnitName(String productUnitName) {
        ProductUnitName = productUnitName;
    }


    static public InvoiceProduct GetSingleInvoiceProduct (Context context, long invoiceProductId) {
        InvoiceProduct returnValue  =new InvoiceProduct();
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);
        try {
            invoiceProductDataSource.open();
            returnValue = invoiceProductDataSource.GetSingleInvoiceProduct(invoiceProductId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleInvoiceProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceProductDataSource.close();
        }
        return returnValue;
    }
    static public List<InvoiceProduct> GetAllInvoiceProductByFK_invoiceId (Context context, long fk_invoiceId) {
        List<InvoiceProduct> invoiceProductList  =new ArrayList<InvoiceProduct>();
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);

        List<Product> products = new ArrayList<>();
        ProductDataSource productDataSource = new ProductDataSource(context);
        int i = 0;
        try {
            invoiceProductDataSource.open();
            invoiceProductList = invoiceProductDataSource.GetInvoiceProductByInvoiceId(fk_invoiceId);

            //invoiceProductList = invoiceProductDataSource.GetAllInvoiceProduct();
            products = Product.GetAllProduct(context);
            i=1;
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllInvoiceProductByFK_invoiceId");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceProductDataSource.close();
        }
        return invoiceProductList;
    }
    static public Integer GetCountByInvoice (Context context, long fk_invoiceId) {
        Integer returnValue = 0;
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);
        try {
            invoiceProductDataSource.open();
            returnValue = invoiceProductDataSource.GetCountByInvoice(fk_invoiceId);

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCountByInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceProductDataSource.close();
        }
        return returnValue;
    }
    static public Double GetSumInvoice (Context context, long fk_invoiceId) {
        double returnValue = 0;
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);
        try {
            invoiceProductDataSource.open();
            returnValue = invoiceProductDataSource.GetSumInvoice(fk_invoiceId);

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSumInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceProductDataSource.close();
        }
        return returnValue;
    }
    static public List<InvoiceProduct> GetAllInvoiceProduct (Context context) {
        List<InvoiceProduct> invoiceProductList  =new ArrayList<InvoiceProduct>();
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);
        try {
            invoiceProductDataSource.open();
            invoiceProductList = invoiceProductDataSource.GetAllInvoiceProduct();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllInvoiceProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceProductDataSource.close();
        }
        return invoiceProductList;
    }
    static public int InsertInvoiceProduct(Context context, List<InvoiceProduct> invoiceProducts) {
        int returnValue = 0;
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);

        try {
            invoiceProductDataSource.open();
            returnValue = invoiceProductDataSource.InsertInvoiceProduct(invoiceProducts);
            if(returnValue != invoiceProducts.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertInvoiceProduct");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in InsertInvoiceProduct array. sizeOfInvoice:" + invoiceProducts.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoiceProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceProductDataSource.close();
            return returnValue;
        }
    }
    static public void ClearInvoiceProduct (Context context) {
        InvoiceProductDataSource invoiceProductDataSource = new InvoiceProductDataSource(context);
        try {
            invoiceProductDataSource.open();
            invoiceProductDataSource.ClearInvoiceProduct();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearInvoiceProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceProductDataSource.close();
        }
    }
    static public void DeleteInvoice (Context context, long invoiceId) {
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.DeleteInvoice(invoiceId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
        }
    }


}