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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.email) TextInputLayout memail;
    @BindView(R.id.password) TextInputLayout mpassword;
    @BindView(R.id.arrow) ImageView marrow;
    @BindView(R.id.login) Button mlogin;
    @BindView(R.id.createaccountclick) TextView mcreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mlogin.setOnClickListener(this);
        mcreate.setOnClickListener(this);
        marrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mlogin){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else if (view == mcreate){
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();

        }else if (view == marrow){
            onBackPressed();

    }
}
}