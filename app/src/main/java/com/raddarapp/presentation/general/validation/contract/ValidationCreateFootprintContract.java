package com.raddarapp.presentation.general.validation.contract;

import com.raddarapp.presentation.android.error.local.ErrorLocalCode;

public interface ValidationCreateFootprintContract {

    ErrorLocalCode validateCreateFootprint(String image, String title, String description, int footprintEmojiCategory);
}
