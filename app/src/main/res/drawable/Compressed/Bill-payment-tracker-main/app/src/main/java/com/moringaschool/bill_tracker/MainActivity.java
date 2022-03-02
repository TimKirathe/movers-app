package com.moringaschool.bill_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import java.text.BreakIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    @BindView(R.id.password) EditText mPassword;
    @BindView(R.id.username) EditText mUsername;
    @BindView(R.id.loginbutton) MaterialButton mLoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v==mLoginButton) {
//                    TODO functionalise the username
                    String username =mUsername.getText().toString();
                    String password =mPassword.getText().toString();
//                passing data from Dashboard activity
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                    onBackPressed();
                }
            }

}