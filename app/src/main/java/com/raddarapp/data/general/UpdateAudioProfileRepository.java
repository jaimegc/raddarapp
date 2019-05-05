package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateAudioProfileWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdateAudioProfileDataSourceFactory;
import com.raddarapp.domain.model.UpdateAudioProfile;

import javax.inject.Inject;

public class UpdateAudioProfileRepository extends RosieRepository<String, UpdateAudioProfile> {

    private final UpdateAudioProfileWriteableDataSourceContract updateAudioProfileWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    UpdateAudioProfileRepository(UpdateAudioProfileDataSourceFactory updateAudioProfileDataSourceFactory) {

        updateAudioProfileWriteableDataSource = updateAudioProfileDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = updateAudioProfileDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(updateAudioProfileWriteableDataSource);
    }

    public UpdateAudioProfile updateAudioProfile(String audioUri) throws Exception {
        UpdateAudioProfile updatedAudioProfile;

        updatedAudioProfile = updateAudioProfileWriteableDataSource.updateAudioProfile(audioUri);
        updateAudioProfilePreferences(updatedAudioProfile.getMediaName());

        return updatedAudioProfile;
    }

    public void updateAudioProfilePreferences(String audioUri) {
        userProfilePreferencesDataSource.setAudio(audioUri);
    }
}
