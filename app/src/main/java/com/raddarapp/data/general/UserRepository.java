package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.writeable.UserWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UserDataSourceFactory;
import com.raddarapp.domain.model.User;

import javax.inject.Inject;

public class UserRepository extends RosieRepository<String, User> {

    private final UserWriteableDataSourceContract userWriteableDataSource;

    @Inject
    UserRepository(UserDataSourceFactory userDataSourceFactory) {

        userWriteableDataSource = userDataSourceFactory.createWriteableDataSource();

        addWriteableDataSources(userWriteableDataSource);
    }

    public int followUser(String userKey) throws Exception {
        return userWriteableDataSource.followUser(userKey);
    }

    public int unfollowUser(String userKey) throws Exception {
        return userWriteableDataSource.unfollowUser(userKey);
    }

    public boolean activateUser(String userKey) throws Exception {
        return userWriteableDataSource.activateUser(userKey);
    }

    public boolean desactivateUser(String userKey) throws Exception {
        return userWriteableDataSource.desactivateUser(userKey);
    }
}
