package ir.dayasoft.steelpars.Communication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Class.InvoiceProduct;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.CreateDialog;

/**
 * Created by navid on 05/10/2015.
 */
public class SubmitInvoiceAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;
    int serverId;
    ProgressDialog progressDialog;
    Invoice invoice;



    public SubmitInvoiceAsyncTask(Context contextMain, Long invoiceId) {
        this.context = contextMain;
        progressDialog = new ProgressDialog(context);
        invoice=Invoice.GetSingleInvoice(context,invoiceId);

    }

    @Override
    protected Integer doInBackground(String... urls) {

        WebService ws = new WebService();
        serverId = 0;

        phrase = Constants.Phrase;
        String dec = AppConfig.GetDec(context);
        flag = false;

        List<InvoiceProduct> invoiceProducts=InvoiceProduct.GetAllInvoiceProductByFK_invoiceId(context,invoice.getInvoiceId());

        String itemsString=CommandHandler.EncodeCommand.InvoiceProduct(invoiceProducts);

        res = ws.SubmitInvoice(AppConfig.GetUserId(context), String.valueOf(invoice.getInvoiceId()), invoice.getAddress(), itemsString, dec, phrase);

        flag = CommandHandler.CommandValidation(res);


        if (flag) {

            res= CommandHandler.GetCommand(res);


            return CommandHandler.errorType_NoError;


        }
        else
            return CommandHandler.errorType_ServerAccess;


    }

    @Override
    protected void onPreExecute() {

        progressDialog.setCancelable(false);
        progressDialog.setMessage("درحال ارسال فاکتور");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer returnValue) {



        if(returnValue == CommandHandler.errorType_ServerAccess) {
            CreateDialog.CreateCustomDialog(context, "خطا در برقراری ارتباط", "تائید", false);
        }
        else {

            if (res.substring(0,2).equals("08")) {//InvoicRegisterSuccessfully = 8,

                Invoice.ConfirmInvoiceSendToServer(context, invoice.getInvoiceId(), Long.valueOf(res.substring(2)));
                Cart.ClearCart(context);
                CreateDialog.CreateInvoiceSubmitDialog(context, "فاکتور با موفقیت ثبت گردید", "تائید", true);
                AppConfig.StartCheckInvoiceStatusReceiver(context);

            }
            else if (res.substring(0,2).equals("02"))
            {
                CreateDialog.CreateCustomDialog ( context, "مجموعه تعطیل می باشد", "تائید", false);
            }
            else {

                CreateDialog.CreateCustomDialog ( context, "خطا در برقراری ارتباط", "تائید", false);
            }
        }


        progressDialog.dismiss();

        super.onPostExecute(returnValue);
    }
}

