package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.UpdateImageProfile;

public interface UpdateImageProfileWriteableDataSourceContract extends WriteableDataSource<String, UpdateImageProfile> {

    UpdateImageProfile updateImageProfile(String uriImage) throws Exception;
}
