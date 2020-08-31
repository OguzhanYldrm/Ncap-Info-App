package com.ncap.ncapbilgi.adaptor;

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

public class ModelYearAdaptor extends ArrayAdapter<Year> {

    private List<Year> years;
    private Activity context;

    public ModelYearAdaptor(Activity context, List<Year> years) {
        super(context, R.layout.item_year,years);
        this.years = years;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View yearView = layoutInflater.inflate(R.layout.item_year, null, true);
        TextView yearText = yearView.findViewById(R.id.customTextView);
        yearText.setText(years.get(position).getYear());

        return yearView;
    }
}
