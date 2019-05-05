package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FootprintMainDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.AddVoteDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.FootprintServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.FootprintMainApiRest;

import retrofit2.Call;

public class FootprintMainApiClient extends ServerApiClient {

    public FootprintMainApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public FootprintServerResponseCollection<FootprintMainDto> getFootprintsMain(RaddarLocationDto raddarLocationDto,
            int pageNumber, int limit) throws ServerApiException {
        FootprintMainApiRest api = getApi(FootprintMainApiRest.class);
        Call<FootprintServerResponseCollection<FootprintMainDto>> call = api.getFootprintsMain(raddarLocationDto, pageNumber, limit);

        return execute(call);
    }

    public ServerResponseCollection<FootprintMainDto> getFootprintsMainByUserId(String userId,
            int offset, int limit) throws ServerApiException {
        FootprintMainApiRest api = getApi(FootprintMainApiRest.class);
        Call<ServerResponseCollection<FootprintMainDto>> call = api.getFootprintsMainByUserId(userId, offset, limit);

        return execute(call);
    }

    public AddVoteDto addVote(String footprintId, int addVoteType) throws ServerApiException {
        FootprintMainApiRest api = getApi(FootprintMainApiRest.class);
        Call<AddVoteDto> call = api.addVote(footprintId, addVoteType);

        return execute(call);
    }
}
