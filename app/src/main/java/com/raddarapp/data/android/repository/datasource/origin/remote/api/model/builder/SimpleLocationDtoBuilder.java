package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.SimpleLocationDto;

public class SimpleLocationDtoBuilder {

    private double latitude;
    private double longitude;

    public SimpleLocationDtoBuilder() {}

    public SimpleLocationDto build() {
        return new SimpleLocationDto(latitude, longitude);
    }

    public SimpleLocationDtoBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public SimpleLocationDtoBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

}
