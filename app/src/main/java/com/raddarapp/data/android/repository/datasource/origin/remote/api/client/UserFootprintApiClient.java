package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UserFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UserFootprintApiRest;

import retrofit2.Call;

public class UserFootprintApiClient extends ServerApiClient {

    public UserFootprintApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ServerResponseCollection<UserFootprintDto> getUserFootprintsByUserId(String userId,
            int offset, int limit) throws ServerApiException {
        UserFootprintApiRest api = getApi(UserFootprintApiRest.class);
        Call<ServerResponseCollection<UserFootprintDto>> call = api.getUserFootprintsByUserId(userId, offset, limit);

        return execute(call);
    }
}
