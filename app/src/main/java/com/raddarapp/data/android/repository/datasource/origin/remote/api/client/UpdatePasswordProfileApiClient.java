package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdatePasswordProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdatePasswordProfileApiRest;

import retrofit2.Call;

public class UpdatePasswordProfileApiClient extends ServerApiClient {

    public UpdatePasswordProfileApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ResponseDto updatePasswordProfile(UpdatePasswordProfileDto updatePasswordProfileDto) throws ServerApiException {
        UpdatePasswordProfileApiRest api = getApi(UpdatePasswordProfileApiRest.class);
        Call<ResponseDto> call = api.updatePasswordProfile(updatePasswordProfileDto);

        return execute(call);
    }
}
