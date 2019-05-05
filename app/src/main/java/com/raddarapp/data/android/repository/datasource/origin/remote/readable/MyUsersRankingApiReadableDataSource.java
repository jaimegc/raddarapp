package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyUserRankingApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserRankingToMyUserRankingDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserRankingDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.MyUserRankingServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserRankingReadableDataSourceContract;
import com.raddarapp.domain.model.MyUserRanking;

import java.util.Collection;

import javax.inject.Inject;

public class MyUsersRankingApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, MyUserRanking>
        implements MyUserRankingReadableDataSourceContract {

    private final MyUserRankingApiClient footprintMainApiClient;
    private final MyUserRankingToMyUserRankingDtoMapper mapper = new MyUserRankingToMyUserRankingDtoMapper();

    @Inject
    public MyUsersRankingApiReadableDataSource(MyUserRankingApiClient footprintMainApiClient) {
        this.footprintMainApiClient = footprintMainApiClient;
    }

    @Override
    public PaginatedMyUserRankingTotalCollection<MyUserRanking> getMyUsersRankingByTerritoryId(String territoryId, Integer pageNumber, Page page) throws Exception {
        int limit = page.getLimit();
        long total;

        MyUserRankingServerResponseCollection<MyUserRankingDto> myUserRankingApiResponse =
                footprintMainApiClient.getMyUsersRankingByTerritoryId(territoryId, pageNumber, limit);

        total = myUserRankingApiResponse.getRanking().getMetadata().getTotalElements();

        Collection<MyUserRanking> myUserRankingCollection = mapper.reverseMapCollection(myUserRankingApiResponse.getRanking().getResponse());

        PaginatedCollection<MyUserRanking> myUserRankingPaginated = new PaginatedCollection<>(myUserRankingCollection);
        myUserRankingPaginated.setPage(page);
        myUserRankingPaginated.setHasMore(
                myUserRankingApiResponse.getRanking().getMetadata().getPageNumber() <
                        myUserRankingApiResponse.getRanking().getMetadata().getTotalPages());

        PaginatedMyUserRankingTotalCollection<MyUserRanking> myUserRanking =
                new PaginatedMyUserRankingTotalCollection<>(myUserRankingPaginated, myUserRankingApiResponse.getPosition(), total);

        return myUserRanking;
    }
}
