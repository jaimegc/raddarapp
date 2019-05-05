package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMobileLanguageDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdateMobileLanguageApiRest;

import retrofit2.Call;

public class UpdateMobileLanguageApiClient extends ServerApiClient {

    public UpdateMobileLanguageApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ResponseDto updateMobileLanguage(String userId, UpdateMobileLanguageDto updateMobileLanguageDto) throws ServerApiException {
        UpdateMobileLanguageApiRest api = getApi(UpdateMobileLanguageApiRest.class);
        Call<ResponseDto> call = api.updateMobileLanguage(userId, updateMobileLanguageDto);

        return execute(call);
    }
}
