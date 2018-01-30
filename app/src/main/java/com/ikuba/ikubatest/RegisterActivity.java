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
import android.widget.Toast;

import com.ikuba.ikubatest.Model.MUser;
import com.ikuba.ikubatest.Parent.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.flwi.util.ActivityUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    private EditText mUsername,mName,mPassword,mEmail,mPhoneNumber;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUsername = findViewById(R.id.username);
        mName = findViewById(R.id.name);
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
        String sName = mName.getText().toString();

        boolean success;

        mUsername.setError(null);
        mName.setError(null);
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
        } else if (TextUtils.isEmpty(sName)) {
            mName.setError(getString(R.string.error_field_required));
            mName.requestFocus();
            success = false;
        } else if(!isNameValid(sName)){
            mName.setError(getString(R.string.error_invalid_name));
            mName.requestFocus();
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
            Call<MUser> postUserCall = apiService.register(mUsername.getText().toString(),
                    mName.getText().toString(),
                    mPassword.getText().toString(),
                    mEmail.getText().toString(),
                    mPhoneNumber.getText().toString());
            postUserCall.enqueue(new Callback<MUser>() {
                @Override
                public void onResponse(Call<MUser> call, Response<MUser> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful()) {
                        if (response.body().isStatus()) {
                            if (response.body().getData() != null) {
                                setDataUser(response.body().getData());
                                startActivity(new Intent(RegisterActivity.this, RegisterVerificationActivity.class));
                                finish();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, String.valueOf(response.body().getMessage()), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, getString(R.string.error_server), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<MUser> call, Throwable t) {
                    progressDialog.dismiss();
                    messsageErrorServer(t);
                }
            });
        }
    }

    private boolean isUsernameValid(String sUsername){

        return mUsername.length() > 5;
    }
    private boolean isNameValid(String sName){

        return mName.length() > 1;
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

    public void setDataUser(MUser.User user) {
        if (user != null) {
            if (user.getId() != null)
                ActivityUtil.setSharedPreference(this, USER_ID, user.getId());
            if (user.getUsername() != null)
                ActivityUtil.setSharedPreference(this, USER_NAME, user.getUsername());
            if (user.getUser_token() != null)
                ActivityUtil.setSharedPreference(this, USER_TOKEN, user.getUser_token());
        }
    }
}
