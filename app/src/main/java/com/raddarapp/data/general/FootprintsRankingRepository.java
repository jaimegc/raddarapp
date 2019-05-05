package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsRankingReadableDataSourceContract;
import com.raddarapp.data.general.factory.FootprintsRankingDataSourceFactory;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.User;

import javax.inject.Inject;

public class FootprintsRankingRepository extends PaginatedRosieRepository<String, FootprintRanking> {

    private final FootprintsRankingReadableDataSourceContract footprintRankingReadableDataSource;

    private static User userFootprintRankingInLocalCache = null;

    @Inject
    FootprintsRankingRepository(FootprintsRankingDataSourceFactory footprintRankingDataSourceFactory,
            PaginatedCacheDataSource<String, FootprintRanking> inMemoryPaginatedCache) {

        this.footprintRankingReadableDataSource = footprintRankingDataSourceFactory.createReadableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(this.footprintRankingReadableDataSource);
        addPaginatedReadableDataSources(this.footprintRankingReadableDataSource);
    }

    public PaginatedGenericTotalCollection<FootprintRanking> getFootprintsRankingByZoneKey(String zoneKey, Integer pageNumber, Page page) throws Exception {
        PaginatedGenericTotalCollection<FootprintRanking> footprintsRanking;

        footprintsRanking = footprintRankingReadableDataSource.getFootprintsRankingByZoneKey(zoneKey, pageNumber, page);

        populatePaginatedCaches(page, footprintsRanking.getPaginatedCollection());

        return footprintsRanking;
    }

    public void addUserFootprintRankingInLocalCache(User user) {
        userFootprintRankingInLocalCache = user;
    }

    public void deleteUserFootprintRankingInLocalCache() {
        userFootprintRankingInLocalCache = null;
    }

    public User getUserFootprintRankingInLocalCache() {
        return userFootprintRankingInLocalCache;
    }
}
