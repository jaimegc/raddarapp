package com.raddarapp.presentation.android.utils;

import android.content.Context;

import com.raddarapp.R;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.vdurmont.emoji.EmojiParser;

import java.util.HashMap;
import java.util.Map;

public class EmojiUtils {

    private static Map<Integer, FootprintEmojiCategory> footprintEmojiCategories;

    // https://unicodey.com/emoji-data/table.htm
    // https://gist.github.com/oliveratgithub/0bf11a9aff0d6da7b46f1490f86a71eb
    // https://github.com/vdurmont/emoji-java#available-emojis
    // https://emojipedia.org
    public static void initializeEmojis(Context context) {
        footprintEmojiCategories = new HashMap<>();

        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_UNCLASSIFIED.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_UNCLASSIFIED.getValue(), context.getString(R.string.category_unclassified), R.drawable.emoji_unclassified));
        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue(), context.getString(R.string.category_moments), R.drawable.emoji_moments));
        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue(), context.getString(R.string.category_gastronomy), R.drawable.emoji_gastronomy));
        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue(), context.getString(R.string.category_things), R.drawable.emoji_things));
        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue(), context.getString(R.string.category_places), R.drawable.emoji_places));
        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue(), context.getString(R.string.category_animals), R.drawable.emoji_animals));
        footprintEmojiCategories.put(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue(),
                new FootprintEmojiCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue(), context.getString(R.string.category_people), R.drawable.emoji_people));
    }

    public Map<Integer, FootprintEmojiCategory> getFootprintEmojiCategories() {
        return footprintEmojiCategories;
    }

    public FootprintEmojiCategory getFootprintEmojiCategory(int emojiCategory) {
        FootprintEmojiCategory footprintEmojiCategory;

        if (emojiCategory < footprintEmojiCategories.size()) {
            footprintEmojiCategory = footprintEmojiCategories.get(emojiCategory);
        } else {
            footprintEmojiCategory = footprintEmojiCategories.get(FootprintCategory.CATEGORY_EMOJI_UNCLASSIFIED.getValue());
        }

        return footprintEmojiCategory;
    }

    public String emojiCountry(String emojiCountryCharacters) {

        if (emojiCountryCharacters.length() == 2) {
            int firstLetter = Character.codePointAt(emojiCountryCharacters, 0) - 0x41 + 0x1F1E6;
            int secondLetter = Character.codePointAt(emojiCountryCharacters, 1) - 0x41 + 0x1F1E6;
            return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
        } else {
            return EmojiParser.parseToUnicode(emojiCountryCharacters);
        }
    }
}
