package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Category;


public class CategoryDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public CategoryDataSource(Context context) {
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
            DataBaseHelper.category_categoryId
            , DataBaseHelper.category_parentId
            , DataBaseHelper.category_level
            , DataBaseHelper.category_name
            , DataBaseHelper.category_imageUrl
            , DataBaseHelper.category_status
            , DataBaseHelper.category_updateDate
            , DataBaseHelper.category_FK_color
            , DataBaseHelper.category_icon

    };

    public List<Category> GetAllCategory() {
        List<Category> returnValue = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_category
                + " order by " + DataBaseHelper.category_order
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = CursorToCategory(cursor);
            returnValue.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return returnValue;
    }
    public Category GetSingleCategory(long categoryId) {
        Category returnValue = new Category();
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_category +
                " WHERE " +
                DataBaseHelper.category_categoryId +
                " = " +
                String.valueOf(categoryId)
                , null);
        cursor.moveToFirst();
        if (cursor.getCount() == 1)
            returnValue = CursorToCategory(cursor);
        else
            returnValue.setCategoryId(Long.valueOf(-1));
        cursor.close();
        return returnValue;
    }
    public List<Category> GetCategoryByParent(Long parentId) {
        List<Category> returnValue = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_category +
                " WHERE " +
                DataBaseHelper.category_parentId +
                " = " +
                String.valueOf(parentId)
                + " order by " + DataBaseHelper.category_order
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = CursorToCategory(cursor);
            returnValue.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return returnValue;
    }
    public List<Category> GetAllCategoryWithColor() {
        List<Category> returnValue = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT" +
                " * " +
                " FROM " +
                DataBaseHelper.TABLE_category +
                " Left Join " + DataBaseHelper.TABLE_categoryColor +
                " On " + DataBaseHelper.categoryColor_FK_categoryId + " = " + DataBaseHelper.category_categoryId +
                " Left Join " + DataBaseHelper.TABLE_color +
                " On " + DataBaseHelper.categoryColor_FK_colorId + " = " + DataBaseHelper.color_colorId +
                " where " +
                DataBaseHelper.categoryColor_order + " = 2 "
                + " order by " + DataBaseHelper.category_order
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = CursorToCategoryWithColor(cursor);
            returnValue.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return returnValue;
    }
    public int InsertCategory(List<Category> categories) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Category category : categories) {
            values.put(DataBaseHelper.category_categoryId, category.getCategoryId());
            values.put(DataBaseHelper.category_parentId, category.getParentId());
            values.put(DataBaseHelper.category_level, category.getLevel());
            values.put(DataBaseHelper.category_name, category.getName());
            values.put(DataBaseHelper.category_imageUrl, category.getImageUrl());
            values.put(DataBaseHelper.category_status, category.getStatus());
            values.put(DataBaseHelper.category_updateDate, category.getUpdateDate());
            values.put(DataBaseHelper.category_FK_color, category.getFK_color());
            values.put(DataBaseHelper.category_icon, category.getIcon());
            values.put(DataBaseHelper.category_order, category.getOrder());

            Long flag = database.insert(DataBaseHelper.TABLE_category, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public int InsertCategory(Category category) {
        ContentValues values = new ContentValues();
        int cnt = 0;

        values.put(DataBaseHelper.category_categoryId, category.getCategoryId());
        values.put(DataBaseHelper.category_parentId, category.getParentId());
        values.put(DataBaseHelper.category_level, category.getLevel());
        values.put(DataBaseHelper.category_name, category.getName());
        values.put(DataBaseHelper.category_imageUrl, category.getImageUrl());
        values.put(DataBaseHelper.category_status, category.getStatus());
        values.put(DataBaseHelper.category_updateDate, category.getUpdateDate());
        values.put(DataBaseHelper.category_FK_color, category.getFK_color());
        values.put(DataBaseHelper.category_icon, category.getIcon());
        values.put(DataBaseHelper.category_order, category.getOrder());

        Long flag = database.insert(DataBaseHelper.TABLE_category, null, values);

        if (flag > 0)
            cnt++;

        return cnt;
    }
    public void ClearCategory() {
        database.delete(DataBaseHelper.TABLE_category, null, null);
        return;
    }
    public void DeleteCategory(Long categoryId) {
        database.delete(DataBaseHelper.TABLE_category, DataBaseHelper.category_categoryId + "=" + Long.valueOf(categoryId), null);
        return;
    }
    public Category CursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setCategoryId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.category_categoryId)));
        category.setParentId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.category_parentId)));
        category.setLevel(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.category_level)));
        category.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_name)));
        category.setImageUrl(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_imageUrl)));
        category.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.category_status)));
        category.setUpdateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_updateDate)));
        category.setFK_color(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.category_FK_color)));
        category.setIcon(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_icon)));

        return category;
    }
    public Category CursorToCategoryWithColor(Cursor cursor) {
        Category category = new Category();

        category.setCategoryId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.category_categoryId)));
        category.setParentId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.category_parentId)));
        category.setLevel(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.category_level)));
        category.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_name)));
        category.setImageUrl(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_imageUrl)));
        category.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.category_status)));
        category.setUpdateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_updateDate)));
        category.setFK_color(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.category_FK_color)));
        category.setIcon(cursor.getString(cursor.getColumnIndex(DataBaseHelper.category_icon)));


        category.setColor(cursor.getString(cursor.getColumnIndex(DataBaseHelper.color_code)));

        return category;
    }
}