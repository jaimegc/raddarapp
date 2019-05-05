package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateAudioProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdateAudioProfileApiRest;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class UpdateAudioProfileApiClient extends ServerApiClient {

    public UpdateAudioProfileApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public UpdateAudioProfileDto updateAudioProfile(String audioUri) throws ServerApiException {
        File audioFile = new File(audioUri);

        RequestBody reqFile = RequestBody.create(MediaType.parse("audio/mpeg"), audioFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("media", audioFile.getName(), reqFile);

        UpdateAudioProfileApiRest api = getApi(UpdateAudioProfileApiRest.class);
        Call<UpdateAudioProfileDto> call = api.updateAudioProfile(body);

        return execute(call);
    }
}
