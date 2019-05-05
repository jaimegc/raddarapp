package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyUserLoginSocialToken;

public class MyUserLoginSocialTokenBuilder {

    private String token;

    public MyUserLoginSocialTokenBuilder() {}

    public MyUserLoginSocialToken build() {
        return new MyUserLoginSocialToken(token);
    }

    public MyUserLoginSocialTokenBuilder withToken(String token) {
        this.token = token;
        return this;
    }
}
