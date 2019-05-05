package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.UpdatePasswordProfile;

public interface UpdatePasswordProfileWriteableDataSourceContract extends WriteableDataSource<String, UpdatePasswordProfile> {

    boolean updatePasswordProfile(UpdatePasswordProfile updatePasswordProfile) throws Exception;
}
