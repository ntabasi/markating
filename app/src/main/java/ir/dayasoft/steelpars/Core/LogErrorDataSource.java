package ir.dayasoft.steelpars.Core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.DataBase.DataBaseHelper;


public class LogErrorDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public LogErrorDataSource(Context context) {
        dbHelper =  new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
        database.close();
    }


    private String[] allColumns = {
             DataBaseHelper.logError_applicationName
            ,DataBaseHelper.logError_deviceSn
            ,DataBaseHelper.logError_createDate
            ,DataBaseHelper.logError_errorLocation
            ,DataBaseHelper.logError_errorMsg
            ,DataBaseHelper.logError_logErrorId
            ,DataBaseHelper.logError_senT
    };

    public LogError InsertLogError ( LogError logError) {
        ContentValues values = new ContentValues();
        values.put( DataBaseHelper.logError_applicationName ,logError.getApplicationName());
        values.put( DataBaseHelper.logError_deviceSn , logError.getDeviceSn());
        values.put( DataBaseHelper.logError_createDate , logError.getCreateDate());
        values.put( DataBaseHelper.logError_errorLocation , logError.getErrorLocation());
        values.put( DataBaseHelper.logError_errorMsg , logError.getErrorMsg() );
        values.put( DataBaseHelper.logError_senT , logError.getSenT() );

        long insertId = database.insert(DataBaseHelper.TABLE_LogError, null,values);
        logError.setID(insertId);

        return logError;
    }
    public void DeleteLogError ( Long id  ) {
        database.delete(DataBaseHelper.TABLE_LogError,DataBaseHelper.logError_logErrorId+ "=" + String.valueOf(id) ,null);
        return ; }
    public LogError GetSingleLogError (Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_LogError , allColumns,DataBaseHelper.logError_logErrorId +"=?" , new String[] { String.valueOf(id) }, null, null, null);
        cursor.moveToFirst();
        LogError logError = new LogError();
        if ( cursor.getCount() > 0 )
            logError = CursorToLogError(cursor);
        else
            logError.setID(Long.valueOf(-1));
        cursor.close();
        return logError;
    }
    public List<LogError> GetAllDisciplinary ()  {
        List<LogError> AllLogErrors = new ArrayList<LogError>();
        //String selection = DatabaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = { String.valueOf(1) };
        Cursor cursor = database.query(DataBaseHelper.TABLE_LogError , allColumns , null , null , null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            LogError logError = CursorToLogError(cursor);
            AllLogErrors.add(logError);
            cursor.moveToNext();
        }
        cursor.close();
        return AllLogErrors;
    }
    public LogError CursorToLogError (Cursor cursor) {

        LogError logError= new LogError();
        logError.setID(cursor.getLong(cursor.getColumnIndex("_id")));
        logError.setApplicationName(cursor.getString(cursor.getColumnIndex("applicationName")));
        logError.setErrorMsg(cursor.getString(cursor.getColumnIndex("errorMsg")));
        logError.setErrorLocation(cursor.getString(cursor.getColumnIndex("errorLocation")));
        logError.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));
        logError.setDeviceSn(cursor.getString(cursor.getColumnIndex("cellphoneSn")));
        logError.setSenT(cursor.getInt(cursor.getColumnIndex("senT")));


        return logError; }





}
