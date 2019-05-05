package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMyUserProfileWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdateMyUserProfileDataSourceFactory;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.UpdateMyUserProfile;

import javax.inject.Inject;

public class UpdateMyUserProfileRepository extends RosieRepository<String, UpdateMyUserProfile> {

    private final UpdateMyUserProfileWriteableDataSourceContract updateMyUserProfileWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    UpdateMyUserProfileRepository(UpdateMyUserProfileDataSourceFactory updateMyUserProfileDataSourceFactory) {

        updateMyUserProfileWriteableDataSource = updateMyUserProfileDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = updateMyUserProfileDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(updateMyUserProfileWriteableDataSource);
    }

    public MyUserProfile updateMyUserProfile(UpdateMyUserProfile updateMyUserProfile) throws Exception {
        MyUserProfile myUserProfileUpdated;

        myUserProfileUpdated = updateMyUserProfileWriteableDataSource.updateMyUserProfile(updateMyUserProfile);

        this.setUserProfilePreferencesFull(myUserProfileUpdated);

        return myUserProfileUpdated;
    }

    public void setUserProfilePreferencesFull(MyUserProfile userProfile) {
        userProfilePreferencesDataSource.setUserProfilePreferencesFull(userProfile);
        userProfilePreferencesDataSource.setRefreshTokenUpdated(System.currentTimeMillis());
    }
}
