package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintCollectionDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.MyFootprintCollectionApiRest;

import retrofit2.Call;

public class MyFootprintCollectionApiClient extends ServerApiClient {

    public MyFootprintCollectionApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ServerResponseCollection<MyFootprintCollectionDto> getMyFootprintCollection(int offset, int limit) throws ServerApiException {
        MyFootprintCollectionApiRest api = getApi(MyFootprintCollectionApiRest.class);
        Call<ServerResponseCollection<MyFootprintCollectionDto>> call = api.getMyFootprintsCollection(offset, limit);

        return execute(call);
    }

    public ResponseDto deleteMyFootprintCollection(String footprintId) throws ServerApiException {
        MyFootprintCollectionApiRest api = getApi(MyFootprintCollectionApiRest.class);
        Call<ResponseDto> call = api.deleteMyFootprintCollection(footprintId);

        return execute(call);
    }
}
