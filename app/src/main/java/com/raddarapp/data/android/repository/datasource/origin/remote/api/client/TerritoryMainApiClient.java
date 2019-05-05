package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.TerritoryMainDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.TerritoryMainApiRest;

import retrofit2.Call;

public class TerritoryMainApiClient extends ServerApiClient {

    public TerritoryMainApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public TerritoryMainDto getTerritoryMainByCoordinates(RaddarLocationDto raddarLocationDto) throws ServerApiException {
        TerritoryMainApiRest api = getApi(TerritoryMainApiRest.class);
        Call<TerritoryMainDto> call = api.getTerritoryMainByCoordinates(raddarLocationDto);

        return execute(call);
    }

    public TerritoryMainDto getTerritoryMainByZoneId(String zoneId) throws ServerApiException {
        TerritoryMainApiRest api = getApi(TerritoryMainApiRest.class);
        Call<TerritoryMainDto> call = api.getTerritoryMainByZoneId(zoneId);

        return execute(call);
    }
}
