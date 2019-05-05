package com.raddarapp.presentation.android.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.EditText;

import com.raddarapp.domain.model.enums.AspectRatioType;

public class DimenUtils {

    public float dpToPx(Context ctx, int dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (dp * density);
    }

    public float pxToDp(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (px / density);
    }

    public int calculateFirePosition(Context ctx, int minDp, int maxDp, long likes, long dislikes) {
        int result;

        if (likes != dislikes) {
            if (likes != 0 && dislikes != 0) {
                result = (int) dpToPx(ctx, weightedValue(minDp, maxDp, (dislikes * 100) / (likes + dislikes)));
            } else {
                if (likes == 0) {
                    result = (int) dpToPx(ctx, maxDp);
                } else {
                    result = (int) dpToPx(ctx, minDp);
                }
            }
        } else {
            result = (int) dpToPx(ctx, middleValue(minDp, maxDp));
        }

        return result;
    }

    private int weightedValue(int min, int max, long value) {
        int dif = max - min;

        return (int) ((value * dif) / 100) + min;
    }

    private int middleValue(int min, int max) {
        int dif = (max - min) / 2;

        return dif + min;
    }

    /**
     * INSTRAGRAM
     * SQUARE (1080x1080) => 165KB, 109KB,
     * 16_9 (1080 x 642) que es 1,68 en vez de 1,77 => 190KB,
     * 5_4 (1080x810) que es 4_3 ==> 91KB
     */

    public int[] ratioResizeImageByAspectRatio(int aspectRatioX, int aspectRatioY) {
        int result[] = new int[3];
        final int SQUARE[] = new int[] {1080, 1080};
        final int ASPECT_RATIO_4_5[] = new int[] {864 , 1080}; // or 1080x1350
        final int ASPECT_RATIO_5_4[] = new int[] {1080 , 864}; // or 1350x1080
        final int ASPECT_RATIO_9_16[] = new int[] {607 , 1080};
        final int ASPECT_RATIO_16_9[] = new int[] {1080 , 607};

        // 1:1 ==> 1080x1080 (shown 600x600)
        // 5:4 ==> 1350x1080 (show 600x480)
        // 16:9 ==> 1080x607 (show 600x337)
        if (aspectRatioX == aspectRatioY) {
            result[0] = SQUARE[0];
            result[1] = SQUARE[1];
            result[2] = AspectRatioType.SQUARE.getValue();
        } else if (aspectRatioX == 4) {
            result[0] = ASPECT_RATIO_4_5[0];
            result[1] = ASPECT_RATIO_4_5[1];
            result[2] = AspectRatioType.FOUR_FIVE.getValue();
        } else if (aspectRatioX == 5) {
            result[0] = ASPECT_RATIO_5_4[0];
            result[1] = ASPECT_RATIO_5_4[1];
            result[2] = AspectRatioType.FIVE_FOUR.getValue();
        } else if (aspectRatioX == 9) {
            result[0] = ASPECT_RATIO_9_16[0];
            result[1] = ASPECT_RATIO_9_16[1];
            result[2] = AspectRatioType.NINE_SIXTEEN.getValue();
        } else {
            result[0] = ASPECT_RATIO_16_9[0];
            result[1] = ASPECT_RATIO_16_9[1];
            result[2] = AspectRatioType.SIXTEEN_NINE.getValue();
        }

        return result;
    }

    public int calculateHeightPxByAspectRatio(Context ctx, int aspectRatio) {
        int width = (int) dpToPx(ctx, ctx.getResources().getConfiguration().screenWidthDp);

        if (aspectRatio == AspectRatioType.SQUARE.getValue()) {
            return width;
        } else if (aspectRatio == AspectRatioType.FOUR_FIVE.getValue()) {
            return (int) (width * (5/4.0));
        } else if (aspectRatio == AspectRatioType.FIVE_FOUR.getValue()) {
            return (int) (width * (4/5.0));
        } else if (aspectRatio == AspectRatioType.NINE_SIXTEEN.getValue()) {
            return (int) (width * (16/9.0));
        } else if (aspectRatio == AspectRatioType.SIXTEEN_NINE.getValue()) {
            return (int) (width * (9/16.0));
        } else {
            return width;
        }
    }

    public boolean maxCharactersByPx(EditText editText, float maxWidthInPx) {
        boolean maxReached = true;
        int numChars;

        String text = editText.getText().toString();

        Paint paint = editText.getPaint();
        for (numChars = 1; numChars <= text.length(); ++numChars) {
            if (paint.measureText(text, 0, numChars) > maxWidthInPx) {
                maxReached = false;
                break;
            }
        }

        return maxReached;
    }

    public int calculateWidthDp(Configuration configuration) {
        return configuration.screenWidthDp;
    }

    public int calculateScreenHeight(final WindowManager windowManager) {
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public int calculateScreenWidth(final WindowManager windowManager) {
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public int calculateRecyclerFootprintMainHeight(Context ctx, final WindowManager windowManager) {
        int MARGIN_RECYCLER_TOP_DP = 50;
        int MARGIN_MENU_BOTTOM_DP = 45;
        int MARGIN_START_AND_END_FOOTPRINT_DP = 12;
        int MARGIN_TOP_AND_BOTTOM_FOOTPRINT_DP = 26;
        float PERFECT_FOOTPRINT_PROPORTION = 1.353448f; // 1413 / 1044 (px in Sony Xperia Z3)

        int maxHeightScreenPx = calculateScreenHeight(windowManager) -
                (int) dpToPx(ctx, MARGIN_RECYCLER_TOP_DP + MARGIN_MENU_BOTTOM_DP + MARGIN_TOP_AND_BOTTOM_FOOTPRINT_DP);
        int maxWidthScreenPx = calculateScreenWidth(windowManager) -  (int) dpToPx(ctx, MARGIN_START_AND_END_FOOTPRINT_DP);

        int recyclerFootprintHeightPx = (int) (maxWidthScreenPx * PERFECT_FOOTPRINT_PROPORTION);

        return recyclerFootprintHeightPx <= maxHeightScreenPx ? recyclerFootprintHeightPx : maxHeightScreenPx;
    }

    public float calculateCenterVoteViewHeight(Context ctx, int recyclerFootprintMainHeight) {
        int MARGIN_MENU_BOTTOM_DP = 45;
        int MARGIN_BOTTOM_FOOTPRINT_DP = 12;
        int SIZE_COIN_DP = 130;

        int totalPx = (int) dpToPx(ctx, MARGIN_MENU_BOTTOM_DP + MARGIN_BOTTOM_FOOTPRINT_DP);
        int sizeCoinPx = (int) dpToPx(ctx, SIZE_COIN_DP);

        return (recyclerFootprintMainHeight / 2) + totalPx - (sizeCoinPx / 2);
    }
}
