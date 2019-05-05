package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UserApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FollowDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UserWriteableDataSourceContract;
import com.raddarapp.domain.model.User;

import javax.inject.Inject;

public class UserApiWriteableDataSource extends EmptyWriteableDataSource<String, User>
        implements UserWriteableDataSourceContract {

    private final UserApiClient userApiClient;

    @Inject
    public UserApiWriteableDataSource(UserApiClient userApiClient) {
        this.userApiClient = userApiClient;
    }

    @Override
    public int followUser(String userKey) throws Exception {
        FollowDto followDto = userApiClient.followUser(userKey);

        return followDto.getUserRelationship();
    }

    @Override
    public int unfollowUser(String userKey) throws Exception {
        FollowDto followDto = userApiClient.unfollowUser(userKey);

        return followDto.getUserRelationship();
    }

    @Override
    public boolean activateUser(String userKey) throws Exception {
        ResponseDto activateUserDto = userApiClient.activateUser(userKey);

        return activateUserDto == null || !activateUserDto.getResponse().isEmpty();
    }

    @Override
    public boolean desactivateUser(String userKey) throws Exception {
        ResponseDto activateUserDto = userApiClient.desactivateUser(userKey);

        return activateUserDto == null || !activateUserDto.getResponse().isEmpty();
    }
}