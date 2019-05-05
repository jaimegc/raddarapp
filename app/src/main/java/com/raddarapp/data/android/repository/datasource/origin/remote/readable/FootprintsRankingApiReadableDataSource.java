package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.FootprintRankingApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.FootprintRankingToFootprintRankingDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintRankingDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsRankingReadableDataSourceContract;
import com.raddarapp.domain.model.FootprintRanking;

import java.util.Collection;

import javax.inject.Inject;

public class FootprintsRankingApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, FootprintRanking>
        implements FootprintsRankingReadableDataSourceContract {

    private final FootprintRankingApiClient footprintRankingApiClient;
    private final FootprintRankingToFootprintRankingDtoMapper mapperFootprintRanking = new FootprintRankingToFootprintRankingDtoMapper();

    @Inject
    public FootprintsRankingApiReadableDataSource(FootprintRankingApiClient footprintRankingApiClient) {
        this.footprintRankingApiClient = footprintRankingApiClient;
    }

    @Override
    public PaginatedGenericTotalCollection<FootprintRanking> getFootprintsRankingByZoneKey(String zoneId, Integer pageNumber, Page page) throws Exception {
        int limit = page.getLimit();
        long total;

        ServerResponseCollection<FootprintRankingDto> myFootprintsApiResponse =
                footprintRankingApiClient.getFootprintsRankingByZoneKey(zoneId, pageNumber, limit);

        total = myFootprintsApiResponse.getMetadata().getTotalElements();

        Collection<FootprintRanking> footprintsRanking = mapperFootprintRanking.reverseMapCollection(myFootprintsApiResponse.getResponse());

        PaginatedCollection<FootprintRanking> footprintsMainPaginated = new PaginatedCollection<>(footprintsRanking);
        footprintsMainPaginated.setPage(page);
        footprintsMainPaginated.setHasMore(
                myFootprintsApiResponse.getMetadata().getPageNumber() < myFootprintsApiResponse.getMetadata().getTotalPages());

        PaginatedGenericTotalCollection<FootprintRanking> myFootprints = new PaginatedGenericTotalCollection<>(footprintsMainPaginated, total);

        return myFootprints;
    }
}
