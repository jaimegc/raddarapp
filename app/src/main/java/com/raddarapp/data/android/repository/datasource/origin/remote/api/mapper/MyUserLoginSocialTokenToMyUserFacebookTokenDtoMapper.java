package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserLoginSocialTokenDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.MyUserLoginSocialTokenDtoBuilder;
import com.raddarapp.domain.model.MyUserLoginSocialToken;

public class MyUserLoginSocialTokenToMyUserFacebookTokenDtoMapper extends Mapper<MyUserLoginSocialToken, MyUserLoginSocialTokenDto> {

    @Override
    public MyUserLoginSocialTokenDto map(MyUserLoginSocialToken userFacebookToken) {
        final MyUserLoginSocialTokenDto userPasswordDto = new MyUserLoginSocialTokenDtoBuilder()
                .withToken(userFacebookToken.getToken())
                .build();

        return userPasswordDto;
    }

    @Override
    public MyUserLoginSocialToken reverseMap(MyUserLoginSocialTokenDto value) {
        throw new UnsupportedOperationException();
    }
}
