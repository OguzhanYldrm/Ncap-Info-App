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
import com.ncap.ncapbilgi.adapter.ModelAdapter;
import com.ncap.ncapbilgi.model.Model;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.ArrayList;

public class ActivityModel extends AppCompatActivity {

    private static RecyclerView modelList;
    private static ModelAdapter modelAdapter;
    private static ArrayList<Model> models;
    private static String modelYear;
    private static String brand;
    private AdView mAdView;
    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
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

        Toolbar toolbar = findViewById(R.id.middle_page_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.back_icon) {
                    Intent intent = new Intent(ActivityModel.this, ActivityBrand.class);
                    intent.putExtra("givenYear",modelYear);
                    ActivityModel.this.startActivity(intent);
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);




        modelList = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        modelList.setLayoutManager(layoutManager);
        modelList.setItemAnimator(new DefaultItemAnimator());

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        NcapApi.getModels(ActivityModel.this,modelYear,brand);

    }

    public static void setModels(Activity activity, ArrayList<Model> givenModels) {
        progressBar.setVisibility(View.GONE);
        models = new ArrayList<>(givenModels);
        modelAdapter = new ModelAdapter(activity,models,modelYear,brand);
        modelList.setAdapter(modelAdapter);

        modelAdapter.notifyDataSetChanged();

    }
}