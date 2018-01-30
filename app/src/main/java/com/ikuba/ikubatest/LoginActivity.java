package com.ikuba.ikubatest;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ikuba.ikubatest.Model.MUser;
import com.ikuba.ikubatest.Parent.BaseActivity;

import id.flwi.util.ActivityUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private EditText mUsername,mPassword;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authLogin(mUsername.getText().toString(), mPassword.getText().toString());
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

    public void authLogin(String username, String password) {
        progressDialog.show();
        Call<MUser> callUser = apiService.login(username, password);
        callUser.enqueue(new Callback<MUser>() {
            @Override
            public void onResponse(Call<MUser> call, Response<MUser> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        if (response.body().getData() != null) {
                            setDataUser(response.body().getData());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, String.valueOf(response.body().getMessage()), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, getString(R.string.error_server), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MUser> call, Throwable t) {
                progressDialog.dismiss();
                messsageErrorServer(t);
            }
        });
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

