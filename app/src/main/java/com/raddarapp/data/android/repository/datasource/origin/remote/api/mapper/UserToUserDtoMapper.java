package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UserDto;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.UserBuilder;

public class UserToUserDtoMapper extends Mapper<User, UserDto> {

    @Override
    public User reverseMap(UserDto userDto) {
        final User user = new UserBuilder()
                .withKey(userDto.getId())
                .withUsername(userDto.getUsername())
                .withImage(userDto.getImage())
                .withAudio(userDto.getAudio())
                .withRange(userDto.getRange())
                .withFollowers(userDto.getFollowers())
                .withFollowing(userDto.getFollowing())
                .withUserRelationship(userDto.getUserRelationship())
                .withLevel(userDto.getLevel())
                .withTotalFootprints(userDto.getTotalFootprints())
                .withUserCompliments(userDto.getUserCompliments())
                .withTotalLikes(userDto.getTotalLikes())
                .withTotalDislikes(userDto.getTotalDislikes())
                .withTotalFootprintsDead(userDto.getTotalFootprintsDead())
                .build();

        return user;
    }

    @Override
    public UserDto map(User user) {
        throw new UnsupportedOperationException();
    }
}
