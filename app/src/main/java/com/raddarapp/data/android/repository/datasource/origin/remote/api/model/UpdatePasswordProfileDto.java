package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class UpdatePasswordProfileDto {

    @SerializedName("password")
    private String password;
    @SerializedName("oldPassword")
    private String oldPassword;

    public UpdatePasswordProfileDto(String password, String oldPassword) {
        this.password = password;
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
