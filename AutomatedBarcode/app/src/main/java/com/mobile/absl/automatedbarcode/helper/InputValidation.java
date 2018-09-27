package com.mobile.absl.automatedbarcode.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by user on 22/09/2018.
 */

public class InputValidation {

    private Context context;

    public InputValidation(Context context){
        this.context=context;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {

        String value=textInputEditText.getText().toString().trim();

        if(value.isEmpty()){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }

        else{
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public boolean isInputEditTextEmailValid(TextInputLayout textInputLayout, TextInputEditText textInputEditText, String errormessage){

        String val=textInputEditText.getText().toString().trim();

        if (val.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            textInputLayout.setError(errormessage);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public boolean isInputEditTextPasswordsMatch(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {

        String password=textInputEditText1.getText().toString().trim();
        String confirmpwd = textInputEditText2.getText().toString().trim();

        if(!password.contentEquals(confirmpwd)) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void hideKeyboardFrom(View view){

        InputMethodManager inputMethodManager=(InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }
}
