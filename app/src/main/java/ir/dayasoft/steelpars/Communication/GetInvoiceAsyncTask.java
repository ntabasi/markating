package ir.dayasoft.steelpars.Communication;


import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;


public class GetInvoiceAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;


    public GetInvoiceAsyncTask(Context contextMain) {
        this.context = contextMain;
    }

    @Override
    protected Integer doInBackground(String... urls) {

        WebService ws = new WebService();


        phrase = Constants.Phrase;
        String dec = AppConfig.GetDec(context);
        flag = false;

        this.res = ws.GetInvoice(dec, phrase);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {
/*            List<Invoice> invoiceList = CommandHandler.DecodeCommand.GetInvoice(this.res);
            Invoice.InsertInvoice(context, invoiceList);*/
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;

    }

}

