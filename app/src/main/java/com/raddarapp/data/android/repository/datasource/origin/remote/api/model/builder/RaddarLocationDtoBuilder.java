package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;

public class RaddarLocationDtoBuilder {

    private double latitude;
    private double longitude;
    private String creationMoment;

    public RaddarLocationDtoBuilder() {}

    public RaddarLocationDto build() {
        return new RaddarLocationDto(latitude, longitude, creationMoment);
    }

    public RaddarLocationDtoBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public RaddarLocationDtoBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public RaddarLocationDtoBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }
}
