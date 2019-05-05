package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsWriteableDataSourceContract;
import com.raddarapp.domain.model.MyFootprint;

import javax.inject.Inject;

public class MyFootprintsFakeWriteableDataSource extends EmptyWriteableDataSource<String, MyFootprint>
        implements MyFootprintsWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public MyFootprintsFakeWriteableDataSource() {
    }

    @Override
    public boolean deleteMyFootprint(String footPrintKey) throws Exception {
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
