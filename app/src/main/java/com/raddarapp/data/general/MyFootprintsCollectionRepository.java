package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintsCollectionReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsCollectionWriteableDataSourceContract;
import com.raddarapp.data.general.factory.MyFootprintsCollectionDataSourceFactory;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.User;

import javax.inject.Inject;

public class MyFootprintsCollectionRepository extends PaginatedRosieRepository<String, MyFootprintCollection> {

    private final MyFootprintsCollectionReadableDataSourceContract myFootprintCollectionReadableDataSource;
    private final MyFootprintsCollectionWriteableDataSourceContract myFootprintsCollectionWriteableDataSource;

    private static User userMyFootprintCollectionInLocalCache = null;

    @Inject
    MyFootprintsCollectionRepository(MyFootprintsCollectionDataSourceFactory myFootprintCollectionDataSourceFactory,
            PaginatedCacheDataSource<String, MyFootprintCollection> inMemoryPaginatedCache) {

        this.myFootprintCollectionReadableDataSource = myFootprintCollectionDataSourceFactory.createReadableDataSource();
        this.myFootprintsCollectionWriteableDataSource = myFootprintCollectionDataSourceFactory.createWriteableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(this.myFootprintCollectionReadableDataSource);
        addPaginatedReadableDataSources(this.myFootprintCollectionReadableDataSource);
    }

    public PaginatedGenericTotalCollection<MyFootprintCollection> getMyFootprintsCollection(Integer pageNumber, Page page) throws Exception {
        PaginatedGenericTotalCollection<MyFootprintCollection> myFootprintsCollection;

        myFootprintsCollection = myFootprintCollectionReadableDataSource.getMyFootprintsCollection(pageNumber, page);

        populatePaginatedCaches(page, myFootprintsCollection.getPaginatedCollection());

        return myFootprintsCollection;
    }

    public boolean deleteMyFootprintCollection(String footprintCollectionKey) throws Exception {
        boolean deletedFootprint;

        deletedFootprint = myFootprintsCollectionWriteableDataSource.deleteMyFootprintCollection(footprintCollectionKey);

        return deletedFootprint;
    }

    public void addUserMyFootprintCollectionInLocalCache(User user) {
        userMyFootprintCollectionInLocalCache = user;
    }

    public void deleteUserMyFootprintCollectionInLocalCache() {
        userMyFootprintCollectionInLocalCache = null;
    }

    public User getUserMyFootprintCollectionInLocalCache() {
        return userMyFootprintCollectionInLocalCache;
    }
}
