package com.raddarapp.presentation.android.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;

public class ColorUtils {

    /**
     * Tints a menu item icon
     * @param item
     */
    public static void tintIcon(MenuItem item, int resId, Context context){
        Drawable drawable = item.getIcon();
        tintIcon(drawable, resId, context);
    }

    public static void tintIcon(Drawable drawable, int resId, Context context){
        if (drawable != null && context != null) {
            drawable.mutate();
            drawable.setColorFilter(context.getResources().getColor(resId),
                    PorterDuff.Mode.SRC_IN);
        }
    }
}
