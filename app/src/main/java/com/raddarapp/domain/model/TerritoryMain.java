package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class TerritoryMain implements Identifiable<String> {

    private final String key;
    private final Territory territory;
    private final TerritoryArea territoryArea;


    public TerritoryMain(String key, Territory territory, TerritoryArea territoryArea) {
        this.key = key;
        this.territory = territory;
        this.territoryArea = territoryArea;
    }

    @Override
    public String getKey() {
        return key;
    }

    public Territory getTerritory() {
        return territory;
    }

    public TerritoryArea getTerritoryArea() {
        return territoryArea;
    }
}
