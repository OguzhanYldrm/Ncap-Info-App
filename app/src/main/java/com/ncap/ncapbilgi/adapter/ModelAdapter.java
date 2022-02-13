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
import com.ncap.ncapbilgi.model.Model;
import com.ncap.ncapbilgi.view.ActivityVehicle;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {

    private ArrayList<Model> models;
    private Activity context;
    private String modelYear;
    private String brand;

    public ModelAdapter(Activity context, ArrayList<Model> models, String modelYear, String brand) {
        this.models = models;
        this.context = context;
        this.modelYear = modelYear;
        this.brand = brand;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, ActivityVehicle.class);
            intent.putExtra("brand",brand);
            intent.putExtra("givenYear",modelYear);
            intent.putExtra("model",models.get(myViewHolder.getAdapterPosition()).getModel());
            context.startActivity(intent);
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.modelText.setText(models.get(position).getModel());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView modelText;

        public MyViewHolder(View view) {
            super(view);
            modelText = view.findViewById(R.id.tvValue);

        }
    }
}
