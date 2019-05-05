package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyPromoCodeWriteableDataSourceContract;
import com.raddarapp.data.general.factory.MyPromoCodeDataSourceFactory;
import com.raddarapp.domain.model.MyPromoCode;

import javax.inject.Inject;

public class MyPromoCodeRepository extends RosieRepository<String, MyPromoCode> {

    private final MyPromoCodeWriteableDataSourceContract myPromoCodeWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    MyPromoCodeRepository(MyPromoCodeDataSourceFactory myPromoCodeDataSourceFactory) {

        myPromoCodeWriteableDataSource = myPromoCodeDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = myPromoCodeDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(myPromoCodeWriteableDataSource);
    }

    public MyPromoCode getMyPromoCode(String promoCodeKey) {
        MyPromoCode myPromoCode = null;

        // Because sometimes when we load a footprint details from notification produces an unhandled exception
        // I don't know the reason :/
        try {
            myPromoCode = myPromoCodeWriteableDataSource.getMyPromoCode(promoCodeKey);
        } catch (Exception e) { }

        return myPromoCode;
    }
}
