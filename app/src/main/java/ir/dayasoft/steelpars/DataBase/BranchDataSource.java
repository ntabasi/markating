package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Branch;


public class BranchDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public BranchDataSource(Context context) {
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
              DataBaseHelper.branch_branchId
            , DataBaseHelper.branch_name
            , DataBaseHelper.branch_status
            , DataBaseHelper.branch_address
            , DataBaseHelper.branch_phone
            , DataBaseHelper.branch_webSite
            , DataBaseHelper.branch_cellPhone
            , DataBaseHelper.branch_openTime1
            , DataBaseHelper.branch_openTime2
            , DataBaseHelper.branch_closeTime1
            , DataBaseHelper.branch_closeTime2
            , DataBaseHelper.branch_description
            , DataBaseHelper.branch_rate
            , DataBaseHelper.branch_votes
            , DataBaseHelper.branch_votesCount
            , DataBaseHelper.branch_outOfService
            , DataBaseHelper.branch_outOfServiceCause
            , DataBaseHelper.branch_updateDate
    };


    public void DeleteBranch(Long id) {
        database.delete(DataBaseHelper.TABLE_branch, DataBaseHelper.branch_branchId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearBranch() {
        database.delete(DataBaseHelper.TABLE_branch, null, null);
        return;
    }
    public Branch GetSingleBranch(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_branch, allColumns, DataBaseHelper.branch_branchId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Branch branch = new Branch();
        if (cursor.getCount() > 0)
            branch = CursorToBranch(cursor);
        else
            branch.setBranchId(Long.valueOf(-1));
        cursor.close();
        return branch;
    }
    public List<Branch> GetAllBranch() {
        List<Branch> AllBranch = new ArrayList<Branch>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.query(DataBaseHelper.TABLE_branch, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Branch branch = CursorToBranch(cursor);
            AllBranch.add(branch);
            cursor.moveToNext();
        }
        cursor.close();
        return AllBranch;
    }
    public Branch InsertBranch(Branch branch) {
        ContentValues values = new ContentValues();


        values.put(DataBaseHelper.branch_branchId , branch.getBranchId ());
        values.put(DataBaseHelper.branch_name , branch.getName ());
        values.put(DataBaseHelper.branch_status , branch.getStatus ());
        values.put(DataBaseHelper.branch_address , branch.getAddress ());
        values.put(DataBaseHelper.branch_phone , branch.getPhone ());
        values.put(DataBaseHelper.branch_webSite , branch.getWebSite ());
        values.put(DataBaseHelper.branch_cellPhone , branch.getCellphone ());
        values.put(DataBaseHelper.branch_openTime1 , branch.getOpenTime1 ());
        values.put(DataBaseHelper.branch_openTime2 , branch.getOpenTime2 ());
        values.put(DataBaseHelper.branch_closeTime1 , branch.getCloseTime1 ());
        values.put(DataBaseHelper.branch_closeTime2 , branch.getCloseTime2 ());
        values.put(DataBaseHelper.branch_description , branch.getDescription ());
        values.put(DataBaseHelper.branch_rate , branch.getRate ());
        values.put(DataBaseHelper.branch_votes , branch.getVotes ());
        values.put(DataBaseHelper.branch_votesCount , branch.getVotesCount ());
        values.put(DataBaseHelper.branch_outOfService , branch.getOutOfService ());
        values.put(DataBaseHelper.branch_outOfServiceCause , branch.getOutOfServiceCause ());
        values.put(DataBaseHelper.branch_updateDate , branch.getUpdateDate());

        long insertId = database.insert(DataBaseHelper.TABLE_branch, null, values);
        branch.setBranchId(insertId);
        return branch;
    }
    public int InsertBranch(List<Branch> branches) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Branch branch : branches) {

            values.put(DataBaseHelper.branch_branchId , branch.getBranchId ());
            values.put(DataBaseHelper.branch_name , branch.getName ());
            values.put(DataBaseHelper.branch_status , branch.getStatus ());
            values.put(DataBaseHelper.branch_address , branch.getAddress ());
            values.put(DataBaseHelper.branch_phone , branch.getPhone ());
            values.put(DataBaseHelper.branch_webSite , branch.getWebSite ());
            values.put(DataBaseHelper.branch_cellPhone , branch.getCellphone ());
            values.put(DataBaseHelper.branch_openTime1 , branch.getOpenTime1 ());
            values.put(DataBaseHelper.branch_openTime2 , branch.getOpenTime2 ());
            values.put(DataBaseHelper.branch_closeTime1 , branch.getCloseTime1 ());
            values.put(DataBaseHelper.branch_closeTime2 , branch.getCloseTime2 ());
            values.put(DataBaseHelper.branch_description , branch.getDescription ());
            values.put(DataBaseHelper.branch_rate , branch.getRate ());
            values.put(DataBaseHelper.branch_votes , branch.getVotes ());
            values.put(DataBaseHelper.branch_votesCount , branch.getVotesCount ());
            values.put(DataBaseHelper.branch_outOfService , branch.getOutOfService ());
            values.put(DataBaseHelper.branch_outOfServiceCause , branch.getOutOfServiceCause ());
            values.put(DataBaseHelper.branch_updateDate , branch.getUpdateDate());

            Long flag = database.insert(DataBaseHelper.TABLE_branch, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }

    public Branch CursorToBranch(Cursor cursor) {

        Branch branch= new Branch();

        branch.setBranchId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.branch_branchId)));
        branch.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_name)));
        branch.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.branch_status)));
        branch.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_address)));
        branch.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_phone)));
        branch.setWebSite(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_webSite)));
        branch.setCellphone(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_cellPhone)));
        branch.setOpenTime1(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_openTime1)));
        branch.setOpenTime2(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_openTime2)));
        branch.setCloseTime1(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_closeTime1)));
        branch.setCloseTime2(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_closeTime2)));
        branch.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_description)));
        branch.setRate(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.branch_rate)));
        branch.setVotes(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.branch_votes)));
        branch.setVotesCount(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.branch_votesCount)));
        branch.setOutOfService(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.branch_outOfService)));
        branch.setOutOfServiceCause(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_outOfServiceCause)));
        branch.setUpdateDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.branch_updateDate)));

        return branch;
    }
}
