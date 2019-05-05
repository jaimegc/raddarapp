package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserRankingDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.MyUserRankingServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.MyUserRankingApiRest;

import retrofit2.Call;

public class MyUserRankingApiClient extends ServerApiClient {

    public MyUserRankingApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public MyUserRankingServerResponseCollection<MyUserRankingDto> getMyUsersRankingByTerritoryId(String territoryId,
        int pageNumber, int limit) throws ServerApiException {
        MyUserRankingApiRest api = getApi(MyUserRankingApiRest.class);
        Call<MyUserRankingServerResponseCollection<MyUserRankingDto>> call = api.getMyUsersRankingByTerritoryId(territoryId, pageNumber, limit);

        return execute(call);
    }

}
