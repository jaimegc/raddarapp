package com.raddarapp.presentation.android.view.map.model;

import com.google.gson.annotations.SerializedName;

public class Northeast {

    @SerializedName("lat") String lat;
    @SerializedName("lng") String lng;

    public String getLat() {
        return lat;
    }

    public Double getLatD() {
        return Double.parseDouble(lat);
    }

    public String getLng() {
        return lng;
    }
}
