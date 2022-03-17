package com.moringaschool.moversapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.moringaschool.moversapp.R;
import com.moringaschool.moversapp.databinding.ActivitySourceMapBinding;
import com.moringaschool.moversapp.models.Booking;
import com.moringaschool.moversapp.models.Service;

import java.io.IOException;

public class SourceMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySourceMapBinding binding;
    Service service;
    String locationString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        service = (Service) intent.getSerializableExtra("Service");

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng nairobi = new LatLng(-1.286389, 36.817223);
        mMap.addMarker(new MarkerOptions().position(nairobi).title("Marker in Nairobi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nairobi, 14f));

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        new AlertDialog.Builder(SourceMapActivity.this)
                                .setCancelable(false)
                                .setTitle("Set location")
                                .setMessage("Select exactly where you live.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();

                        mMap.setMyLocationEnabled(true);
                        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                            @Override
                            public void onMyLocationChange(@NonNull Location location) {
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f));
                                mMap.setOnMyLocationChangeListener(null);
                            }
                        });

                        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                            @Override
                            public void onMapClick(@NonNull LatLng latLng) {
                                Geocoder geocoder = new Geocoder(SourceMapActivity.this);
                                try {
                                    Address address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0);
                                    locationString = address.getCountryName() + ", " + address.getLocality();
                                    Toast.makeText(SourceMapActivity.this, address.getCountryName() + ", " + address.getLocality() + ", " + address.getSubLocality() + ", " + address.getPremises(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(SourceMapActivity.this, "Map Clicked!", Toast.LENGTH_SHORT).show();
                                mMap.clear();

                                mMap.addMarker(new MarkerOptions().position(latLng));

                                new AlertDialog.Builder(SourceMapActivity.this)
                                        .setCancelable(false)
                                        .setTitle("Select this location")
                                        .setMessage("Is this where you live?")
                                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                Intent intent2 = new Intent(SourceMapActivity.this, DestinationMapActivity.class);
                                                intent2.putExtra("Service", service);
                                                Booking booking = new Booking();
                                                booking.setLatFrom(String.valueOf(latLng.latitude));
                                                booking.setLongFrom(String.valueOf(latLng.longitude));
                                                booking.setUserId(1);
                                                intent2.putExtra("Booking", booking);
                                                intent2.putExtra("Location String", locationString);
                                                startActivity(intent2);
                                            }
                                        })
                                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        })
                                        .create().show();

                                Toast.makeText(SourceMapActivity.this, "Latitude: " + latLng.latitude, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(SourceMapActivity.this, "Permission Required to continue", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }
}