package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.City;


public class CityDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public CityDataSource(Context context) {
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
              DataBaseHelper.city_cityId
            , DataBaseHelper.city_name
            , DataBaseHelper.city_cost
    };


    public void DeleteCity(Long id) {
        database.delete(DataBaseHelper.TABLE_city, DataBaseHelper.city_cityId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearCity() {
        database.delete(DataBaseHelper.TABLE_city, null, null);
        return;
    }
    public City GetSingleCity(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_city, allColumns, DataBaseHelper.city_cityId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        City city= new City();
        if (cursor.getCount() > 0)
            city = CursorToCity(cursor);
        else
            city.setCityId(Long.valueOf(-1));
        cursor.close();
        return city;
    }
    public List<City> GetAllCity() {
        List<City> AllColor = new ArrayList<City>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_city, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            City city = CursorToCity(cursor);
            AllColor.add(city);
            cursor.moveToNext();
        }
        cursor.close();
        return AllColor;
    }
    public City InsertCity(City city) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.city_cityId , city.getCityId ());
        values.put(DataBaseHelper.city_name , city.getName ());
        values.put(DataBaseHelper.city_cost, city.getCost());


        long insertId = database.insert(DataBaseHelper.TABLE_city, null, values);
        city.setCityId(insertId);
        return city;
    }
    public int InsertCity (List<City> cityList) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (City city: cityList) {

            values.put(DataBaseHelper.city_cityId , city.getCityId ());
            values.put(DataBaseHelper.city_name , city.getName ());
            values.put(DataBaseHelper.city_cost, city.getCost());


            Long flag = database.insert(DataBaseHelper.TABLE_city, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }

    public City CursorToCity(Cursor cursor) {

        City city = new City();

        city.setCityId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.city_cityId)));
        city.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.city_name)));
        city.setCost(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.city_cost)));


        return city;
    }
}
