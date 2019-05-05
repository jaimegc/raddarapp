package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.karumi.rosie.repository.datasource.CacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoryMainReadableDataSourceContract;
import com.raddarapp.data.general.factory.TerritoryMainDataSourceFactory;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryMain;

import javax.inject.Inject;

public class TerritoryMainRepository extends RosieRepository<String, TerritoryMain> {

    private final TerritoryMainReadableDataSourceContract territoryMainReadableDataSource;

    private static TerritoryMain localCache = null;

    @Inject
    TerritoryMainRepository(TerritoryMainDataSourceFactory territoryAreaDataSourceFactory,
            CacheDataSource<String, TerritoryMain> inMemoryPaginatedCache) {

        territoryMainReadableDataSource = territoryAreaDataSourceFactory.createReadableDataSource();

        addReadableDataSources(territoryMainReadableDataSource);
        addCacheDataSources(inMemoryPaginatedCache);
        addWriteableDataSources(inMemoryPaginatedCache);
    }

    public TerritoryMain getTerritoryMainByCoordinates(RaddarLocation raddarLocation) throws Exception {

        TerritoryMain territoryMain = territoryMainReadableDataSource.getTerritoryMainByCoordinates(raddarLocation);

        addOrUpdate(territoryMain);

        setTerritoryMainInLocalCache(territoryMain);

        return territoryMain;
    }

    public TerritoryMain getTerritoryMainByZone(String zoneKey) throws Exception {

        TerritoryMain territoryMain = territoryMainReadableDataSource.getTerritoryMainByZone(zoneKey);

        addOrUpdate(territoryMain);

        setTerritoryMainInLocalCache(territoryMain);

        return territoryMain;
    }

    public void setTerritoryMainInLocalCache(TerritoryMain territoryMain) throws Exception {
        TerritoryMainRepository.localCache = territoryMain;
    }

    public TerritoryMain getTerritoryMainInLocalCache() {
        return localCache;
    }

    public void deleteMyTerritoryMainLocalCache() {
        localCache = null;
    }
}
