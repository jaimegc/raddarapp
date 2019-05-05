package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class RaddarLocationDto {

    @SerializedName("lat")
    private double latitude;
    @SerializedName("lng")
    private double longitude;
    @SerializedName("creationMoment")
    private String creationMoment;

    public RaddarLocationDto(double latitude, double longitude, String creationMoment) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.creationMoment = creationMoment;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCreationMoment() {
        return creationMoment;
    }
}
