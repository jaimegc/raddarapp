package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdatePasswordProfileDto;

public class UpdatePasswordProfileDtoBuilder {

    private String password;
    private String oldPassword;

    public UpdatePasswordProfileDtoBuilder() {}

    public UpdatePasswordProfileDto build() {
        return new UpdatePasswordProfileDto(password, oldPassword);
    }

    public UpdatePasswordProfileDtoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UpdatePasswordProfileDtoBuilder withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }
}
