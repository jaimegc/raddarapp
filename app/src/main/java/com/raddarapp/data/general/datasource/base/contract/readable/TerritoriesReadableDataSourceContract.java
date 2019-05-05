package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.Territory;

public interface TerritoriesReadableDataSourceContract extends PaginatedReadableDataSource<String, Territory> {

    PaginatedCollection<Territory> getTerritoriesFirstLevel(Page page) throws Exception;

    PaginatedCollection<Territory> getTerritoriesByZoneId(String zoneKey, Page page) throws Exception;

    Territory getTerritoryDetailsByCoordinates(RaddarLocation raddarLocation) throws Exception;
}
