package ir.dayasoft.steelpars.Class;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Communication.GetCategoryAsyncTask;


import ir.dayasoft.steelpars.Communication.GetProductAsyncTask;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.CategoryDataSource;
import ir.dayasoft.steelpars.DataBase.CustomerDataSource;

public class Customer {

    Long CustomerId;
    String Name;
    String Family;
    String Username;
    String Password;
    String Phone;
    String CellPhone;
    String Address;
    String Email;
    String CreateDate;
    Integer PaymentType;
    Double MinDeposit;
    Integer Status;

    public static final String Tag = "database.Customer";
    public static final Integer Register_DuplicateCellPhone = -2;


    public static final Integer GroupAgency = 1;
    public static final Integer GroupPerson = 2;

    public static final Integer PaymentTypeComplete = 1;
    public static final Integer PaymentTypeDeposit = 2;
    public static final Integer PaymentTypeNoDeposit = 3;



    public Long getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getFamily() {
        return Family;
    }
    public void setFamily(String family) {
        Family = family;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getCellPhone() {
        return CellPhone;
    }
    public void setCellPhone(String cellPhone) {
        CellPhone = cellPhone;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getCreateDate() {
        return CreateDate;
    }
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
    public Integer getPaymentType() {
        return PaymentType;
    }
    public void setPaymentType(Integer paymentType) {
        PaymentType = paymentType;
    }
    public Double getMinDeposit() {
        return MinDeposit;
    }
    public void setMinDeposit(Double minDeposit) {
        MinDeposit = minDeposit;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;}


    static public Customer GetSingleCustomer (Context context, long customerId) {
        Customer returnValue  =new Customer();
        CustomerDataSource customerDataSource = new CustomerDataSource(context);
        try {
            customerDataSource.open();
            returnValue = customerDataSource.GetSingleCustomer(customerId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            customerDataSource.close();
        }
        return returnValue;
    }
    static public Customer GetCustomer (Context context) {
        Customer returnValue  =new Customer();
        CustomerDataSource customerDataSource = new CustomerDataSource(context);
        try {
            customerDataSource.open();
            returnValue = customerDataSource.GetCustomer();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            customerDataSource.close();
        }
        return returnValue;
    }
    static public Customer UpdateCustomerId (Context context, long customerId) {
        Customer returnValue = new Customer();
        CustomerDataSource customerDataSource = new CustomerDataSource(context);
        try {
            customerDataSource.open();

            customerDataSource.UpdateCustomerId(customerId);
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
            customerDataSource.close();
        }
        return returnValue;
    }
    static public Customer UpdateCustomer (Context context, Customer customer) {
        Customer returnValue = new Customer();
        CustomerDataSource customerDataSource = new CustomerDataSource(context);
        try {
            customerDataSource.open();
            customerDataSource.DeleteCustomer("0");
            returnValue = customerDataSource.InsertCustomer(customer);
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
            customerDataSource.close();
        }
        return returnValue;
    }
    static public List<Customer> GetAllCustomer (Context context) {
        List<Customer> customers  =new ArrayList<Customer>();
        CustomerDataSource customerDataSource = new CustomerDataSource(context);
        try {
            customerDataSource.open();
            customers = customerDataSource.GetAllCustomer();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            customerDataSource.close();
        }
        return customers;
    }
    static public int InsertCustomer(Context context, List<Customer> categories) {
        int returnValue = 0;
        CustomerDataSource customerDataSource = new CustomerDataSource(context);

        try {
            customerDataSource.open();
            returnValue = customerDataSource.InsertCustomer(categories);
            if(returnValue != categories.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertCustomer");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Category array. sizeOfCustomer:" + categories.size()+" Size of sucsses insert:" + returnValue);
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
            customerDataSource.close();
            return returnValue;
        }
    }
    static public Customer InsertCustomer(Context context, Customer customer) {
        CustomerDataSource customerDataSource = new CustomerDataSource(context);

        try {
            customerDataSource.open();
            customer = customerDataSource.InsertCustomer(customer);
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
            customerDataSource.close();
            return customer;
        }
    }
    static public void ClearCustomer (Context context) {
        CustomerDataSource customerDataSource = new CustomerDataSource(context);
        try {
            customerDataSource.open();
            customerDataSource.ClearCustomer();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            customerDataSource.close();
        }
    }
    static public void DeleteCustomer (Context context, long customerId) {
        CategoryDataSource categoryDataSource = new CategoryDataSource(context);
        try {
            categoryDataSource.open();
            categoryDataSource.DeleteCategory(customerId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            categoryDataSource.close();
        }
    }


    static public void CheckConfirmCode(Context context )
    {



    }



    static public void RefreshData(Context context )
    {
        GetCategoryAsyncTask categoryAsyncTask = new GetCategoryAsyncTask(context);
        categoryAsyncTask.execute();

        GetProductAsyncTask productAsyncTask = new GetProductAsyncTask(context);
        productAsyncTask.execute();

      /*  GetMessageAsyncTask getMessageAsyncTask = new GetMessageAsyncTask(context);
        getMessageAsyncTask.execute();
*/
    }


    static public void SignInCustomer (Context context, Customer mCustomer, Customer resCustomer) {
        ClearCustomer(context);
        mCustomer.setCustomerId(resCustomer.CustomerId);
        InsertCustomer(context, mCustomer);
        UpdateCustomer(context, resCustomer);

        Customer customer = GetCustomer(context);

        AppConfig.SetIsLogin(context, true);
        AppConfig.SetUserId(context, String.valueOf(customer.getCustomerId()));
        AppConfig.SetUsername(context, customer.getUsername());
      //  AppConfig.SetGroups(context, customer.getGroups());
    }

    static public void SignOutCustomer (Context context) {
        AppConfig.SetIsLogin(context, false);
        AppConfig.SetUserId(context, "");
        AppConfig.SetUsername(context, "");
        AppConfig.SetGroups(context, GroupPerson);

        ClearCustomer(context);
    }


}
