package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserRankingReadableDataSourceContract;
import com.raddarapp.data.general.factory.MyUsersRankingDataSourceFactory;
import com.raddarapp.domain.model.MyUserRanking;

import javax.inject.Inject;

public class MyUsersRankingRepository extends PaginatedRosieRepository<String, MyUserRanking> {

    private final MyUserRankingReadableDataSourceContract myUsersRankingReadableDataSource;

    // This is because when we click on all users ranking we need to update the cache but
    // if we delete the cache during this process, then when we back to the tab 2 (users ranking)
    // sometimes throws an exception in the usecase handler
    private static PaginatedCollection<MyUserRanking> localCache = null;

    private static MyUserRanking userFootprintInLocalCache = null;

    @Inject
    MyUsersRankingRepository(MyUsersRankingDataSourceFactory myUsersRankingReadableDataSource,
            PaginatedCacheDataSource<String, MyUserRanking> inMemoryPaginatedCache) {

        this.myUsersRankingReadableDataSource = myUsersRankingReadableDataSource.createReadableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(this.myUsersRankingReadableDataSource);
        addPaginatedReadableDataSources(this.myUsersRankingReadableDataSource);
    }

    public PaginatedMyUserRankingTotalCollection<MyUserRanking> getMyUsersRankingByTerritoryId(String territoryKey, Integer pageNumber, Page page) throws Exception {
        PaginatedMyUserRankingTotalCollection<MyUserRanking> myUsersRanking;

        myUsersRanking = myUsersRankingReadableDataSource.getMyUsersRankingByTerritoryId(territoryKey, pageNumber, page);

        populatePaginatedCaches(page, myUsersRanking.getPaginatedCollection());

        return myUsersRanking;
    }

    public void addMyUsersRankingInLocalCache(PaginatedCollection<MyUserRanking> myUsersRanking) {
        localCache = new PaginatedCollection<>(myUsersRanking.getItems());

        Page page = Page.withOffsetAndLimit(0, localCache.getItems().size());

        localCache.setPage(page);
        // Always true because if we can navigate to all users ranking screen
        localCache.setHasMore(true);
    }

    public PaginatedCollection<MyUserRanking> getUsersRankingInLocalCache() throws Exception {

        populatePaginatedCaches(localCache.getPage(), localCache);

        return localCache;
    }

    public void deleteUsersRankingLocalCache() {
        localCache = null;
    }

    public void addUserFootprintInLocalCache(MyUserRanking user) {
        userFootprintInLocalCache = user;
    }

    public void deleteUserFootprintInLocalCache() {
        userFootprintInLocalCache = null;
    }

    public MyUserRanking getUserFootprintInLocalCache() {
        return userFootprintInLocalCache;
    }
}
