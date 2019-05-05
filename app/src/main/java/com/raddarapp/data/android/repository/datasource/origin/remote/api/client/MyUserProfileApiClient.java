package com.raddarapp.data.android.repository.datasource.origin.remote.api.client;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserLoginSocialTokenDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserPasswordDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponse;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.MyUserProfileApiRest;

import retrofit2.Call;

public class MyUserProfileApiClient extends ServerApiClient {

    public MyUserProfileApiClient(ServerApiConfig serverApiConfig) {
        super(serverApiConfig);
    }

    public ServerResponse<MyUserProfileDto> getByKey(String key) throws ServerApiException {
        MyUserProfileApiRest api = getApi(MyUserProfileApiRest.class);
        Call<ServerResponse<MyUserProfileDto>> call = api.getUserProfileByUserId(key);

        return execute(call);
    }

    public MyUserProfileDto login(MyUserPasswordDto userPasswordDto) throws ServerApiException {
        MyUserProfileApiRest api = getApi(MyUserProfileApiRest.class);
        Call<MyUserProfileDto> call = api.login(userPasswordDto);

        return execute(call);
    }

    public MyUserProfileDto loginFacebook(MyUserLoginSocialTokenDto userFacebookTokenDto) throws ServerApiException {
        MyUserProfileApiRest api = getApi(MyUserProfileApiRest.class);
        Call<MyUserProfileDto> call = api.loginFacebook(userFacebookTokenDto);

        return execute(call);
    }

    public MyUserProfileDto loginGoogle(MyUserLoginSocialTokenDto userGoogleTokenDto) throws ServerApiException {
        MyUserProfileApiRest api = getApi(MyUserProfileApiRest.class);
        Call<MyUserProfileDto> call = api.loginGoogle(userGoogleTokenDto);

        return execute(call);
    }
}
