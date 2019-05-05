package com.raddarapp.presentation.android.view.map.model;

import com.google.gson.annotations.SerializedName;

public class Southwest {

    @SerializedName("lat") String lat;
    @SerializedName("lng") String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public Double getLatD() {
        return Double.parseDouble(lat);
    }

    public void setLat(final String lat) {
        this.lat = lat;
    }
}
