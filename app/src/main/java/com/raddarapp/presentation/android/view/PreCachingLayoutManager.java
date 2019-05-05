package com.raddarapp.presentation.android.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

// https://github.com/ovy9086/recyclerview-playground
// Precaching two items in recyclerview
public class PreCachingLayoutManager extends LinearLayoutManager {
    private static int DEFAULT_EXTRA_LAYOUT_SPACE;
    private int extraLayoutSpace = -1;
    private Context context;

    public PreCachingLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    public PreCachingLayoutManager(Context context, int extraLayoutSpace, int defaultExtraLayoutSpace) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;
        this.DEFAULT_EXTRA_LAYOUT_SPACE = defaultExtraLayoutSpace;
    }

    public PreCachingLayoutManager(Context context, int orientation, boolean reverseLayout, int defaultExtraLayoutSpace) {
        super(context, orientation, reverseLayout);
        this.context = context;
        this.DEFAULT_EXTRA_LAYOUT_SPACE = defaultExtraLayoutSpace;
    }

    public void setExtraLayoutSpace(int extraLayoutSpace) {
        this.extraLayoutSpace = extraLayoutSpace;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE;
    }
}