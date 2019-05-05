package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.TerritoryMainApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.RaddarLocationToRaddarLocationDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.TerritoryMainToTerritoryMainDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryMainDto;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoryMainReadableDataSourceContract;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryMain;

import javax.inject.Inject;

public class TerritoryMainApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, TerritoryMain>
    implements TerritoryMainReadableDataSourceContract {

    private final TerritoryMainApiClient territoryMainApiClient;
    private final TerritoryMainToTerritoryMainDtoMapper mapper = new TerritoryMainToTerritoryMainDtoMapper();

    @Inject
    public TerritoryMainApiReadableDataSource(TerritoryMainApiClient territoryMainApiClient) {
        this.territoryMainApiClient = territoryMainApiClient;
    }

    @Override
    public TerritoryMain getTerritoryMainByCoordinates(RaddarLocation raddarLocation) throws Exception {
        final RaddarLocationToRaddarLocationDtoMapper raddarLocationToRaddarLocationDtoMapper = new RaddarLocationToRaddarLocationDtoMapper();
        TerritoryMainDto territoryMainDtoApiResponse =
                territoryMainApiClient.getTerritoryMainByCoordinates(raddarLocationToRaddarLocationDtoMapper.map(raddarLocation));

        return mapper.reverseMap(territoryMainDtoApiResponse);
    }

    @Override
    public TerritoryMain getTerritoryMainByZone(String zoneKey) throws Exception {
        TerritoryMainDto territoryMainDtoApiResponse =
                territoryMainApiClient.getTerritoryMainByZoneId(zoneKey);

        return mapper.reverseMap(territoryMainDtoApiResponse);
    }
}
