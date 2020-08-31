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
import com.ncap.ncapbilgi.adaptor.ModelAdaptor;
import com.ncap.ncapbilgi.model.Model;
import com.ncap.ncapbilgi.service.NcapApi;

import java.util.ArrayList;

public class ActivityModel extends AppCompatActivity {

    private static RecyclerView modelList;
    private static ModelAdaptor modelAdaptor;
    private static ArrayList<Model> models;
    private static String modelYear;
    private static String brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

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

                switch (item.getItemId()){

                    case R.id.back_icon:
                        Intent intent = new Intent(ActivityModel.this, ActivityBrand.class);
                        intent.putExtra("givenYear",modelYear);
                        ActivityModel.this.startActivity(intent);
                        break;
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);




        modelList = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        modelList.setLayoutManager(layoutManager);
        modelList.setItemAnimator(new DefaultItemAnimator());

        NcapApi.getModels(ActivityModel.this,modelYear,brand);

    }

    public static void setModels(Activity activity, ArrayList<Model> givenModels) {
        models = new ArrayList<>(givenModels);
        modelAdaptor = new ModelAdaptor(activity,models,modelYear,brand);
        modelList.setAdapter(modelAdaptor);

        modelAdaptor.notifyDataSetChanged();

    }
}