package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyPromoCodeApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyPromoCodeToMyPromoCodeDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyPromoCodeDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyPromoCodeWriteableDataSourceContract;
import com.raddarapp.domain.model.MyPromoCode;

import javax.inject.Inject;

public class MyPromoCodeApiWriteableDataSource extends EmptyWriteableDataSource<String, MyPromoCode>
        implements MyPromoCodeWriteableDataSourceContract {

    private final MyPromoCodeApiClient myPromoCodeApiClient;
    private final MyPromoCodeToMyPromoCodeDtoMapper mapper = new MyPromoCodeToMyPromoCodeDtoMapper();

    @Inject
    public MyPromoCodeApiWriteableDataSource(MyPromoCodeApiClient myPromoCodeApiClient) {
        this.myPromoCodeApiClient = myPromoCodeApiClient;
    }

    @Override
    public MyPromoCode getMyPromoCode(String promoCodeKey) throws Exception {
        MyPromoCode myPromoCode;
        MyPromoCodeDto myPromoCodeDto;

        myPromoCodeDto = myPromoCodeApiClient.getMyPromoCode(promoCodeKey);

        myPromoCode = mapper.reverseMap(myPromoCodeDto);

        return myPromoCode;
    }
}