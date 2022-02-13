package com.ncap.ncapbilgi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Year;

import java.util.List;

public class ModelYearAdapter extends ArrayAdapter<Year> {

    private List<Year> years;
    private Activity context;

    public ModelYearAdapter(Activity context, List<Year> years) {
        super(context, R.layout.item_text,years);
        this.years = years;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View yearView = layoutInflater.inflate(R.layout.item_text, null, true);
        TextView yearText = yearView.findViewById(R.id.tvValue);
        yearText.setText(years.get(position).getYear());

        return yearView;
    }
}
