package com.moringaschool.moversapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.moringaschool.moversapp.Adapters.HistoryAdapters;
import com.moringaschool.moversapp.Constants;
import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.Booking;
import com.moringaschool.moversapp.models.ResponseBookingList;
import com.moringaschool.moversapp.models.ResponseList;
import com.moringaschool.moversapp.models.ResponseObject;
import com.moringaschool.moversapp.models.ResponseUser;
import com.moringaschool.moversapp.models.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.direction) ImageView arrow;
    @BindView(R.id.bookingsRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.historyNameTextView)
    TextView mNameTextView;
    @BindView(R.id.historyEmailTextView) TextView mEmailTextView;

    private SharedPreferences mPreferences;
    private String email;
    private List<Booking> bookings;

    private MoversApi moversApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        showProgressBar();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(HistoryActivity.this);

        email = mPreferences.getString(Constants.SHARED_PREFERENCES_EMAIL, null);
        arrow.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        moversApi = MoversClient.getClient();

        findUser(email);
    }

    private void findUser(String userEmail) {

        Call<ResponseUser> call = moversApi.getUserByEmail(userEmail);

        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                User user = response.body().getData();
                mNameTextView.setText(user.getName());
                mEmailTextView.setText(user.getEmail());

                getAllUserBookings(String.valueOf(user.getId()));
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Sorry there has been an error.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getAllUserBookings(String userId) {

        Call<ResponseBookingList> call2 = moversApi.getAllBookingsForUser(userId);

        call2.enqueue(new Callback<ResponseBookingList>() {
            @Override
            public void onResponse(Call<ResponseBookingList> call, Response<ResponseBookingList> response) {
                Log.e("Response: ", response.raw().toString());
                bookings = response.body().getData();
                Toast.makeText(HistoryActivity.this, "" + bookings.size(), Toast.LENGTH_SHORT).show();
                HistoryAdapters adapters = new HistoryAdapters(HistoryActivity.this, bookings);
                recyclerView.setAdapter(adapters);

                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ResponseBookingList> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Sorry there has been an error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == arrow){
            onBackPressed();
        }

    }

    private void showProgressBar() {
    }

    private void hideProgressBar() {
    }
}