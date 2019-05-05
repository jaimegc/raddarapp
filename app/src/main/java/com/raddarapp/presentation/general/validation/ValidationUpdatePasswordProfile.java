package com.raddarapp.presentation.general.validation;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationUpdatePasswordProfileContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationUpdatePasswordProfile implements ValidationUpdatePasswordProfileContract {

    private ValidationUtils validationUtils;

    @Inject
    public ValidationUpdatePasswordProfile() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validateUpdatePasswordProfile(String newPassword, String newPasswordRepeated, String oldPassword) {

        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmpty(newPassword) || validationUtils.isEmpty(newPasswordRepeated) || validationUtils.isEmpty(oldPassword)) {
            code = ErrorLocalCode.FILL_ALL_PASSWORDS;
        } else if (validationUtils.equals(newPassword, newPasswordRepeated, oldPassword)) {
            code = ErrorLocalCode.ALL_EQUALS_PASSWORDS;
        } else if (!validationUtils.equals(newPassword, newPasswordRepeated)) {
            code = ErrorLocalCode.NEW_DIFFERENT_PASSWORDS;
        }

        return code;
    }
}
