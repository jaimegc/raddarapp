package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.karumi.rosie.repository.datasource.CacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserProfileReadableDataSourceContract;
import com.raddarapp.data.general.factory.MyUserProfileDataSourceFactory;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.MyUserLoginSocialToken;
import com.raddarapp.domain.model.MyUserPassword;

import javax.inject.Inject;

public class MyUserProfileRepository extends RosieRepository<String, MyUserProfile> {

    private final MyUserProfileReadableDataSourceContract userProfileReadableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    MyUserProfileRepository(MyUserProfileDataSourceFactory userProfileDataSourceFactory,
                            CacheDataSource<String, MyUserProfile> inMemoryPaginatedCache) {

        userProfileReadableDataSource = userProfileDataSourceFactory.createReadableDataSource();
        userProfilePreferencesDataSource = userProfileDataSourceFactory.createPreferencesDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addReadableDataSources(userProfileReadableDataSource);
    }

    public MyUserProfile loginFacebook(MyUserLoginSocialToken userFacebookToken) throws Exception {
        MyUserProfile userProfile = userProfileReadableDataSource.loginFacebook(userFacebookToken);

        this.setUserProfilePreferencesFull(userProfile);

        return userProfile;
    }

    public MyUserProfile loginGoogle(MyUserLoginSocialToken userGoogleToken) throws Exception {
        MyUserProfile userProfile = userProfileReadableDataSource.loginGoogle(userGoogleToken);

        this.setUserProfilePreferencesFull(userProfile);

        return userProfile;
    }

    public MyUserProfile login(MyUserPassword userPassword) throws Exception {
        MyUserProfile userProfile = userProfileReadableDataSource.login(userPassword);

        this.setUserProfilePreferencesFull(userProfile);

        return userProfile;
    }

    public boolean logout() throws Exception {
        userProfilePreferencesDataSource.logout();

        return !userProfilePreferencesDataSource.isLoggedIn();
    }

    public boolean isLoggedIn() throws Exception {
        return userProfilePreferencesDataSource.isLoggedIn();
    }

    public void setUserProfilePreferencesFull(MyUserProfile userProfile) {
        userProfilePreferencesDataSource.setUserProfilePreferencesFull(userProfile);
    }

    public MyUserProfile getUserProfilePreferences() {
        MyUserProfile userProfile = userProfilePreferencesDataSource.getUserProfilePreferences();

        return userProfile;
    }
}
