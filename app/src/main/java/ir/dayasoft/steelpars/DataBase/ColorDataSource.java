package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Color;


public class ColorDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public ColorDataSource(Context context) {
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
              DataBaseHelper.color_colorId
            , DataBaseHelper.color_code
    };


    public void DeleteColor(Long id) {
        database.delete(DataBaseHelper.TABLE_color, DataBaseHelper.color_colorId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearColor() {
        database.delete(DataBaseHelper.TABLE_color, null, null);
        return;
    }
    public Color GetSingleColor(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_color, allColumns, DataBaseHelper.color_colorId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Color color= new Color();
        if (cursor.getCount() > 0)
            color = CursorToColor(cursor);
        else
            color.setColorId(Long.valueOf(-1));
        cursor.close();
        return color;
    }
    public List<Color> GetAllColor() {
        List<Color> AllColor = new ArrayList<Color>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_color, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Color color = CursorToColor(cursor);
            AllColor.add(color);
            cursor.moveToNext();
        }
        cursor.close();
        return AllColor;
    }
    public List<Color> GetColorByCategoryId(Long categoryId) {
        List<Color> AllColor = new ArrayList<Color>();
        Cursor cursor = database.rawQuery(" Select " +
                " * " +
                " From " +
                DataBaseHelper.TABLE_color + " Inner Join " + DataBaseHelper.TABLE_categoryColor +
                    " On " + DataBaseHelper.color_colorId + " = " + DataBaseHelper.categoryColor_FK_colorId +
                " Where " + DataBaseHelper.categoryColor_FK_categoryId + " = " + String.valueOf(categoryId) +
                " Order by " + DataBaseHelper.categoryColor_order
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Color color = CursorToColor(cursor);
            AllColor.add(color);
            cursor.moveToNext();
        }
        cursor.close();
        return AllColor;
    }
    public Color InsertColor(Color color) {
        ContentValues values = new ContentValues();


        values.put(DataBaseHelper.color_colorId , color.getColorId ());
        values.put(DataBaseHelper.color_code , color.getCode ());


        long insertId = database.insert(DataBaseHelper.TABLE_color, null, values);
        color.setColorId(insertId);
        return color;
    }
    public int InsertColor(List<Color> colorList) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Color color: colorList) {

            values.put(DataBaseHelper.color_colorId , color.getColorId ());
            values.put(DataBaseHelper.color_code , color.getCode ());

            Long flag = database.insert(DataBaseHelper.TABLE_color, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }

    public Color CursorToColor(Cursor cursor) {

        Color color = new Color();

        color.setColorId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.color_colorId)));
        color.setCode(cursor.getString(cursor.getColumnIndex(DataBaseHelper.color_code)));


        return color;
    }
}
