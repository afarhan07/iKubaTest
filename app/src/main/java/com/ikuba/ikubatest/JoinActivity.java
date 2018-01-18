package com.ikuba.ikubatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JoinActivity extends AppCompatActivity {

    Button btn_joinQR,btn_joinID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btn_joinQR = findViewById(R.id.btn_joinQR);
        btn_joinQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinActivity.this,JoinQRActivity.class));
            }
        });

        btn_joinID = findViewById(R.id.btn_joinID);
        btn_joinID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
