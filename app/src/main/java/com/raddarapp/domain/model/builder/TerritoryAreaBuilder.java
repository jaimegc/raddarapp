package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.SimpleLocation;
import com.raddarapp.domain.model.TerritoryArea;

import java.util.List;

public class TerritoryAreaBuilder {
    private String key;
    private List<List<SimpleLocation>> area;

    public TerritoryAreaBuilder() {}

    public TerritoryArea build() {
        return new TerritoryArea(key, area);
    }

    public TerritoryAreaBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public TerritoryAreaBuilder withArea(List<List<SimpleLocation>> area) {
        this.area = area;
        return this;
    }
}
