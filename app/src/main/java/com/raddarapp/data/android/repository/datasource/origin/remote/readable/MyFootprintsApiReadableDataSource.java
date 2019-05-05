package com.raddarapp.data.android.repository.datasource.origin.remote.readable;


import com.google.gson.GsonBuilder;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyFootprintToMyFootprintDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintReadableDataSourceContract;
import com.raddarapp.domain.model.MyFootprint;

import java.util.Collection;

import javax.inject.Inject;

public class MyFootprintsApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, MyFootprint>
        implements MyFootprintReadableDataSourceContract {

    private final MyFootprintApiClient myFootprintApiClient;
    private final MyFootprintToMyFootprintDtoMapper mapper = new MyFootprintToMyFootprintDtoMapper();

    @Inject
    public MyFootprintsApiReadableDataSource(MyFootprintApiClient myFootprintApiClient) {
        this.myFootprintApiClient = myFootprintApiClient;
    }

    @Override
    public PaginatedGenericTotalCollection<MyFootprint> getMyFootprints(Integer pageNumber, Page page) throws Exception {
        int limit = page.getLimit();
        long total;

        ServerResponseCollection<MyFootprintDto> myFootprintsApiResponse = myFootprintApiClient.getMyFootprints(pageNumber, limit);

        total = myFootprintsApiResponse.getMetadata().getTotalElements();

        Collection<MyFootprint> myFootprintsCollection = mapper.reverseMapCollection(myFootprintsApiResponse.getResponse());

        PaginatedCollection<MyFootprint> footprintsMainPaginated = new PaginatedCollection<>(myFootprintsCollection);
        footprintsMainPaginated.setPage(page);
        footprintsMainPaginated.setHasMore(
                myFootprintsApiResponse.getMetadata().getPageNumber() < myFootprintsApiResponse.getMetadata().getTotalPages());

        PaginatedGenericTotalCollection<MyFootprint> myFootprints = new PaginatedGenericTotalCollection<>(footprintsMainPaginated, total);

        return myFootprints;
    }

    // This method should be in a MyFootprintsNotificationReadableDatasource or similar
    @Override
    public MyFootprint addMyFootprintInCacheFromNotification(String content) throws Exception {
        MyFootprintDto myFootprintDto = new GsonBuilder().create().fromJson(content, MyFootprintDto.class);

        MyFootprint myFootprint = mapper.reverseMap(myFootprintDto);

        return myFootprint;
    }

}
