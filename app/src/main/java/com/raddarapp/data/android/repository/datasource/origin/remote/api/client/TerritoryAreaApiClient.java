package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryAreaDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.TerritoryAreaApiRest;

import retrofit2.Call;

public class TerritoryAreaApiClient extends ServerApiClient {

    public TerritoryAreaApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public TerritoryAreaDto getTerritoryAreaByCoordinates(RaddarLocationDto raddarLocationDto) throws ServerApiException {
        TerritoryAreaApiRest api = getApi(TerritoryAreaApiRest.class);
        Call<TerritoryAreaDto> call = api.getTerritoryAreaByCoordinates(raddarLocationDto);

        return execute(call);
    }
}
