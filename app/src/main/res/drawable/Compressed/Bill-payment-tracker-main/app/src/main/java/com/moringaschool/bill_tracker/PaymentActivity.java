package com.moringaschool.bill_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity  extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent= getIntent();
        String payment = intent.getStringExtra("payment");
    }
}
