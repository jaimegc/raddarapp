package com.raddarapp.presentation.general.validation;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationEnterCompleteProfileContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationEnterCompleteProfile implements ValidationEnterCompleteProfileContract {

    private ValidationUtils validationUtils;
    private int MINIMUM_AGE = 14;
    private int MAXIMUM_AGE = 120;

    @Inject
    public ValidationEnterCompleteProfile() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validateCompleteProfile(String username, String birthdate, boolean termsConditions) {
        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmptyTrim(birthdate)) {
            code = ErrorLocalCode.EMPTY_BIRTHDATE;
        } else if (!validationUtils.isValidDate(birthdate)) {
            code = ErrorLocalCode.BIRTHDATE_PATTERN;
        }

        if (code == ErrorLocalCode.SUCCESS) {
            int age = validationUtils.calculateAgeFromDate(birthdate);

            if (age < 0) {
                code = ErrorLocalCode.AGE_FUTURE;
            } else if (age >= MAXIMUM_AGE) {
                code = ErrorLocalCode.MAXIMUM_AGE;
            } else if (age < MINIMUM_AGE) {
                code = ErrorLocalCode.UNDER_FOURTEEN;
            } else if (validationUtils.isEmptyTrim(username)) {
                code = ErrorLocalCode.EMPTY_USERNAME;
            } else if (!validationUtils.isValidUsername(username)) {
                code = ErrorLocalCode.USERNAME_PATTERN;
            } else if (!termsConditions) {
                code = ErrorLocalCode.TERMS_CONDITIONS;
            }
        }

        return code;
    }

    @Override
    public ErrorLocalCode validateCompleteProfileWithEmail(String username, String email, String birthdate, boolean termsConditions) {
        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmptyTrim(email)) {
            code = ErrorLocalCode.EMPTY_EMAIL;
        } else if (!validationUtils.isValidEmail(email)) {
            code = ErrorLocalCode.EMAIL_PATTERN;
        }

        if (code == ErrorLocalCode.SUCCESS) {
            return validateCompleteProfile(username, birthdate, termsConditions);
        } else {
            return code;
        }
    }

}
