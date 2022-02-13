package com.ncap.ncapbilgi.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Ncap;
import com.ncap.ncapbilgi.service.NcapApi;
import com.ncap.ncapbilgi.utils.NcapUtils;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityNcap extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static List<CarouselItem> imageList;
    private static ImageCarousel carousel;
    private static Bundle bundle;
    private static List<Ncap> favouriteList;
    private static boolean favExistance;
    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncap);

       // Getting vehicle id from previous page
        Intent intent = getIntent();
        final String vehicleId = intent.getStringExtra("id");


        carousel = findViewById(R.id.carousel);
        imageList = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        favouriteList = new ArrayList<>();
        favExistance = false;

        favouriteList = NcapUtils.getFavourites(getApplicationContext());
        //check fav
        for (Ncap favCar:favouriteList
        ) {
            if (favCar.getVehicleId().equals(vehicleId)){
                favExistance = true;
            }
        }

        //Toolbar
        Toolbar toolbar = findViewById(R.id.ncap_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(ActivityNcap.this, ActivityMain.class);
                    ActivityNcap.this.startActivity(intent);
                }
                else if (item.getItemId() == R.id.favourite){
                    if (favExistance){
                        Toast.makeText(getApplicationContext(),"Already Added To Favourite",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        NcapUtils.setFavourites(favouriteList,getApplicationContext());
                        favExistance = true;
                        Toast.makeText(getApplicationContext(),"Added To Favourite",Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_ncap);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        NcapApi.getNcapData(ActivityNcap.this, vehicleId);
    }


    public static void setNcaps(Ncap carNcap) {
        progressBar.setVisibility(View.GONE);
        if (!favExistance){
            favouriteList.add(carNcap);
        }


        bundle = new Bundle();
        bundle.putSerializable("ncapData", (Serializable) carNcap);

        //Adding images
        imageList.add(NcapUtils.imageValidator(carNcap.getVehiclePicture(), carNcap.getVehicleDescription()));

        imageList.add(NcapUtils.imageValidator(carNcap.getFrontCrashPicture(), carNcap.getVehicleDescription()));

        imageList.add(NcapUtils.imageValidator(carNcap.getSideCrashPicture(), carNcap.getVehicleDescription()));

        imageList.add(NcapUtils.imageValidator(carNcap.getSidePolePicture(), carNcap.getVehicleDescription()));

        carousel.addData(imageList);


        final GeneralFragment generalFragment = new GeneralFragment();
        generalFragment.setArguments(bundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,generalFragment);
        fragmentTransaction.commit();

        carousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i, int i1, CarouselItem carouselItem) {

                switch (i1){
                    case 0:
                        GeneralFragment generalFragment1 = new GeneralFragment();
                        generalFragment1.setArguments(bundle);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout,generalFragment1);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        FrontFragment frontFragment = new FrontFragment();
                        frontFragment.setArguments(bundle);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout,frontFragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        SideFragment sideFragment = new SideFragment();
                        sideFragment.setArguments(bundle);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout,sideFragment);
                        fragmentTransaction.commit();
                        break;
                    case 3:
                        SidePoleFragment sidePoleFragment = new SidePoleFragment();
                        sidePoleFragment.setArguments(bundle);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout,sidePoleFragment);
                        fragmentTransaction.commit();
                        break;

                    default:
                        generalFragment.setArguments(bundle);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout,generalFragment);
                        fragmentTransaction.commit();
                        break;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i1) {


            }
        });
    }
}