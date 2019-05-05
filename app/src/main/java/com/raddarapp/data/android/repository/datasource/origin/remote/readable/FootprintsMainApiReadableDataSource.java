package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.FootprintMainApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.FootprintMainToFootprintMainDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.RaddarLocationToRaddarLocationDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserProfileToMyUserProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintMainDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.FootprintServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedFootprintsMainTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsMainReadableDataSourceContract;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.MyUserProfile;

import java.util.Collection;

import javax.inject.Inject;

public class FootprintsMainApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, FootprintMain>
        implements FootprintsMainReadableDataSourceContract {

    private final FootprintMainApiClient footprintMainApiClient;
    private final FootprintMainToFootprintMainDtoMapper mapperFootprintMain = new FootprintMainToFootprintMainDtoMapper();
    private final MyUserProfileToMyUserProfileDtoMapper mapperUserProfile = new MyUserProfileToMyUserProfileDtoMapper();

    @Inject
    public FootprintsMainApiReadableDataSource(FootprintMainApiClient footprintMainApiClient) {
        this.footprintMainApiClient = footprintMainApiClient;
    }

    @Override
    public PaginatedFootprintsMainTotalCollection<FootprintMain> getFootprintsMain(RaddarLocation raddarLocation, Integer pageNumber, Page page) throws Exception {
        final RaddarLocationToRaddarLocationDtoMapper raddarLocationToRaddarLocationDtoMapper = new RaddarLocationToRaddarLocationDtoMapper();
        int limit = page.getLimit();
        long total;

        FootprintServerResponseCollection<FootprintMainDto> footprintsMainApiResponse =
                footprintMainApiClient.getFootprintsMain(raddarLocationToRaddarLocationDtoMapper.map(raddarLocation), pageNumber, limit);

        total = footprintsMainApiResponse.getFootprints().getMetadata().getTotalElements();

        MyUserProfile userProfile = mapperUserProfile.reverseMap(footprintsMainApiResponse.getMyUserProfile());
        Collection<FootprintMain> footprintsMainCollection = mapperFootprintMain.reverseMapCollection(footprintsMainApiResponse.getFootprints().getResponse());

        PaginatedCollection<FootprintMain> footprintsMainPaginated = new PaginatedCollection<>(footprintsMainCollection);
        footprintsMainPaginated.setPage(page);
        footprintsMainPaginated.setHasMore(
                footprintsMainApiResponse.getFootprints().getMetadata().getPageNumber() <
                        footprintsMainApiResponse.getFootprints().getMetadata().getTotalPages());

        PaginatedFootprintsMainTotalCollection<FootprintMain> footprintsMain = new PaginatedFootprintsMainTotalCollection<>(footprintsMainPaginated, userProfile, total);

        return footprintsMain;
    }

    @Override
    public PaginatedCollection<FootprintMain> getFootprintsMainByUserId(String userKey, Page page) throws Exception {
        int offset = page.getOffset();
        int limit = page.getLimit();
        long total;

        ServerResponseCollection<FootprintMainDto> footprintsMainApiResponse =
                footprintMainApiClient.getFootprintsMainByUserId(userKey, offset, limit);

        total = footprintsMainApiResponse.getMetadata().getTotalElements();

        Collection<FootprintMain> footprintsMainCollection = mapperFootprintMain.reverseMapCollection(footprintsMainApiResponse.getResponse());

        PaginatedCollection<FootprintMain> footprintsMainPage = new PaginatedCollection<>(footprintsMainCollection);
        footprintsMainPage.setPage(page);

        footprintsMainPage.setHasMore(offset + footprintsMainCollection.size() < total);

        return footprintsMainPage;
    }
}
