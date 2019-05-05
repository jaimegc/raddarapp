package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateImageProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateImageProfile;
import com.raddarapp.domain.model.builder.UpdateImageProfileBuilder;

import javax.inject.Inject;

public class UpdateImageProfileFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdateImageProfile>
        implements UpdateImageProfileWriteableDataSourceContract {

    private static final String UPDATE_IMAGE_PROFILE_KEY = "fakeKey";
    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public UpdateImageProfileFakeWriteableDataSource() {
    }

    @Override
    public UpdateImageProfile updateImageProfile(String audioUri) throws Exception {
        fakeDelay();

        final UpdateImageProfile updateImageProfile = new UpdateImageProfileBuilder()
                .withKey(UPDATE_IMAGE_PROFILE_KEY)
                .withMediaName("http://static.wixstatic.com/media/c65fd6_6a73f934bd584a05987b7acf6eb1b128.jpg")
                .build();

        return updateImageProfile;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
