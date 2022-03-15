package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuotationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.acceptbtn) LinearLayout accept;
    @BindView(R.id.declinebtn) LinearLayout decline;
    @BindView(R.id.direction) LinearLayout arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        ButterKnife.bind(this);
        accept.setOnClickListener(this);
        decline.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view ==accept){
            Intent intent =new Intent(QuotationActivity.this, InvoiceActivity.class);
            startActivity(intent);
            finish();
        }else if(view == decline){
            Intent intent =new Intent(QuotationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(view == arrow){
                onBackPressed();
        }

    }
}