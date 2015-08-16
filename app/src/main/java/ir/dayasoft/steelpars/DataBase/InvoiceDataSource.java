package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Invoice;


public class InvoiceDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public InvoiceDataSource(Context context) {
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
            DataBaseHelper.invoice_port
            , DataBaseHelper.invoice_address
            , DataBaseHelper.invoice_invoiceId
            , DataBaseHelper.invoice_price
            , DataBaseHelper.invoice_deliveryCost
            , DataBaseHelper.invoice_createDate
            , DataBaseHelper.invoice_paymentType
            , DataBaseHelper.invoice_deposit
            , DataBaseHelper.invoice_primaryInvoiceId
            , DataBaseHelper.invoice_serverInvoiceId
            , DataBaseHelper.invoice_FK_customerId
            , DataBaseHelper.invoice_status
            , DataBaseHelper.invoice_input
            , DataBaseHelper.invoice_paymentCode
    };

    public void DeleteInvoice(Long id) {
        database.delete(DataBaseHelper.TABLE_invoice, DataBaseHelper.invoice_invoiceId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearInvoice () {
        database.delete(DataBaseHelper.TABLE_invoice, null, null);
        return;
    }
    public Invoice GetSingleInvoice(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_invoice, allColumns, DataBaseHelper.invoice_invoiceId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Invoice invoice = new Invoice();
        if (cursor.getCount() > 0)
            invoice = CursorToInvoice(cursor);
        else
            invoice.setInvoiceId(Long.valueOf(-1));
        cursor.close();
        return invoice;
    }
    public Invoice GetLastInvoice() {
        Cursor cursor = database.rawQuery(" Select * From " +
                DataBaseHelper.TABLE_invoice +
                " order by " +
                DataBaseHelper.invoice_invoiceId + " desc limit 1", null);

        cursor.moveToFirst();
        Invoice invoice = new Invoice();
        if (cursor.getCount() > 0)
            invoice = CursorToInvoice(cursor);
        else
            invoice.setInvoiceId(Long.valueOf(-1));
        cursor.close();
        return invoice;
    }
    public List<Invoice> GetAllInvoice(String userId,int top) {
        List<Invoice> AllInvoice = new ArrayList<Invoice>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ; 
        String[] selectionArgs = {String.valueOf(1)};


        //Cursor cursor = database.query(DataBaseHelper.TABLE_invoice, allColumns, DataBaseHelper.invoice_FK_customerId + "=" +userId, null, null, null , DataBaseHelper.invoice_invoiceId + " desc" );
        Cursor cursor =database.rawQuery("select * from " + DataBaseHelper.TABLE_invoice + " where " + DataBaseHelper.invoice_FK_customerId + "=" + userId + " and " + DataBaseHelper.invoice_status + " != "
                + Invoice.Status_Pending  + " order by " + DataBaseHelper.invoice_invoiceId + " desc" + " limit " + top  , null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Invoice invoice = CursorToInvoice(cursor);

            AllInvoice.add(invoice);
            cursor.moveToNext();
        }
        cursor.close();
        return AllInvoice;
    }


    public int ChangeInvoiceStatus( Long invoiceId , int status) {
        ContentValues values = new ContentValues();


        values.put(DataBaseHelper.invoice_status, status);

        int numRowEffected = database.update(DataBaseHelper.TABLE_invoice, values, DataBaseHelper.invoice_invoiceId + "=?", new String[]{String.valueOf(invoiceId)});
        return numRowEffected;
    }

    public int ConfirmInvoiceSendToServer( Long invoiceId , Long serverInvoiceId) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.invoice_serverInvoiceId, serverInvoiceId);
        values.put(DataBaseHelper.invoice_status, Invoice.Status_Deliver);

        int numRowEffected = database.update(DataBaseHelper.TABLE_invoice, values, DataBaseHelper.invoice_invoiceId + "=?", new String[]{String.valueOf(invoiceId)});
        return numRowEffected;
    }

    public List<Invoice> GetAllInvoiceByStatus(int status,int situation) {
        List<Invoice> AllInvoice = new ArrayList<Invoice>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.rawQuery(" SELECT * FROM " +
                DataBaseHelper.TABLE_invoice +
                " WHERE " +
                DataBaseHelper.invoice_status +
                " = " +
                status
                + " and " +
                DataBaseHelper.invoice_input +
                " = " +
                situation
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Invoice invoice = CursorToInvoice(cursor);
            AllInvoice.add(invoice);
            cursor.moveToNext();
        }
        cursor.close();
        return AllInvoice;
    }


    public List<Invoice> GetAllInvoiceByInput(int input) {
        List<Invoice> AllInvoice = new ArrayList<Invoice>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.rawQuery(" SELECT * FROM " +
                DataBaseHelper.TABLE_invoice +
                " WHERE " +
                DataBaseHelper.invoice_input +
                " = " +
                input
                ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Invoice invoice = CursorToInvoice(cursor);
            AllInvoice.add(invoice);
            cursor.moveToNext();
        }
        cursor.close();
        return AllInvoice;
    }
    public Invoice InsertInvoice (Invoice invoice) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.invoice_port, invoice.getPort());
        values.put(DataBaseHelper.invoice_address, invoice.getAddress());
        values.put(DataBaseHelper.invoice_invoiceId, invoice.getInvoiceId());
        values.put(DataBaseHelper.invoice_price, invoice.getPrice());
        values.put(DataBaseHelper.invoice_deliveryCost, invoice.getDeliveryCost());
        values.put(DataBaseHelper.invoice_createDate, invoice.getCreateDate());
        values.put(DataBaseHelper.invoice_paymentType, invoice.getPaymentType());
        values.put(DataBaseHelper.invoice_deposit, invoice.getDeposit());
        values.put(DataBaseHelper.invoice_primaryInvoiceId, invoice.getPrimaryInvoiceId());
        values.put(DataBaseHelper.invoice_serverInvoiceId, invoice.getServerInvoiceId());
        values.put(DataBaseHelper.invoice_FK_customerId, invoice.getFK_customerId());
        values.put(DataBaseHelper.invoice_status, invoice.getStatus());
        values.put(DataBaseHelper.invoice_input, invoice.getInput());
        values.put(DataBaseHelper.invoice_paymentCode, invoice.getPaymentCode());

        long insertId = database.insert(DataBaseHelper.TABLE_invoice, null, values);
        invoice.setInvoiceId(insertId);
        return invoice;
    }
    public int InsertInvoice (List<Invoice> invoices) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Invoice invoice : invoices) {

            values.put(DataBaseHelper.invoice_port, invoice.getPort());
            values.put(DataBaseHelper.invoice_address, invoice.getAddress());
            values.put(DataBaseHelper.invoice_invoiceId, invoice.getInvoiceId());
            values.put(DataBaseHelper.invoice_price, invoice.getPrice());
            values.put(DataBaseHelper.invoice_deliveryCost, invoice.getDeliveryCost());
            values.put(DataBaseHelper.invoice_createDate, invoice.getCreateDate());
            values.put(DataBaseHelper.invoice_paymentType, invoice.getPaymentType());
            values.put(DataBaseHelper.invoice_deposit, invoice.getDeposit());
            values.put(DataBaseHelper.invoice_primaryInvoiceId, invoice.getPrimaryInvoiceId());
            values.put(DataBaseHelper.invoice_serverInvoiceId, invoice.getServerInvoiceId());
            values.put(DataBaseHelper.invoice_FK_customerId, invoice.getFK_customerId());
            values.put(DataBaseHelper.invoice_status, invoice.getStatus());
            values.put(DataBaseHelper.invoice_input, invoice.getInput());
            values.put(DataBaseHelper.invoice_paymentCode, invoice.getPaymentCode());

            Long flag = database.insert(DataBaseHelper.TABLE_invoice, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public int Update(Invoice invoice) {
        ContentValues values = new ContentValues();

            values.put(DataBaseHelper.invoice_status, invoice.getStatus());

        if (invoice.getDeliveryCost() != -1)
            values.put(DataBaseHelper.invoice_deliveryCost, invoice.getDeliveryCost());







             values.put(DataBaseHelper.invoice_price, invoice.getPrice());
             values.put(DataBaseHelper.invoice_invoiceId, invoice.getInvoiceId());
             values.put(DataBaseHelper.invoice_serverInvoiceId, invoice.getServerInvoiceId());

        int numRowEffected = database.update(DataBaseHelper.TABLE_invoice, values, DataBaseHelper.invoice_invoiceId + "=?", new String[]{String.valueOf(invoice.getInvoiceId())});
        return numRowEffected;
    }
    public Invoice CursorToInvoice(Cursor cursor) {
        Invoice invoice = new Invoice();
        invoice.setPort(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.invoice_port)));
        invoice.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseHelper.invoice_address)));
        invoice.setInvoiceId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoice_invoiceId)));
        invoice.setPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoice_price)));
        invoice.setDeliveryCost(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoice_deliveryCost)));
        invoice.setCreateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.invoice_createDate)));
        invoice.setPaymentType(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.invoice_paymentType)));
        invoice.setDeposit(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoice_deposit)));
        invoice.setPrimaryInvoiceId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoice_primaryInvoiceId)));
        invoice.setServerInvoiceId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoice_serverInvoiceId)));
        invoice.setFK_customerId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoice_FK_customerId)));
        invoice.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.invoice_status)));
        invoice.setInput(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.invoice_input)));
        invoice.setPaymentCode(cursor.getString(cursor.getColumnIndex(DataBaseHelper.invoice_paymentCode)));

        return invoice;
    }
}
