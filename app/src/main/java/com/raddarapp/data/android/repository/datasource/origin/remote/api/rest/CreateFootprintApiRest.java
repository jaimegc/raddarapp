package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateFootprintDto;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CreateFootprintApiRest {

    @Multipart
    @POST("footprints")
    Call<CreateFootprintDto> createFootprint(@Part MultipartBody.Part image, @Part("data") RequestBody data);
}
