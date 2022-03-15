package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.moversapp.R;

public class SplashActivity extends AppCompatActivity {
    private static int SCREEN_TIME =3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Intro1Activity.class);
                startActivity(intent);
                finish();
            }
        }, SCREEN_TIME);
    }


}