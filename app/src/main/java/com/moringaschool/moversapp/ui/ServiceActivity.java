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
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.ServiceResponse;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ServiceActivity.class.getSimpleName();
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private MoversApi moversApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        showProgressBar();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        moversApi = MoversClient.getClient();

        getServices();
    }

    private void showProgressBar() {
    }

    private void getServices() {
        Call<ServiceResponse> call = moversApi.getServices();

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                Log.e(TAG, response.raw().toString());
                Toast.makeText(ServiceActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                String imageUrl = response.body().getData().get(0).getPhotoLink();


                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Toast.makeText(ServiceActivity.this, "There has been an error.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
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

    @Override
    public void onClick(View view) {

    }
}