package com.ncap.ncapbilgi.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Model {

    @Nullable
    @SerializedName("Model")
    private String model;

    @Nullable
    public String getModel() {
        return model;
    }

    public void setModel(@Nullable String model) {
        this.model = model;
    }
}