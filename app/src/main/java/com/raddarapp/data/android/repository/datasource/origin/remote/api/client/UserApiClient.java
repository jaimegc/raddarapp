package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FollowDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UserApiRest;

import retrofit2.Call;

public class UserApiClient extends ServerApiClient {

    public UserApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public FollowDto followUser(String userId) throws ServerApiException {
        UserApiRest api = getApi(UserApiRest.class);
        Call<FollowDto> call = api.followUser(userId);

        return execute(call);
    }

    public FollowDto unfollowUser(String userId) throws ServerApiException {
        UserApiRest api = getApi(UserApiRest.class);
        Call<FollowDto> call = api.unfollowUser(userId);

        return execute(call);
    }

    public ResponseDto activateUser(String userId) throws ServerApiException {
        UserApiRest api = getApi(UserApiRest.class);
        Call<ResponseDto> call = api.activateUser(userId);

        return execute(call);
    }

    public ResponseDto desactivateUser(String userId) throws ServerApiException {
        UserApiRest api = getApi(UserApiRest.class);
        Call<ResponseDto> call = api.desactivateUser(userId);

        return execute(call);
    }
}
