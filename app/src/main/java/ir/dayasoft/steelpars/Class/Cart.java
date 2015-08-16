package ir.dayasoft.steelpars.Class;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.CartDataSource;

public class Cart {

    Long CartId;
    Long FK_productId;
    Double Count;
    Integer Order;
    Integer Status;
    String CreateDate;

    private String productName;
    private String productUnit;
    private String productPrice;
    private String productImage;


    public static final String Tag = "database.ClassSession";


    public Long getCartId() {
        return CartId;
    }
    public void setCartId(Long cartId) {
        CartId = cartId;
    }
    public Long getFK_productId() {
        return FK_productId;
    }
    public void setFK_productId(Long fK_productId) {
        FK_productId = fK_productId;
    }
    public Double getCount() {
        return Count;
    }
    public void setCount(Double count) {
        Count = count;
    }
    public Integer getOrder() {
        return Order;
    }
    public void setOrder(Integer order) {
        Order = order;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;
    }
    public String getCreateDate() {
        return CreateDate;
    }
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductUnit() {
        return productUnit;
    }
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
    public String getProductImage() {
        return productImage;
    }
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    static public List<Cart> GetAllCart (Context context) {
        List<Cart> carts  =new ArrayList<>();
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            carts = cartDataSource.GetAllCart();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
        }
        return carts;
    }
    static public Integer GetOrder (Context context) {
        Cart cart ;
        int returnValue = 0;
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            cart = cartDataSource.GetLastCart();
            if(cart.getOrder() != null)
                returnValue = cart.getOrder();
            else
                returnValue = 0;
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetOrder");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
        }
        return returnValue;
    }
    static public Double GetCartSum (Context context) {
        String st;
        double returnValue = -1;
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            st = cartDataSource.GetCartSum();
            returnValue = Double.valueOf(st);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCartSum");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
        }
        return returnValue;
    }
    static public Cart GetSingleCart (Context context, Long cartId) {
        Cart cart = new Cart();
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            cart = cartDataSource.GetSingleCart(cartId);
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
            cartDataSource.close();
        }
        return cart;
    }
    static public Cart GetLastCart (Context context) {
        Cart cart = new Cart();
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            cartDataSource.GetAllCartTest();
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
            cartDataSource.close();
        }
        return cart;
    }
    static public Cart GetCartByFK_ProductId (Context context, Long fk_productId) {
        Cart cart = new Cart();
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            cart = cartDataSource.GetCartByFK_productId(fk_productId);
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
            cartDataSource.close();
        }
        return cart;
    }
    static public Integer GetCartCounter (Context context) {
        int returnValue = 0;
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            returnValue = cartDataSource.CartCount();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetOrder");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
        }
        return returnValue;
    }

    static public int InsertCart(Context context, List<Cart> carts) {
        int returnValue = 0;
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            returnValue = cartDataSource.InsertCart(carts);
            if(returnValue != carts.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertCart");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Cart array. sizeOfCategory:" + carts.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
            return returnValue;
        }
    }
    static public Cart InsertCart(Context context, Cart cart) {
        Cart returnValue = new Cart();
        CartDataSource cartDataSource = new CartDataSource(context);

        try {
            cartDataSource.open();
            returnValue = cartDataSource.InsertCart(cart);

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
            return returnValue;
        }
    }
    static public Cart InsertCart(Context context, Long productId, String count) {

        Cart thisCart = Cart.GetCartByFK_ProductId(context,productId);
        if(thisCart.getCartId() != -1)
            Cart.DeleteCart(context,thisCart.CartId);

        Cart cart,returnValue = new Cart();
        CartDataSource cartDataSource = new CartDataSource(context);
        String date = Core.Dates.GetCurentDate();

        cart=new Cart();
        cart.setFK_productId(productId);
        cart.setCreateDate(date);
        cart.setOrder(Cart.GetOrder(context)+1);
        cart.setCount(Double.valueOf(count));
        cart.setCreateDate(Core.Dates.GetCurentDate());

        if(count.equals("0")) {
            return cart;
        }

        try {
            cartDataSource.open();
            returnValue = cartDataSource.InsertCart(cart);

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cartDataSource.close();
            return returnValue;
        }
    }

    static public void ClearCart (Context context) {
        CartDataSource cartDataSource = new CartDataSource(context);
        try {
            cartDataSource.open();
            cartDataSource.ClearCart();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            cartDataSource.close();
        }
    }
    static public void DeleteCart (Context context, long cartId) {
        CartDataSource cartDataSource = new CartDataSource(context);
        try {
            cartDataSource.open();
            cartDataSource.DeleteCart(cartId);

            Intent intent = new Intent(AppIntent.ListenerUpdateSumPrice);
            context.sendBroadcast(intent);

            intent = new Intent(AppIntent.ListenerUpdateCart);
            context.sendBroadcast(intent);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteCart");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            cartDataSource.close();
        }
    }


}
