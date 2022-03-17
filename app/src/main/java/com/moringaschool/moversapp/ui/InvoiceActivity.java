package com.moringaschool.moversapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.Booking;
import com.moringaschool.moversapp.models.Invoice;
import com.moringaschool.moversapp.models.PostBookingResponse;
import com.moringaschool.moversapp.models.PostResponse;
import com.moringaschool.moversapp.models.ResponseInvoice;
import com.moringaschool.moversapp.models.ResponseObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.direction) ImageView arrow;
    @BindView(R.id.nameTextView)
    TextView mNameTextView;
    @BindView(R.id.emailTextView) TextView mEmailTextView;
    @BindView(R.id.textViewMovingFrom) TextView mTextViewMovingFrom;
    @BindView(R.id.textViewMovingTo) TextView mTextViewMovingTo;
    @BindView(R.id.textViewTotalPrice) TextView mTextViewTotalPrice;

    private Intent intent;
    private int invoiceId;
    private Booking booking;
    private Invoice invoice;
    String locationString1;
    String locationString2;

    private MoversApi moversApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        ButterKnife.bind(this);
        showProgressBar();

        arrow.setOnClickListener(this);

        intent = getIntent();
        invoiceId = intent.getIntExtra("InvoiceId", 0);
        booking = (Booking) intent.getSerializableExtra("Booking");
        locationString1 = intent.getStringExtra("Location String1");
        locationString2 = intent.getStringExtra("Location String2");

        moversApi = MoversClient.getClient();

        createBooking();

    }

    private void createBooking() {
        Call<PostBookingResponse> call = moversApi.postBooking(booking);

        call.enqueue(new Callback<PostBookingResponse>() {
            @Override
            public void onResponse(Call<PostBookingResponse> call, Response<PostBookingResponse> response) {
                getInvoice(invoiceId);
            }

            @Override
            public void onFailure(Call<PostBookingResponse> call, Throwable t) {

            }
        });

    }

    private void getInvoice(int invoiceId) {

        Call<ResponseInvoice> call2 = moversApi.getInvoiceById(String.valueOf(invoiceId));

        call2.enqueue(new Callback<ResponseInvoice>() {
            @Override
            public void onResponse(Call<ResponseInvoice> call, Response<ResponseInvoice> response) {
                invoice = response.body().getData();
                mNameTextView.setText(invoice.getName());
                mEmailTextView.setText(invoice.getEmail());
                mTextViewMovingFrom.setText(locationString1);
                mTextViewMovingTo.setText(locationString2);
                mTextViewTotalPrice.setText("" + invoice.getPrice());

                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ResponseInvoice> call, Throwable t) {

            }
        });

    }

    private void showProgressBar() {
    }

    private void hideProgressBar() {
    }

    @Override
    public void onClick(View view) {
        if(view == arrow){
            onBackPressed();
        }

    }
}