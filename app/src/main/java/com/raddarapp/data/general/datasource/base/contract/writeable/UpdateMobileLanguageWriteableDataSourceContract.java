package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.UpdateMobileLanguage;

public interface UpdateMobileLanguageWriteableDataSourceContract extends WriteableDataSource<String, UpdateMobileLanguage> {

    boolean updateMobileLanguage(String userKey, UpdateMobileLanguage updateMobileLanguage) throws Exception;
}
