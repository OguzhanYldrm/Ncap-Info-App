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
import com.ncap.ncapbilgi.adapter.BrandAdapter;
import com.ncap.ncapbilgi.model.Brand;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.ArrayList;

public class ActivityBrand extends AppCompatActivity {

    private static RecyclerView brandList;
    private static BrandAdapter brandAdapter;
    private static ArrayList<Brand> brands;
    private static String modelYear;
    private AdView mAdView;
    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
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


        Toolbar toolbar = findViewById(R.id.middle_page_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.back_icon:
                        Intent intent = new Intent(ActivityBrand.this, ActivityMain.class);
                        ActivityBrand.this.startActivity(intent);
                        break;
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);




        brandList = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        brandList.setLayoutManager(layoutManager);
        brandList.setItemAnimator(new DefaultItemAnimator());

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        NcapApi.getBrands(ActivityBrand.this,modelYear);

    }

    public static void setBrands(Activity activity, ArrayList<Brand> givenBrands) {
        progressBar.setVisibility(View.GONE);
        brands = new ArrayList<>(givenBrands);
        brandAdapter = new BrandAdapter(activity,brands,modelYear);
        brandList.setAdapter(brandAdapter);
        brandAdapter.notifyDataSetChanged();

    }
}