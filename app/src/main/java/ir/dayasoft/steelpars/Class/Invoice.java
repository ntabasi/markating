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
import ir.dayasoft.steelpars.DataBase.InvoiceDataSource;
import ir.dayasoft.steelpars.DataBase.InvoiceProductDataSource;


public class Invoice {

    Long InvoiceId;
    Long ServerInvoiceId;
    Long FK_customerId;
    Double Price;
    Double DeliveryCost;
    String Address;
    Integer Port;
    Integer Status;
    Integer PaymentType;
    Double Deposit; //بیعانه//
    Long PrimaryInvoiceId;
    String CreateDate;
    Integer Input;

    private String PaymentCode;


    final static public int PaymentTypeAny = 0;
    final static public int PaymentTypeDeposit = 1;
    final static public int PaymentTypeFull = 2;


    public static final int  Status_Pending = 0;
    public static final int  Status_Deliver = 1;
    public static final int  Status_Confirm = 2;
    public static final int  Status_Reject = 3 ;


    public static final int  Status_Server_Pendding = 0;
    public static final int  Status_Server_Confirm = 1;
    public static final int  Status_Server_Reject = 2;
    public static final int  Status_Server_inPreparition = 5;


    public static final String Tag = "database.Invoice";


    public Long getInvoiceId() {
        return InvoiceId;
    }
    public void setInvoiceId(Long invoiceId) {
        InvoiceId = invoiceId;
    }
    public Long getServerInvoiceId() {
        return ServerInvoiceId;
    }
    public void setServerInvoiceId(Long serverInvoiceId) {
        ServerInvoiceId = serverInvoiceId;
    }
    public Long getFK_customerId() {
        return FK_customerId;
    }
    public void setFK_customerId(Long fK_customerId) {
        FK_customerId = fK_customerId;
    }
    public Double getPrice() {
        return Price;
    }
    public void setPrice(Double price) {
        Price = price;
    }
    public Double getDeliveryCost() {
        return DeliveryCost;
    }
    public void setDeliveryCost(Double deliveryCost) {
        DeliveryCost = deliveryCost;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public Integer getPort() {
        return Port;
    }
    public void setPort(Integer port) {
        Port = port;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;
    }
    public Integer getPaymentType() {
        return PaymentType;
    }
    public void setPaymentType(Integer paymentType) {
        PaymentType = paymentType;
    }
    public Double getDeposit() {
        return Deposit;
    }
    public void setDeposit(Double deposit) {
        Deposit = deposit;
    }
    public Long getPrimaryInvoiceId() {
        return PrimaryInvoiceId;
    }
    public void setPrimaryInvoiceId(Long primaryInvoiceId) {
        PrimaryInvoiceId = primaryInvoiceId;
    }
    public String getCreateDate() {
        return CreateDate;
    }
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
    public Integer getInput() {
        return Input;
    }
    public void setInput(Integer input) {
        Input = input;
    }
    public String getPaymentCode() {
        return PaymentCode;
    }
    public void setPaymentCode(String paymentCode) {
        PaymentCode = paymentCode;
    }



    static public Invoice GetSingleInvoice (Context context, long invoiceId) {
        Invoice returnValue  =new Invoice();
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.GetSingleInvoice(invoiceId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return returnValue;
    }
    static public List<Invoice> GetAllInvoice (Context context) {
        List<Invoice> invoices  =new ArrayList<Invoice>();
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        String userId = AppConfig.GetUserId(context);
        try {
            invoiceDataSource.open();
            invoices = invoiceDataSource.GetAllInvoice(userId, 30);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return invoices;
    }
    static public Invoice GetLastInvoice (Context context) {
        Invoice invoice = new Invoice();
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            invoice = invoiceDataSource.GetLastInvoice();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetLastInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return invoice;
    }
    static public int InsertInvoice(Context context, List<Invoice> invoices) {
        int returnValue = 0;
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);

        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.InsertInvoice(invoices);
            if(returnValue != invoices.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertInvoice");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Invoice array. sizeOfInvoice:" + invoices.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return returnValue;
        }
    }
    static public Invoice InsertInvoice(Context context, Invoice invoice) {
        Invoice returnValue = new Invoice();
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);

        try {
            invoiceDataSource.open();
            returnValue = invoiceDataSource.InsertInvoice(invoice);

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return returnValue;
        }
    }
    static public void UpdateInvoice (Context context, Invoice invoice) {
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();

            invoiceDataSource.Update(invoice);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "UpdateCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
    }

    static public int InsertInvoice(Context context, Integer situation, String paymentCode, int paymentType, String deposit) {
        int returnValue = 0;
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        Invoice invoice = new Invoice();
        List<Cart> carts = Cart.GetAllCart(context);
        List<InvoiceProduct> invoiceProductList = new ArrayList<>();

        try {
            invoiceDataSource.open();

            double sumPriceDouble = Cart.GetCartSum(context);

            invoice.setDeposit(Double.valueOf(deposit));
            invoice.setPaymentCode(paymentCode);
            invoice.setStatus(Invoice.Status_Pending);
            invoice.setCreateDate(Core.Dates.GetCurentDate());
            invoice.setPrice(sumPriceDouble);
            invoice.setInput(situation);
            invoice.setFK_customerId(Long.valueOf(AppConfig.GetUserId(context)));
            invoice = Invoice.InsertInvoice(context,invoice);

            for(Cart cart: carts) {
                InvoiceProduct invoiceProduct = new InvoiceProduct();


                invoiceProduct.setFK_productId(cart.getFK_productId());
                invoiceProduct.setPrice(Double.valueOf(cart.getProductPrice()));
                invoiceProduct.setCount(cart.getCount());
                invoiceProduct.setOrder(cart.getOrder());
                invoiceProduct.setFK_invoiceId(invoice.getInvoiceId());

                invoiceProductList.add(invoiceProduct);

            }

            Cart.ClearCart(context);

            Intent intent = new Intent(AppIntent.ListenerUpdateCart);
            context.sendBroadcast(intent);


            returnValue = InvoiceProduct.InsertInvoiceProduct(context,invoiceProductList);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return returnValue;
        }
    }

    static public long InsertInvoice(Context context, Invoice  invoice ,List<InvoiceProduct> invoiceProducts) {
        int returnValue = 0;

        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);

        try {
            invoiceDataSource.open();



            invoice.setCreateDate(Core.Dates.GetCurentDate());


            invoice = Invoice.InsertInvoice(context,invoice);

            int order=0;
            for(InvoiceProduct invoiceProduct: invoiceProducts) {

                invoiceProduct.setOrder(order++);
                invoiceProduct.setFK_invoiceId(invoice.getInvoiceId());

            }
            Intent intent = new Intent(AppIntent.ListenerUpdateCart);
            context.sendBroadcast(intent);

            InvoiceProduct.InsertInvoiceProduct(context,invoiceProducts);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return invoice.getInvoiceId();
        }
    }

