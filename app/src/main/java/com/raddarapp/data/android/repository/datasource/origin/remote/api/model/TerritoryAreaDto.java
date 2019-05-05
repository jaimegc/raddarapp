package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TerritoryAreaDto {

    @SerializedName("id")
    private String id;

    @SerializedName("areaPoints")
    private List<List<SimpleLocationDto>> area;

    public TerritoryAreaDto(String id, List<List<SimpleLocationDto>> area) {
        this.id = id;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public List<List<SimpleLocationDto>> getArea() {
        return area;
    }


}
