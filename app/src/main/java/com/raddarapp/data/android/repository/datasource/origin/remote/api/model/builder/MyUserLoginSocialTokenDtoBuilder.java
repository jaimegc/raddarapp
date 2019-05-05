package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserLoginSocialTokenDto;

public class MyUserLoginSocialTokenDtoBuilder {

    private String token;

    public MyUserLoginSocialTokenDtoBuilder() {}

    public MyUserLoginSocialTokenDto build() {
        return new MyUserLoginSocialTokenDto(token);
    }

    public MyUserLoginSocialTokenDtoBuilder withToken(String token) {
        this.token = token;
        return this;
    }
}
