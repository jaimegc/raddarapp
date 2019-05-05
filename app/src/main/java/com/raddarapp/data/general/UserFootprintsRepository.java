package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.UserFootprintReadableDataSourceContract;
import com.raddarapp.data.general.factory.UserFootprintDataSourceFactory;
import com.raddarapp.domain.model.UserFootprint;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class UserFootprintsRepository extends PaginatedRosieRepository<String, UserFootprint> {

    private final UserFootprintReadableDataSourceContract userFootprintReadableDataSource;

    // This is because when we click on all user footprints we need to update the cache but
    // if we delete the cache during this process, then when we back to the user profile
    // sometimes throws an exception in the usecase handler
    private static PaginatedCollection<UserFootprint> localCache = null;

    @Inject
    UserFootprintsRepository(UserFootprintDataSourceFactory userFootprintDataSourceFactory,
            PaginatedCacheDataSource<String, UserFootprint> inMemoryPaginatedCache) {

        this.userFootprintReadableDataSource = userFootprintDataSourceFactory.createReadableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(this.userFootprintReadableDataSource);
        addPaginatedReadableDataSources(this.userFootprintReadableDataSource);
    }

    public PaginatedGenericTotalCollection<UserFootprint> getUserFootprints(String userKey, Integer pageNumber, Page page) throws Exception {
        PaginatedGenericTotalCollection<UserFootprint> userFootprints;

        userFootprints = userFootprintReadableDataSource.getUserFootprintsByUserId(userKey, pageNumber, page);

        populatePaginatedCaches(page, userFootprints.getPaginatedCollection());

        return userFootprints;
    }

    public PaginatedCollection<UserFootprint> addUserFootprintInCache(UserFootprint newUserFootprint, PaginatedCollection<UserFootprint> paginatedUserFootprints) throws Exception {
        List<UserFootprint> userFootprints = new LinkedList<>();

        userFootprints.add(newUserFootprint);

        for (UserFootprint userFootprint : paginatedUserFootprints.getItems()) {
            userFootprints.add(userFootprint);
        }

        Comparator<UserFootprint> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(userFootprints, comparator);

        PaginatedCollection<UserFootprint> finalPaginatedComments = new PaginatedCollection<>(userFootprints);
        paginatedUserFootprints.setPage(paginatedUserFootprints.getPage());
        populatePaginatedCaches(paginatedUserFootprints.getPage(), finalPaginatedComments);

        return finalPaginatedComments;
    }

    public void addUserFootprintsInLocalCache(PaginatedCollection<UserFootprint> userFootprints) {
        localCache = new PaginatedCollection<>(userFootprints.getItems());

        Page page = Page.withOffsetAndLimit(0, localCache.getItems().size());

        localCache.setPage(page);
        // Always true because if we can navigate to my all footprints screen
        localCache.setHasMore(true);
    }

    public PaginatedCollection<UserFootprint> getUserFootprintsInLocalCache() throws Exception {

        populatePaginatedCaches(localCache.getPage(), localCache);

        return localCache;
    }

    public void deleteUserFootprintsLocalCache() {
        localCache = null;
    }
}
