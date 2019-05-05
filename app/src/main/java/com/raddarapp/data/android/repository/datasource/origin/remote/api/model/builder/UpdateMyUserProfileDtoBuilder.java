package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMyUserProfileDto;

public class UpdateMyUserProfileDtoBuilder {

    private String name;
    private String surname;
    private String username;
    private String email;
    private int gender;
    private String birthdate;

    public UpdateMyUserProfileDtoBuilder() {}

    public UpdateMyUserProfileDto build() {
        return new UpdateMyUserProfileDto(name, surname, username, email, gender, birthdate);
    }

    public UpdateMyUserProfileDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UpdateMyUserProfileDtoBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UpdateMyUserProfileDtoBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UpdateMyUserProfileDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UpdateMyUserProfileDtoBuilder withGender(int gender) {
        this.gender = gender;
        return this;
    }

    public UpdateMyUserProfileDtoBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

}
