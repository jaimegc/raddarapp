package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.PromoCodeApiRest;

import retrofit2.Call;

public class PromoCodeApiClient extends ServerApiClient {

    public PromoCodeApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ResponseDto promoCode(String promoCode) throws ServerApiException {
        PromoCodeApiRest api = getApi(PromoCodeApiRest.class);
        Call<ResponseDto> call = api.promoCode(promoCode);

        return execute(call);
    }
}
