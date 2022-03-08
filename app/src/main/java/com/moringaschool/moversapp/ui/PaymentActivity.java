package com.moringaschool.moversapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.moringaschool.moversapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.paypal) CardView paypal;
    @BindView(R.id.card) CardView card;
    @BindView(R.id.mpesa) CardView mpesa;
    @BindView(R.id.pay) CardView pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        paypal.setOnClickListener(this);
        card.setOnClickListener(this);
        mpesa.setOnClickListener(this);
        pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}