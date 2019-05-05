package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateImageProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdateImageProfileApiRest;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class UpdateImageProfileApiClient extends ServerApiClient {

    public UpdateImageProfileApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public UpdateImageProfileDto updateImageProfile(String imageUri) throws ServerApiException {
        File imageFile = new File(imageUri);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("media", imageFile.getName(), reqFile);

        UpdateImageProfileApiRest api = getApi(UpdateImageProfileApiRest.class);
        Call<UpdateImageProfileDto> call = api.updateImageProfile(body);

        return execute(call);
    }
}
