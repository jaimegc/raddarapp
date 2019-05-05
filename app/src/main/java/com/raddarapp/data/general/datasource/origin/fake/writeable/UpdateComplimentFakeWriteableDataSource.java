package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateComplimentWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateCompliment;

import java.util.Random;

import javax.inject.Inject;

public class UpdateComplimentFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdateCompliment>
        implements UpdateComplimentWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public UpdateComplimentFakeWriteableDataSource() {
    }

    @Override
    public boolean updateMyCompliments(UpdateCompliment updateCompliment) throws Exception {
        fakeDelay();

        return true;
    }

    @Override
    public boolean updateCompliments(UpdateCompliment updateCompliment) throws Exception {
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
