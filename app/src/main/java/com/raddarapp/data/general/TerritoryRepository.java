package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoriesReadableDataSourceContract;
import com.raddarapp.data.general.factory.TerritoriesDataSourceFactory;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.Territory;

import javax.inject.Inject;

public class TerritoryRepository extends PaginatedRosieRepository<String, Territory> {

    private final TerritoriesReadableDataSourceContract territoriesReadableDataSource;

    @Inject
    TerritoryRepository(TerritoriesDataSourceFactory territoriesDataSourceFactory,
            PaginatedCacheDataSource<String, Territory> inMemoryPaginatedCache) {

        territoriesReadableDataSource = territoriesDataSourceFactory.createReadableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(territoriesReadableDataSource);
        addPaginatedReadableDataSources(territoriesReadableDataSource);
    }

    public PaginatedCollection<Territory> getTerritoriesFirstLevel(Page page) throws Exception {
        PaginatedCollection<Territory> territories;

        territories = territoriesReadableDataSource.getTerritoriesFirstLevel(page);

        populatePaginatedCaches(page, territories);

        return territories;
    }

    public PaginatedCollection<Territory> getTerritoriesByZoneId(String zoneKey, Page page) throws Exception {
        PaginatedCollection<Territory> territories;

        territories = territoriesReadableDataSource.getTerritoriesByZoneId(zoneKey, page);

        populatePaginatedCaches(page, territories);

        return territories;
    }

    public Territory getTerritoryDetailsByCoordinates(RaddarLocation raddarLocation) throws Exception {

        Territory territory = territoriesReadableDataSource.getTerritoryDetailsByCoordinates(raddarLocation);

        return territory;
    }
}
