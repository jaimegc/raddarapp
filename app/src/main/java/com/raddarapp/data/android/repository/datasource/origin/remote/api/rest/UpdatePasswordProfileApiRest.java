package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdatePasswordProfileDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface UpdatePasswordProfileApiRest {

    @PUT("users/pass_changing")
    Call<ResponseDto> updatePasswordProfile(@Body UpdatePasswordProfileDto updatePasswordProfileDto);
}
