package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyFootprintCollectionApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.FootprintMainToFootprintMainDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyFootprintCollectionToMyFootprintCollectionDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserProfileToMyUserProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintCollectionDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintsCollectionReadableDataSourceContract;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.MyFootprintCollection;

import java.util.Collection;

import javax.inject.Inject;

public class MyFootprintsCollectionApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, MyFootprintCollection>
        implements MyFootprintsCollectionReadableDataSourceContract {

    private final MyFootprintCollectionApiClient myFootprintCollectionApiClient;
    private final MyFootprintCollectionToMyFootprintCollectionDtoMapper mapperMyFootprintCollection = new MyFootprintCollectionToMyFootprintCollectionDtoMapper();

    @Inject
    public MyFootprintsCollectionApiReadableDataSource(MyFootprintCollectionApiClient myFootprintCollectionApiClient) {
        this.myFootprintCollectionApiClient = myFootprintCollectionApiClient;
    }

    @Override
    public PaginatedGenericTotalCollection<MyFootprintCollection> getMyFootprintsCollection(Integer pageNumber, Page page) throws Exception {
        int limit = page.getLimit();
        long total;

        ServerResponseCollection<MyFootprintCollectionDto> myFootprintsApiResponse =
                myFootprintCollectionApiClient.getMyFootprintCollection(pageNumber, limit);

        total = myFootprintsApiResponse.getMetadata().getTotalElements();

        Collection<MyFootprintCollection> myFootprintsCollection = mapperMyFootprintCollection.reverseMapCollection(myFootprintsApiResponse.getResponse());

        PaginatedCollection<MyFootprintCollection> footprintsMainPaginated = new PaginatedCollection<>(myFootprintsCollection);
        footprintsMainPaginated.setPage(page);
        footprintsMainPaginated.setHasMore(
                myFootprintsApiResponse.getMetadata().getPageNumber() < myFootprintsApiResponse.getMetadata().getTotalPages());

        PaginatedGenericTotalCollection<MyFootprintCollection> myFootprints = new PaginatedGenericTotalCollection<>(footprintsMainPaginated, total);

        return myFootprints;
    }
}
