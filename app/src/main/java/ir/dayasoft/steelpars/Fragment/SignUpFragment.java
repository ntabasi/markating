package ir.dayasoft.steelpars.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.Communication.RegisterCustomerAsyncTask;

import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.CreateDialog;
import ir.dayasoft.steelpars.R;


public class SignUpFragment extends Fragment {

    ImageView submitButton;
    ImageView cancelButton;

    EditText firstName;
    EditText lastName;
    EditText tell;
    EditText email;
    EditText cellphone;
    EditText address;
    EditText password;
    Spinner citySpinner;
  //  List<City> cityList;

    Customer customer;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        Log.i("page", "SignUpFragment"); //todo log


        //define
        submitButton = (ImageView) rootView.findViewById(R.id.signUp_ok_button);
        cancelButton = (ImageView) rootView.findViewById(R.id.signUp_cancel_button);

        firstName = (EditText) rootView.findViewById(R.id.signUp_fName_editText);
        lastName = (EditText) rootView.findViewById(R.id.signUp_lName_editText);
        tell = (EditText) rootView.findViewById(R.id.signUp_tell_editText);
        cellphone = (EditText) rootView.findViewById(R.id.signUp_cellphone_editText);
        email = (EditText) rootView.findViewById(R.id.signUp_email_editText);
        address = (EditText) rootView.findViewById(R.id.signUp_address_editText);
        password = (EditText) rootView.findViewById(R.id.signUp_password_editText);
      //  citySpinner = (Spinner) rootView.findViewById(R.id.signUp_city_spinner);

      /*  cityList = City.GetCity(getActivity());

        List<String> cityString = City.GetCityString(getActivity());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                cityString);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(dataAdapter);*/

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customer = new Customer();
                int cnt = 0;

                if (!TextUtils.isEmpty(firstName.getText())) {
                    customer.setName(firstName.getText().toString());
                } else {
                    cnt++;
                }

                if (!TextUtils.isEmpty(password.getText())) {
                    customer.setPassword(password.getText().toString());
                } else {
                    cnt++;
                }

                if (!TextUtils.isEmpty(lastName.getText())) {
                    customer.setFamily(lastName.getText().toString());
                } else {
                    cnt++;
                }

                if (!TextUtils.isEmpty(tell.getText())) {
                    customer.setPhone(tell.getText().toString());
                } else {
                    cnt++;
                }

                if (!TextUtils.isEmpty(cellphone.getText())) {
                    customer.setCellPhone(cellphone.getText().toString());
                } else {
                    cnt++;
                }

                // Long cityId = cityList.get(citySpinner.getSelectedItemPosition()).getCityId();
                //  AppConfig.SetCityId(getActivity(), cityId);

                if (!TextUtils.isEmpty(email.getText()))
                {
                   if ( Core.Email.isEmailValid(email.getText().toString()))
                         customer.setEmail(email.getText().toString());
                    else{
                        CreateDialog.CreateCustomDialog(getActivity(), "ایمیل نا معتبر می باشد", false);
                        cnt++;
                        return ;
                    }
                }

                customer.setAddress(address.getText().toString());

                if(password.getText().toString().length() < 6) {
                    CreateDialog.CreateCustomDialog(getActivity(), " کلمه عبور باید حداقل 6 حرف باشد", false);
                } else {
                    if (cnt > 0) {
                        CreateDialog.CreateCustomDialog(getActivity(), "پر کردن فیلد های قرمز اجباری است", false);
                    } else {

                        Customer.InsertCustomer(getActivity(), customer);
                        RegisterCustomerAsyncTask asyncTask = new RegisterCustomerAsyncTask(getActivity());
                        asyncTask.execute();
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        return rootView;
    }


}