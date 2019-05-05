package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RefreshTokenDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserLoginSocialTokenDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserPasswordDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponse;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyUserProfileApiRest {

    @GET("userProfile")
    Call<ServerResponse<MyUserProfileDto>> getUserProfileByUserId(@Query("id") String key);

    @POST("security/login")
    Call<MyUserProfileDto> login(@Body MyUserPasswordDto userPasswordDto);

    @POST("social/security/facebook/login")
    Call<MyUserProfileDto> loginFacebook(@Body MyUserLoginSocialTokenDto userFacebookTokenDto);

    @POST("social/security/google/login")
    Call<MyUserProfileDto> loginGoogle(@Body MyUserLoginSocialTokenDto userGoogleTokenDto);

    @POST("security/refresh_token")
    Call<MyUserProfileDto> refreshToken(@Body RefreshTokenDto refreshTokenDto);
}
