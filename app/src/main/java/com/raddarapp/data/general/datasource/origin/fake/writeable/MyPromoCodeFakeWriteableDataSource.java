package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyPromoCodeWriteableDataSourceContract;
import com.raddarapp.domain.model.MyPromoCode;
import com.raddarapp.domain.model.builder.MyPromoCodeBuilder;

import javax.inject.Inject;

public class MyPromoCodeFakeWriteableDataSource extends EmptyWriteableDataSource<String, MyPromoCode>
        implements MyPromoCodeWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public MyPromoCodeFakeWriteableDataSource() {
    }

    @Override
    public MyPromoCode getMyPromoCode(String promoCodeKey) throws Exception {
        fakeDelay();

        final MyPromoCode myPromoCode = new MyPromoCodeBuilder()
                .withCode("PR1HELLO")
                .withExchanged(true)
                .withExchangeDate("2017-11-02T14:25:23.000+0000")
                .withTimesExchanged(4l)
                .withMyUserKey("123456")
                .build();

        return myPromoCode;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
