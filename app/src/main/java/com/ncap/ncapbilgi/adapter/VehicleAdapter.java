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
import com.ncap.ncapbilgi.model.Vehicle;
import com.ncap.ncapbilgi.view.ActivityNcap;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

    private ArrayList<Vehicle> vehicles;
    private Activity context;

    public VehicleAdapter(Activity context, ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityNcap.class);
                intent.putExtra("id",vehicles.get(myViewHolder.getAdapterPosition()).getVehicleId());
                context.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.VehicleDescriptionText.setText(vehicles.get(position).getVehicleDescription());

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView VehicleDescriptionText;


        public MyViewHolder(View view) {
            super(view);
            VehicleDescriptionText = view.findViewById(R.id.tvValue);

        }
    }
}
