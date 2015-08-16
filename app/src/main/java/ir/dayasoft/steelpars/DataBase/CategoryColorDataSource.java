package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.CategoryColor;


public class CategoryColorDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public CategoryColorDataSource(Context context) {
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
              DataBaseHelper.categoryColor_categoryColorId
            , DataBaseHelper.categoryColor_FK_categoryId
            , DataBaseHelper.categoryColor_FK_colorId
            , DataBaseHelper.categoryColor_order
    };


    public void DeleteColor(Long id) {
        database.delete(DataBaseHelper.TABLE_categoryColor, DataBaseHelper.categoryColor_categoryColorId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearCategoryColor() {
        database.delete(DataBaseHelper.TABLE_categoryColor, null, null);
        return;
    }
    public CategoryColor GetSingleCategoryColor(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_categoryColor, allColumns, DataBaseHelper.categoryColor_categoryColorId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        CategoryColor categoryColor= new CategoryColor();
        if (cursor.getCount() > 0)
            categoryColor = CursorToCategoryColor(cursor);
        else
            categoryColor.setCategoryColorId(Long.valueOf(-1));
        cursor.close();
        return categoryColor;
    }
    public List<CategoryColor> GetAllCategoryColor() {
        List<CategoryColor> allCategoryColor = new ArrayList<CategoryColor>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_categoryColor, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CategoryColor categoryColor = CursorToCategoryColor(cursor);
            allCategoryColor.add(categoryColor);
            cursor.moveToNext();
        }
        cursor.close();
        return allCategoryColor;
    }
    public CategoryColor InsertColor(CategoryColor categoryColor) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.categoryColor_categoryColorId , categoryColor.getCategoryColorId());
        values.put(DataBaseHelper.categoryColor_FK_categoryId , categoryColor.getFK_categoryId());
        values.put(DataBaseHelper.categoryColor_FK_colorId , categoryColor.getFK_colorId());
        values.put(DataBaseHelper.categoryColor_order , categoryColor.getOrder());


        long insertId = database.insert(DataBaseHelper.TABLE_categoryColor, null, values);
        categoryColor.setCategoryColorId(insertId);
        return categoryColor;
    }
    public int InsertCategoryColor(List<CategoryColor> categoryColors) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (CategoryColor categoryColor: categoryColors) {

            values.put(DataBaseHelper.categoryColor_categoryColorId , categoryColor.getCategoryColorId());
            values.put(DataBaseHelper.categoryColor_FK_categoryId , categoryColor.getFK_categoryId());
            values.put(DataBaseHelper.categoryColor_FK_colorId , categoryColor.getFK_colorId());
            values.put(DataBaseHelper.categoryColor_order , categoryColor.getOrder());


            Long flag = database.insert(DataBaseHelper.TABLE_categoryColor, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }

    public CategoryColor CursorToCategoryColor(Cursor cursor) {

        CategoryColor categoryColor = new CategoryColor();

        categoryColor.setCategoryColorId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.categoryColor_categoryColorId)));
        categoryColor.setFK_categoryId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.categoryColor_FK_categoryId)));
        categoryColor.setFK_colorId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.categoryColor_FK_colorId)));
        categoryColor.setOrder(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.categoryColor_order)));


        return categoryColor;
    }
}
