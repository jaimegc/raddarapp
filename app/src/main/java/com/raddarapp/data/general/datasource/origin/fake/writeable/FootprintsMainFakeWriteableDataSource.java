package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.FootprintsMainWriteableDataSourceContract;
import com.raddarapp.domain.model.FootprintMain;

import javax.inject.Inject;

public class FootprintsMainFakeWriteableDataSource extends EmptyWriteableDataSource<String, FootprintMain>
        implements FootprintsMainWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public FootprintsMainFakeWriteableDataSource() {
    }


    @Override
    public double addVote(String footprintKey, int addVoteType) throws Exception {
        fakeDelay();

        return addVoteType;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
