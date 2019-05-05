package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.ReadableDataSource;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryMain;

public interface TerritoryMainReadableDataSourceContract extends ReadableDataSource<String, TerritoryMain> {

    TerritoryMain getTerritoryMainByCoordinates(RaddarLocation raddarLocation) throws Exception;

    TerritoryMain getTerritoryMainByZone(String zoneKey) throws Exception;
}
