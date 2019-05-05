package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsWriteableDataSourceContract;
import com.raddarapp.data.general.factory.MyFootprintsDataSourceFactory;
import com.raddarapp.domain.model.MyFootprint;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MyFootprintsRepository extends PaginatedRosieRepository<String, MyFootprint> {

    private final MyFootprintReadableDataSourceContract myFootprintsReadableDataSource;
    private final MyFootprintsWriteableDataSourceContract myFootprintsWriteableDataSource;

    // This is because when we click on all my footprints we need to update the cache but
    // if we delete the cache during this process, then when we back to the tab 4 (my profile)
    // sometimes throws an exception in the usecase handler
    private static PaginatedCollection<MyFootprint> localCache = null;

    @Inject
    MyFootprintsRepository(MyFootprintsDataSourceFactory myFootprintsDataSourceFactory,
            PaginatedCacheDataSource<String, MyFootprint> inMemoryPaginatedCache) {

        myFootprintsReadableDataSource = myFootprintsDataSourceFactory.createReadableDataSource();
        myFootprintsWriteableDataSource = myFootprintsDataSourceFactory.createWriteableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(myFootprintsReadableDataSource);
        addPaginatedReadableDataSources(myFootprintsReadableDataSource);
    }

    public PaginatedGenericTotalCollection<MyFootprint> getMyFootprints(Integer pageNumber, Page page) throws Exception {
        PaginatedGenericTotalCollection<MyFootprint> myFootprints;

        myFootprints = myFootprintsReadableDataSource.getMyFootprints(pageNumber, page);

        populatePaginatedCaches(page, myFootprints.getPaginatedCollection());

        return myFootprints;
    }

    public PaginatedCollection<MyFootprint> addMyFootprintInCache(MyFootprint newMyFootprint, PaginatedCollection<MyFootprint> paginatedMyFootprints) throws Exception {
        List<MyFootprint> myFootprints = new LinkedList<>();

        myFootprints.add(newMyFootprint);

        for (MyFootprint myFootprint : paginatedMyFootprints.getItems()) {
            myFootprints.add(myFootprint);
        }

        Comparator<MyFootprint> comparator = (one, two) -> two.getRaddarLocation().getCreationMoment().compareTo(
                one.getRaddarLocation().getCreationMoment());
        Collections.sort(myFootprints, comparator);

        PaginatedCollection<MyFootprint> finalPaginatedComments = new PaginatedCollection<>(myFootprints);
        paginatedMyFootprints.setPage(paginatedMyFootprints.getPage());
        populatePaginatedCaches(paginatedMyFootprints.getPage(), finalPaginatedComments);

        return finalPaginatedComments;
    }

    public boolean deleteMyFootprint(String footprintKey) throws Exception {
        boolean deletedFootprint;

        deletedFootprint = myFootprintsWriteableDataSource.deleteMyFootprint(footprintKey);

        return deletedFootprint;
    }

    public void addMyFootprintsInLocalCache(PaginatedCollection<MyFootprint> myFootprints) {
        localCache = new PaginatedCollection<>(myFootprints.getItems());

        Page page = Page.withOffsetAndLimit(0, localCache.getItems().size());

        localCache.setPage(page);
        // Always true because if we can navigate to my all footprints screen
        localCache.setHasMore(true);
    }

    public PaginatedCollection<MyFootprint> getMyFootprintsInLocalCache() throws Exception {

        populatePaginatedCaches(localCache.getPage(), localCache);

        return localCache;
    }

    public void deleteInLocalCacheByKey(String footprintKey) {

        for (MyFootprint myFootprint : localCache.getItems()) {
            if (myFootprint.getKey().equals(footprintKey)) {
                localCache.getItems().remove(myFootprint);
                break;
            }
        }
    }

    public void deleteMyFootprintsLocalCache() {
        localCache = null;
    }

    public MyFootprint addMyFootprintInCacheFromNotification(String content) throws Exception {
        MyFootprint myFootprint;

        PaginatedCollection<MyFootprint> paginatedCollection = new PaginatedCollection<>(new LinkedList<>());
        Page page = Page.withOffsetAndLimit(0, 1);
        paginatedCollection.setPage(page);
        paginatedCollection.setHasMore(true);

        myFootprint = myFootprintsReadableDataSource.addMyFootprintInCacheFromNotification(content);

        paginatedCollection.getItems().add(myFootprint);

        populatePaginatedCaches(page, paginatedCollection);

        return myFootprint;
    }

    public MyFootprint addMyFootprintInCacheFromNotificationForeground(String content) throws Exception {
        MyFootprint myFootprint;

        myFootprint = myFootprintsReadableDataSource.addMyFootprintInCacheFromNotification(content);

        MyFootprint myFootprintFromCache = getByKey(myFootprint.getKey());

        if (myFootprintFromCache != null) {
            return myFootprintFromCache;
        } else {
            PaginatedCollection<MyFootprint> paginatedCollection = new PaginatedCollection<>(getAll());
            Page page = Page.withOffsetAndLimit(0, getAll().size() + 1);
            paginatedCollection.setPage(page);
            paginatedCollection.setHasMore(true);

            paginatedCollection.getItems().add(myFootprint);

            addOrUpdate(myFootprint);
            populatePaginatedCaches(page, paginatedCollection);
            return myFootprint;
        }

        /*PaginatedCollection<MyFootprint> paginatedCollection = new PaginatedCollection<>(new LinkedList<>());
        Page page = Page.withOffsetAndLimit(0, 1);
        paginatedCollection.setPage(page);
        paginatedCollection.setHasMore(true);

        myFootprint = myFootprintsReadableDataSource.addMyFootprintInCacheFromNotification(content);

        paginatedCollection.getItems().add(myFootprint);

        populatePaginatedCaches(page, paginatedCollection);

        return myFootprint;*/
    }
}
