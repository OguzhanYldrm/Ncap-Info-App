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
import com.ncap.ncapbilgi.adaptor.BrandAdaptor;
import com.ncap.ncapbilgi.model.Brand;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.ArrayList;

public class ActivityBrand extends AppCompatActivity {

    private static RecyclerView brandList;
    private static BrandAdaptor brandAdaptor;
    private static ArrayList<Brand> brands;
    private static String modelYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

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

        NcapApi.getBrands(ActivityBrand.this,modelYear);

    }

    public static void setBrands(Activity activity, ArrayList<Brand> givenBrands) {
        brands = new ArrayList<>(givenBrands);
        brandAdaptor = new BrandAdaptor(activity,brands,modelYear);
        brandList.setAdapter(brandAdaptor);

        brandAdaptor.notifyDataSetChanged();

    }
}