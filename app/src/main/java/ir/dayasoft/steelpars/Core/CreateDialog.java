package ir.dayasoft.steelpars.Core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Activity.CartActivity;
import ir.dayasoft.steelpars.Activity.CategoryActivity;
import ir.dayasoft.steelpars.Activity.PaymentActivity;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Class.City;
import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Class.InvoiceProduct;
import ir.dayasoft.steelpars.Communication.SubmitInvoiceAsyncTask;
import ir.dayasoft.steelpars.R;


public class CreateDialog {



    /*static public void CreateAlertDialog(Context context,
                                         String content,
                                         String buttonOkText,
                                         String buttonCancelText) {
        Typeface tf = Farsi.GetFarsiFont(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View loginView = inflater.inflate(R.layout.dialog_alertdialog,
                null);


        TextView textViewContent = (TextView) loginView.findViewById(R.id.dialog_alert_message_content_textView);
        Button buttonOk = (Button) loginView.findViewById(R.id.dialog_alert_message_ok_button);
        Button buttonCancel = (Button) loginView.findViewById(R.id.dialog_alert_message_cancel_button);

        textViewContent.setTypeface(tf);
        buttonOk.setTypeface(tf, 1);
        buttonCancel.setTypeface(tf, 1);


        textViewContent.setText(content);
        buttonOk.setText(buttonOkText);
        buttonCancel.setText(buttonCancelText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(loginView);

        final AlertDialog alert = builder.create();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alert.dismiss();

            }

        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alert.dismiss();

            }

        });

        alert.show();
    }

    static public void CreateAppreciateDialog(Context context) {
        Typeface tf = Farsi.GetFarsiFont(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View loginView = inflater.inflate(R.layout.dialog_alertdialog,
                null);

        String content = "با تشکر، امیدواریم خدمات ما شایسته انتخاب شما باشد.";
        String buttonOkText = "تائید";

        CreateCustomDialog(context,content,buttonOkText,true);
    }

    static public void CreateSetPassDialog(final Context context) {
        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;
        String Content = "تغییرات با موفقیت ثبت شد." ;
        Boolean color = true;
        String ButtonText = "تایید";

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(color)
            dialogView = inflater.inflate(R.layout.dialog_custom_message_correct,null);
        else
            dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect,null);


        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        Button buttonOk = (Button) dialogView.findViewById(R.id.dialog_custom_message_button_textView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(Content);

        buttonOk.setTypeface(tf, 1);
        buttonOk.setText(ButtonText);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
                Intent intent=new Intent(context, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        alert.show();
    }
    static public void CreateChangeInfo(final Context context) {
        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;
        String Content = "تغییرات با موفقیت ثبت شد." ;
        Boolean color = true;
        String ButtonText = "تایید";

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(color)
            dialogView = inflater.inflate(R.layout.dialog_custom_message_correct,null);
        else
            dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect,null);


        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        Button buttonOk = (Button) dialogView.findViewById(R.id.dialog_custom_message_button_textView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(Content);

        buttonOk.setTypeface(tf, 1);
        buttonOk.setText(ButtonText);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
                Intent intent=new Intent(context, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        alert.show();
    }

    static public void CreateSimChangeDialog(Context context) {
        Typeface tf = Farsi.GetFarsiFont(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View loginView = inflater.inflate(R.layout.dialog_alertdialog,
                null);

        String content = "سیم کارت تغییر کرده، کاربر شناسایی نشد.";
        String buttonOkText = "ثبت نام";
        String buttonCancelText = "خروج";

        TextView textViewContent = (TextView) loginView.findViewById(R.id.dialog_alert_message_content_textView);
        Button buttonOk = (Button) loginView.findViewById(R.id.dialog_alert_message_ok_button);
        Button buttonCancel = (Button) loginView.findViewById(R.id.dialog_alert_message_cancel_button);

        textViewContent.setTypeface(tf);
        buttonOk.setTypeface(tf, 1);
        buttonCancel.setTypeface(tf, 1);


        textViewContent.setText(content);
        buttonOk.setText(buttonOkText);
        buttonCancel.setText(buttonCancelText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(loginView);

        final Context myContext= context;
        final AlertDialog alert = builder.create();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
                Intent intent=new Intent(myContext, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
                Intent intent=new Intent(myContext, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
            }

        });

        alert.show();
    }
    static public void CreateSendInformation(Context context) {
        Typeface tf = Farsi.GetFarsiFont(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View loginView = inflater.inflate(R.layout.dialog_alertdialog,
                null);

        String content = "نرم افزار قصد ارسال پیام درخواست ثبت نام ثبت نام به فروشگاه را دارد.";
        String buttonOkText = "تائید";
        String buttonCancelText = "انصراف";

        TextView textViewContent = (TextView) loginView.findViewById(R.id.dialog_alert_message_content_textView);
        Button buttonOk = (Button) loginView.findViewById(R.id.dialog_alert_message_ok_button);
        Button buttonCancel = (Button) loginView.findViewById(R.id.dialog_alert_message_cancel_button);

        textViewContent.setTypeface(tf);
        buttonOk.setTypeface(tf, 1);
        buttonCancel.setTypeface(tf, 1);


        textViewContent.setText(content);
        buttonOk.setText(buttonOkText);
        buttonCancel.setText(buttonCancelText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(loginView);

        final AlertDialog alert = builder.create();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }

        });

        alert.show();
    }
    */
    static public void CreateCustomDialog(Context context,String Content, boolean flag) {

        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(flag) {
            dialogView = inflater.inflate(R.layout.dialog_custom_message_correct, null);
        }
        else {
            dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect, null);
        }


        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        ImageView submitButton = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(Content);



        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        alert.show();
    }


      static public void CreateInvoiceSubmitDialog(final Context context,String Content, String ButtonText,boolean color) {

        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(color)
            dialogView = inflater.inflate(R.layout.dialog_custom_message_correct,null);
        else
            dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect,null);


        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        ImageView imageViewOk = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(Content);




        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

          imageViewOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();

                Intent goIntent=new Intent(context, CategoryActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(goIntent);


            }
        });

        alert.show();
    }


    static public void CreateCustomDialog(final Context context,String Content, String ButtonText,boolean color) {

        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(color)
            dialogView = inflater.inflate(R.layout.dialog_custom_message_correct,null);
        else
            dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect,null);


        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        ImageView imageViewOk = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(Content);




        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        imageViewOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        alert.show();
    }



    static public void CreateRegisterCustomer (final Context context) {

        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        dialogView = inflater.inflate(R.layout.dialog_custom_message_correct,null);



        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        ImageView buttonOk = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText("با تشکر امیدواریم خدمات ما شایسته انتخاب شما باشد");

      /*  buttonOk.setTypeface(tf, 1);
        buttonOk.setText("تائید");*/


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();

                Intent goIntent=new Intent(context, CategoryActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(goIntent);
            }
        });

        alert.show();
    }



    static public void CreateAvailableDialog (final Context context, List<String> Content) {

        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;

        ArrayAdapter<String> mArrayAdapter;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect, null);


        ListView mListView = (ListView) dialogView.findViewById(R.id.dialog_custom_message_content_listView);
        ImageView submitButton = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);
        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);


        mListView.setVisibility(View.VISIBLE);
        textViewContent.setText("موارد زیر بیشتر از موجودی انبار هستند:");
        mArrayAdapter = new ArrayAdapter<String> (context, android.R.layout.simple_list_item_1, Content);
        mListView.setAdapter(mArrayAdapter);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goIntent = new Intent(context, CartActivity.class);
                goIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(goIntent);

                alert.dismiss();
            }
        });

        alert.show();
    }

    static public void CreateSentRequestDialog(final Context context, Boolean flag) {
        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;
        String Content ;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(flag) {
            dialogView = inflater.inflate(R.layout.dialog_custom_message_correct, null);
            Content = "سفارش با موفقیت ثبت شد.";
        }
        else {
            dialogView = inflater.inflate(R.layout.dialog_custom_message_incorrect, null);
            Content = "خطا در ارسال، لطفاً مجدداً تلاش کنید.";
        }


        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        ImageView imageViewOk = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(Content);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        imageViewOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();

                Intent intent=new Intent(context, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        alert.show();
    }

    static public void CreateConfirmCartDialog(final Context context, final String paymentCode, final int paymentType, final Double deposit, final Long invoiceId, final double cost) {
        Typeface tf = Farsi.GetFarsiFont(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View loginView = inflater.inflate(R.layout.dialog_alertdialog,
                null);

        String content = " سفارش ارسال شود؟";

        TextView textViewContent = (TextView) loginView.findViewById(R.id.dialog_alert_message_content_textView);
        ImageView imageViewOk = (ImageView) loginView.findViewById(R.id.dialog_alert_message_ok_imageView);
        ImageView imageViewCancel = (ImageView) loginView.findViewById(R.id.dialog_alert_message_cancel_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText(content);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(loginView);

        final AlertDialog alert = builder.create();

        imageViewOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();

                Invoice invoice = new Invoice();
                invoice.setPaymentCode(paymentCode);
                invoice.setPaymentType(paymentType);
                invoice.setInvoiceId(invoiceId);
                invoice.setDeposit(deposit);
                invoice.setPrice(cost);
                invoice.setDeliveryCost(Double.valueOf(City.GetCustomerCity(context).getCost()));

                invoice.setStatus(Invoice.Status_Confirm);

                Invoice.UpdateInvoice(context, invoice);
                CreateSentRequestDialog(context, true);


            }
        });

        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }

        });

        alert.show();
    }

    static public void CreateFinishSignUpDialog (final Context context) {

        Typeface tf = Farsi.GetFarsiFont(context);
        final View dialogView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogView = inflater.inflate(R.layout.dialog_custom_message_correct, null);



        TextView textViewContent = (TextView) dialogView.findViewById(R.id.dialog_custom_message_content_textView);
        ImageView submitButton = (ImageView) dialogView.findViewById(R.id.dialog_custom_message_button_imageView);

        textViewContent.setTypeface(tf);
        textViewContent.setText("ورود موفقیت آمیز");



        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                alert.dismiss();
            }
        });

        alert.show();
    }

    static public void CreatePreInvoiceDialog (final Context context) {

        final View dialogView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogView = inflater.inflate(R.layout.dialog_custom_pre_invoice, null);



        ImageView submitButton = (ImageView) dialogView.findViewById(R.id.dialog_preInvoice_submit);
        ImageView cancelButton = (ImageView) dialogView.findViewById(R.id.dialog_preInvoice_cancel);
        final RadioButton newAddress = (RadioButton) dialogView.findViewById(R.id.dialog_preInvoice_newAddress);
        final RadioButton defaultAddress = (RadioButton) dialogView.findViewById(R.id.dialog_preInvoice_defaultAddress);
        final EditText mEditText = (EditText) dialogView.findViewById(R.id.dialog_preInvoice_editText);


        newAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mEditText.setEnabled(true);
                }
            }
        });

        defaultAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mEditText.setEnabled(false);
                }
            }
        });



        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final AlertDialog alert = builder.create();

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Customer customer = Customer.GetCustomer(context);
                double sumPriceDouble = Cart.GetCartSum(context);

                Invoice invoice = new Invoice();
                invoice.setCreateDate(Core.Dates.GetCurentDate());
                invoice.setFK_customerId(Long.valueOf(AppConfig.GetUserId(context)));
                invoice.setPrice(sumPriceDouble+Double.valueOf(City.GetCustomerCity(context).getCost()));
                invoice.setStatus(Invoice.Status_Pending);
                invoice.setFK_customerId(Long.valueOf(AppConfig.GetUserId(context)));
                if(newAddress.isChecked()) {
                    invoice.setAddress(mEditText.getText().toString());
                } else {
                    invoice.setAddress(customer.getAddress());
                }
                invoice = Invoice.InsertInvoice(context,invoice);



                List<Cart> carts = Cart.GetAllCart(context);
                List<InvoiceProduct> invoiceProductList = new ArrayList<>();
                for(Cart cart: carts) {
                    InvoiceProduct invoiceProduct = new InvoiceProduct();

                    invoiceProduct.setFK_productId(cart.getFK_productId());
                    invoiceProduct.setPrice(Double.valueOf(cart.getProductPrice()));
                    invoiceProduct.setCount(cart.getCount());
                    invoiceProduct.setOrder(cart.getOrder());
                    invoiceProduct.setFK_invoiceId(invoice.getInvoiceId());

                    invoiceProductList.add(invoiceProduct);
                }
                Cart.ClearCart(context);
                InvoiceProduct.InsertInvoiceProduct(context,invoiceProductList);


                Intent intent = new Intent(context, PaymentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(AppIntent.InvoiceId, invoice.getInvoiceId());
                context.startActivity(intent);


                alert.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        alert.show();
    }


    static public void CreateConfirmCartDialog(final Context context) {
        Typeface tf = Farsi.GetFarsiFont(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View loginView = inflater.inflate(R.layout.dialog_alertdialog,
                null);





        //String content = "آیا سفارش ارسال شود؟\n در صورت تایید مبلغ سفارش بعد از تایید فروشگاه حساب شما کسر خواهد شد.";
        String content = "آیا سفارش ارسال گردد؟";
        String buttonOkText = "تائید";
        String buttonCancelText = "انصراف";

        TextView textViewContent = (TextView) loginView.findViewById(R.id.dialog_alert_message_content_textView);
        ImageView imageViewOk = (ImageView) loginView.findViewById(R.id.dialog_alert_message_ok_imageView);
        ImageView imageViewCancel = (ImageView) loginView.findViewById(R.id.dialog_alert_message_cancel_imageView);

        textViewContent.setTypeface(tf);
      //  buttonOk.setTypeface(tf, 1);
     //   buttonCancel.setTypeface(tf, 1);


        textViewContent.setText(content);
//        buttonCancel.setText(buttonCancelText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(loginView);

        final AlertDialog alert = builder.create();

        imageViewOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
                long invoiceId=Invoice.InsertInvoice(context, Constants.Situation_output);
                if (invoiceId > 0)
                {
                    SubmitInvoiceAsyncTask submitInvoiceAsyncTask=new SubmitInvoiceAsyncTask(context,invoiceId);
                    submitInvoiceAsyncTask.execute();
                }

                //  CreateSentRequestDialog(context);


            }
        });

        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }

        });

        alert.show();
    }




}