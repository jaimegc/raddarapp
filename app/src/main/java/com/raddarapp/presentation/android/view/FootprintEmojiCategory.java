package com.raddarapp.presentation.android.view;

public class FootprintEmojiCategory {

    private final int code;
    private final String name;
    private final int emojiImageResource;

    public FootprintEmojiCategory(int code, String name, int emojiImageResource) {
        this.code = code;
        this.name = name;
        this.emojiImageResource = emojiImageResource;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getEmojiImageResource() {
        return emojiImageResource;
    }
}
