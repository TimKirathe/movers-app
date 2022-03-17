package com.moringaschool.moversapp.ui;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.moversapp.Adapters.ServiceAdapter;
import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.Service;
import com.moringaschool.moversapp.models.ServiceResponse;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = ServiceActivity.class.getSimpleName();

    @BindView(R.id.recyclerView1) RecyclerView recyclerView;
    @BindView(R.id.viewHistoryImageView)
    ImageView mImageView;

    private MoversApi moversApi;
    private ArrayList<Service> services;

    ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        showProgressBar();

        moversApi = MoversClient.getClient();

        getServices();

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        Toast.makeText(this, "Adapter Setting", Toast.LENGTH_SHORT).show();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ServiceActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        serviceAdapter = new ServiceAdapter(ServiceActivity.this, services);
        recyclerView.setAdapter(serviceAdapter);
    }

    private void getServices() {

        Call<ServiceResponse> call = moversApi.getServices();

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                services = (ArrayList<Service>) response.body().getData();
                Toast.makeText(ServiceActivity.this, "" + services.size(), Toast.LENGTH_SHORT).show();
                hideProgressBar();
                setAdapter();
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(ServiceActivity.this, "There has been an error.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });

    }

    private void showProgressBar() {

    }

    private void hideProgressBar() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Intent intent = new Intent(ServiceActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}