package com.raddarapp.presentation.general.validation;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationEnterLoginContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationEnterLogin implements ValidationEnterLoginContract {

    private ValidationUtils validationUtils;

    @Inject
    public ValidationEnterLogin() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validateEnterLogin(String username, String password) {

        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmptyTrim(username)) {
            code = ErrorLocalCode.EMPTY_USERNAME;
        } else if (validationUtils.isEmptyTrim(password)) {
            code = ErrorLocalCode.EMPTY_PASSWORD;
        }

        return code;
    }
}
