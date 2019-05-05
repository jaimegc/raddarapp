package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

import java.util.List;

public class TerritoryArea implements Identifiable<String> {

    private final String key;
    private final List<List<SimpleLocation>> area;

    public TerritoryArea(String key, List<List<SimpleLocation>> area) {
        this.key = key;
        this.area = area;
    }

    @Override
    public String getKey() {
        return key;
    }

    public List<List<SimpleLocation>> getArea() {
        return area;
    }
}
