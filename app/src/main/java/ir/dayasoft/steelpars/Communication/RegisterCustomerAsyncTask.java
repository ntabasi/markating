package ir.dayasoft.steelpars.Communication;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.List;

import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.CreateDialog;


public class RegisterCustomerAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    String phrase;
    Boolean flag;
    int serverId;
    ProgressDialog progressDialog;



    public RegisterCustomerAsyncTask(Context contextMain) {
        this.context = contextMain;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected Integer doInBackground(String... urls) {

        WebService ws = new WebService();
        serverId = 0;

        phrase = Constants.Phrase;
        String dec = AppConfig.GetDec(context);
        flag = false;
        List<Customer> customerList;
        String customerString;

        customerList = Customer.GetAllCustomer(context);
        customerString = CommandHandler.EncodeCommand.Customer(customerList, context);

        res = ws.SendCustomer(customerString, dec, phrase);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {
            String returnValue  = CommandHandler.GetCommand(res);
            if(returnValue != "0") {

                return CommandHandler.errorType_NoError ;
            }
        } else
            return CommandHandler.errorType_ServerAccess;

        return 0;
    }


    @Override
    protected void onPreExecute() {

        progressDialog.setCancelable(false);
        progressDialog.setMessage("درحال برقراری ارتباط");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer returnValue) {

        Intent intent = new Intent(AppIntent.BroadCastCustomer);
        context.sendBroadcast(intent);

        if(returnValue == CommandHandler.errorType_ServerAccess) {
            Customer.ClearCustomer(context);
            CreateDialog.CreateCustomDialog(context, "خطا در برقراری ارتباط", "تائید", false);

        }
        else {

            String flag = CommandHandler.DecodeCommand.GetLogin(res, context);
            if (flag.equals(String.valueOf(Customer.Register_DuplicateCellPhone))) {

                Customer.ClearCustomer(context);
                CreateDialog.CreateCustomDialog(context, "شماره موبایل شما قبلاً در سیستم ثبت شده است", "تائید", false);

            } else if (flag.equals("0")) {

                Customer.ClearCustomer(context);
                CreateDialog.CreateCustomDialog(context, "خطا در ثبت نام", "تائید", false);

            } else {

                CreateDialog.CreateRegisterCustomer(context);
            }
        }

        progressDialog.dismiss();

        super.onPostExecute(returnValue);
    }





}

