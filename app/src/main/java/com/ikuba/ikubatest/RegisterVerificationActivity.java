package com.ikuba.ikubatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterVerificationActivity extends AppCompatActivity {

    private Button mFinish;
    private EditText mCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_verification);
        mCode = findViewById(R.id.code);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFinish = findViewById(R.id.btn_register_finish);
        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationCode();
            }
        });
    }

    private void verificationCode(){
        String sCode = mCode.getText().toString();
        mCode.setError(null);
        boolean success;

        if(TextUtils.isEmpty(sCode)){
            mCode.setError(getString(R.string.error_field_required));
            mCode.requestFocus();
            success = false;
        } else if(!isCodeValid(sCode)){
            mCode.setError(getString(R.string.error_invalid_code));
            mCode.requestFocus();
            success = false;
        } else{
            success = true;
        }

        if(success){
            startActivity(new Intent(RegisterVerificationActivity.this,MainActivity.class));
            finish();
        }
    }

    private boolean isCodeValid(String sCode){
        return mCode.length() == 4;
    }
}
