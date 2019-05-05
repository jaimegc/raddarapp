package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CoinMiningDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface CoinMiningApiRest {

    @PUT("users/mining")
    Call<CoinMiningDto> coinMining(@Body CoinMiningDto coinMiningDto);

    @GET("users/mining")
    Call<CoinMiningDto> getCoinMining();
}
