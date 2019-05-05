package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.TerritoryAreaViewModelContract;

public class TerritoryAreaViewModel implements TerritoryAreaViewModelContract {

    private final double latitude;
    private final double longitude;

    public TerritoryAreaViewModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
