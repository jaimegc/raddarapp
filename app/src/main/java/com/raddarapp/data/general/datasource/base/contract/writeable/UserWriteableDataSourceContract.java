package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.User;

public interface UserWriteableDataSourceContract extends WriteableDataSource<String, User> {

    int followUser(String userKey) throws Exception;

    int unfollowUser(String userKey) throws Exception;

    boolean activateUser(String userKey) throws Exception;

    boolean desactivateUser(String userKey) throws Exception;
}
