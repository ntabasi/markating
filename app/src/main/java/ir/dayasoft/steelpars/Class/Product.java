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
import ir.dayasoft.steelpars.DataBase.ProductDataSource;
import ir.dayasoft.steelpars.DataBase.ProductImageDataSource;


public class Product {

    Long ProductId;
    String Name;
    Integer FK_categoryId;
    String ImageUrl;
    Double Price;
    String PriceAgent;
    Double DiscountPrice;
    String Description;
    Integer UnitCountability;
    String UnitName;
    String UpdateDate;


    private boolean inCart;//not in db

    public static final String Tag = "database.Product";

    public static int UnitCountable = 1;
    public static int UnitNotCountable = 0;



    public Long getProductId() {
        return ProductId;
    }
    public void setProductId(Long productId) {
        ProductId = productId;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public Integer getFK_categoryId() {
        return FK_categoryId;
    }
    public void setFK_categoryId(Integer fK_categoryId) {
        FK_categoryId = fK_categoryId;
    }
    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
    public Double getPrice() {

        return Price;
    }
    public void setPrice(Double price) {
        Price = price;
    }
    public String getPriceAgent() {
        return PriceAgent;
    }
    public void setPriceAgent(String priceAgent) {
        PriceAgent = priceAgent;
    }
    public Double getDiscountPrice() {
        return DiscountPrice;
    }
    public void setDiscountPrice(Double discountPrice) {
        DiscountPrice = discountPrice;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public Integer getUnitCountability() {
        return UnitCountability;
    }
    public void setUnitCountability(Integer unitCountability) {
        UnitCountability = unitCountability;
    }
    public String getUnitName() {
        return UnitName;
    }
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
    public String getUpdateDate() {
        return UpdateDate;
    }
    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }



    public boolean isInCart() {
        return inCart;
    }
    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    static public List<Product> FillSampleProduct () {
        List<Product> returnValue = new ArrayList<>();

        Product tmp = new Product();
        tmp.setProductId(Long.valueOf("0"));
        tmp.setName("تخت مدل الماس");
        tmp.setDescription("توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت");
        tmp.setUnitName("عدد");
        tmp.setFK_categoryId(1);
        tmp.setPrice(Double.valueOf("2500"));
        tmp.setUnitCountability(UnitCountable);
        returnValue.add(tmp);

        tmp = new Product();
        tmp.setProductId(Long.valueOf("1"));
        tmp.setName("تخت مدل نازنین");
        tmp.setDescription("توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت");
        tmp.setUnitName("عدد");
        tmp.setFK_categoryId(1);
        tmp.setUnitCountability(UnitCountable);
        tmp.setPrice(Double.valueOf("2200"));
        returnValue.add(tmp);

        tmp = new Product();
        tmp.setProductId(Long.valueOf("2"));
        tmp.setName("تخت مدل نوید");
        tmp.setDescription("توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت");
        tmp.setUnitName("عدد");
        tmp.setFK_categoryId(1);
        tmp.setUnitCountability(UnitNotCountable);
        tmp.setPrice(Double.valueOf("2000"));
        returnValue.add(tmp);

        tmp = new Product();
        tmp.setProductId(Long.valueOf("3"));
        tmp.setName("تخت سگی");
        tmp.setDescription("توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت");
        tmp.setUnitName("بسته");
        tmp.setFK_categoryId(1);
        tmp.setUnitCountability(UnitNotCountable);
        tmp.setPrice(Double.valueOf("25000"));
        returnValue.add(tmp);

        tmp = new Product();
        tmp.setProductId(Long.valueOf("4"));
        tmp.setName("تخت سوگند");
        tmp.setDescription("توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت");
        tmp.setUnitName("بسته");
        tmp.setFK_categoryId(1);
        tmp.setUnitCountability(UnitNotCountable);
        tmp.setPrice(Double.valueOf("13000"));
        returnValue.add(tmp);

        tmp = new Product();
        tmp.setProductId(Long.valueOf("5"));
        tmp.setName("تخت مرجان");
        tmp.setDescription("توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت توضیحات تخت");
        tmp.setUnitName("بسته");
        tmp.setFK_categoryId(1);
        tmp.setUnitCountability(UnitNotCountable);
        tmp.setPrice(Double.valueOf("25000.3"));
        returnValue.add(tmp);



        return returnValue;
    }

    static public Product GetLastProductUpdate(Context context) {

        Product returnValue  =new Product();
        ProductDataSource productDataSource = new ProductDataSource(context);
        try {
            productDataSource.open();
            returnValue = productDataSource.GetLastProductUpdate();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetLastProductUpdate");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productDataSource.close();
        }
        return returnValue;
    }
    static public Product GetSingleProduct (Context context, long productId) {
        Product returnValue  =new Product();
        ProductDataSource invoiceDataSource = new ProductDataSource(context);
        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.GetSingleProduct(productId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return returnValue;
    }
    static public List<Product> GetProductByCategoryIdWithLeftJoin (Context context, long categoryId) {
        List<Product> returnValue = new ArrayList<>();
        ProductDataSource invoiceDataSource = new ProductDataSource(context);
        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.GetProductByCategoryIdWithLeftJoin(categoryId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetProductByCategoryId");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return returnValue;
    }
    static public List<Product> GetProductByCategoryIdWithColor (Context context, long categoryId, long colorId) {
        List<Product> returnValue = new ArrayList<>();
        ProductDataSource invoiceDataSource = new ProductDataSource(context);
        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.GetProductByCategoryId(categoryId, colorId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetProductByCategoryId");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return returnValue;
    }
    static public List<Product> GetAllProduct (Context context) {
        List<Product> products  =new ArrayList<Product>();
        ProductDataSource productDataSource = new ProductDataSource(context);
        try {
            productDataSource.open();
            products = productDataSource.GetAllProduct();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productDataSource.close();
        }
        return products;
    }
    static public int InsertProduct(Context context, List<Product> products) {
        int returnValue = 0;
        ProductDataSource productDataSource = new ProductDataSource(context);

        try {
            productDataSource.open();
            returnValue = productDataSource.InsertProduct(products);
            if(returnValue != products.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertProduct");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Product array. sizeOfCustomer:" + products.size()+" Size of sucsses insert:" + returnValue);
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
            productDataSource.close();
            return returnValue;
        }
    }
    static public Product InsertProduct(Context context, Product product) {
        Product returnValue = new Product();
        ProductDataSource invoiceDataSource = new ProductDataSource(context);

        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.InsertProduct(product);

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return returnValue;
        }
    }
    static public void ClearProduct (Context context) {
        ProductDataSource productDataSource = new ProductDataSource(context);
        try {
            productDataSource.open();
            productDataSource.ClearProduct();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            productDataSource.close();
        }
    }
    static public void DeleteProduct (Context context, long productId) {
        ProductDataSource invoiceDataSource = new ProductDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.DeleteProduct(productId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteProduct");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
        }
    }
    static public void UpdatePriceWithCart (Context context, List<Cart> cartList) {
        ProductDataSource productDataSource = new ProductDataSource(context);

        try {
            productDataSource.open();
            for(Cart cart: cartList) {
                long productId = cart.getFK_productId();
                Double price = Double.valueOf(cart.getProductPrice());

                Product product = Product.GetSingleProduct(context, productId);
                product.setPrice(price);

                Product.DeleteProduct(context, productId);
                Product.InsertProduct(context, product);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "UpdatePriceWithCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            productDataSource.close();
        }
    }


    static public List<String> GetAllImagesUrl(Context context)
    {
        List<String> returnValue  =new ArrayList<>();
        List<Product> products=new ArrayList<>();
        List<ProductImage> productImages=new ArrayList<>();
        ProductDataSource productDataSource = new ProductDataSource(context);
        ProductImageDataSource productImageDataSource=new ProductImageDataSource(context);
        try {

            productDataSource.open();
            productImageDataSource.open();
            products = productDataSource.GetAllProduct();
            productImages= productImageDataSource.GetAllProductImage();

            for (Product tmp : products )
                returnValue.add(tmp.ImageUrl);

            for (ProductImage tmp: productImages)
                returnValue.add(tmp.getImageUrl());

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
            productDataSource.close();
            productImageDataSource.close();
        }
        return returnValue;
    }

    static public Integer SyncWithServer(Context context,String updateDate, String categoryId) {

        WebService ws = new WebService();



        String dec = AppConfig.GetDec(context);
        Boolean flag = false;

       //    Product lastProductUpdate = Product.GetLastProductUpdate(context);


        String res = ws.GetProduct(dec, Constants.Phrase, updateDate, categoryId);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {

            List<Product> productList = CommandHandler.DecodeCommand.GetProduct(res);

            Product.ClearProduct(context);
            Product.InsertProduct(context, productList);
            Intent intent = new Intent(AppIntent.ListenerProduct);
            context.sendBroadcast(intent);
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;


    }


}