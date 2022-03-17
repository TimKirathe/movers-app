package com.moringaschool.moversapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.moversapp.Constants;
import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.Booking;
import com.moringaschool.moversapp.models.Calculate;
import com.moringaschool.moversapp.models.Invoice;
import com.moringaschool.moversapp.models.PostInvoiceResponse;
import com.moringaschool.moversapp.models.ResponseDistance;
import com.moringaschool.moversapp.models.ResponseUser;
import com.moringaschool.moversapp.models.Service;
import com.moringaschool.moversapp.models.User;

import java.sql.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = QuotationActivity.class.getSimpleName();

    @BindView(R.id.declinebutton) LinearLayout decline;
    @BindView(R.id.acceptbutton) LinearLayout accept;
//    @BindView(R.id.direction) LinearLayout arrow;
    @BindView(R.id.movingFromTextView) TextView mMovingFromTextView;
    @BindView(R.id.movingToTextView) TextView mMovingToTextView;
    @BindView(R.id.totalPriceTextView) TextView mTotalPriceTextView;

    private MoversApi moversApi;
    private String locationString1;
    private String locationString2;
    private Service service;
    private Booking booking;
    private User user;
    private Calculate calculate;
    private int totalPrice;
    private String dateString;
    private int createdInvoiceId;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        ButterKnife.bind(this);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(QuotationActivity.this);


        accept.setOnClickListener(this);
        decline.setOnClickListener(this);
//        arrow.setOnClickListener(this);

        Intent intent = getIntent();
        locationString1 = intent.getStringExtra("Location String1");
        locationString2 = intent.getStringExtra("Location String2");
        service = (Service) intent.getSerializableExtra("Service");
        booking = (Booking) intent.getSerializableExtra("Booking");
        calculate = new Calculate(booking.getLatFrom(), booking.getLongFrom(), booking.getLatTo(), booking.getLongTo());
        calculate.setId(service.getId());

        showProgressBar();
        moversApi = MoversClient.getClient();

        getDistancePrice();
    }

    private void getDistancePrice() {

        Call<ResponseDistance> call = moversApi.calculateDistance(calculate);

        call.enqueue(new Callback<ResponseDistance>() {
            @Override
            public void onResponse(Call<ResponseDistance> call, Response<ResponseDistance> response) {
                Double totalPriceDouble = response.body().getData();
                totalPrice = totalPriceDouble.intValue();

                mMovingFromTextView.setText(locationString1);
                mMovingToTextView.setText(locationString2);
                mTotalPriceTextView.setText("" + totalPrice + "Ksh.");
                
                getUser();
            }

            @Override
            public void onFailure(Call<ResponseDistance> call, Throwable t) {
                Toast.makeText(QuotationActivity.this, "Response Failure!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });

    }

    private void getUser() {
        String userEmail = mPreferences.getString(Constants.SHARED_PREFERENCES_EMAIL, null);

        Call<ResponseUser> call2 = moversApi.getUserByEmail(userEmail);

        call2.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                user = response.body().getData();
                booking.setUserId(user.getId());
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                dateString = String.valueOf(date);
                booking.setDate(dateString);

                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });
    }

    private void createInvoice() {
        Invoice invoice = new Invoice(user.getId(), user.getName(), user.getEmail(), totalPrice, service.getNoOfBedRooms(), booking.getLatFrom(), booking.getLongFrom(), booking.getLatTo(), booking.getLongTo(), dateString);

        Call<PostInvoiceResponse> invoiceResponse = moversApi.postInvoice(invoice);

        invoiceResponse.enqueue(new Callback<PostInvoiceResponse>() {
            @Override
            public void onResponse(Call<PostInvoiceResponse> call, Response<PostInvoiceResponse> response) {
                Invoice createdInvoice = response.body().getData();
                createdInvoiceId = createdInvoice.getId();
                hideProgressBar2();

                Intent intent = new Intent(QuotationActivity.this, InvoiceActivity.class);
                intent.putExtra("InvoiceId", createdInvoiceId);
                intent.putExtra("Booking", booking);
                intent.putExtra("Location String1", locationString1);
                intent.putExtra("Location String2", locationString2);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostInvoiceResponse> call, Throwable t) {
                Toast.makeText(QuotationActivity.this, "Sorry, there's been an error.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void hideProgressBar() {
    }

    private void showProgressBar() {
    }

    @Override
    public void onClick(View view) {
        if(view == accept){
            showProgressBar2();
            createInvoice();
        }
        else if(view == decline){
            Intent intent = new Intent(QuotationActivity.this, ServiceActivity.class);
            startActivity(intent);
            finish();
        }
//        else if(view == arrow){
//                onBackPressed();
//        }

    }

    private void showProgressBar2() {
    }

    private void hideProgressBar2() {
    }
}