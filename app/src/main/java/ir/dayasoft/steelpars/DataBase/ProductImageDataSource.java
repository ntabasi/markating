package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.ProductImage;


public class ProductImageDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public ProductImageDataSource(Context context) {
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
            DataBaseHelper.productImage_productImageId
            , DataBaseHelper.productImage_imageUrl
            , DataBaseHelper.productImage_FK_productId
            , DataBaseHelper.productImage_FK_imageId
    };

    public ProductImage InsertProductImage (ProductImage productImage) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.productImage_productImageId, productImage.getProductImageId());
        values.put(DataBaseHelper.productImage_imageUrl, productImage.getImageUrl());
        values.put(DataBaseHelper.productImage_FK_productId, productImage.getFK_productId());
        values.put(DataBaseHelper.productImage_FK_imageId, productImage.getFK_imageId());
        long insertId = database.insert(DataBaseHelper.TABLE_productImage, null, values);
        productImage.setProductImageId(insertId);
        return productImage;
    }
    public int InsertProductImage (List<ProductImage> productImages) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (ProductImage productImage : productImages) {

            values.put(DataBaseHelper.productImage_productImageId, productImage.getProductImageId());
            values.put(DataBaseHelper.productImage_imageUrl, productImage.getImageUrl());
            values.put(DataBaseHelper.productImage_FK_productId, productImage.getFK_productId());
            values.put(DataBaseHelper.productImage_FK_imageId, productImage.getFK_imageId());

            Long flag = database.insert(DataBaseHelper.TABLE_productImage, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public void DeleteProductImage(Long id) {
        database.delete(DataBaseHelper.TABLE_productImage, DataBaseHelper.productImage_productImageId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearProductImage() {
        database.delete(DataBaseHelper.TABLE_productImage,null, null);
        return;
    }
    public ProductImage GetSingleProductImage(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_productImage, allColumns, DataBaseHelper.productImage_productImageId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        ProductImage productImage = new ProductImage();
        if (cursor.getCount() > 0)
            productImage = CursorToProductImage(cursor);
        else
            productImage.setProductImageId(Long.valueOf(-1));
        cursor.close();
        return productImage;
    }
    public List<ProductImage> GetProductImageByProductId(Long productId) {
        List<ProductImage> AllProductImage = new ArrayList<ProductImage>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ; 
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.query(DataBaseHelper.TABLE_productImage, allColumns,
                DataBaseHelper.productImage_FK_productId + " = "+ String.valueOf(productId),
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductImage productImage = CursorToProductImage(cursor);
            AllProductImage.add(productImage);
            cursor.moveToNext();
        }
        cursor.close();
        return AllProductImage;
    }
    public List<ProductImage> GetAllProductImage() {
        List<ProductImage> AllProductImage = new ArrayList<ProductImage>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.query(DataBaseHelper.TABLE_productImage, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductImage productImage = CursorToProductImage(cursor);
            AllProductImage.add(productImage);
            cursor.moveToNext();
        }
        cursor.close();
        return AllProductImage;
    }
    public int Update(ProductImage productImage) {
        ContentValues values = new ContentValues();
        if (productImage.getProductImageId() != -1)
            values.put(DataBaseHelper.productImage_productImageId, productImage.getProductImageId());
        if (productImage.getImageUrl() != "")
            values.put(DataBaseHelper.productImage_imageUrl, productImage.getImageUrl());
        if (productImage.getFK_productId() != -1)
            values.put(DataBaseHelper.productImage_FK_productId, productImage.getFK_productId());
        if (productImage.getFK_imageId() != -1)
            values.put(DataBaseHelper.productImage_FK_imageId, productImage.getFK_imageId());
        int numRowEffected = database.update(DataBaseHelper.TABLE_productImage, values, DataBaseHelper.productImage_productImageId + "=?", new String[]{String.valueOf(productImage.getProductImageId())});
        return numRowEffected;
    }
    public ProductImage CursorToProductImage(Cursor cursor) {
        ProductImage productImage = new ProductImage();
        productImage.setProductImageId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.productImage_productImageId)));
        productImage.setImageUrl(cursor.getString(cursor.getColumnIndex(DataBaseHelper.productImage_imageUrl)));
        productImage.setFK_productId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.productImage_FK_productId)));
        productImage.setFK_imageId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.productImage_FK_imageId)));
        return productImage;
    }

}