package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyPromoCodeDto;
import com.raddarapp.domain.model.MyPromoCode;
import com.raddarapp.domain.model.builder.MyPromoCodeBuilder;

public class MyPromoCodeToMyPromoCodeDtoMapper extends Mapper<MyPromoCode, MyPromoCodeDto> {

    @Override
    public MyPromoCode reverseMap(MyPromoCodeDto myPromoCodeDto) {
        final MyPromoCode myPromoCode = new MyPromoCodeBuilder()
                .withCode(myPromoCodeDto.getCode())
                .withExchangeDate(myPromoCodeDto.getExchangeDate())
                .withExchanged(myPromoCodeDto.isExchanged())
                .withTimesExchanged(myPromoCodeDto.getTimesExchanged())
                .withMyUserKey(myPromoCodeDto.getMyUserId())
                .build();

        return myPromoCode;
    }

    @Override
    public MyPromoCodeDto map(MyPromoCode myPromoCode) {
        throw new UnsupportedOperationException();
    }
}