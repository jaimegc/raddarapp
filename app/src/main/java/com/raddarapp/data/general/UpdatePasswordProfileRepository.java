package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdatePasswordProfileWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdatePasswordProfileDataSourceFactory;
import com.raddarapp.domain.model.UpdatePasswordProfile;

import javax.inject.Inject;

public class UpdatePasswordProfileRepository extends RosieRepository<String, UpdatePasswordProfile> {

    private final UpdatePasswordProfileWriteableDataSourceContract updatePasswordProfileWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    UpdatePasswordProfileRepository(UpdatePasswordProfileDataSourceFactory updatePasswordProfileDataSourceFactory) {

        updatePasswordProfileWriteableDataSource = updatePasswordProfileDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = updatePasswordProfileDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(updatePasswordProfileWriteableDataSource);
    }

    public boolean updatePasswordProfile(UpdatePasswordProfile updatePasswordProfile) throws Exception {
        boolean updatedPasswordProfile;

        updatedPasswordProfile = updatePasswordProfileWriteableDataSource.updatePasswordProfile(updatePasswordProfile);

        return updatedPasswordProfile;
    }
}
