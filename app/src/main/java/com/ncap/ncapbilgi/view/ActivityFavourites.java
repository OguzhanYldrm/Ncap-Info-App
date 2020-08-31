package com.ncap.ncapbilgi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.adaptor.BrandAdaptor;
import com.ncap.ncapbilgi.adaptor.FavouriteAdaptor;
import com.ncap.ncapbilgi.model.Ncap;
import com.ncap.ncapbilgi.utils.NcapUtils;

import java.util.ArrayList;
import java.util.List;

public class ActivityFavourites extends AppCompatActivity {

    private static RecyclerView favList;
    private static List<Ncap> favs;
    private static FavouriteAdaptor favouriteAdaptor;
    private static RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);



        Toolbar toolbar = findViewById(R.id.middle_page_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.back_icon:
                        Intent intent = new Intent(ActivityFavourites.this, ActivityMain.class);
                        ActivityFavourites.this.startActivity(intent);
                        break;
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);


        favs = NcapUtils.getFavourites(getApplicationContext());
        favList = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        favList.setLayoutManager(layoutManager);
        favList.setItemAnimator(new DefaultItemAnimator());
        favouriteAdaptor = new FavouriteAdaptor(ActivityFavourites.this, favs);
        favList.setAdapter(favouriteAdaptor);

    }

    public static void removeFromRecyclerView(int position){

        favs.remove(position);
        layoutManager.removeViewAt(position);
        favouriteAdaptor.notifyItemRemoved(position);
        favouriteAdaptor.notifyItemRangeChanged(position,favs.size());

    }
}