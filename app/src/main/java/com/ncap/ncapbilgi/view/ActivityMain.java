package com.ncap.ncapbilgi.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.adapter.ModelYearAdapter;
import com.ncap.ncapbilgi.model.Year;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.List;

public class ActivityMain extends AppCompatActivity {

    private static ListView listView;
    private long backPressedTime;
    Toast backToast;
    private AdView mAdView;
    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Animatoo.animateCard(this);

        Toolbar toolbar = findViewById(R.id.dashboard_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.menu_dashboard);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_favs:
                        Intent intent = new Intent(ActivityMain.this, ActivityFavourites.class);
                        ActivityMain.this.startActivity(intent);
                        break;
                }
                return true;
            }
        });

        listView = findViewById(R.id.listView);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        NcapApi.getYears(ActivityMain.this);

    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            finishAffinity();
            return;
        }
        else{

            backToast = Toast.makeText(ActivityMain.this,"Press Again to Exit.",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public static void setYears(final Activity activity, final List<Year> yearList){
        progressBar.setVisibility(View.GONE);
            //get year list
            ModelYearAdapter modelYearAdapter = new ModelYearAdapter(activity, yearList);

            listView.setAdapter(modelYearAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(activity, ActivityBrand.class);
                    intent.putExtra("givenYear",yearList.get(i).getYear());
                    activity.startActivity(intent);
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard,menu);
        return true;
    }

    private void exitApp(){
        new AlertDialog.Builder(this)
                .setTitle("Exitting The App!")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                    }
                }).create().show();
    }
}