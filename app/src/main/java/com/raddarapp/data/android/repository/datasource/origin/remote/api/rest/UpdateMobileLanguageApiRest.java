package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMobileLanguageDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface UpdateMobileLanguageApiRest {

    @PATCH("users/{userId}/language")
    Call<ResponseDto> updateMobileLanguage(@Path("userId") String userId, @Body UpdateMobileLanguageDto updateMobileLanguageDto);
}
