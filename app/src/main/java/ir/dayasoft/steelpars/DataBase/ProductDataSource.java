package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Core.Constants;


public class ProductDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public ProductDataSource(Context context) {
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
            DataBaseHelper.product_description
            , DataBaseHelper.product_unitName
            , DataBaseHelper.product_price
            , DataBaseHelper.product_imageUrl
            , DataBaseHelper.product_updateDate
            , DataBaseHelper.product_FK_categoryId
            , DataBaseHelper.product_discountPrice
            , DataBaseHelper.product_productId
            , DataBaseHelper.product_name
            , DataBaseHelper.product_unitCountability
            , DataBaseHelper.product_priceAgent
    };

    public Product InsertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.product_description, product.getDescription());
        values.put(DataBaseHelper.product_unitName, product.getUnitName());
        values.put(DataBaseHelper.product_price, product.getPrice());
        values.put(DataBaseHelper.product_imageUrl, product.getImageUrl());
        values.put(DataBaseHelper.product_updateDate, product.getUpdateDate());
        values.put(DataBaseHelper.product_FK_categoryId, product.getFK_categoryId());
        values.put(DataBaseHelper.product_discountPrice, product.getDiscountPrice());
        values.put(DataBaseHelper.product_productId, product.getProductId());
        values.put(DataBaseHelper.product_name, product.getName());
        values.put(DataBaseHelper.product_unitCountability, product.getUnitCountability());
        values.put(DataBaseHelper.product_priceAgent, product.getPriceAgent());
        long insertId = database.insert(DataBaseHelper.TABLE_product, null, values);
        product.setProductId(insertId);
        return product;
    }
    public int InsertProduct (List<Product> products) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Product product : products) {

            values.put(DataBaseHelper.product_description, product.getDescription());
            values.put(DataBaseHelper.product_unitName, product.getUnitName());
            values.put(DataBaseHelper.product_price, product.getPrice());
            values.put(DataBaseHelper.product_imageUrl, product.getImageUrl());
            values.put(DataBaseHelper.product_updateDate, product.getUpdateDate());
            values.put(DataBaseHelper.product_FK_categoryId, product.getFK_categoryId());
            values.put(DataBaseHelper.product_discountPrice, product.getDiscountPrice());
            values.put(DataBaseHelper.product_productId, product.getProductId());
            values.put(DataBaseHelper.product_name, product.getName());
            values.put(DataBaseHelper.product_unitCountability, product.getUnitCountability());
            values.put(DataBaseHelper.product_priceAgent, product.getPriceAgent());

            Long flag = database.insert(DataBaseHelper.TABLE_product, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public void DeleteProduct(Long id) {
        database.delete(DataBaseHelper.TABLE_product, DataBaseHelper.product_productId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearProduct () {
        database.delete(DataBaseHelper.TABLE_product, null, null);
        return;
    }
    public Product GetSingleProduct(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_product, allColumns, DataBaseHelper.product_productId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Product product = new Product();
        if (cursor.getCount() > 0)
            product = CursorToProduct(cursor);
        else
            product.setProductId(Long.valueOf(-1));
        cursor.close();
        return product;
    }
    public Product GetLastProductUpdate() {
        Cursor cursor = database.rawQuery("SELECT " +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_product +
                " Order By " +
                DataBaseHelper.product_updateDate + " desc " +
                " limit 1 "
                , null);
        cursor.moveToFirst();
        Product product = new Product();
        if (cursor.getCount() > 0)
            product = CursorToProduct(cursor);
        else {
            product.setProductId(Long.valueOf(-1));
            product.setUpdateDate(Constants.DateOfOrigin);
        }
        cursor.close();
        return product;
    }
    public List<Product> GetAllProduct() {
        List<Product> AllProduct = new ArrayList<Product>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ; 
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.query(DataBaseHelper.TABLE_product, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = CursorToProduct(cursor);
            AllProduct.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return AllProduct;
    }
    public List<Product> GetProductByCategoryId(Long categoryId, Long colorId) {
        List<Product> AllProduct = new ArrayList<Product>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        Cursor cursor = database.rawQuery("SELECT " +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_product + " Inner Join " + DataBaseHelper.TABLE_color +
                " On " +
                String.valueOf(colorId) + " = " + DataBaseHelper.color_colorId +
                " WHERE " +
                DataBaseHelper.product_FK_categoryId +
                " = " +
                String.valueOf(categoryId)
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = CursorToProductWithColor(cursor);
            AllProduct.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return AllProduct;
    }
    public List<Product> GetProductByCategoryIdWithLeftJoin(Long categoryId) {
        List<Product> AllProduct = new ArrayList<Product>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        Cursor cursor = database.rawQuery("SELECT " +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_product +
                " LEFT JOIN " +
                DataBaseHelper.TABLE_cart +
                " On " +
                DataBaseHelper.TABLE_product +"." +DataBaseHelper.product_productId +
                " = " +
                DataBaseHelper.TABLE_cart +"." +DataBaseHelper.cart_FK_productId +
                " WHERE " +
                DataBaseHelper.product_FK_categoryId +
                " = " +
                String.valueOf(categoryId)
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = CursorToProductWithCart(cursor);
            AllProduct.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return AllProduct;
    }


    public int Update(Product product) {
        ContentValues values = new ContentValues();
        if (product.getDescription() != "")
            values.put(DataBaseHelper.product_description, product.getDescription());
        if (product.getUnitName() != "")
            values.put(DataBaseHelper.product_unitName, product.getUnitName());
        if (product.getPrice() != -1.0)
            values.put(DataBaseHelper.product_price, product.getPrice());
        if (product.getImageUrl() != "")
            values.put(DataBaseHelper.product_imageUrl, product.getImageUrl());
        if (product.getUpdateDate() != "")
            values.put(DataBaseHelper.product_updateDate, product.getUpdateDate());
        if (product.getFK_categoryId() != -1)
            values.put(DataBaseHelper.product_FK_categoryId, product.getFK_categoryId());
        if (product.getDiscountPrice() != -1.0)
            values.put(DataBaseHelper.product_discountPrice, product.getDiscountPrice());
        if (product.getProductId() != -1)
            values.put(DataBaseHelper.product_productId, product.getProductId());
        if (product.getName() != "")
            values.put(DataBaseHelper.product_name, product.getName());
        if (product.getUnitCountability() != -1)
            values.put(DataBaseHelper.product_unitCountability, product.getUnitCountability());
        if (product.getPriceAgent() != "")
            values.put(DataBaseHelper.product_priceAgent, product.getPriceAgent());
        int numRowEffected = database.update(DataBaseHelper.TABLE_product, values, DataBaseHelper.product_productId + "=?", new String[]{String.valueOf(product.getProductId())});
        return numRowEffected;
    }
    public Product CursorToProduct(Cursor cursor) {
        Product product = new Product();
        product.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_description)));
        product.setUnitName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_unitName)));
        product.setPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.product_price)));
        product.setImageUrl(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_imageUrl)));
        product.setUpdateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_updateDate)));
        product.setFK_categoryId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.product_FK_categoryId)));
        product.setDiscountPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.product_discountPrice)));
        product.setProductId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.product_productId)));
        product.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_name)));
        product.setUnitCountability(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.product_unitCountability)));
        product.setPriceAgent(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_priceAgent)));




        return product;
    }
    public Product CursorToProductWithCart(Cursor cursor) {
        Product product = new Product();
        product.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_description)));
        product.setUnitName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_unitName)));
        product.setPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.product_price)));
        product.setImageUrl(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_imageUrl)));
        product.setUpdateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_updateDate)));
        product.setFK_categoryId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.product_FK_categoryId)));
        product.setDiscountPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.product_discountPrice)));
        product.setProductId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.product_productId)));
        product.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_name)));
        product.setUnitCountability(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.product_unitCountability)));
        product.setPriceAgent(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_priceAgent)));

        if(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.cart_count)) > 0)
            product.setInCart(true);
        else
            product.setInCart(false);

        int i = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.cart_count));


        return product;
    }
    public Product CursorToProductWithColor(Cursor cursor) {
        Product product = new Product();
        product.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_description)));
        product.setUnitName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_unitName)));
        product.setPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.product_price)));
        product.setImageUrl(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_imageUrl)));
        product.setUpdateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_updateDate)));
        product.setFK_categoryId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.product_FK_categoryId)));
        product.setDiscountPrice(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.product_discountPrice)));
        product.setProductId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.product_productId)));
        product.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_name)));
        product.setUnitCountability(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.product_unitCountability)));
        product.setPriceAgent(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_priceAgent)));

//        product.setColorFirst(cursor.getString(cursor.getColumnIndex(DataBaseHelper.color_codeFirst)));
//        product.setColorSecond(cursor.getString(cursor.getColumnIndex(DataBaseHelper.color_codeSecond)));
//        product.setColorThird(cursor.getString(cursor.getColumnIndex(DataBaseHelper.color_codeThird)));


        return product;
    }



}
