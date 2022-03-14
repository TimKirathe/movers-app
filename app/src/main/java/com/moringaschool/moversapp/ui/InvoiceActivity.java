package com.moringaschool.moversapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.direction) ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        ButterKnife.bind(this);
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == arrow){
            onBackPressed();
        }

    }
}