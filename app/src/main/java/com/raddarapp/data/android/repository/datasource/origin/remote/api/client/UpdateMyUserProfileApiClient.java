package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdateMyUserProfileApiRest;

import retrofit2.Call;


public class UpdateMyUserProfileApiClient extends ServerApiClient {

    public UpdateMyUserProfileApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public MyUserProfileDto updateMyUserProfile(UpdateMyUserProfileDto updateMyUserProfileDto) throws ServerApiException {
        UpdateMyUserProfileApiRest api = getApi(UpdateMyUserProfileApiRest.class);
        Call<MyUserProfileDto> call = api.updateMyUserProfile(updateMyUserProfileDto);

        return execute(call);
    }
}
