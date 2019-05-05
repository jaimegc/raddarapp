package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.CommentApiRest;

import retrofit2.Call;

public class CommentApiClient extends ServerApiClient {

    public CommentApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ServerResponseCollection<CommentDto> getCommentsByFootprintId(String footprintId, int pageNumber, int limit) throws ServerApiException {
        CommentApiRest api = getApi(CommentApiRest.class);
        Call<ServerResponseCollection<CommentDto>> call = api.getCommentsByFootprintId(footprintId, pageNumber, limit);

        return execute(call);
    }
}
