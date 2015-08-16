package ir.dayasoft.steelpars.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Class.Message;


public class MessageDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public MessageDataSource(Context context) {
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
            DataBaseHelper.message_contentType
            , DataBaseHelper.message_status
            , DataBaseHelper.message_FK_receiverUserId
            , DataBaseHelper.message_Content
            , DataBaseHelper.message_FK_senderUserId
            , DataBaseHelper.message_date
            , DataBaseHelper.message_type
            , DataBaseHelper.message_messageId
            , DataBaseHelper.message_input
    };

    public Message InsertMessage(Message message) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.message_contentType, message.getContentType());
        values.put(DataBaseHelper.message_status, message.getStatus());
        values.put(DataBaseHelper.message_FK_receiverUserId, message.getFK_receiverUserId());
        values.put(DataBaseHelper.message_Content, message.getContent());
        values.put(DataBaseHelper.message_FK_senderUserId, message.getFK_senderUserId());
        values.put(DataBaseHelper.message_date, message.getDate());
        values.put(DataBaseHelper.message_type, message.getType());
        values.put(DataBaseHelper.message_messageId, message.getMessageId());
        values.put(DataBaseHelper.message_input, message.getInput());

        long insertId = database.insert(DataBaseHelper.TABLE_message, null, values);
        message.setMessageId(insertId);
        return message;
    }
    public int InsertMessage (List<Message> messages) {
        ContentValues values = new ContentValues();
        int cnt = 0;
        for (Message message : messages) {

            values.put(DataBaseHelper.message_contentType, message.getContentType());
            values.put(DataBaseHelper.message_status, message.getStatus());
            values.put(DataBaseHelper.message_FK_receiverUserId, message.getFK_receiverUserId());
            values.put(DataBaseHelper.message_Content, message.getContent());
            values.put(DataBaseHelper.message_FK_senderUserId, message.getFK_senderUserId());
            values.put(DataBaseHelper.message_date, message.getDate());
            values.put(DataBaseHelper.message_type, message.getType());
            values.put(DataBaseHelper.message_messageId, message.getMessageId());
            values.put(DataBaseHelper.message_input, message.getInput());

            Long flag = database.insert(DataBaseHelper.TABLE_invoice, null, values);
            values.clear();
            if (flag > 0)
                cnt++;
        }
        return cnt;
    }
    public void DeleteMessage(Long id) {
        database.delete(DataBaseHelper.TABLE_message, DataBaseHelper.message_messageId + "=" + String.valueOf(id), null);
        return;
    }
    public void ClearMessage() {
        database.delete(DataBaseHelper.TABLE_message, null, null);
        return;
    }
    public Message GetSingleMessage(Long id) {
        Cursor cursor = database.query(DataBaseHelper.TABLE_message, allColumns, DataBaseHelper.message_messageId + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Message message = new Message();
        if (cursor.getCount() > 0)
            message = CursorToMessage(cursor);
        else
            message.setMessageId(Long.valueOf(-1));
        cursor.close();
        return message;
    }
    public List<Message> GetAllMessage() {
        List<Message> AllMessage = new ArrayList<Message>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.query(DataBaseHelper.TABLE_message, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Message message = CursorToMessage(cursor);
            AllMessage.add(message);
            cursor.moveToNext();
        }
        cursor.close();
        return AllMessage;
    }
    public List<Message> GetAllMessageByInput(int input) {
        List<Message> AllMessage = new ArrayList<Message>();
        //String selection = DataBaseHelper.RestaurantsTable_RSTStatus + " =? " ;
        String[] selectionArgs = {String.valueOf(1)};
        Cursor cursor = database.rawQuery(" SELECT * FROM " +
                DataBaseHelper.TABLE_message +
                " WHERE " +
                DataBaseHelper.message_input +
                " = " +
                input
                ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Message message = CursorToMessage(cursor);
            AllMessage.add(message);
            cursor.moveToNext();
        }
        cursor.close();
        return AllMessage;
    }
    public int Update(Message message) {
        ContentValues values = new ContentValues();
        if (message.getContentType() != -1)
            values.put(DataBaseHelper.message_contentType, message.getContentType());
        if (message.getStatus() != -1)
            values.put(DataBaseHelper.message_status, message.getStatus());
        if (message.getFK_receiverUserId() != -1)
            values.put(DataBaseHelper.message_FK_receiverUserId, message.getFK_receiverUserId());
        if (message.getContent() != "")
            values.put(DataBaseHelper.message_Content, message.getContent());
        if (message.getFK_senderUserId() != -1)
            values.put(DataBaseHelper.message_FK_senderUserId, message.getFK_senderUserId());
        if (message.getDate() != "")
            values.put(DataBaseHelper.message_date, message.getDate());
        if (message.getType() != -1)
            values.put(DataBaseHelper.message_type, message.getType());
        if (message.getMessageId() != -1)
            values.put(DataBaseHelper.message_messageId, message.getMessageId());
        int numRowEffected = database.update(DataBaseHelper.TABLE_message, values, DataBaseHelper.message_messageId + "=?", new String[]{String.valueOf(message.getMessageId())});
        return numRowEffected;
    }
    public Message CursorToMessage(Cursor cursor) {
        Message message = new Message();
        message.setContentType(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.message_contentType)));
        message.setStatus(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.message_status)));
        message.setFK_receiverUserId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.message_FK_receiverUserId)));
        message.setContent(cursor.getString(cursor.getColumnIndex(DataBaseHelper.message_Content)));
        message.setFK_senderUserId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.message_FK_senderUserId)));
        message.setDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.message_date)));
        message.setType(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.message_type)));
        message.setMessageId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.message_messageId)));
        message.setInput(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.message_input)));

        return message;
    }

}
