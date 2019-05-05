package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.UpdateMyUserProfile;

public interface UpdateMyUserProfileWriteableDataSourceContract extends WriteableDataSource<String, UpdateMyUserProfile> {

    MyUserProfile updateMyUserProfile(UpdateMyUserProfile updateMyUserProfile) throws Exception;
}
