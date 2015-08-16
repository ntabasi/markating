package ir.dayasoft.steelpars.Communication;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.Class.User;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.CreateDialog;


public class LoginAsyncTask extends AsyncTask<String, Integer, Integer> {


    Context context;
    String res = "";
    Boolean flag;
    String dec;
    String phrase;

    Customer mCustomer;
    String UserName;
    String Password;

    ProgressDialog progressDialog;


    public LoginAsyncTask(Context contextMain, String userName, String password) {

        this.context = contextMain;
        this.mCustomer = new Customer();
        mCustomer.setUsername(userName);
        mCustomer.setPassword(password);
        UserName=userName;
        Password=password;
        progressDialog = new ProgressDialog(context);

    }

    @Override
    protected Integer doInBackground(String... urls) {

        WebService ws = new WebService();

        phrase = Constants.Phrase;
        dec = AppConfig.GetDec(context);

        String signInString = UserName + CommandHandler.ColumnSeparator + Password ;

        flag = false;
        this.res = ws.SignIn(signInString, dec, phrase);

        flag= CommandHandler.CommandValidation(res);
        if (flag)
            return CommandHandler.errorType_NoError;
        else
            return CommandHandler.errorType_ServerAccess;

    }

    @Override
    protected void onPreExecute() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage("درحال برقراری ارتباط");
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(Integer result) {
        progressDialog.dismiss();


        if (result == CommandHandler.errorType_ServerAccess) {
            CreateDialog.CreateCustomDialog(context, "خطا در برقراری ارتباط", false);
            return;
        } else if (User.GetRequestValidation(this.res)) {

            Customer resCustomer = CommandHandler.DecodeCommand.GetCustomer(this.res,context );
            if (!resCustomer.getCustomerId().equals("-1")) {

                Customer.SignInCustomer(context, mCustomer, resCustomer);
                CreateDialog.CreateFinishSignUpDialog(context);

          //      CreateDialog.CreateFinishSignUpDialog(context);
                return;
            } else {
                CreateDialog.CreateCustomDialog(context, "خطار در ورود", false);
                return;
            }
        } else {
            CreateDialog.CreateCustomDialog(context, "خطا در ورود", false);
            return;
        }

    }

    protected void onProgressUpdate(Integer... progress) {
    }


}