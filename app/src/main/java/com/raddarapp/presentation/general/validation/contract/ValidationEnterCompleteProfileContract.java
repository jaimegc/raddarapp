package com.raddarapp.presentation.general.validation.contract;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;

public interface ValidationEnterCompleteProfileContract {

    ErrorLocalCode validateCompleteProfile(String username, String birthdate, boolean termsConditions);

    ErrorLocalCode validateCompleteProfileWithEmail(String username, String email, String birthdate, boolean termsConditions);

}
