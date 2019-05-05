package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdatePasswordProfile;

public class UpdatePasswordProfileBuilder {

    private String key;
    private String password;
    private String oldPassword;

    public UpdatePasswordProfileBuilder() {}

    public UpdatePasswordProfile build() {
        return new UpdatePasswordProfile(key, password, oldPassword);
    }

    public UpdatePasswordProfileBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UpdatePasswordProfileBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UpdatePasswordProfileBuilder withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }
}
