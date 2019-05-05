package com.raddarapp.presentation.general.validation;

import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.validation.contract.ValidationCreateFootprintContract;
import com.raddarapp.presentation.general.validation.utils.ValidationUtils;

import javax.inject.Inject;

public class ValidationCreateFootprint implements ValidationCreateFootprintContract {

    private ValidationUtils validationUtils;

    @Inject
    public ValidationCreateFootprint() {
        validationUtils = new ValidationUtils();
    }

    @Override
    public ErrorLocalCode validateCreateFootprint(String image, String title, String description, int footprintEmojiCategory) {
        ErrorLocalCode code = ErrorLocalCode.SUCCESS;

        if (validationUtils.isEmptyTrim(image)) {
            code = ErrorLocalCode.EMPTY_IMAGE;
        } else if (footprintEmojiCategory == FootprintCategory.CATEGORY_EMOJI_UNCLASSIFIED.getValue()) {
            code = ErrorLocalCode.UNCLASSIFIED_FOOTPRINT_EMOJI_CATEGORY;
        }

        /*
         else if (validationUtils.isEmptyTrim(title)) {
            code = ErrorLocalCode.EMPTY_TITLE;
        } else if (validationUtils.isEmptyTrim(description)) {
            code = ErrorLocalCode.EMPTY_DESCRIPTION;
        }*/

        return code;
    }
}
