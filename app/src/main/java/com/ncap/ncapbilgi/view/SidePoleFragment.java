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

public class SidePoleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_side_pole,container,false);

        //Get args
        Bundle args = getArguments();
        Ncap givenNcapData = (Ncap) args.getSerializable("ncapData");
        final String URL = givenNcapData.getSidePoleVideo();

        Bundle bundle = new Bundle();
        bundle.putString("url", URL);

        final VideoFragment videoFragment = new VideoFragment();
        videoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.video_frame,videoFragment);
        fragmentTransaction.commit();


        RatingBar side_pole_overall_rating = rootView.findViewById(R.id.side_pole_overall_rating);

        side_pole_overall_rating.setRating(Float.parseFloat(givenNcapData.getSidePoleCrashRating()));


        return rootView;
    }

}