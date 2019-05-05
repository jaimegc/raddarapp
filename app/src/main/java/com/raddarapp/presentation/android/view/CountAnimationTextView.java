package com.raddarapp.presentation.android.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.NumberUtils;

import java.text.NumberFormat;

/*
    https://github.com/MasayukiSuda/CountAnimationTextView
 */
public class CountAnimationTextView extends android.support.v7.widget.AppCompatTextView {

    private boolean isAnimating = false;
    private ValueAnimator mCountAnimator;
    private CountAnimationListener mCountAnimationListener;
    private Context context;

    private NumberFormat mNumberFormat;
    NumberUtils numberUtils = new NumberUtils();
    private String finalValue = "";

    private boolean youAreAmazing = false;
    private boolean stringWithPlus = false;

    private static final long DEFAULT_DURATION = 500;

    public CountAnimationTextView(Context context) {
        this(context, null, 0);
    }

    public CountAnimationTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountAnimationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setUpAnimator();
    }

    private void setUpAnimator() {
        mCountAnimator = new ValueAnimator();
        mCountAnimator.addUpdateListener(animation -> {
            String value;

            value = !stringWithPlus ? numberUtils.rangeOrScopeToString((Integer) animation.getAnimatedValue()) :
                    numberUtils.rangeOrScopeToStringPlus((Integer) animation.getAnimatedValue());

            CountAnimationTextView.super.setText(value);
        });

        mCountAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;

                if (mCountAnimationListener == null) return;
                    mCountAnimationListener.onAnimationStart(mCountAnimator.getAnimatedValue());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;

                CountAnimationTextView.super.setText(finalValue);

                if (mCountAnimationListener == null) return;
                    mCountAnimationListener.onAnimationEnd(mCountAnimator.getAnimatedValue());

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // do nothing
            }
        });
        mCountAnimator.setDuration(DEFAULT_DURATION);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mCountAnimator != null) {
            mCountAnimator.cancel();
        }
    }

    public void countAnimation(int fromValue, int toValue) {
        if (isAnimating) return;

        mCountAnimator.setIntValues(fromValue, toValue);
        mCountAnimator.start();
    }

    // cmValue is to avoid errors with rounded numbers
    public void countAnimation(Long fromValue, Long toValue, long delay) {
        if (isAnimating) return;

        stringWithPlus = false;
        finalValue  = numberUtils.rangeOrScopeToString(toValue);

        Integer to = 0, from = 0;

        // MAX VALUE: 2147483647cm ==> 21.474.836,47m ==> 21.474,83647km
        if (fromValue <= Integer.MAX_VALUE) {
            from = longToInt(fromValue);
        } else {
            from = -1;
            youAreAmazing = true;
        }

        if (toValue <= Integer.MAX_VALUE) {
            to = longToInt(toValue);
        } else {
            to = -1;
            youAreAmazing = true;
        }

        if (!youAreAmazing) {
            mCountAnimator.setIntValues(from, to);
            mCountAnimator.setStartDelay(delay);
            mCountAnimator.start();
        } else {
            CountAnimationTextView.super.setText(context.getString(R.string.you_are_amazing));
        }
    }

    public void countAnimationRangeMined(int fromValue, int toValue, long delay) {
        if (isAnimating) return;

        stringWithPlus = true;
        finalValue  = numberUtils.rangeOrScopeToStringPlus(toValue);

        mCountAnimator.setIntValues(fromValue, toValue);
        mCountAnimator.setStartDelay(delay);
        mCountAnimator.start();
    }

    private int longToInt(long l) {
        return (int) l;
    }

    public CountAnimationTextView setAnimationDuration(long duration) {
        mCountAnimator.setDuration(duration);
        return this;
    }

    public CountAnimationTextView setInterpolator(@NonNull TimeInterpolator value) {
        mCountAnimator.setInterpolator(value);
        return this;
    }


    // interface progress animationListener
    public interface CountAnimationListener {
        void onAnimationStart(Object animatedValue);

        void onAnimationEnd(Object animatedValue);
    }

    public CountAnimationTextView setDecimalFormat(NumberFormat mNumberFormat) {
        this.mNumberFormat = mNumberFormat;
        return this;
    }

    public void clearDecimalFormat() {
        this.mNumberFormat = null;
    }

    public CountAnimationTextView setCountAnimationListener(CountAnimationListener mCountAnimationListener) {
        this.mCountAnimationListener = mCountAnimationListener;
        return this;
    }
}
