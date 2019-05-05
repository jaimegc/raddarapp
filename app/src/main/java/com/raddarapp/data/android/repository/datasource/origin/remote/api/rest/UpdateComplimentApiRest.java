package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateComplimentDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UpdateComplimentApiRest {

    @PUT("users/compliments")
    Call<ResponseDto> updateCompliments(@Body UpdateComplimentDto updateComplimentDto, @Query("own") Boolean own);
}
