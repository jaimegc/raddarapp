package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateImageProfileWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdateImageProfileDataSourceFactory;
import com.raddarapp.domain.model.UpdateImageProfile;

import javax.inject.Inject;

public class UpdateImageProfileRepository extends RosieRepository<String, UpdateImageProfile> {

    private final UpdateImageProfileWriteableDataSourceContract updateImageProfileWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    UpdateImageProfileRepository(UpdateImageProfileDataSourceFactory updateImageProfileDataSourceFactory) {

        updateImageProfileWriteableDataSource = updateImageProfileDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = updateImageProfileDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(updateImageProfileWriteableDataSource);
    }

    public UpdateImageProfile updateImageProfile(String imageUri) throws Exception {
        UpdateImageProfile updatedImageProfile;

        updatedImageProfile = updateImageProfileWriteableDataSource.updateImageProfile(imageUri);
        updateImageProfilePreferences(updatedImageProfile.getMediaName());

        return updatedImageProfile;
    }

    public void updateImageProfilePreferences(String imageUri) {
        userProfilePreferencesDataSource.setImage(imageUri);
    }
}
