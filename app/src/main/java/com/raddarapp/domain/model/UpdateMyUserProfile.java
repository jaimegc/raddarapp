package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UpdateMyUserProfile implements Identifiable<String> {

    private final String key;
    private final String username;
    private final String name;
    private final String surname;
    private final String email;
    private final String birthdate;
    private final int gender;

    public UpdateMyUserProfile(String key, String username, String name, String surname, String email,
            String birthdate, int gender) {
        this.key = key;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getGender() {
        return gender;
    }

}
