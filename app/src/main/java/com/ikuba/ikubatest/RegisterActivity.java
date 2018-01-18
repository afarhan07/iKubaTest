package com.ikuba.ikubatest;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsername,mPassword,mEmail,mPhoneNumber;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mEmail = findViewById(R.id.email);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mNext = findViewById(R.id.btn_register);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });

        TextView have = findViewById(R.id.have);
        have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MenuActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void attemptRegister() {
        String sUsername = mUsername.getText().toString();
        String sPassword = mPassword.getText().toString();
        String sEmail = mEmail.getText().toString();
        String sPhoneNumber = mPhoneNumber.getText().toString();

        boolean success;

        mUsername.setError(null);
        mPassword.setError(null);
        mEmail.setError(null);
        mPhoneNumber.setError(null);

        if (TextUtils.isEmpty(sUsername)) {
            mUsername.setError(getString(R.string.error_field_required));
            mUsername.requestFocus();
            success = false;
        } else if(!isUsernameValid(sUsername)){
            mUsername.setError(getString(R.string.error_invalid_username));
            mUsername.requestFocus();
            success = false;
        } else if (TextUtils.isEmpty(sEmail)) {
            mEmail.setError(getString(R.string.error_field_required));
            mEmail.requestFocus();
            success = false;
        } else if(!isEmailValid(sEmail)){
            mEmail.setError(getString(R.string.error_invalid_email));
            mEmail.requestFocus();
            success = false;
        } else if (TextUtils.isEmpty(sPassword)) {
            mPassword.setError(getString(R.string.error_field_required));
            mPassword.requestFocus();
            success = false;
        } else if(!isPasswordValid(sPassword)){
            mPassword.setError(getString(R.string.error_invalid_password));
            mPassword.requestFocus();
            success = false;
        } else if (TextUtils.isEmpty(sPhoneNumber)) {
            mPhoneNumber.setError(getString(R.string.error_field_required));
            mPhoneNumber.requestFocus();
            success = false;
        } else if(!isPhoneNumberValid(sPassword)){
            mPhoneNumber.setError(getString(R.string.error_invalid_phone_number));
            mPhoneNumber.requestFocus();
            success = false;
        } else{
            success = true;
        }

        if(success){
            startActivity(new Intent(RegisterActivity.this,RegisterVerificationActivity.class));
        }
    }

    private boolean isUsernameValid(String sUsername){

        return mUsername.length() > 5;
    }
    private boolean isEmailValid(String sEmail){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(sEmail);
        return matcher.matches();
    }

    private boolean isPasswordValid(String sPassword){

        return mPassword.length() > 5;
    }

    private boolean isPhoneNumberValid(String sPhoneNumber){

        return mPhoneNumber.length() > 10;
    }

}
