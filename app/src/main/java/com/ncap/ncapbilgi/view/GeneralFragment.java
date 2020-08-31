package com.ncap.ncapbilgi.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Ncap;

public class GeneralFragment extends Fragment {

    public GeneralFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_general,container,false);

        Bundle args = getArguments();
        Ncap givenNcapData = (Ncap) args.getSerializable("ncapData");

        RatingBar rating = rootView.findViewById(R.id.rating);
        TextView make = rootView.findViewById(R.id.make);
        TextView year = rootView.findViewById(R.id.year);
        TextView model = rootView.findViewById(R.id.model);
        TextView complaints = rootView.findViewById(R.id.complaints);
        TextView recalls = rootView.findViewById(R.id.recalls);

        rating.setRating(Float.parseFloat(givenNcapData.getOverallRating()));
        make.setText(givenNcapData.getMake());
        year.setText(givenNcapData.getModelYear());
        model.setText(givenNcapData.getModel());
        complaints.setText(givenNcapData.getComplaintsCount());
        recalls.setText(givenNcapData.getRecallsCount());

        return rootView;
    }
}