package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.TerritoryAreaRepository;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryArea;

import javax.inject.Inject;

public class GetTerritoryArea extends RosieUseCase {

    public static final String USE_CASE_GET_TERRITORY_AREA_BY_ZONE_ID = "getTerritoryAreaByZoneId";
    public static final String USE_CASE_GET_TERRITORY_AREA_BY_TERRITORY_ID = "getTerritoryAreaByTerritoryId";
    public static final String USE_CASE_GET_TERRITORY_AREA_BY_COORDINATES = "getTerritoryAreaByCoordinates";

    private final TerritoryAreaRepository territoryAreaRepository;

    @Inject
    public GetTerritoryArea(TerritoryAreaRepository territoryAreaRepository) {
        this.territoryAreaRepository = territoryAreaRepository;
    }

    public void deleteCache() {
        try {
            territoryAreaRepository.deleteAll();
        } catch (Exception e) {}
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_AREA_BY_ZONE_ID)
    public void getTerritoryAreaByZoneId(String zoneKey) throws Exception {
        TerritoryArea territoryArea = territoryAreaRepository.getTerritoryAreaByZoneId(zoneKey);
        notifySuccess(territoryArea);
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_AREA_BY_TERRITORY_ID)
    public void getTerritoryAreaByTerritoryId(String territoryKey) throws Exception {
        TerritoryArea territoryArea = territoryAreaRepository.getTerritoryAreaByTerritoryId(territoryKey);
        notifySuccess(territoryArea);
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_AREA_BY_COORDINATES)
    public void getTerritoryAreaByCoordinates(RaddarLocation raddarLocation) throws Exception {
        TerritoryArea territoryArea = territoryAreaRepository.getTerritoryAreaByCoordinates(raddarLocation);
        notifySuccess(territoryArea);
    }
}