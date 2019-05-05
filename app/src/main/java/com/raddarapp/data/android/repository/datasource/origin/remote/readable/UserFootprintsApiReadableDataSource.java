package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UserFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UserFootprintToUserFootprintDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UserFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.UserFootprintReadableDataSourceContract;
import com.raddarapp.domain.model.UserFootprint;

import java.util.Collection;

import javax.inject.Inject;

public class UserFootprintsApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, UserFootprint>
        implements UserFootprintReadableDataSourceContract {

    private final UserFootprintApiClient userFootprintApiClient;
    private final UserFootprintToUserFootprintDtoMapper mapper = new UserFootprintToUserFootprintDtoMapper();

    @Inject
    public UserFootprintsApiReadableDataSource(UserFootprintApiClient userFootprintApiClient) {
        this.userFootprintApiClient = userFootprintApiClient;
    }

    @Override
    public PaginatedGenericTotalCollection<UserFootprint> getUserFootprintsByUserId(String userKey, Integer pageNumber, Page page) throws Exception {
        int limit = page.getLimit();
        long total;

        ServerResponseCollection<UserFootprintDto> userFootprintsApiResponse = userFootprintApiClient.getUserFootprintsByUserId(userKey, pageNumber, limit);

        total = userFootprintsApiResponse.getMetadata().getTotalElements();

        Collection<UserFootprint> userFootprintsCollection = mapper.reverseMapCollection(userFootprintsApiResponse.getResponse());

        PaginatedCollection<UserFootprint> userFootprintsPaginated = new PaginatedCollection<>(userFootprintsCollection);
        userFootprintsPaginated.setPage(page);
        userFootprintsPaginated.setHasMore(
                userFootprintsApiResponse.getMetadata().getPageNumber() < userFootprintsApiResponse.getMetadata().getTotalPages());

        PaginatedGenericTotalCollection<UserFootprint> userFootprints = new PaginatedGenericTotalCollection<>(userFootprintsPaginated, total);

        return userFootprints;
    }
}
