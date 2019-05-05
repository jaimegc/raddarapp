package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateImageProfileDto;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UpdateImageProfileApiRest {

    @Multipart
    @PUT("users/profile_image_changing")
    Call<UpdateImageProfileDto> updateImageProfile(@Part MultipartBody.Part image);
}
