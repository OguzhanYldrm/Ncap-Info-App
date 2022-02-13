package com.ncap.ncapbilgi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Brand;
import com.ncap.ncapbilgi.view.ActivityModel;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {

    private ArrayList<Brand> brands;
    private Activity context;
    private String modelYear;

    public BrandAdapter(Activity context, ArrayList<Brand> brands, String modelYear) {
        this.brands = brands;
        this.context = context;
        this.modelYear = modelYear;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityModel.class);

                intent.putExtra("givenYear",modelYear);
                intent.putExtra("brand",brands.get(myViewHolder.getAdapterPosition()).getBrand());
                context.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.brandText.setText(brands.get(position).getBrand());

    }

    @Override
    public int getItemCount() {
        return brands.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView brandText;

        public MyViewHolder(View view) {
            super(view);
            brandText = view.findViewById(R.id.tvValue);

        }
    }
}
