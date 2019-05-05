package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.CreateFootprintApiRest;
import com.raddarapp.presentation.android.utils.JsonUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class CreateFootprintApiClient extends ServerApiClient {

    public CreateFootprintApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public CreateFootprintDto createFootprint(CreateFootprintDto createFootprintDto, String imageUri) throws ServerApiException {
        JsonUtils jsonUtils = new JsonUtils();
        File imageFile = new File(imageUri);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("media", imageFile.getName(), reqFile);

        RequestBody footprint = RequestBody.create(
                MediaType.parse("multipart/form-data"), jsonUtils.objetToJson(createFootprintDto));

        CreateFootprintApiRest api = getApi(CreateFootprintApiRest.class);
        Call<CreateFootprintDto> call = api.createFootprint(body, footprint);

        return execute(call);
    }
}
