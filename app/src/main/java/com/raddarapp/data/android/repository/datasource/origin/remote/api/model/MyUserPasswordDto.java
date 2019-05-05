package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class MyUserPasswordDto {

    @SerializedName("user")
    private String user;
    @SerializedName("password")
    private String password;

    public MyUserPasswordDto(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
