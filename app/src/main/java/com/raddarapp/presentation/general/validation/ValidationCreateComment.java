package com.raddarapp.presentation.general.validation;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationCreateCommentContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationCreateComment implements ValidationCreateCommentContract {

    private ValidationUtils validationUtils;

    @Inject
    public ValidationCreateComment() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validateCreateComment(String comment) {
        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmptyTrim(comment)) {
            code = ErrorLocalCode.EMPTY_COMMENT;
        }

        return code;
    }
}
