package com.raddarapp.presentation.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import android.view.MenuItem;
import android.widget.TextView;

import com.raddarapp.presentation.android.view.BorderTextView;

import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class FontUtils {

    public void applyFont(Context context, String fontName, TextView... views) {
        Typeface font = TypefaceUtils.load(context.getAssets(), "fonts/" + fontName);

        for (TextView t : views) {
            t.setTypeface(font);
        }
    }

    public void applyFont(Context context, String fontName, BorderTextView... views) {
        Typeface font = TypefaceUtils.load(context.getAssets(), "fonts/" + fontName);

        for (BorderTextView t : views) {
            t.setTypeface(font);
        }
    }

    public void applyFontMenuItem(Context context, MenuItem menuItem, String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);

        SpannableString mNewTitle = new SpannableString(menuItem.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        menuItem.setTitle(mNewTitle);
    }

    @SuppressLint("ParcelCreator")
    private class CustomTypefaceSpan extends TypefaceSpan {

        private final Typeface newType;

        public CustomTypefaceSpan(String family, Typeface type) {
            super(family);
            newType = type;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            applyCustomTypeFace(ds, newType);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            applyCustomTypeFace(paint, newType);
        }

        private void applyCustomTypeFace(Paint paint, Typeface tf) {
            int oldStyle;
            Typeface old = paint.getTypeface();
            if (old == null) {
                oldStyle = 0;
            } else {
                oldStyle = old.getStyle();
            }

            int fake = oldStyle & ~tf.getStyle();
            if ((fake & Typeface.BOLD) != 0) {
                paint.setFakeBoldText(true);
            }

            if ((fake & Typeface.ITALIC) != 0) {
                paint.setTextSkewX(-0.25f);
            }

            paint.setTypeface(tf);
        }
    }
}
