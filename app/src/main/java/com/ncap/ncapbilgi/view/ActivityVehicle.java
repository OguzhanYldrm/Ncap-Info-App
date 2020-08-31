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

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.adaptor.VehicleAdaptor;
import com.ncap.ncapbilgi.model.Vehicle;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.ArrayList;

public class ActivityVehicle extends AppCompatActivity {

    private static RecyclerView vehicleList;
    private static VehicleAdaptor vehicleAdaptor;
    private static ArrayList<Vehicle> vehicles;
    private String modelYear;
    private String brand;
    private String model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

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

                switch (item.getItemId()){

                    case R.id.back_icon:
                        Intent intent = new Intent(ActivityVehicle.this, ActivityModel.class);
                        intent.putExtra("givenYear",modelYear);
                        intent.putExtra("brand",brand);
                        ActivityVehicle.this.startActivity(intent);
                        break;
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);


        vehicleList = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        vehicleList.setLayoutManager(layoutManager);
        vehicleList.setItemAnimator(new DefaultItemAnimator());

        NcapApi.getVehicleId(ActivityVehicle.this,modelYear,brand,model);

    }

    public static void setVehicles(Activity activity, ArrayList<Vehicle> givenVehicles) {
        vehicles = new ArrayList<>(givenVehicles);
        vehicleAdaptor = new VehicleAdaptor(activity,vehicles);
        vehicleList.setAdapter(vehicleAdaptor);

        vehicleAdaptor.notifyDataSetChanged();

    }
}