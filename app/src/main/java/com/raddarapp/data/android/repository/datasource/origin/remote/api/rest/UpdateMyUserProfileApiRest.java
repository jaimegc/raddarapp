package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMyUserProfileDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface UpdateMyUserProfileApiRest {

    @PATCH("users/update_profile")
    Call<MyUserProfileDto> updateMyUserProfile(@Body UpdateMyUserProfileDto updateMyUserProfileDto);
}
