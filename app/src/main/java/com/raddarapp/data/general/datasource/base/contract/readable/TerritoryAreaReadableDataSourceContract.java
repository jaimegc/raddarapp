package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.ReadableDataSource;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryArea;

public interface TerritoryAreaReadableDataSourceContract extends ReadableDataSource<String, TerritoryArea> {

    TerritoryArea getTerritoryAreaByZoneId(String zoneKey) throws Exception;

    TerritoryArea getTerritoryAreaByTerritoryId(String territoryKey) throws Exception;

    TerritoryArea getTerritoryAreaByCoordinates(RaddarLocation raddarLocation) throws Exception;
}
