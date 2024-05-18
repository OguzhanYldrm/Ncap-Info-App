package com.ncap.ncapbilgi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.adapter.VehicleAdapter;
import com.ncap.ncapbilgi.model.Vehicle;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.ArrayList;

public class ActivityVehicle extends AppCompatActivity {

    private static RecyclerView vehicleList;
    private static VehicleAdapter vehicleAdapter;
    private static ArrayList<Vehicle> vehicles;
    private String modelYear;
    private String brand;
    private String model;
    private AdView mAdView;
    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        modelYear = intent.getStringExtra("givenYear");
        brand = intent.getStringExtra("brand");
        model = intent.getStringExtra("model");

        Toolbar toolbar = findViewById(R.id.middle_page_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.back_icon) {
                        Intent intent = new Intent(ActivityVehicle.this, ActivityModel.class);
                        intent.putExtra("givenYear",modelYear);
                        intent.putExtra("brand",brand);
                        ActivityVehicle.this.startActivity(intent);
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);


        vehicleList = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        vehicleList.setLayoutManager(layoutManager);
        vehicleList.setItemAnimator(new DefaultItemAnimator());

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        NcapApi.getVehicleId(ActivityVehicle.this,modelYear,brand,model);

    }

    public static void setVehicles(Activity activity, ArrayList<Vehicle> givenVehicles) {
        progressBar.setVisibility(View.GONE);
        vehicles = new ArrayList<>(givenVehicles);
        vehicleAdapter = new VehicleAdapter(activity,vehicles);
        vehicleList.setAdapter(vehicleAdapter);

        vehicleAdapter.notifyDataSetChanged();

    }
}