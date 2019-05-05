package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoryAreaReadableDataSourceContract;
import com.raddarapp.data.general.factory.TerritoryAreaDataSourceFactory;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.TerritoryArea;

import javax.inject.Inject;

public class TerritoryAreaRepository extends RosieRepository<String, TerritoryArea> {

    private final TerritoryAreaReadableDataSourceContract territoryAreaReadableDataSource;

    @Inject
    TerritoryAreaRepository(TerritoryAreaDataSourceFactory territoryAreaDataSourceFactory) {

        territoryAreaReadableDataSource = territoryAreaDataSourceFactory.createReadableDataSource();

        addReadableDataSources(territoryAreaReadableDataSource);
    }

    public TerritoryArea getTerritoryAreaByZoneId(String zoneKey) throws Exception {

        TerritoryArea territoryArea = territoryAreaReadableDataSource.getTerritoryAreaByZoneId(zoneKey);

        return territoryArea;
    }

    public TerritoryArea getTerritoryAreaByTerritoryId(String territoryKey) throws Exception {

        TerritoryArea territoryArea = territoryAreaReadableDataSource.getTerritoryAreaByTerritoryId(territoryKey);

        return territoryArea;
    }

    public TerritoryArea getTerritoryAreaByCoordinates(RaddarLocation raddarLocation) throws Exception {

        TerritoryArea territoryArea = territoryAreaReadableDataSource.getTerritoryAreaByCoordinates(raddarLocation);

        return territoryArea;
    }
}
