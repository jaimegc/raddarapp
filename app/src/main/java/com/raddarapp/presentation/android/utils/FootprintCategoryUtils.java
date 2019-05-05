package com.raddarapp.presentation.android.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raddarapp.R;
import com.raddarapp.domain.model.enums.FootprintCategory;

import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class FootprintCategoryUtils {

    private Context context;
    private Typeface font;

    // Footprint Main
    private RelativeLayout background;
    private ImageView backgroundDecoration;
    private TextView footprintCategoryName;
    private int footprintCategory;

    public FootprintCategoryUtils(Context context, String fontName, RelativeLayout background, ImageView backgroundDecoration,
            TextView footprintCategoryName, int footprintCategory) {
        this.context = context;
        this.background = background;
        this.backgroundDecoration = backgroundDecoration;
        this.footprintCategoryName = footprintCategoryName;
        this.footprintCategory = footprintCategory;

        font = TypefaceUtils.load(context.getAssets(), "fonts/" + fontName);
    }

    private void drawAnimalCategory() {
        backgroundDecoration.setImageTintList(context.getResources().getColorStateList(R.color.red));
        footprintCategoryName.setText("FLAG\nANIMALES");
    }

    private void drawLoveCategory() {
        backgroundDecoration.setImageTintList(context.getResources().getColorStateList(R.color.marker_me));
        footprintCategoryName.setText("FLAG\nAMOR");
    }

    private void drawOthersCategory() {
        backgroundDecoration.setImageTintList(context.getResources().getColorStateList(R.color.background_card_decoration));
        footprintCategoryName.setText("FLAG\nOTROS");
    }

    private void drawStrangerThings() {
        backgroundDecoration.setImageTintList(context.getResources().getColorStateList(R.color.marker_friend));
        footprintCategoryName.setText("FLAG\nCOSAS RARAS");
    }
}
