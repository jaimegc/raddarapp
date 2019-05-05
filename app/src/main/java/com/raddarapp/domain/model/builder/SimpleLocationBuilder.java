package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.SimpleLocation;

public class SimpleLocationBuilder {

    private double latitude;
    private double longitude;

    public SimpleLocationBuilder() {}

    public SimpleLocation build() {
        return new SimpleLocation(latitude, longitude);
    }

    public SimpleLocationBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public SimpleLocationBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }
}
