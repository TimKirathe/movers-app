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

import androidx.cardview.widget.CardView;

import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.clients.MoversClient;
import com.moringaschool.moversapp.interfaces.MoversApi;
import com.moringaschool.moversapp.models.ServiceResponse;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ServiceActivity.class.getSimpleName();

    @BindView(R.id.onebedroom) CardView oneBedroom;
    @BindView(R.id.twobedroom) CardView twoBedroom;
    @BindView(R.id.threebedroom) CardView threeBedroom;
    @BindView(R.id.fourbedroom) CardView fourBedroom;
    @BindView(R.id.fivebedroom) CardView fiveBedroom;

    @BindView(R.id.imageonebedroom) ImageView oneBedroomImageView;
    @BindView(R.id.imagetwobedroom) ImageView twoBedroomImageView;
    @BindView(R.id.imagethreebedroom) ImageView threeBedroomImageView;
    @BindView(R.id.imagefourbedroom) ImageView fourBedroomImageView;
    @BindView(R.id.imagefivebedroom) ImageView fiveBedroomImageView;

    private MoversApi moversApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        showProgressBar();

        moversApi = MoversClient.getClient();

        getServices();
        oneBedroom.setOnClickListener(this);
        twoBedroom.setOnClickListener(this);
        threeBedroom.setOnClickListener(this);
        fourBedroom.setOnClickListener(this);
        fiveBedroom.setOnClickListener(this);
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
                Picasso.get().load(imageUrl).into(oneBedroomImageView);
                Picasso.get().load(imageUrl).into(twoBedroomImageView);
                Picasso.get().load(imageUrl).into(threeBedroomImageView);
                Picasso.get().load(imageUrl).into(fourBedroomImageView);
                Picasso.get().load(imageUrl).into(fiveBedroomImageView);


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
        if(view == oneBedroom){
            Intent intent =new Intent(ServiceActivity.this, SourceMapActivity.class);
            startActivity(intent);
        }else if(view == twoBedroom){
            Intent intent =new Intent(ServiceActivity.this, SourceMapActivity.class);
            startActivity(intent);
        }else if(view == threeBedroom){
            Intent intent =new Intent(ServiceActivity.this, SourceMapActivity.class);
            startActivity(intent);
        }else if(view == fourBedroom){
            Intent intent =new Intent(ServiceActivity.this, SourceMapActivity.class);
            startActivity(intent);
        }else if(view == fiveBedroom){
            Intent intent =new Intent(ServiceActivity.this, SourceMapActivity.class);
            startActivity(intent);
        }


    }
}