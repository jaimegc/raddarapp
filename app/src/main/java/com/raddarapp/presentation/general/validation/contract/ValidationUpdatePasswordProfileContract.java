package com.raddarapp.presentation.general.validation.contract;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;

public interface ValidationUpdatePasswordProfileContract {

    ErrorLocalCode validateUpdatePasswordProfile(String newPassword, String newPasswordRepeated, String oldPassword);
}
