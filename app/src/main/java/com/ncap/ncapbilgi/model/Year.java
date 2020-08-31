package com.ncap.ncapbilgi.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Year {

    @Nullable
    @SerializedName("ModelYear")
    private String year;

    @Nullable
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}