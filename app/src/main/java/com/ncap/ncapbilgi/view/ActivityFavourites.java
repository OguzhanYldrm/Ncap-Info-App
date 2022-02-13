package com.ncap.ncapbilgi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.adapter.FavouriteAdapter;
import com.ncap.ncapbilgi.model.Ncap;
import com.ncap.ncapbilgi.utils.NcapUtils;

import java.util.List;

public class ActivityFavourites extends AppCompatActivity {

    private static RecyclerView favList;
    private static List<Ncap> favs;
    private static FavouriteAdapter favouriteAdapter;
    private static RecyclerView.LayoutManager layoutManager;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        MobileAds.initialize(this, initializationStatus -> {

        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Toolbar toolbar = findViewById(R.id.middle_page_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()){

                case R.id.back_icon:
                    Intent intent = new Intent(ActivityFavourites.this, ActivityMain.class);
                    ActivityFavourites.this.startActivity(intent);
                    break;
            }
            return true;
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);


        favs = NcapUtils.getFavourites(getApplicationContext());
        favList = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        favList.setLayoutManager(layoutManager);
        favList.setItemAnimator(new DefaultItemAnimator());
        favouriteAdapter = new FavouriteAdapter(ActivityFavourites.this, favs);
        favList.setAdapter(favouriteAdapter);

    }

    public static void removeFromRecyclerView(int position){

        favs.remove(position);
        layoutManager.removeViewAt(position);
        favouriteAdapter.notifyItemRemoved(position);
        favouriteAdapter.notifyItemRangeChanged(position,favs.size());

    }
}