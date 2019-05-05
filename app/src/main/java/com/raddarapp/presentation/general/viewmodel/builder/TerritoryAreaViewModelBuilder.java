package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.TerritoryAreaViewModel;

public class TerritoryAreaViewModelBuilder {

    private double latitude;
    private double longitude;

    public TerritoryAreaViewModelBuilder() {}

    public TerritoryAreaViewModel build() {
        return new TerritoryAreaViewModel(latitude, longitude);
    }

    public TerritoryAreaViewModelBuilder withLatitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public TerritoryAreaViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

}
