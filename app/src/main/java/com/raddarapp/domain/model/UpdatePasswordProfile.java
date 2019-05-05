package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UpdatePasswordProfile implements Identifiable<String> {

    private final String key;
    private final String password;
    private final String oldPassword;

    public UpdatePasswordProfile(String key, String password, String oldPassword) {
        this.key = key;
        this.password = password;
        this.oldPassword = oldPassword;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
