package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.bedsitter) CardView bedsitter;
    @BindView(R.id.apartment) CardView apartment;
    @BindView(R.id.non_bungalow) CardView non_bungalow;
    @BindView(R.id.bungalow) CardView bungalow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        bedsitter.setOnClickListener(this);
        apartment.setOnClickListener(this);
        non_bungalow.setOnClickListener(this);
        bungalow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == bedsitter){
            Intent intent = new Intent(ServiceActivity.this, QuotationActivity.class);
            startActivity(intent);
        }else if(view == apartment){
            Intent intent = new Intent(ServiceActivity.this, ApartmentSurveyActivity.class);
            startActivity(intent);
        }else if(view == non_bungalow){
            Intent intent = new Intent(ServiceActivity.this, Non_BungalowSurveyActivity.class);
            startActivity(intent);
        }else if(view == bungalow){
            Intent intent = new Intent(ServiceActivity.this, BungalowSurveyActivity.class);
            startActivity(intent);
        }

    }
}