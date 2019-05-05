package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UserWriteableDataSourceContract;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.enums.UserRelationshipType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class UserFakeWriteableDataSource extends EmptyWriteableDataSource<String, User>
        implements UserWriteableDataSourceContract {

    private static Map<String, Integer> mapUserFollowed = new HashMap<>();

    @Inject
    public UserFakeWriteableDataSource() {

    }

    @Override
    public int followUser(String userKey) throws Exception {
        return checkFollowStatus(userKey);
    }

    @Override
    public int unfollowUser(String userKey) throws Exception {
        return checkFollowStatus(userKey);
    }

    @Override
    public boolean activateUser(String userKey) throws Exception {
        return true;
    }

    @Override
    public boolean desactivateUser(String userKey) throws Exception {
        return true;
    }

    // Only works fine if the first click is on an unknown user
    private int checkFollowStatus(String userKey) {
        if (!mapUserFollowed.containsKey(userKey)) {
            mapUserFollowed.put(userKey, UserRelationshipType.FOLLOWING.getValue());
        } else {
            if (mapUserFollowed.get(userKey) == UserRelationshipType.FOLLOWING.getValue()) {
                mapUserFollowed.put(userKey, UserRelationshipType.UNKNOWN.getValue());
            } else if (mapUserFollowed.get(userKey) == UserRelationshipType.UNKNOWN.getValue()) {
                mapUserFollowed.put(userKey, UserRelationshipType.FRIEND.getValue());
            } else if (mapUserFollowed.get(userKey) == UserRelationshipType.FRIEND.getValue()){
                mapUserFollowed.put(userKey, UserRelationshipType.FOLLOW_ME.getValue());
            } else if (mapUserFollowed.get(userKey) == UserRelationshipType.FOLLOW_ME.getValue()) {
                mapUserFollowed.put(userKey, UserRelationshipType.FOLLOWING.getValue());
            }
        }

        return mapUserFollowed.get(userKey);
    }
}
