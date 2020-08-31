package com.ncap.ncapbilgi.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @Nullable
    @SerializedName("VehicleId")
    private String vehicleId;

    @Nullable
    @SerializedName("VehicleDescription")
    private String vehicleDescription;

    @Nullable
    public String getVehicleId() {
        return vehicleId;
    }

    @Nullable
    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleId(@Nullable String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleDescription(@Nullable String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }
}