package com.mobile.absl.automatedbarcode.activities;

import android.content.Intent;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.mobile.absl.automatedbarcode.R;
import com.mobile.absl.automatedbarcode.helper.InputValidation;
import com.mobile.absl.automatedbarcode.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity= LoginActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    Handler handler= new Handler();

    Runnable runnable= new Runnable() {
        @Override
        public void run() {
            textInputLayoutEmail.setVisibility(View.VISIBLE);
            textInputLayoutPassword.setVisibility(View.VISIBLE);
            appCompatButtonLogin.setVisibility(View.VISIBLE);
            textViewLinkRegister.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        handler.postDelayed(runnable, 2000);

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail= (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword= (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword= (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister= (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper= new DatabaseHelper(activity);
        inputValidation= new InputValidation(activity);
    }

    @Override
    public  void onClick(View view){

        switch (view.getId()){

            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;

            case R.id.textViewLinkRegister:
                Intent intentRegister= new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite(){

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){

            return;
        }

        if(!inputValidation.isInputEditTextEmailValid(textInputLayoutEmail, textInputEditTextEmail, getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }

        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())){

            Intent accountsintent = new Intent(activity, UsersActivity.class);
            accountsintent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            //Bundle bundle= new Bundle();
            //bundle.putString("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            //HomeFragment homeFragment= new HomeFragment();
            //homeFragment.setArguments(bundle);
            startActivity(accountsintent);
        }

        else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password),Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
