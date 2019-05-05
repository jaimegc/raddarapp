package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMobileLanguageWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateMobileLanguage;

import javax.inject.Inject;

public class UpdateMobileLanguageFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdateMobileLanguage>
        implements UpdateMobileLanguageWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public UpdateMobileLanguageFakeWriteableDataSource() {}

    @Override
    public boolean updateMobileLanguage(String userKey, UpdateMobileLanguage updateMobileLanguage) throws Exception {
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
