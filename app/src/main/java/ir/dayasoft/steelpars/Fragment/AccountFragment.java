package ir.dayasoft.steelpars.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.dayasoft.steelpars.Activity.SignUpActivity;

import ir.dayasoft.steelpars.Communication.LoginAsyncTask;
import ir.dayasoft.steelpars.Core.CreateDialog;
import ir.dayasoft.steelpars.Core.Farsi;
import ir.dayasoft.steelpars.R;


public class AccountFragment extends Fragment {

    ImageView signInImageView;
    ImageView enterImageView;
    EditText userNameEditText;
    EditText passwordEditText;
    TextView accountHintTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        signInImageView = (ImageView) rootView.findViewById(R.id.account_signIn_imageView);
        enterImageView = (ImageView) rootView.findViewById(R.id.account_enter_imageView);
        userNameEditText = (EditText) rootView.findViewById(R.id.account_userName_editText);
        passwordEditText = (EditText) rootView.findViewById(R.id.account_password_editText);
        accountHintTextView = (TextView) rootView.findViewById(R.id.account_hint_textView);

        Typeface tf= Farsi.GetFarsiFont(getActivity());

        accountHintTextView.setTypeface(tf);


        signInImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(intent);
            }
        });

        enterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userString, passwordString;
                userString = userNameEditText.getText().toString();
                passwordString = passwordEditText.getText().toString();

                if(userString.equals("") || passwordString.equals("")) {
                    CreateDialog.CreateCustomDialog(getActivity(),"نام کاربری و کلمه عبور را وارد کنید.", false);
                } else {
                    Toast.makeText(getActivity(), "SignUp user", Toast.LENGTH_SHORT).show();
                    LoginAsyncTask loginAsyncTask = new LoginAsyncTask(getActivity(),userString, passwordString);
                    loginAsyncTask.execute();
                }
            }
        });

        return rootView;
    }

}