package com.moringaschool.moversapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Intro3Activity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.one) ImageView tab1;
    @BindView(R.id.two) ImageView tab2;
    @BindView(R.id.three) ImageView tab3;
    @BindView(R.id.skip) Button skip;
    @BindView(R.id.getstarted) Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro3);
        ButterKnife.bind(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        skip.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == tab1){
            Intent intent =new Intent(Intro3Activity.this,Intro1Activity.class);
            startActivity(intent);
        }else if(view == tab2){
            Intent intent =new Intent(Intro3Activity.this,Intro2Activity.class);
            startActivity(intent);
        }else if(view == tab3){
            Intent intent =new Intent(Intro3Activity.this,Intro3Activity.class);
            startActivity(intent);
        }else if(view == skip){
            Intent intent =new Intent(Intro3Activity.this,LoginActivity.class);
            startActivity(intent);
        }else  if(view == start){
            Intent intent =new Intent(Intro3Activity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}