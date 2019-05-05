package com.raddarapp.presentation.general.validation;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationPromoCodeContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationPromoCode implements ValidationPromoCodeContract {

    private ValidationUtils validationUtils;

    @Inject
    public ValidationPromoCode() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validatePromoCode(String promoCode) {
        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmpty(promoCode)) {
            code = ErrorLocalCode.EMPTY_PROMO_CODE;
        }

        return code;
    }
}
