package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateAudioProfileDto;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UpdateAudioProfileApiRest {

    @Multipart
    @PUT("users/profile_audio_changing")
    Call<UpdateAudioProfileDto> updateAudioProfile(@Part MultipartBody.Part audio);
}
