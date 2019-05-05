package com.raddarapp.data.android.repository.datasource.origin.remote.api.rest;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;

import retrofit2.Call;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface UpdateNotificationApiRest {

    @PATCH("users/update_notification_topic/{notificationTopic}")
    Call<ResponseDto> updateNotificationTopic(@Path("notificationTopic") String notificationTopic);

    @PATCH("users/update_notification_token/{notificationToken}")
    Call<ResponseDto> updateNotificationToken(@Path("notificationToken") String notificationToken);
}
