package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMobileLanguageWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdateMobileLanguageDataSourceFactory;
import com.raddarapp.domain.model.UpdateMobileLanguage;

import javax.inject.Inject;

public class UpdateMobileLanguageRepository extends RosieRepository<String, UpdateMobileLanguage> {

    private final UpdateMobileLanguageWriteableDataSourceContract updateMobileLanguageWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    UpdateMobileLanguageRepository(UpdateMobileLanguageDataSourceFactory updateMobileLanguageDataSourceFactory) {

        updateMobileLanguageWriteableDataSource = updateMobileLanguageDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = updateMobileLanguageDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(updateMobileLanguageWriteableDataSource);
    }

    public boolean updateMobileLanguage(String userKey, UpdateMobileLanguage updateMobileLanguage) throws Exception {
        boolean updatedMobileLanguage;

        updatedMobileLanguage = updateMobileLanguageWriteableDataSource.updateMobileLanguage(userKey, updateMobileLanguage);

        return updatedMobileLanguage;
    }
}
