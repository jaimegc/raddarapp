package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.general.TerritoryRepository;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.Territory;

import javax.inject.Inject;

public class GetTerritoryDetails extends RosieUseCase {

    public static final String USE_CASE_GET_TERRITORY_AREA_DETAILS = "getTerritoryAreaDetails";
    public static final String USE_CASE_GET_TERRITORY_AREA_DETAILS_BY_COORDINATES = "getTerritoryAreaDetailsByCoordinates";

    private final TerritoryRepository territoryRepository;

    @Inject
    public GetTerritoryDetails(TerritoryRepository territoryRepository) {
        this.territoryRepository = territoryRepository;
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_AREA_DETAILS)
    public void getTerritoryDetails(String zoneKey) throws Exception {
        Territory territory = territoryRepository.getByKey(zoneKey);
        notifySuccess(territory);
    }

    @UseCase(name = USE_CASE_GET_TERRITORY_AREA_DETAILS_BY_COORDINATES)
    public void getTerritoryDetailsByCoordinates(RaddarLocation raddarLocation) throws Exception {
        Territory territory = territoryRepository.getTerritoryDetailsByCoordinates(raddarLocation);
        notifySuccess(territory);
    }
}
