package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FollowDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;

import retrofit2.Call;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApiRest {

    @POST("users/follow/{userId}")
    Call<FollowDto> followUser(@Path("userId") String userId);

    @POST("users/unfollow/{userId}")
    Call<FollowDto> unfollowUser(@Path("userId") String userId);

    @PATCH("users/{userId}/activate")
    Call<ResponseDto> activateUser(@Path("userId") String userId);

    @PATCH("users/{userId}/desactivate")
    Call<ResponseDto> desactivateUser(@Path("userId") String userId);
}
