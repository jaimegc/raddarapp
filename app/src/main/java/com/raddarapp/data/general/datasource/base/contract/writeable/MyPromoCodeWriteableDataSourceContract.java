package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.MyPromoCode;

public interface MyPromoCodeWriteableDataSourceContract extends WriteableDataSource<String, MyPromoCode> {

    MyPromoCode getMyPromoCode(String promoCodeKey) throws Exception;
}
