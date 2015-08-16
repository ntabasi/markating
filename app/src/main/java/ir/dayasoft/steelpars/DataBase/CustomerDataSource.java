package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Customer;

public class CustomerDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public CustomerDataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database.close();
    }


    private String[] allColumns = {
            DataBaseHelper.customer_family
            , DataBaseHelper.customer_name
            , DataBaseHelper.customer_cellPhone
            , DataBaseHelper.customer_phone
            , DataBaseHelper.customer_customerId
            , DataBaseHelper.customer_password
            , DataBaseHelper.customer_paymentType
            , DataBaseHelper.customer_email
            , DataBaseHelper.customer_address
            , DataBaseHelper.customer_status
            , DataBaseHelper.customer_minDeposit
            , DataBaseHelper.customer_createDate
            , DataBaseHelper.customer_username
    };


    public Customer InsertCustomer (Customer customer) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.customer_customerId, customer.getCustomerId());
        values.put(DataBaseHelper.customer_family, customer.getFamily());
        values.put(DataBaseHelper.customer_name, customer.getName());
        values.put(DataBaseHelper.customer_cellPhone, customer.getCellPhone());
        values.put(DataBaseHelper.customer_phone, customer.getPhone());
        values.put(DataBaseHelper.customer_password, customer.getPassword());
        values.put(DataBaseHelper.customer_paymentType, customer.getPaymentType());
        values.put(DataBaseHelper.customer_email, customer.getEmail());
        values.put(DataBaseHelper.customer_address, customer.getAddress());
        values.put(DataBaseHelper.customer_status, customer.getStatus());
        values.put(DataBaseHelper.customer_minDeposit, customer.getMinDeposit());
        values.put(DataBaseHelper.customer_createDate, customer.getCreateDate());
        values.put(DataBaseHelper.customer_username, customer.getUsername());

        long insertId = database.insert(DataBaseHelper.TABLE_customer, null, values);
        customer.setCustomerId(insertId);
        return customer;
    }
    public List<Customer> GetAllCustomer() {
        List<Customer> returnValue = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_customer
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Customer customer = CursorToCustomer(cursor);
            returnValue.add(customer);
            cursor.moveToNext();
        }
        cursor.close();
        return returnValue;
    }
    public Customer GetCustomer() {
        Customer returnValue = new Customer();
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_customer
                , null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            returnValue = CursorToCustomer(cursor);
        } else
            returnValue.setCustomerId(Long.valueOf(-1));
        cursor.close();
        return returnValue;
    }
    public int InsertCustomer(List<Customer> customers) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Customer customer : customers) {

            values.put(DataBaseHelper.customer_family, customer.getFamily());
            values.put(DataBaseHelper.customer_name, customer.getName());
            values.put(DataBaseHelper.customer_cellPhone, customer.getCellPhone());
            values.put(DataBaseHelper.customer_phone, customer.getPhone());
            values.put(DataBaseHelper.customer_customerId, customer.getCustomerId());
            values.put(DataBaseHelper.customer_password, customer.getPassword());
            values.put(DataBaseHelper.customer_paymentType, customer.getPaymentType());
            values.put(DataBaseHelper.customer_email, customer.getEmail());
            values.put(DataBaseHelper.customer_address, customer.getAddress());
            values.put(DataBaseHelper.customer_status, customer.getStatus());
            values.put(DataBaseHelper.customer_minDeposit, customer.getMinDeposit());
            values.put(DataBaseHelper.customer_createDate, customer.getCreateDate());
            values.put(DataBaseHelper.customer_username, customer.getUsername());

            Long flag = database.insert(DataBaseHelper.TABLE_category, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public void DeleteCustomer(String id) {
        database.delete(DataBaseHelper.TABLE_customer, DataBaseHelper.customer_customerId + "=" + id, null);
        return;
    }
    public void ClearCustomer() {
        database.delete(DataBaseHelper.TABLE_customer, null , null);
        return;
    }
    public int UpdateCustomerId(Long customerId) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.customer_customerId, customerId);
        int numRowEffected = database.update(DataBaseHelper.TABLE_customer, values, DataBaseHelper.customer_customerId + "=?", new String[]{String.valueOf(0)});
        return numRowEffected;
    }
    public int Update(Customer customer) {
        ContentValues values = new ContentValues();
        if (customer.getFamily() != "")
            values.put(DataBaseHelper.customer_family, customer.getFamily());
        if (customer.getName() != "")
            values.put(DataBaseHelper.customer_name, customer.getName());
        if (customer.getCellPhone() != "")
            values.put(DataBaseHelper.customer_cellPhone, customer.getCellPhone());
        if (customer.getPhone() != "")
            values.put(DataBaseHelper.customer_phone, customer.getPhone());
        if (customer.getCustomerId() != -1)
            values.put(DataBaseHelper.customer_customerId, customer.getCustomerId());
        if (customer.getPassword() != "")
            values.put(DataBaseHelper.customer_password, customer.getPassword());
        if (customer.getPaymentType() != -1)
            values.put(DataBaseHelper.customer_paymentType, customer.getPaymentType());
        if (customer.getEmail() != "")
            values.put(DataBaseHelper.customer_email, customer.getEmail());
        if (customer.getAddress() != "")
            values.put(DataBaseHelper.customer_address, customer.getAddress());
        if (customer.getStatus() != -1)
            values.put(DataBaseHelper.customer_status, customer.getStatus());
        if (customer.getMinDeposit() != -1.0)
            values.put(DataBaseHelper.customer_minDeposit, customer.getMinDeposit());
        if (customer.getCreateDate() != "")
            values.put(DataBaseHelper.customer_createDate, customer.getCreateDate());
        if (customer.getUsername() != "")
            values.put(DataBaseHelper.customer_username, customer.getUsername());
        int numRowEffected = database.update(DataBaseHelper.TABLE_customer, values, DataBaseHelper.customer_customerId + "=?", new String[]{String.valueOf(customer.getCustomerId())});
        return numRowEffected;
    }
    public Customer GetSingleCustomer(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_customer, allColumns, DataBaseHelper.customer_customerId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Customer customer = new Customer();
        if (cursor.getCount() > 0)
            customer = CursorToCustomer(cursor);
        else
            customer.setCustomerId(Long.valueOf(-1));
        cursor.close();
        return customer;
    }
    public Customer CursorToCustomer(Cursor cursor) {
        Customer customer = new Customer();
        customer.setFamily(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_family)));
        customer.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_name)));
        customer.setCellPhone(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_cellPhone)));
        customer.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_phone)));
        customer.setCustomerId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.customer_customerId)));
        customer.setPassword(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_password)));
        customer.setPaymentType(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.customer_paymentType)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_email)));
        customer.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_address)));
        customer.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.customer_status)));
        customer.setMinDeposit(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.customer_minDeposit)));
        customer.setCreateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_createDate)));
        customer.setUsername(cursor.getString(cursor.getColumnIndex(DataBaseHelper.customer_username)));
        return customer;
    }


}

