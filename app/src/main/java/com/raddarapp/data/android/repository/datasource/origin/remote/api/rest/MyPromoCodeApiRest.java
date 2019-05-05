package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyPromoCodeDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyPromoCodeApiRest {

    @GET("promotions/{promotionId}/code/user")
    Call<MyPromoCodeDto> getMyPromoCode(@Path("promotionId") String promotionId);
}
