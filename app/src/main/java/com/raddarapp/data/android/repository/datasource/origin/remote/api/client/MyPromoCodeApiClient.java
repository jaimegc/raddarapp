package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyPromoCodeDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.MyPromoCodeApiRest;

import retrofit2.Call;

public class MyPromoCodeApiClient extends ServerApiClient {

    public MyPromoCodeApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public MyPromoCodeDto getMyPromoCode(String promoCodeId) throws ServerApiException {
        MyPromoCodeApiRest api = getApi(MyPromoCodeApiRest.class);
        Call<MyPromoCodeDto> call = api.getMyPromoCode(promoCodeId);

        return execute(call);
    }
}
