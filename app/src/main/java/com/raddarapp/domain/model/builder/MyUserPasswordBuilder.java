package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyUserPassword;

public class MyUserPasswordBuilder {

    private String user;
    private String password;

    public MyUserPasswordBuilder() {}

    public MyUserPassword build() {
        return new MyUserPassword(user, password);
    }

    public MyUserPasswordBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public MyUserPasswordBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
}
