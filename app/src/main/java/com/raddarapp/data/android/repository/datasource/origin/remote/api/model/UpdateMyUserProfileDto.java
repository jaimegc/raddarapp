package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class UpdateMyUserProfileDto {

    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birthDate")
    private String birthdate;

    public UpdateMyUserProfileDto(String name, String surname, String username, String email, int gender, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

}
