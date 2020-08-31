package com.ncap.ncapbilgi.model;

import java.io.Serializable;

public class Ncap implements Serializable {

    //Overall
    private  String vehicleId;
    private String vehicleDescription;
    private String vehiclePicture;
    private String overallRating;
    private String modelYear;
    private String make;
    private String model;
    private String complaintsCount;
    private String recallsCount;

    //Front
    private String frontCrashPicture;
    private String frontCrashVideo;
    private String overallFrontCrashRating;
    private String frontCrashDriversideRating;
    private String frontCrashPassengersideRating;

    //Side
    private String sideCrashPicture;
    private String sideCrashVideo;
    private String overallSideCrashRating;
    private String sideCrashDriversideRating;
    private String sideCrashPassengersideRating;

    //Side Pole
    private String sidePolePicture;
    private String sidePoleVideo;
    private String sidePoleCrashRating;

    public Ncap(){

    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehiclePicture() {
        return vehiclePicture;
    }

    public void setVehiclePicture(String vehiclePicture) {
        this.vehiclePicture = vehiclePicture;
    }

    public String getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }

    public String getOverallFrontCrashRating() {
        return overallFrontCrashRating;
    }

    public void setOverallFrontCrashRating(String overallFrontCrashRating) {
        this.overallFrontCrashRating = overallFrontCrashRating;
    }

    public String getFrontCrashDriversideRating() {
        return frontCrashDriversideRating;
    }

    public void setFrontCrashDriversideRating(String frontCrashDriversideRating) {
        this.frontCrashDriversideRating = frontCrashDriversideRating;
    }

    public String getFrontCrashPassengersideRating() {
        return frontCrashPassengersideRating;
    }

    public void setFrontCrashPassengersideRating(String frontCrashPassengersideRating) {
        this.frontCrashPassengersideRating = frontCrashPassengersideRating;
    }

    public String getFrontCrashPicture() {
        return frontCrashPicture;
    }

    public void setFrontCrashPicture(String frontCrashPicture) {
        this.frontCrashPicture = frontCrashPicture;
    }

    public String getFrontCrashVideo() {
        return frontCrashVideo;
    }

    public void setFrontCrashVideo(String frontCrashVideo) {
        this.frontCrashVideo = frontCrashVideo;
    }

    public String getOverallSideCrashRating() {
        return overallSideCrashRating;
    }

    public void setOverallSideCrashRating(String overallSideCrashRating) {
        this.overallSideCrashRating = overallSideCrashRating;
    }

    public String getSideCrashDriversideRating() {
        return sideCrashDriversideRating;
    }

    public void setSideCrashDriversideRating(String sideCrashDriversideRating) {
        this.sideCrashDriversideRating = sideCrashDriversideRating;
    }

    public String getSideCrashPassengersideRating() {
        return sideCrashPassengersideRating;
    }

    public void setSideCrashPassengersideRating(String sideCrashPassengersideRating) {
        this.sideCrashPassengersideRating = sideCrashPassengersideRating;
    }

    public String getSideCrashPicture() {
        return sideCrashPicture;
    }

    public void setSideCrashPicture(String sideCrashPicture) {
        this.sideCrashPicture = sideCrashPicture;
    }

    public String getSideCrashVideo() {
        return sideCrashVideo;
    }

    public void setSideCrashVideo(String sideCrashVideo) {
        this.sideCrashVideo = sideCrashVideo;
    }

    public String getSidePoleCrashRating() {
        return sidePoleCrashRating;
    }

    public void setSidePoleCrashRating(String sidePoleCrashRating) {
        this.sidePoleCrashRating = sidePoleCrashRating;
    }

    public String getSidePolePicture() {
        return sidePolePicture;
    }

    public void setSidePolePicture(String sidePolePicture) {
        this.sidePolePicture = sidePolePicture;
    }

    public String getSidePoleVideo() {
        return sidePoleVideo;
    }

    public void setSidePoleVideo(String sidePoleVideo) {
        this.sidePoleVideo = sidePoleVideo;
    }

    public String getComplaintsCount() {
        return complaintsCount;
    }

    public void setComplaintsCount(String complaintsCount) {
        this.complaintsCount = complaintsCount;
    }

    public String getRecallsCount() {
        return recallsCount;
    }

    public void setRecallsCount(String recallsCount) {
        this.recallsCount = recallsCount;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

}