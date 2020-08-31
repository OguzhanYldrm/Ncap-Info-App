package com.ncap.ncapbilgi.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Brand {

    @SerializedName("Make")
    private String brand;

    @Nullable
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}