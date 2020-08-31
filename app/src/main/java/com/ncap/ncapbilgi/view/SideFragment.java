package com.ncap.ncapbilgi.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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

import java.util.ArrayList;

public class SideFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_side,container,false);

        //Get args
        Bundle args = getArguments();
        final Ncap givenNcapData = (Ncap) args.getSerializable("ncapData");
        final String URL = givenNcapData.getSideCrashVideo();

        Bundle bundle = new Bundle();
        bundle.putString("url", URL);

        final VideoFragment videoFragment = new VideoFragment();
        videoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.video_frame,videoFragment);
        fragmentTransaction.commit();

        RatingBar side_overall_rating = rootView.findViewById(R.id.side_overall_rating);
        RatingBar side_driver_rating = rootView.findViewById(R.id.side_driver_rating);
        RatingBar side_passenger_rating = rootView.findViewById(R.id.side_passenger_rating);


        side_overall_rating.setRating(Float.parseFloat(givenNcapData.getOverallSideCrashRating()));
        side_driver_rating.setRating(Float.parseFloat(givenNcapData.getSideCrashDriversideRating()));
        side_passenger_rating.setRating(Float.parseFloat(givenNcapData.getSideCrashPassengersideRating()));



        return rootView;
    }

}