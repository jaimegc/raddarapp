package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintRankingDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.FootprintRankingApiRest;

import retrofit2.Call;

public class FootprintRankingApiClient extends ServerApiClient {

    public FootprintRankingApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ServerResponseCollection<FootprintRankingDto> getFootprintsRankingByZoneKey(String zoneId, int offset, int limit) throws ServerApiException {
        FootprintRankingApiRest api = getApi(FootprintRankingApiRest.class);
        Call<ServerResponseCollection<FootprintRankingDto>> call = api.getFootprintsRankingByZoneKey(zoneId, offset, limit);

        return execute(call);
    }

}
