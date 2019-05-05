package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdatePasswordProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdatePasswordProfile;

import javax.inject.Inject;

public class UpdatePasswordProfileFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdatePasswordProfile>
        implements UpdatePasswordProfileWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public UpdatePasswordProfileFakeWriteableDataSource() {}

    @Override
    public boolean updatePasswordProfile(UpdatePasswordProfile updatePasswordProfile) throws Exception {
        fakeDelay();

        return true;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
