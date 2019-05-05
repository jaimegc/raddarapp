package com.raddarapp.domain.model;

public class RaddarLocation {

    private final double latitude;
    private final double longitude;
    private final String creationMoment;

    public RaddarLocation(double latitude, double longitude, String creationMoment) {
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
