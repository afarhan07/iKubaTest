package com.ikuba.ikubatest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new CountDownTimer(2000,1000){

            public void onTick(long millisUntilFinished){

            }

            public void onFinish(){
                startActivity(new Intent(SplashActivity.this,MenuActivity.class)) ;
                finish();
            }

        }.start();
    }
}
