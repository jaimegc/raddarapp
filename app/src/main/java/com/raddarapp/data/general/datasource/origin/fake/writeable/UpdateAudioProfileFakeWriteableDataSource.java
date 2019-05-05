package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateAudioProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateAudioProfile;
import com.raddarapp.domain.model.builder.UpdateAudioProfileBuilder;

import javax.inject.Inject;

public class UpdateAudioProfileFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdateAudioProfile>
        implements UpdateAudioProfileWriteableDataSourceContract {

    private static final String UPDATE_AUDIO_PROFILE_KEY = "fakeKey";
    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public UpdateAudioProfileFakeWriteableDataSource() {
    }

    @Override
    public UpdateAudioProfile updateAudioProfile(String imageUri) throws Exception {
        fakeDelay();

        final UpdateAudioProfile updateAudioProfile = new UpdateAudioProfileBuilder()
                .withKey(UPDATE_AUDIO_PROFILE_KEY)
                .withMediaName("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .build();

        return updateAudioProfile;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
