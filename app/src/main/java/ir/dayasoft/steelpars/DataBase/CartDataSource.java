package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Cart;


public class CartDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    public static final String Tag = "database.Cart";

    public CartDataSource(Context context) {
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
              DataBaseHelper.cart_cartId
            , DataBaseHelper.cart_FK_productId
            , DataBaseHelper.cart_count
            , DataBaseHelper.cart_order
            , DataBaseHelper.cart_status
            , DataBaseHelper.cart_createDate

    };

    public Cart InsertCart(Cart cart) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.cart_cartId, cart.getCartId());
        values.put(DataBaseHelper.cart_FK_productId, cart.getFK_productId());
        values.put(DataBaseHelper.cart_count, cart.getCount());
        values.put(DataBaseHelper.cart_order, cart.getOrder());
        values.put(DataBaseHelper.cart_status, cart.getStatus());
        values.put(DataBaseHelper.cart_createDate, cart.getCreateDate());


        long insertId = database.insert(DataBaseHelper.TABLE_cart, null, values);

        cart.setCartId(insertId);
        return cart;
    }
    public int InsertCart (List<Cart> carts) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Cart cart : carts) {
            values.put(DataBaseHelper.cart_status, cart.getStatus());
            values.put(DataBaseHelper.cart_createDate, cart.getCreateDate());
            values.put(DataBaseHelper.cart_order, cart.getOrder());
            values.put(DataBaseHelper.cart_cartId, cart.getCartId());
            values.put(DataBaseHelper.cart_FK_productId, cart.getFK_productId());
            values.put(DataBaseHelper.cart_count, cart.getCount());

            Long flag = database.insert(DataBaseHelper.TABLE_cart, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public void DeleteCart(Long id) {
        int i = database.delete(DataBaseHelper.TABLE_cart, DataBaseHelper.cart_cartId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearCart() {
        database.delete(DataBaseHelper.TABLE_cart, null, null);
        return;
    }
    public List<Cart> GetAllCart() {
        List<Cart> AllCart = new ArrayList<Cart>();

        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_cart +
                " INNER JOIN " +
                DataBaseHelper.TABLE_product +
                " ON " +
                DataBaseHelper.TABLE_cart + "." + DataBaseHelper.cart_FK_productId +
                " = " +
                DataBaseHelper.TABLE_product + "." + DataBaseHelper.product_productId +
                " ORDER BY " +
                DataBaseHelper.cart_order
                , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Cart cart = CursorToCartWithProductJoin(cursor);
            AllCart.add(cart);
            cursor.moveToNext();
        }
        cursor.close();
        return AllCart;
    }

    public List<Cart> GetAllCartTest() {
        List<Cart> AllCart = new ArrayList<Cart>();

        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_cart

                , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Cart cart = CursorToCartWithProductJoin(cursor);
            AllCart.add(cart);
            cursor.moveToNext();
        }
        cursor.close();
        return AllCart;
    }

    public Cart GetSingleCart(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_cart, allColumns, DataBaseHelper.cart_cartId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Cart cart = new Cart();
        if (cursor.getCount() > 0)
            cart = CursorToCart(cursor);
        else
            cart.setCartId(Long.valueOf(-1));
        cursor.close();
        return cart;
    }
    public Cart GetCartByFK_productId(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_cart, allColumns, DataBaseHelper.cart_FK_productId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Cart cart = new Cart();
        if (cursor.getCount() > 0)
            cart = CursorToCart(cursor);
        else
            cart.setCartId(Long.valueOf(-1));
        cursor.close();
        return cart;
    }
    public String GetCartSum() {
        Cursor cursor = database.rawQuery("SELECT" +
                " SUM( "+
                DataBaseHelper.product_price + "*" + DataBaseHelper.cart_count +
                ") as summation" +
                " FROM " +
                DataBaseHelper.TABLE_cart +
                " INNER JOIN " +
                DataBaseHelper.TABLE_product +
                " ON " +
                DataBaseHelper.TABLE_cart + "." + DataBaseHelper.cart_FK_productId +
                " = " +
                DataBaseHelper.TABLE_product + "." + DataBaseHelper.product_productId +
                " ORDER BY " +
                DataBaseHelper.cart_order
                , null);
        cursor.moveToFirst();

        String summation = cursor.getString(cursor.getColumnIndex("summation"));

        cursor.close();
        return summation;
    }
    public Cart GetLastCart() {
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_cart +
                " ORDER BY " +
                DataBaseHelper.cart_cartId +
                " desc " +
                " limit 1"
                , null);
        cursor.moveToFirst();
        Cart cart = new Cart();
        if (cursor.getCount() > 0)
            cart = CursorToCart(cursor);
        else
            cart.setCartId(Long.valueOf(-1));
        cursor.close();
        return cart;
    }
    public int CartCount() {
        int returnValue;
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_cart +
                " INNER JOIN " +
                DataBaseHelper.TABLE_product +
                " ON " +
                DataBaseHelper.TABLE_cart + "." + DataBaseHelper.cart_FK_productId +
                " = " +
                DataBaseHelper.TABLE_product + "." + DataBaseHelper.product_productId +
                " ORDER BY " +
                DataBaseHelper.cart_order
                , null);

        cursor.moveToFirst();
        returnValue = cursor.getCount();
        cursor.close();
        return returnValue;
    }



    public int Update(Cart cart) {
        ContentValues values = new ContentValues();
        if (cart.getStatus() != -1)
            values.put(DataBaseHelper.cart_status, cart.getStatus());
        if (cart.getCreateDate() != "")
            values.put(DataBaseHelper.cart_createDate, cart.getCreateDate());
        if (cart.getOrder() != -1)
            values.put(DataBaseHelper.cart_order, cart.getOrder());
        if (cart.getCartId() != -1)
            values.put(DataBaseHelper.cart_cartId, cart.getCartId());
        if (cart.getFK_productId() != -1)
            values.put(DataBaseHelper.cart_FK_productId, cart.getFK_productId());
        if (cart.getCount() != -1.0)
            values.put(DataBaseHelper.cart_count, cart.getCount());
        int numRowEffected = database.update(DataBaseHelper.TABLE_cart, values, DataBaseHelper.cart_cartId + "=?", new String[]{String.valueOf(cart.getCartId())});
        return numRowEffected;
    }
    public Cart CursorToCart(Cursor cursor) {
        Cart cart = new Cart();
        cart.setCartId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.cart_cartId)));
        cart.setFK_productId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.cart_FK_productId)));
        cart.setCount(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.cart_count)));
        cart.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.cart_status)));
        cart.setCreateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.cart_createDate)));
        cart.setOrder(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.cart_order)));
        return cart;
    }
    public Cart CursorToCartWithProductJoin(Cursor cursor) {
        Cart cart = new Cart();
        cart.setCartId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.cart_cartId)));
        cart.setFK_productId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.cart_FK_productId)));
        cart.setCount(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.cart_count)));
        cart.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.cart_status)));
        cart.setCreateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.cart_createDate)));
        cart.setOrder(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.cart_order)));


        cart.setProductName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_name)));
        cart.setProductUnit(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_unitName)));
        cart.setProductPrice(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_price)));
        cart.setProductImage(cursor.getString(cursor.getColumnIndex(DataBaseHelper.product_imageUrl)));

        return cart;
    }

}


