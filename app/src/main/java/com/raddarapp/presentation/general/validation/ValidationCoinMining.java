package com.raddarapp.presentation.general.validation;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationCoinMiningContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationCoinMining implements ValidationCoinMiningContract {

    private ValidationUtils validationUtils;
    private static final int MINIMUM_COIN_MINED = 200;

    @Inject
    public ValidationCoinMining() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validateCoinMined(int rangeMinedNow) {
        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (rangeMinedNow < MINIMUM_COIN_MINED) {
            code = ErrorLocalCode.MINIMUM_RANGE_MINED;
        }

        return code;
    }
}
