package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.PromoCodeApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.BooleanToResponseDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.PromoCodeWriteableDataSourceContract;
import com.raddarapp.domain.model.PromoCode;

import javax.inject.Inject;

public class PromoCodeApiWriteableDataSource extends EmptyWriteableDataSource<String, PromoCode>
        implements PromoCodeWriteableDataSourceContract {

    private final PromoCodeApiClient promoCodeApiClient;
    private final BooleanToResponseDtoMapper mapperBoolean = new BooleanToResponseDtoMapper();

    @Inject
    public PromoCodeApiWriteableDataSource(PromoCodeApiClient promoCodeApiClient) {
        this.promoCodeApiClient = promoCodeApiClient;
    }

    @Override
    public boolean promoCode(String promoCode) throws Exception {
        boolean promoCodeExchanged;
        ResponseDto responseDto;

        responseDto = promoCodeApiClient.promoCode(promoCode);
        promoCodeExchanged = mapperBoolean.reverseMap(responseDto);

        return promoCodeExchanged;
    }
}