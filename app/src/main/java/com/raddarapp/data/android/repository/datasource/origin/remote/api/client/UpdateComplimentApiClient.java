package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateComplimentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdateComplimentApiRest;

import retrofit2.Call;


public class UpdateComplimentApiClient extends ServerApiClient {

    public UpdateComplimentApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ResponseDto updateMyCompliments(UpdateComplimentDto updateComplimentDto) throws ServerApiException {
        UpdateComplimentApiRest api = getApi(UpdateComplimentApiRest.class);
        Call<ResponseDto> call = api.updateCompliments(updateComplimentDto, true);

        return execute(call);
    }

    public ResponseDto updateCompliments(UpdateComplimentDto updateComplimentDto) throws ServerApiException {
        UpdateComplimentApiRest api = getApi(UpdateComplimentApiRest.class);
        Call<ResponseDto> call = api.updateCompliments(updateComplimentDto, false);

        return execute(call);
    }
}
