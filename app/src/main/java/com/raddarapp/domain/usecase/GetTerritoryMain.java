package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.TerritoryMainRepository;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryMain;

import java.util.Collection;

import javax.inject.Inject;

public class GetTerritoryMain extends RosieUseCase {

    public static final String USE_CASE_GET_TERRITORY_MAIN_BY_COORDINATES = "getTerritoryMainByCoordinates";
    public static final String USE_CASE_GET_TERRITORY_MAIN_BY_ZONE = "getTerritoryMainByZone";

    private final TerritoryMainRepository territoryMainRepository;

    @Inject
    public GetTerritoryMain(TerritoryMainRepository territoryMainRepository) {
        this.territoryMainRepository = territoryMainRepository;
    }

    public TerritoryMain getTerritoryMainInCache(String key) {
        TerritoryMain territoryMain;

        try {
            territoryMain = territoryMainRepository.getByKey(key, ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            territoryMain = null;
        }

        return territoryMain;
    }

    public TerritoryMain getTerritoryInCache() {

        TerritoryMain territoryMain = territoryMainRepository.getTerritoryMainInLocalCache();

        return territoryMain;
    }

    public TerritoryMain getTerritoryInCacheByZoneKey(String zoneKey) {

        TerritoryMain territoryMain = null;

        try {
            territoryMain = territoryMainRepository.getByKey(zoneKey);
        } catch (Exception e) {}

        return territoryMain;
    }

    public void deleteCache() {
        try {
            territoryMainRepository.deleteAll();
        } catch (Exception e) {}
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_MAIN_BY_COORDINATES)
    public void getTerritoryMainByCoordinates(RaddarLocation raddarLocation) throws Exception {
        TerritoryMain territoryMain = territoryMainRepository.getTerritoryMainByCoordinates(raddarLocation);
        notifySuccess(territoryMain);
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_MAIN_BY_ZONE)
    public void getTerritoryMainByZone(String zoneKey) throws Exception {
        TerritoryMain territoryMain = territoryMainRepository.getTerritoryMainByZone(zoneKey);
        notifySuccess(territoryMain);
    }
}