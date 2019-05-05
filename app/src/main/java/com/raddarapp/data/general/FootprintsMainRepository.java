package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedFootprintsMainTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsMainReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.FootprintsMainWriteableDataSourceContract;
import com.raddarapp.data.general.factory.FootprintsMainDataSourceFactory;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class FootprintsMainRepository extends PaginatedRosieRepository<String, FootprintMain> {

    private final FootprintsMainReadableDataSourceContract footprintsMainReadableDataSource;
    private final FootprintsMainWriteableDataSourceContract footprintsMainWriteableDataSource;

    private static User userFootprintMainInLocalCache = null;

    @Inject
    FootprintsMainRepository(FootprintsMainDataSourceFactory footprintsMainDataSourceFactory,
            PaginatedCacheDataSource<String, FootprintMain> inMemoryPaginatedCache) {

        footprintsMainReadableDataSource = footprintsMainDataSourceFactory.createReadableDataSource();
        footprintsMainWriteableDataSource = footprintsMainDataSourceFactory.createWriteableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(footprintsMainReadableDataSource);
        addPaginatedReadableDataSources(footprintsMainReadableDataSource);
    }

    public PaginatedFootprintsMainTotalCollection<FootprintMain> getFootprintsMain(RaddarLocation raddarLocation, Integer pageNumber, Page page) throws Exception {
        PaginatedFootprintsMainTotalCollection<FootprintMain> footprintsMain;

        footprintsMain = footprintsMainReadableDataSource.getFootprintsMain(raddarLocation, pageNumber, page);

        populatePaginatedCaches(page, footprintsMain.getPaginatedCollection());

        return footprintsMain;
    }

    public PaginatedCollection<FootprintMain> getFootprintsMainByUserId(String userKey, Page page) throws Exception {
        PaginatedCollection<FootprintMain> footprintsMain;

        footprintsMain = footprintsMainReadableDataSource.getFootprintsMainByUserId(userKey, page);

        populatePaginatedCaches(page, footprintsMain);

        return footprintsMain;
    }

    public double addVote(String footprintKey, int addVoteType) throws Exception {
        double actualScope;

        actualScope = footprintsMainWriteableDataSource.addVote(footprintKey, addVoteType);

        return actualScope;
    }

    public PaginatedCollection<FootprintMain> addFootprintsMainInCache(FootprintMain newFootprintMain, PaginatedCollection<FootprintMain> paginatedFootprintsMain) throws Exception {
        List<FootprintMain> footprintsMain = new LinkedList<>();

        footprintsMain.add(newFootprintMain);

        for (FootprintMain footprintMain : paginatedFootprintsMain.getItems()) {
            footprintsMain.add(footprintMain);
        }

        Comparator<FootprintMain> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(footprintsMain, comparator);

        PaginatedCollection<FootprintMain> finalPaginatedComments = new PaginatedCollection<>(footprintsMain);
        paginatedFootprintsMain.setPage(paginatedFootprintsMain.getPage());
        populatePaginatedCaches(paginatedFootprintsMain.getPage(), finalPaginatedComments);

        return finalPaginatedComments;
    }

    public PaginatedCollection<FootprintMain> removeFootprintsMainInCache(String footprintMainKey, PaginatedCollection<FootprintMain> paginatedFootprintsMain) throws Exception {
        List<FootprintMain> footprintsMain = new LinkedList<>();

        for (FootprintMain footprintMain : paginatedFootprintsMain.getItems()) {
            if (!footprintMain.getKey().equals(footprintMainKey)) {
                footprintsMain.add(footprintMain);
            } else {
                deleteByKey(footprintMainKey);
            }
        }

        Comparator<FootprintMain> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(footprintsMain, comparator);

        PaginatedCollection<FootprintMain> finalPaginatedComments = new PaginatedCollection<>(footprintsMain);
        paginatedFootprintsMain.setPage(paginatedFootprintsMain.getPage());
        populatePaginatedCaches(paginatedFootprintsMain.getPage(), finalPaginatedComments);

        return finalPaginatedComments;
    }

    public void addUserFootprintMainInLocalCache(User user) {
        userFootprintMainInLocalCache = user;
    }

    public void deleteUserFootprintMainInLocalCache() {
        userFootprintMainInLocalCache = null;
    }

    public User getUserFootprintMainInLocalCache() {
        return userFootprintMainInLocalCache;
    }
}
