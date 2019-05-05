package com.raddarapp.presentation.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.raddarapp.R;

public class StableAspectSquareCardView extends CardView {

    private int aspectWidth = 1;
    private int aspectHeight = 1;

    public StableAspectSquareCardView(Context context) {
        this(context, null, 0);
    }

    public StableAspectSquareCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StableAspectSquareCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractCustomAttrs(context, attrs);
    }

    private void extractCustomAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray a = context.getResources().obtainAttributes(attrs, R.styleable.StableAspectSquareCardView);
        try {
            aspectWidth = a.getInteger(R.styleable.StableAspectSquareCardView_aspect_width, 1);
            aspectHeight = a.getInteger(R.styleable.StableAspectSquareCardView_aspect_height, 1);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int newSpecWidth = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
        int newH = Math.round(((float) getMeasuredWidth()) * aspectHeight / aspectWidth);
        int newSpecHeigh = MeasureSpec.makeMeasureSpec(newH, MeasureSpec.EXACTLY);
        super.onMeasure(newSpecWidth, newSpecHeigh);
    }
}