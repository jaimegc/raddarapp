package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CoinMiningDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.CoinMiningApiRest;

import retrofit2.Call;

public class CoinMiningApiClient extends ServerApiClient {

    public CoinMiningApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public CoinMiningDto coinMining(CoinMiningDto coinMiningDto) throws ServerApiException {
        CoinMiningApiRest api = getApi(CoinMiningApiRest.class);
        Call<CoinMiningDto> call = api.coinMining(coinMiningDto);

        return execute(call);
    }

    public CoinMiningDto getCoinMining() throws ServerApiException {
        CoinMiningApiRest api = getApi(CoinMiningApiRest.class);
        Call<CoinMiningDto> call = api.getCoinMining();

        return execute(call);
    }
}
