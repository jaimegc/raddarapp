package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.UpdateNotificationApiRest;

import retrofit2.Call;

public class UpdateNotificationTopicApiClient extends ServerApiClient {

    public UpdateNotificationTopicApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ResponseDto updateNotificationTopic(String notificationTopicId) throws ServerApiException {
        UpdateNotificationApiRest api = getApi(UpdateNotificationApiRest.class);
        Call<ResponseDto> call = api.updateNotificationTopic(notificationTopicId);

        return execute(call);
    }

    public ResponseDto updateNotificationToken(String notificationToken) throws ServerApiException {
        UpdateNotificationApiRest api = getApi(UpdateNotificationApiRest.class);
        Call<ResponseDto> call = api.updateNotificationToken(notificationToken);

        return execute(call);
    }
}
