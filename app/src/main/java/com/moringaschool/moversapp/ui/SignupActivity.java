package com.moringaschool.moversapp.ui;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.name) TextInputLayout name;
    @BindView(R.id.email) TextInputLayout email;
    @BindView(R.id.password) TextInputLayout password;
    @BindView(R.id.confirmpassword) TextInputLayout confirmPassword;
    @BindView(R.id.arrow) ImageView arrow;
    @BindView(R.id.signup) Button signup;
    @BindView(R.id.loginClick) TextView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        arrow.setOnClickListener(this);
        signup.setOnClickListener(this);
        loginView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == loginView){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

    }else if (view == signup){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
    } else if(view == arrow){
            onBackPressed();

    }
    }
}