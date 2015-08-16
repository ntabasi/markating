package ir.dayasoft.steelpars.Class;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.MessageDataSource;


public class Message {

    Long MessageId;
    Long FK_senderUserId;
    Long FK_receiverUserId;
    String Content;
    Integer ContentType;
    Integer Type;
    String Date;
    Integer Status;

    Integer Input;

    public static final String Tag = "database.Message";

    final static public int Message_contentType_proposal = 0; /*پیشنهاد*/
    final static public int Message_contentType_criticism = 1; /*انتقاد*/

    final static public int Message_type_text = 0;
    final static public int Message_type_image = 1;



    public Long getMessageId() {
        return MessageId;
    }
    public void setMessageId(Long messageId) {
        MessageId = messageId;
    }
    public Long getFK_senderUserId() {
        return FK_senderUserId;
    }
    public void setFK_senderUserId(Long fK_senderUserId) {
        FK_senderUserId = fK_senderUserId;
    }
    public Long getFK_receiverUserId() {
        return FK_receiverUserId;
    }
    public void setFK_receiverUserId(Long fK_receiverUserId) {
        FK_receiverUserId = fK_receiverUserId;
    }
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public Integer getContentType() {
        return ContentType;
    }
    public void setContentType(Integer contentType) {
        ContentType = contentType;
    }
    public Integer getType() {
        return Type;
    }
    public void setType(Integer type) {
        Type = type;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;
    }
    public Integer getInput() {
        return Input;
    }
    public void setInput(Integer input) {
        Input = input;
    }


    static public Message GetSingleMessage (Context context, long messageId) {
        Message returnValue  =new Message();
        MessageDataSource messageDataSource = new MessageDataSource(context);
        try {
            messageDataSource.open();
            returnValue = messageDataSource.GetSingleMessage(messageId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleMessage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            messageDataSource.close();
        }
        return returnValue;
    }
    static public List<Message> GetAllMessage (Context context) {
        List<Message> messages  =new ArrayList<Message>();
        MessageDataSource invoiceDataSource = new MessageDataSource(context);
        try {
            invoiceDataSource.open();
            messages = invoiceDataSource.GetAllMessage();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllMessage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return messages;
    }
    static public int InsertMessage(Context context, List<Message> messages) {
        int returnValue = 0;
        MessageDataSource messageDataSource = new MessageDataSource(context);

        try {
            messageDataSource.open();
            returnValue = messageDataSource.InsertMessage(messages);
            if(returnValue != messages.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertMessage");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Message array. sizeOfCustomer:" + messages.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            messageDataSource.close();
            return returnValue;
        }
    }
    static public Message InsertMessage(Context context, Message message,int situation) {
        Message returnValue = new Message() ;
        MessageDataSource messageDataSource = new MessageDataSource(context);

        try {
            messageDataSource.open();
            message.setInput(situation);
            returnValue = messageDataSource.InsertMessage(message);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertMessage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            messageDataSource.close();
            return returnValue;
        }
    }
    static public void ClearMessage (Context context) {
        MessageDataSource invoiceDataSource = new MessageDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.ClearMessage();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCustomer");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
        }
    }
    static public void DeleteMessage (Context context, long invoiceId) {
        MessageDataSource invoiceDataSource = new MessageDataSource(context);
        try {
            invoiceDataSource.open();
            invoiceDataSource.DeleteMessage(invoiceId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteMessage");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            invoiceDataSource.close();
        }
    }
}