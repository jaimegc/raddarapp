package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdateMyUserProfile;

public class UpdateMyUserProfileBuilder {

    private String key;
    private String name;
    private String surname;
    private String username;
    private String email;
    private int gender;
    private String birthdate;

    public UpdateMyUserProfileBuilder() {}

    public UpdateMyUserProfile build() {
        return new UpdateMyUserProfile(key, username, name, surname, email, birthdate, gender);
    }

    public UpdateMyUserProfileBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UpdateMyUserProfileBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UpdateMyUserProfileBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UpdateMyUserProfileBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UpdateMyUserProfileBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UpdateMyUserProfileBuilder withGender(int gender) {
        this.gender = gender;
        return this;
    }

    public UpdateMyUserProfileBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }
}
