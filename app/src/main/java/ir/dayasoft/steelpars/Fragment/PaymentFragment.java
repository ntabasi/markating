package ir.dayasoft.steelpars.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.dayasoft.steelpars.Class.City;
import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Core.AppIntent;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.CreateDialog;
import ir.dayasoft.steelpars.R;


public class PaymentFragment extends Fragment {

    int paymentType;


    ImageView fullPayImageView;
    ImageView depositImageView;
    ImageView withoutImageView;

    TextView commentOneTextView;
    LinearLayout paymentPartTowLinearLayout;

    ImageView dividerPartTowImageView;

    TextView enterCodeTextView;
    LinearLayout codeLinearLayout;
    ImageView submitPaymentImageView;
    EditText codeEditText;

    TextView orderPriceTextView;
    TextView sendPriceTextView;
    TextView availableTextView;

    double availableCost;

    Context context;
    Long invoiceId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment_type, container, false);
        Log.i("page", "PaymentFragment"); //todo log
        context = getActivity();
        invoiceId = getArguments().getLong(AppIntent.InvoiceId, Long.valueOf("0"));

        Customer myCustomer = Customer.GetCustomer(context);
        int customerPaymentType = myCustomer.getPaymentType();

        fullPayImageView = (ImageView) rootView.findViewById(R.id.payment_full_imageView);
        depositImageView = (ImageView) rootView.findViewById(R.id.payment_deposit_imageView);
        withoutImageView = (ImageView) rootView.findViewById(R.id.payment_without_imageView);

        if(customerPaymentType == Customer.PaymentTypeDeposit) {
            withoutImageView.setVisibility(View.GONE);
        } else if (customerPaymentType == Customer.PaymentTypeComplete) {
            withoutImageView.setVisibility(View.GONE);
            depositImageView.setVisibility(View.GONE);
        }

        commentOneTextView = (TextView) rootView.findViewById(R.id.payment_comment_one_textView);

        paymentPartTowLinearLayout = (LinearLayout) rootView.findViewById(R.id.payment_second_layout);

        dividerPartTowImageView = (ImageView) rootView.findViewById(R.id.payment_dot2_imageView);

        enterCodeTextView = (TextView) rootView.findViewById(R.id.payment_paymentCode_textView);
        codeLinearLayout = (LinearLayout) rootView.findViewById(R.id.payment_code_linearLayout);
        codeEditText = (EditText) rootView.findViewById(R.id.payment_code_editText);
        submitPaymentImageView = (ImageView) rootView.findViewById(R.id.payment_register_imageView);


        orderPriceTextView = (TextView) rootView.findViewById(R.id.payment_orderPrice_textView);
        sendPriceTextView = (TextView) rootView.findViewById(R.id.payment_sendPrice_textView);
        availableTextView = (TextView) rootView.findViewById(R.id.payment_sumPrice_textView);


        fullPayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentOneTextView.setVisibility(View.GONE);

                Invoice lastInvoice = Invoice.GetSingleInvoice(context, invoiceId);
                double allCost = lastInvoice.getPrice();
                double sendCost = City.GetCustomerCity(context).getCost();
                availableCost = allCost;

                orderPriceTextView.setText(Core.Converter.DoubleToString(allCost*1000)); //fullPay
                sendPriceTextView.setText(Core.Converter.DoubleToString(sendCost*1000));
                availableTextView.setText(Core.Converter.DoubleToString(availableCost*1000));

                paymentType = Invoice.PaymentTypeFull;


                paymentPartTowLinearLayout.setVisibility(View.VISIBLE);
                dividerPartTowImageView.setVisibility(View.VISIBLE);
                enterCodeTextView.setVisibility(View.VISIBLE);
                codeLinearLayout.setVisibility(View.VISIBLE);
                submitPaymentImageView.setVisibility(View.VISIBLE);
                codeEditText.setVisibility(View.VISIBLE);

            }
        });

        depositImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentOneTextView.setVisibility(View.GONE);


                Invoice lastInvoice = Invoice.GetSingleInvoice(context, invoiceId);
                double allCost = lastInvoice.getPrice();
                double sendCost = City.GetCustomerCity(context).getCost();

                availableCost = Customer.GetCustomer(context).getMinDeposit();
                availableCost = Math.min(availableCost, allCost);

                orderPriceTextView.setText(Core.Converter.DoubleToString(allCost*1000)); //deposit
                sendPriceTextView.setText(Core.Converter.DoubleToString(sendCost*1000));
                availableTextView.setText(Core.Converter.DoubleToString(availableCost*1000));


                paymentType = Invoice.PaymentTypeDeposit;


                paymentPartTowLinearLayout.setVisibility(View.VISIBLE);
                dividerPartTowImageView.setVisibility(View.VISIBLE);
                enterCodeTextView.setVisibility(View.VISIBLE);
                codeLinearLayout.setVisibility(View.VISIBLE);
                submitPaymentImageView.setVisibility(View.VISIBLE);
                codeEditText.setVisibility(View.VISIBLE);

            }
        });

        withoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentOneTextView.setVisibility(View.GONE);

                Invoice lastInvoice = Invoice.GetSingleInvoice(context, invoiceId);
                double allCost = lastInvoice.getPrice();
                double sendCost = City.GetCustomerCity(context).getCost();
                availableCost = 0.0;

                orderPriceTextView.setText(Core.Converter.DoubleToString(allCost*1000)); //anyPay
                sendPriceTextView.setText(Core.Converter.DoubleToString(sendCost*1000));
                availableTextView.setText("0");

                paymentType = Invoice.PaymentTypeAny;


                codeEditText.setVisibility(View.GONE);
                codeLinearLayout.setVisibility(View.GONE);
                paymentPartTowLinearLayout.setVisibility(View.VISIBLE);
                dividerPartTowImageView.setVisibility(View.VISIBLE);
                enterCodeTextView.setVisibility(View.VISIBLE);
                submitPaymentImageView.setVisibility(View.VISIBLE);

            }
        });

        submitPaymentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Invoice lastInvoice = Invoice.GetSingleInvoice(context, invoiceId);
                double allCost = lastInvoice.getPrice();
                double sendCost = City.GetCustomerCity(context).getCost();
                Double mDeposit = allCost;

                if(paymentType == Invoice.PaymentTypeDeposit) {
                    availableCost = Customer.GetCustomer(context).getMinDeposit();
                    availableCost = Math.min(availableCost, allCost);
                }


                if(paymentType == Invoice.PaymentTypeAny)
                    CreateDialog.CreateConfirmCartDialog(getActivity(),"",  paymentType , 0.0, invoiceId, availableCost);
                else {
                    if(codeEditText.getText().toString().length() > 5) {
                        CreateDialog.CreateConfirmCartDialog(getActivity(), codeEditText.getText().toString(), paymentType, mDeposit, invoiceId, availableCost);
                    } else
                        CreateDialog.CreateCustomDialog(getActivity(),"شماره فیش پرداختی حداقل 6 رقم میباشد.", false);
                    }
            }
        });


        return rootView;
    }


}



