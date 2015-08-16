package ir.dayasoft.steelpars.Class;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.InvoiceDataSource;
import ir.dayasoft.steelpars.DataBase.MessageDataSource;


public class Archive {

    Long FK_id;
    Integer Type;
    String CreateDate;
    Integer Input;


    public static final int Archive_Type_text = Message.Message_type_text;
    public static final int Archive_Type_image = Message.Message_type_image;
    public static final int Archive_Type_invoice = 2;



    public static final String Tag = "database.Archive";


    public Long getFK_id() {
        return FK_id;
    }
    public void setFk_id(Long fk_id) {
        FK_id = fk_id;
    }
    public void setType(Integer type) {
        Type= type;
    }
    public Integer getType() {
        return Type;
    }
    public void setInput(Integer input) {
        Input= input;
    }
    public Integer getInput() {
        return Input;
    }
    public String getCreateDate() {
        return CreateDate;
    }
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }


    static public List<Archive> GetAllArchiveByInput (Context context,int input) {
        List<Archive> archives  =new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        InvoiceDataSource invoiceDataSource = new InvoiceDataSource(context);
        MessageDataSource messageDataSource = new MessageDataSource(context);
        try {
            invoiceDataSource.open();
            invoices = invoiceDataSource.GetAllInvoiceByInput(input);

            for(Invoice invoice: invoices) {
                Archive tmp = new Archive();
                tmp.setInput(input);
                tmp.setType(Archive_Type_invoice);
                tmp.setCreateDate(invoice.getCreateDate());
                tmp.setFk_id(invoice.getInvoiceId());

                archives.add(tmp);
            }
            messageDataSource.open();
            messages = messageDataSource.GetAllMessageByInput(input);

            for(Message message: messages) {
                Archive tmp = new Archive();
                tmp.setInput(input);
                tmp.setType(message.getType());
                tmp.setCreateDate(message.getDate());
                tmp.setFk_id(message.getMessageId());

                archives.add(tmp);
            }

        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetAllArchive");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            invoiceDataSource.close();
        }
        return archives;
    }


}