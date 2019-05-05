package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.PromoCodeWriteableDataSourceContract;
import com.raddarapp.domain.model.PromoCode;

import javax.inject.Inject;

public class PromoCodeFakeWriteableDataSource extends EmptyWriteableDataSource<String, PromoCode>
        implements PromoCodeWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public PromoCodeFakeWriteableDataSource() {
    }

    @Override
    public boolean promoCode(String promoCode) throws Exception {
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
