package com.raddarapp.domain.model.enums;

public enum FootprintCategory {

    CATEGORY_EMOJI_UNCLASSIFIED(0), // :man-shrugging:
    CATEGORY_EMOJI_MOMENTS(1), // :selfie:
    CATEGORY_EMOJI_GASTRONOMY(2), // :pizza:
    CATEGORY_EMOJI_THINGS(3), // :sunglasses:
    CATEGORY_EMOJI_PLACES(4), // :desert:
    CATEGORY_EMOJI_ANIMALS(5),
    CATEGORY_EMOJI_PEOPLE(6); // :lying_face:

    private final int value;

    FootprintCategory(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
