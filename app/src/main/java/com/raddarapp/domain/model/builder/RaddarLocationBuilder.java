package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.RaddarLocation;

public class RaddarLocationBuilder {

    private double latitude;
    private double longitude;
    private String creationMoment;

    public RaddarLocationBuilder() {}

    public RaddarLocation build() {
        return new RaddarLocation(latitude, longitude, creationMoment);
    }

    public RaddarLocationBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public RaddarLocationBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public RaddarLocationBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }
}
