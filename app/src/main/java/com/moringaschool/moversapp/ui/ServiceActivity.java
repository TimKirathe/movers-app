package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.onebedroom) CardView onebedroom;
    @BindView(R.id.twobedroom) CardView twobedroom;
    @BindView(R.id.threebedroom) CardView threebedroom;
    @BindView(R.id.fourbedroom) CardView fourbedroom;
    @BindView(R.id.fivebedroom) CardView fivebedroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        onebedroom.setOnClickListener(this);
        twobedroom.setOnClickListener(this);
        threebedroom.setOnClickListener(this);
        fourbedroom.setOnClickListener(this);
        fivebedroom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == onebedroom){
            Intent intent =new Intent(ServiceActivity.this, MapActivity.class);
            startActivity(intent);
        }else if(view == twobedroom){
            Intent intent =new Intent(ServiceActivity.this, MapActivity.class);
            startActivity(intent);
        }else if(view == threebedroom){
            Intent intent =new Intent(ServiceActivity.this, MapActivity.class);
            startActivity(intent);
        }else if(view == fourbedroom){
            Intent intent =new Intent(ServiceActivity.this, MapActivity.class);
            startActivity(intent);
        }else if(view == fivebedroom){
            Intent intent =new Intent(ServiceActivity.this, MapActivity.class);
            startActivity(intent);
        }


    }
}