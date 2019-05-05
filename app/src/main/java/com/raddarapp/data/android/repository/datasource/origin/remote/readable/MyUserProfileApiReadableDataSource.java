package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.datasource.EmptyReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyUserProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserLoginSocialTokenToMyUserFacebookTokenDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserPasswordToMyUserPasswordDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserProfileToMyUserProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserProfileReadableDataSourceContract;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.MyUserLoginSocialToken;
import com.raddarapp.domain.model.MyUserPassword;

import javax.inject.Inject;

public class MyUserProfileApiReadableDataSource extends EmptyReadableDataSource<String, MyUserProfile>
        implements MyUserProfileReadableDataSourceContract {

    private final MyUserProfileApiClient userProfileApiClient;
    private final MyUserProfileToMyUserProfileDtoMapper mapperUserProfile = new MyUserProfileToMyUserProfileDtoMapper();
    private final MyUserPasswordToMyUserPasswordDtoMapper mapperUserPassword = new MyUserPasswordToMyUserPasswordDtoMapper();
    private final MyUserLoginSocialTokenToMyUserFacebookTokenDtoMapper mapperUserLoginSocialToken = new MyUserLoginSocialTokenToMyUserFacebookTokenDtoMapper();

    @Inject
    MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    @Inject
    public MyUserProfileApiReadableDataSource(MyUserProfileApiClient userProfileApiClient) {
        this.userProfileApiClient = userProfileApiClient;

    }

    @Override
    public MyUserProfile login(MyUserPassword userPassword) throws Exception {
        MyUserProfileDto userProfileDto = userProfileApiClient.login(mapperUserPassword.map(userPassword));

        MyUserProfile userProfile = mapperUserProfile.reverseMap(userProfileDto);

        return userProfile;
    }

    @Override
    public boolean logout() throws Exception {
        userProfilePreferencesDataSource.logout();

        return userProfilePreferencesDataSource.getUsername().isEmpty();
    }

    @Override
    public MyUserProfile loginFacebook(MyUserLoginSocialToken userFacebookToken) throws Exception {
        MyUserProfileDto userProfileDto = userProfileApiClient.loginFacebook(mapperUserLoginSocialToken.map(userFacebookToken));

        MyUserProfile userProfile = mapperUserProfile.reverseMap(userProfileDto);

        return userProfile;
    }

    @Override
    public MyUserProfile loginGoogle(MyUserLoginSocialToken userGoogleToken) throws Exception {
        MyUserProfileDto userProfileDto = userProfileApiClient.loginGoogle(mapperUserLoginSocialToken.map(userGoogleToken));

        MyUserProfile userProfile = mapperUserProfile.reverseMap(userProfileDto);

        return userProfile;
    }
}
