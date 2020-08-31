package com.ncap.ncapbilgi.adaptor;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Model;
import com.ncap.ncapbilgi.model.Ncap;
import com.ncap.ncapbilgi.utils.NcapUtils;
import com.ncap.ncapbilgi.view.ActivityFavourites;
import com.ncap.ncapbilgi.view.ActivityVehicle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdaptor extends RecyclerView.Adapter<FavouriteAdaptor.MyViewHolder> {

    private List<Ncap> favs;
    private Activity context;

    public FavouriteAdaptor(Activity context, List<Ncap> favs) {
        this.favs = favs;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favourites, viewGroup, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdaptor.MyViewHolder viewHolder, final int position) {
        Picasso.get().load(favs.get(position).getVehiclePicture()).into(viewHolder.carImage);
        viewHolder.carDescription.setText(favs.get(position).getVehicleDescription());
        viewHolder.carRating.setRating(Float.parseFloat(favs.get(position).getOverallRating()));

        viewHolder.removeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Ncap> tempFavs = new ArrayList<>(favs);
                NcapUtils.removeFavourite(tempFavs.get(position),context);
                ActivityFavourites.removeFromRecyclerView(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return favs.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView carImage;
        private TextView carDescription;
        private RatingBar carRating;
        private ImageView removeIcon;

        public MyViewHolder(View view) {
            super(view);
            carImage = view.findViewById(R.id.car_image);
            carDescription = view.findViewById(R.id.car_description);
            carRating = view.findViewById(R.id.car_rating);
            removeIcon = view.findViewById(R.id.remove_icon);
        }
    }
}
