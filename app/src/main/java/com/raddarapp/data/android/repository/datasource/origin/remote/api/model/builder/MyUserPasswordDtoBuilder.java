package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserPasswordDto;

public class MyUserPasswordDtoBuilder {

    private String user;
    private String password;

    public MyUserPasswordDtoBuilder() {}

    public MyUserPasswordDto build() {
        return new MyUserPasswordDto(user, password);
    }

    public MyUserPasswordDtoBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public MyUserPasswordDtoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
}
