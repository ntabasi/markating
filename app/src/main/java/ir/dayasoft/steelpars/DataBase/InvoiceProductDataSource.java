package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.InvoiceProduct;


public class InvoiceProductDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public InvoiceProductDataSource(Context context) {
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
            DataBaseHelper.invoiceProduct_invoiceProductId
            , DataBaseHelper.invoiceProduct_price
            , DataBaseHelper.invoiceProduct_FK_invoiceId
            , DataBaseHelper.invoiceProduct_FK_productId
            , DataBaseHelper.invoiceProduct_count
            , DataBaseHelper.invoiceProduct_order
    };


    public InvoiceProduct InsertInvoiceProduct (InvoiceProduct invoiceProduct) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.invoiceProduct_invoiceProductId, invoiceProduct.getInvoiceProductId());
        values.put(DataBaseHelper.invoiceProduct_price, invoiceProduct.getPrice());
        values.put(DataBaseHelper.invoiceProduct_FK_productId, invoiceProduct.getFK_productId());
        values.put(DataBaseHelper.invoiceProduct_FK_invoiceId, invoiceProduct.getFK_invoiceId());
        values.put(DataBaseHelper.invoiceProduct_count, invoiceProduct.getCount());
        values.put(DataBaseHelper.invoiceProduct_order, invoiceProduct.getOrder());
        long insertId = database.insert(DataBaseHelper.TABLE_invoiceProduct, null, values);
        invoiceProduct.setInvoiceProductId(insertId);
        return invoiceProduct;
    }
    public int InsertInvoiceProduct (List<InvoiceProduct> invoiceProducts) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (InvoiceProduct invoiceProduct : invoiceProducts) {

            values.put(DataBaseHelper.invoiceProduct_invoiceProductId, invoiceProduct.getInvoiceProductId());
            values.put(DataBaseHelper.invoiceProduct_price, invoiceProduct.getPrice());
            values.put(DataBaseHelper.invoiceProduct_FK_productId, invoiceProduct.getFK_productId());
            values.put(DataBaseHelper.invoiceProduct_FK_invoiceId, invoiceProduct.getFK_invoiceId());
            values.put(DataBaseHelper.invoiceProduct_count, invoiceProduct.getCount());
            values.put(DataBaseHelper.invoiceProduct_order, invoiceProduct.getOrder());

            Long flag = database.insert(DataBaseHelper.TABLE_invoiceProduct, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public void DeleteInvoiceProduct(String id) {
        database.delete(DataBaseHelper.TABLE_invoiceProduct, DataBaseHelper.invoiceProduct_invoiceProductId + "=" + id, null);
        return;
    }
    public void DeleteInvoiceProductBaseInvoiceId(String invoiceId) {
        database.delete(DataBaseHelper.TABLE_invoiceProduct, DataBaseHelper.invoiceProduct_FK_invoiceId + "=" + invoiceId, null);
        return;
    }
    public void ClearInvoiceProduct() {
        database.delete(DataBaseHelper.TABLE_invoiceProduct, DataBaseHelper.invoiceProduct_invoiceProductId, null);
        return;
    }
    public InvoiceProduct GetSingleInvoiceProduct(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_invoiceProduct, allColumns, DataBaseHelper.invoiceProduct_invoiceProductId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        InvoiceProduct invoiceProduct = new InvoiceProduct();
        if (cursor.getCount() > 0)
            invoiceProduct = CursorToInvoiceProduct(cursor);
        else
            invoiceProduct.setInvoiceProductId(Long.valueOf(-1));
        cursor.close();
        return invoiceProduct;
    }
    public List<InvoiceProduct> GetAllInvoiceProduct() {
        List<InvoiceProduct> AllInvoiceProduct = new ArrayList<InvoiceProduct>();
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.query(DataBaseHelper.TABLE_invoiceProduct, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            InvoiceProduct invoiceProduct = CursorToInvoiceProduct(cursor);
            AllInvoiceProduct.add(invoiceProduct);
            cursor.moveToNext();
        }
        cursor.close();
        return AllInvoiceProduct;
    }
    public List<InvoiceProduct> GetInvoiceProductByInvoiceId(Long fk_invoiceId) {
        List<InvoiceProduct> AllInvoiceProduct = new ArrayList<InvoiceProduct>();
        String[] selectionArgs = {String.valueOf(1)};

        Cursor cursor = database.rawQuery("" +
                " Select * From " +
                DataBaseHelper.TABLE_invoiceProduct +
                " Inner Join " +
                DataBaseHelper.TABLE_product +
                " On " +
                DataBaseHelper.TABLE_invoiceProduct + "." + DataBaseHelper.invoiceProduct_FK_productId +
                " = " +
                DataBaseHelper.TABLE_product + "." + DataBaseHelper.product_productId +
                " Where " +
                DataBaseHelper.invoiceProduct_FK_invoiceId +
                " = " +
                String.valueOf(fk_invoiceId) ,null);

/*        cursor = database.rawQuery("" +
                " Select * From " +
                DataBaseHelper.TABLE_invoiceProduct +
                " Where " +
                DataBaseHelper.invoiceProduct_FK_invoiceId +
                " = " +
                String.valueOf(fk_invoiceId) ,null);*/
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            InvoiceProduct invoiceProduct = CursorToInvoiceProductWithProduct(cursor);
            AllInvoiceProduct.add(invoiceProduct);
            cursor.moveToNext();
        }
        cursor.close();
        return AllInvoiceProduct;
    }
    public int GetCountByInvoice(Long fk_invoiceId) {
        int returnValue = 0;
        Cursor cursor = database.rawQuery("" +
                " Select Count(*) count From " +
                DataBaseHelper.TABLE_invoiceProduct +
                " Where " +
                DataBaseHelper.invoiceProduct_FK_invoiceId +
                " = " +
                String.valueOf(fk_invoiceId) ,null);

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            returnValue =  Integer.valueOf(cursor.getString(cursor.getColumnIndex("count")));
        }
        cursor.close();
        return returnValue;
    }
    public double GetSumInvoice(Long fk_invoiceId) {
        double returnValue = 0;
        Cursor cursor = database.rawQuery("" +
                " Select Sum("+
                DataBaseHelper.invoiceProduct_price + "*" + DataBaseHelper.invoiceProduct_count +
                ") sumPrice From " +
                DataBaseHelper.TABLE_invoiceProduct +
                " Where " +
                DataBaseHelper.invoiceProduct_FK_invoiceId +
                " = " +
                String.valueOf(fk_invoiceId) ,null);

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            returnValue =  cursor.getDouble(cursor.getColumnIndex("sumPrice"));
        }
        cursor.close();
        return returnValue;
    }
    public int Update(InvoiceProduct invoiceProduct) {
        ContentValues values = new ContentValues();
        if (invoiceProduct.getInvoiceProductId() != -1)
            values.put(DataBaseHelper.invoiceProduct_invoiceProductId, invoiceProduct.getInvoiceProductId());
        if (invoiceProduct.getPrice() != -1.0)
            values.put(DataBaseHelper.invoiceProduct_price, invoiceProduct.getPrice());
        if (invoiceProduct.getFK_productId() != -1)
            values.put(DataBaseHelper.invoiceProduct_FK_productId, invoiceProduct.getFK_productId());
        if (invoiceProduct.getCount() != -1.0)
            values.put(DataBaseHelper.invoiceProduct_count, invoiceProduct.getCount());
        if (invoiceProduct.getOrder() != -1)
            values.put(DataBaseHelper.invoiceProduct_order, invoiceProduct.getOrder());
        int numRowEffected = database.update(DataBaseHelper.TABLE_invoiceProduct, values, DataBaseHelper.invoiceProduct_invoiceProductId + "=?", new String[]{String.valueOf(invoiceProduct.getInvoiceProductId())});
        return numRowEffected;
    }
    public InvoiceProduct CursorToInvoiceProduct(Cursor cursor) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setInvoiceProductId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_invoiceProductId)));
        invoiceProduct.setPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_price)));
        invoiceProduct.setFK_productId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_FK_productId)));
        invoiceProduct.setFK_invoiceId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_FK_invoiceId)));
        invoiceProduct.setCount(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_count)));
        invoiceProduct.setOrder(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_order)));

        return invoiceProduct;
    }
    public InvoiceProduct CursorToInvoiceProductWithProduct(Cursor cursor) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setInvoiceProductId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_invoiceProductId)));
        invoiceProduct.setPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_price)));
        invoiceProduct.setFK_productId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_FK_productId)));
        invoiceProduct.setFK_invoiceId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_FK_invoiceId)));
        invoiceProduct.setCount(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_count)));
        invoiceProduct.setOrder(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.invoiceProduct_order)));

        invoiceProduct.setProductName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_name)));
        invoiceProduct.setProductUnitName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_unitName)));

        return invoiceProduct;
    }

}
