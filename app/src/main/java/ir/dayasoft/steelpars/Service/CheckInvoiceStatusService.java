package ir.dayasoft.steelpars.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import ir.dayasoft.steelpars.Activity.InvoiceActivity;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Class.InvoiceProduct;
import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Notification;

public class CheckInvoiceStatusService extends IntentService {

    String TAG = "CheckInvoiceStatusService";

    public CheckInvoiceStatusService() {

        super("CheckInvoiceStatusService");

    }

    @Override
    protected void onHandleIntent(Intent workIntent) {


        if (workIntent.equals(null))
            return;


        Log.i(TAG, "1");

        List<Invoice> invoices = Invoice.GetInvoiceByStatus(this, Invoice.Status_Deliver,Constants.Situation_output);

        WebService ws = new WebService();

        if (invoices.isEmpty()) {

            AppConfig.StopCheckInvoiceStatusReceiver(this);
        }

        for (Invoice invoice : invoices) {

            String res = ws.CheckInvoiceStatus(AppConfig.GetUserId(this), String.valueOf(invoice.getServerInvoiceId()), AppConfig.GetDec(this), Constants.Phrase);
            Log.i(TAG, res);
            Boolean flag = CommandHandler.CommandValidation(res);
            if (flag) {

                int status = CommandHandler.DecodeCommand.CheckInvoiceStatus(res);
                switch (status) {
                    case Invoice.Status_Server_Confirm:
                      List<InvoiceProduct> products = CommandHandler.DecodeCommand.GetInvoiceProduct(res);

                        Invoice invoiceRes = new Invoice();
                        invoiceRes.setInvoiceId(invoice.getInvoiceId());
                        invoiceRes.setPrimaryInvoiceId(invoice.getInvoiceId());
                        invoiceRes.setServerInvoiceId(invoice.getServerInvoiceId());
                        invoiceRes.setPrice(CommandHandler.DecodeCommand.GetInvoiceTotalCost(res));
                        invoiceRes.setInput(Constants.Situation_output);
                        invoiceRes.setStatus(Invoice.Status_Confirm);
                        invoiceRes.setDeliveryCost(CommandHandler.DecodeCommand.GetInvoiceExtraCost(res));

                        Invoice.UpdateInvoice(this, invoiceRes, products);

                        Invoice.ChangeInvoiceStatus(invoice.getInvoiceId(), Invoice.Status_Confirm, this);

                        Log.i(TAG, "Confrim");

                        if (!Invoice.InvoiceProductExists(products)) {

                            //update product Seda zade shavad

                        }


                        HashMap<String, String> hashMap = new HashMap<String, String>();

                        hashMap.put(AppIntent.InvoiceId, String.valueOf(invoice.getInvoiceId()));

                        Notification notification = new Notification();
                        notification.NotificationInvoiceResult(this,
                                InvoiceActivity.class, "فاکتور" ,
                                Invoice.GetStatusText(invoiceRes.getStatus()), hashMap);



                        break;
                    case Invoice.Status_Server_Reject:

                        Invoice.ChangeInvoiceStatus(invoice.getInvoiceId(), Invoice.Status_Reject, this);

                        HashMap<String, String> hashMap1 = new HashMap<String, String>();

                        hashMap1.put(AppIntent.InvoiceId, String.valueOf(invoice.getInvoiceId()));
                        Notification notification1 = new Notification();
                        notification1.NotificationInvoiceResult(this,
                                InvoiceActivity.class, "فاکتور",
                                Invoice.GetStatusText(Invoice.Status_Reject), hashMap1);

                        break;
                    case Invoice.Status_Server_inPreparition:
                        //felan kaari nemikonim
                        break;
                    case Invoice.Status_Server_Pendding:
                        //felan kaari nemikonim
                        break;
                }

            }
        }

    }



}


