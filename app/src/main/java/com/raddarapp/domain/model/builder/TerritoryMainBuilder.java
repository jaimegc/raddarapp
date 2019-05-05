package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.model.TerritoryMain;

public class TerritoryMainBuilder {

    private String key;

    private Territory territory;
    private TerritoryArea territoryArea;

    public TerritoryMainBuilder() {}

    public TerritoryMain build() {
        return new TerritoryMain(key, territory, territoryArea);
    }

    public TerritoryMainBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public TerritoryMainBuilder withTerritory(Territory territory) {
        this.territory = territory;
        return this;
    }

    public TerritoryMainBuilder withTerritoryArea(TerritoryArea territoryArea) {
        this.territoryArea = territoryArea;
        return this;
    }
}
