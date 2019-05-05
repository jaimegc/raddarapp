package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.PromoCode;

public interface PromoCodeWriteableDataSourceContract extends WriteableDataSource<String, PromoCode> {

    boolean promoCode(String promoCode) throws Exception;
}
