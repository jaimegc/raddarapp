package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.TerritoryAreaApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.RaddarLocationToRaddarLocationDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.TerritoryAreaToTerritoryAreaDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryAreaDto;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoryAreaReadableDataSourceContract;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.TerritoryArea;

import javax.inject.Inject;

public class TerritoryAreaApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, TerritoryArea>
    implements TerritoryAreaReadableDataSourceContract {

    private final TerritoryAreaApiClient territoryAreaApiClient;
    private final TerritoryAreaToTerritoryAreaDtoMapper mapper = new TerritoryAreaToTerritoryAreaDtoMapper();

    @Inject
    public TerritoryAreaApiReadableDataSource(TerritoryAreaApiClient territoryAreaApiClient) {
        this.territoryAreaApiClient = territoryAreaApiClient;
    }

    @Override
    public TerritoryArea getTerritoryAreaByZoneId(String territoryAreaKey) throws Exception {
        return null;
    }

    @Override
    public TerritoryArea getTerritoryAreaByTerritoryId(String territoryKey) throws Exception {
        return null;
    }

    @Override
    public TerritoryArea getTerritoryAreaByCoordinates(RaddarLocation raddarLocation) throws Exception {
        final RaddarLocationToRaddarLocationDtoMapper raddarLocationToRaddarLocationDtoMapper = new RaddarLocationToRaddarLocationDtoMapper();
        TerritoryAreaDto territoryAreaDtoApiResponse =
                territoryAreaApiClient.getTerritoryAreaByCoordinates(raddarLocationToRaddarLocationDtoMapper.map(raddarLocation));

        return mapper.reverseMap(territoryAreaDtoApiResponse);
    }
}
