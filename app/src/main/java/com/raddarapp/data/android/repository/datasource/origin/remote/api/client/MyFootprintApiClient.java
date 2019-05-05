package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.MyFootprintApiRest;

import retrofit2.Call;

public class MyFootprintApiClient extends ServerApiClient {

    public MyFootprintApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ServerResponseCollection<MyFootprintDto> getMyFootprints(int pageNumber, int limit) throws ServerApiException {
        MyFootprintApiRest api = getApi(MyFootprintApiRest.class);
        Call<ServerResponseCollection<MyFootprintDto>> call = api.getMyFootprintsMain(pageNumber, limit);

        return execute(call);
    }

    public ResponseDto deleteMyFootprint(String footprintId) throws ServerApiException {
        MyFootprintApiRest api = getApi(MyFootprintApiRest.class);
        Call<ResponseDto> call = api.deleteMyFootprint(footprintId);

        return execute(call);
    }
}
