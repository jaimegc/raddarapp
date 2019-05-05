package com.raddarapp.presentation.android.utils;

import android.view.View;

public class ViewUtils {

    public void visibilityVisible(View...view) {

        for (View v : view) {
            v.setVisibility(View.VISIBLE);
        }
    }

    public void visibilityInvisible(View...view) {

        for (View v : view) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    public void visibilityGone(View...view) {

        for (View v : view) {
            v.setVisibility(View.GONE);
        }
    }
}