    static public int UpdateInvoice(Context context, Invoice  invoice ,List<InvoiceProduct> invoiceProducts )
    {
        int returnValue = 0;

        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        InvoiceProductDataSource invoiceProductDataSource=new InvoiceProductDataSource(context);

        try {
            invoiceDataSource.open();
            invoiceProductDataSource.open();

//            invoice.setCreateDate(Core.Dates.GetCurentDate());


            if ( invoiceDataSource.Update(invoice)>0) {

                invoiceProductDataSource.DeleteInvoiceProductBaseInvoiceId(String.valueOf(invoice.getInvoiceId()));
                int order = 0;
                for (InvoiceProduct invoiceProduct : invoiceProducts) {

                    invoiceProduct.setOrder(order++);
                    invoiceProduct.setFK_invoiceId(invoice.getInvoiceId());

                }
                Intent intent = new Intent(AppIntent.ListenerUpdateCart);
                context.sendBroadcast(intent);

                InvoiceProduct.InsertInvoiceProduct(context, invoiceProducts);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return 1;
        }
    }


    static public long InsertInvoice(Context context, Integer situation) {
        int returnValue = 0;
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        Invoice invoice = new Invoice();
        List<Cart> carts = Cart.GetAllCart(context);
        List<InvoiceProduct> invoiceProductList = new ArrayList<>();

        try {
            invoiceDataSource.open();

            double sumPriceDouble = Cart.GetCartSum(context);

            invoice.setCreateDate(Core.Dates.GetCurentDate());
            invoice.setPrice(sumPriceDouble);
            invoice.setInput(situation);
            invoice.setStatus(Invoice.Status_Pending);
            invoice.setFK_customerId(Long.valueOf(AppConfig.GetUserId(context)));
            invoice = Invoice.InsertInvoice(context,invoice);


            for(Cart cart: carts) {
                InvoiceProduct invoiceProduct = new InvoiceProduct();


                invoiceProduct.setFK_productId(cart.getFK_productId());
                invoiceProduct.setPrice(Double.valueOf(cart.getProductPrice()));
                invoiceProduct.setCount(cart.getCount());
                invoiceProduct.setOrder(cart.getOrder());
                invoiceProduct.setFK_invoiceId(invoice.getInvoiceId());

                invoiceProductList.add(invoiceProduct);

            }

            //     Cart.ClearCart(context);

            Intent intent = new Intent(AppIntent.ListenerUpdateCart);
            context.sendBroadcast(intent);


            InvoiceProduct.InsertInvoiceProduct(context,invoiceProductList);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
            return invoice.getInvoiceId();
        }
    }


    static public List<Invoice> GetInvoiceByStatus (Context context ,Integer status,int situation) {
        List<Invoice> invoices  =new ArrayList<Invoice>();
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            invoices = invoiceDataSource.GetAllInvoiceByStatus(status,situation);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetInvoiceByStatus");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return invoices;
    }


    static public void ClearInvoice (Context context) {
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.ClearInvoice();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearInvoice");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
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

    static public void ConfirmInvoiceSendToServer (Context context, long invoiceId ,long serverInvoiceId) {
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.ConfirmInvoiceSendToServer(invoiceId, serverInvoiceId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ConfrimInvoiceSendToServer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
        }
    }

    static public Boolean InvoiceProductExists(List<InvoiceProduct> invoiceProducts )
    {


        return true;
    }

    static public void ChangeInvoiceStatus(Long invoiceId,int status,Context context)
    {

        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.ChangeInvoiceStatus(invoiceId, status);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ChangeInvoiceStatus");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
        }

    }


    static public String GetStatusText(int status)
    {
        switch (status)
        {
            case Status_Confirm:
                return "فاکتور تایید شده است";
            case Status_Reject:
                return "فاکتور رد شده است";
            case Status_Deliver:
                return "فاکتور منتظر تایید مدیریت می باشد";
            default:
                return "";
        }


    }

    static public String GetStatusColor(int status)
    {
        switch (status)
        {
            case Status_Confirm:
                return "#139905";
            case Status_Reject:
                return "#bc0000";
            case Status_Deliver:
                return "#bc6a00";
            default:
                return "";
        }


    }



}