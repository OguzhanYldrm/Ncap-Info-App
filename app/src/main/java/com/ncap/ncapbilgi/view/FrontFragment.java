package com.ncap.ncapbilgi.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;


import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Ncap;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.io.Serializable;
import java.util.ArrayList;


public class FrontFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_front,container,false);

        //Get args
        Bundle args = getArguments();
        final Ncap givenNcapData = (Ncap) args.getSerializable("ncapData");
        final String URL = givenNcapData.getFrontCrashVideo();


        Bundle bundle = new Bundle();
        bundle.putString("url", URL);

        final VideoFragment videoFragment = new VideoFragment();
        videoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.video_frame,videoFragment);
        fragmentTransaction.commit();


        RatingBar front_overall_rating = rootView.findViewById(R.id.front_overall_rating);
        RatingBar front_driver_rating = rootView.findViewById(R.id.front_driver_rating);
        RatingBar front_passenger_rating = rootView.findViewById(R.id.front_passenger_rating);

        front_overall_rating.setRating(Float.parseFloat(givenNcapData.getOverallFrontCrashRating()));
        front_driver_rating.setRating(Float.parseFloat(givenNcapData.getFrontCrashDriversideRating()));
        front_passenger_rating.setRating(Float.parseFloat(givenNcapData.getFrontCrashPassengersideRating()));


        return rootView;
    }

}



