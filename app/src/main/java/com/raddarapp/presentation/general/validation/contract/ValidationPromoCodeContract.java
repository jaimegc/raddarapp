package com.raddarapp.presentation.general.validation.contract;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;

public interface ValidationPromoCodeContract {

    ErrorLocalCode validatePromoCode(String promoCode);
}
