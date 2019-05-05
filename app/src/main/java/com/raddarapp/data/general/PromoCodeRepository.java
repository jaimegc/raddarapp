package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.PromoCodeWriteableDataSourceContract;
import com.raddarapp.data.general.factory.PromoCodeDataSourceFactory;
import com.raddarapp.domain.model.PromoCode;

import javax.inject.Inject;

public class PromoCodeRepository extends RosieRepository<String, PromoCode> {

    private final PromoCodeWriteableDataSourceContract promoCodeWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    PromoCodeRepository(PromoCodeDataSourceFactory promoCodeDataSourceFactory) {

        promoCodeWriteableDataSource = promoCodeDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = promoCodeDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(promoCodeWriteableDataSource);
    }

    public boolean promoCode(String promoCode) throws Exception {
        boolean promoCodeExchaned;

        promoCodeExchaned = promoCodeWriteableDataSource.promoCode(promoCode);

        return promoCodeExchaned;
    }
}
