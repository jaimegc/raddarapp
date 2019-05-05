package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.SimpleLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryAreaDto;

import java.util.List;

public class TerritoryAreaDtoBuilder {

    private String id;
    private List<List<SimpleLocationDto>> area;

    public TerritoryAreaDtoBuilder() {}

    public TerritoryAreaDto build() {
        return new TerritoryAreaDto(id, area);
    }

    public TerritoryAreaDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public TerritoryAreaDtoBuilder withArea(List<List<SimpleLocationDto>> area) {
        this.area = area;
        return this;
    }
}
