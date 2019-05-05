package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PromoCodeApiRest {

    @PUT("promotions/code/{code}/exchange")
    Call<ResponseDto> promoCode(@Path("code") String promoCode);
}
