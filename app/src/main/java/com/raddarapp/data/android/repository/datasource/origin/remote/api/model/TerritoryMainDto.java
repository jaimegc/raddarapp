package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class TerritoryMainDto {

    @SerializedName("territory")
    private TerritoryDto territory;

    @SerializedName("territoryArea")
    private TerritoryAreaDto territoryArea;

    public TerritoryDto getTerritory() {
        return territory;
    }

    public TerritoryAreaDto getTerritoryArea() {
        return territoryArea;
    }
}
