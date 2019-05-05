package com.raddarapp.domain.model;

public class MyUserPassword {

    private String user;
    private String password;

    public MyUserPassword(String user, String password) {
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
