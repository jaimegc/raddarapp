package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateCommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.CreateCommentApiRest;

import retrofit2.Call;


public class CreateCommentApiClient extends ServerApiClient {

    public CreateCommentApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public CreateCommentDto createComment(String footprintId, CreateCommentDto createCommentDto) throws ServerApiException {
        CreateCommentApiRest api = getApi(CreateCommentApiRest.class);
        Call<CreateCommentDto> call = api.createComment(footprintId, createCommentDto);

        return execute(call);
    }
}
