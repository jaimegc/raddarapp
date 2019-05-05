package com.raddarapp.presentation.general.validation.view;

public interface ValidationEnterCompleteProfileView {

    void showErrorLocalTermsConditions();

    void showErrorLocalEmailPattern();

    void showErrorLocalEmptyEmail();

    void showErrorLocalEmptyUsername();

    void showErrorLocalEmptyBirthdate();

    void showErrorLocalBirthdatePattern();

    void showErrorLocalUnderFourteen();

    void showErrorLocalMaximumAge();

    void showErrorLocalAgeFuture();

    void showErrorLocalUsernamePattern();
}
