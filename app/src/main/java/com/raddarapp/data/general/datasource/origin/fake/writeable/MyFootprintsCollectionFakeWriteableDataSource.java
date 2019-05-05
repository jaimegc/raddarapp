package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsCollectionWriteableDataSourceContract;
import com.raddarapp.domain.model.MyFootprintCollection;

import javax.inject.Inject;

public class MyFootprintsCollectionFakeWriteableDataSource extends EmptyWriteableDataSource<String, MyFootprintCollection>
        implements MyFootprintsCollectionWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public MyFootprintsCollectionFakeWriteableDataSource() {
    }

    @Override
    public boolean deleteMyFootprintCollection(String myFootPrintCollectionKey) throws Exception {
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
