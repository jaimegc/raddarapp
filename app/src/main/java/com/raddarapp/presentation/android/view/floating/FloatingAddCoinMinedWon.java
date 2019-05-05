package com.raddarapp.presentation.android.view.floating;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.rebound.SpringSystem;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.transition.FloatingTransition;
import com.ufreedom.floatingview.transition.YumFloating;

/**
 * Author UFreedom
 * Date : 2016 十月 17
 * Custom changes :]
 */

public class FloatingAddCoinMinedWon {
    private FloatingDecorTextView mFloatingDecorView;
    private SpringSystem mSpringSystem;
    private Activity activity;

    public FloatingAddCoinMinedWon(Activity activity){

        if (activity == null){
            throw new NullPointerException("Activity should not be null");
        }

        this.activity = activity;

        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View decorView = rootView.findViewById(com.ufreedom.floatingview.R.id.floating_decor);
        if (decorView instanceof FloatingDecorTextView){
            mFloatingDecorView = (FloatingDecorTextView) decorView;
        } else {
            mFloatingDecorView = new FloatingDecorTextView(activity);
            mFloatingDecorView.setId(com.ufreedom.floatingview.R.id.floating_decor);

            rootView.addView(mFloatingDecorView);
        }

        if (mSpringSystem == null){
            mSpringSystem = SpringSystem.create();
        }
    }

    public void detach() {

        if (mFloatingDecorView == null) return;

        mFloatingDecorView.removeAllViews();
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        rootView.removeView(mFloatingDecorView);
        mFloatingDecorView = null;
        activity = null;
    }

    public void startFloating(FloatingElement floatingElement, String reachValue, String fontName) {
        View anchorView = floatingElement.anchorView;
        View targetView = floatingElement.targetView;

        if (targetView == null){
            targetView = LayoutInflater.from(anchorView.getContext()).inflate(floatingElement.targetViewLayoutResId, mFloatingDecorView,false);
        }

        Rect rect = new Rect();
        anchorView.getGlobalVisibleRect(rect);
        int[] location = new int[2];
        mFloatingDecorView.getLocationOnScreen(location);
        rect.offset(-location[0], -location[1]);

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        targetView.measure(widthMeasureSpec,heightMeasureSpec);

        int topMargin = rect.top  + ((anchorView.getMeasuredHeight() - targetView.getMeasuredHeight())) + floatingElement.offsetY;
        int leftMargin = rect.left  + ((anchorView.getMeasuredWidth() - targetView.getMeasuredWidth())) + floatingElement.offsetX;

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = topMargin;
        lp.leftMargin = leftMargin;

        TextView reachView = ((TextView) targetView.getRootView().findViewById(R.id.total_coin_mined_won));
        FontUtils fontUtils = new FontUtils();
        fontUtils.applyFont(activity, fontName, reachView);
        reachView.setText(reachValue);

        mFloatingDecorView.addView(targetView, lp);

        FloatingTransition floatingAnimator = floatingElement.floatingTransition;
        floatingAnimator.applyFloating(new YumFloating(targetView, mSpringSystem));
    }

    private class FloatingDecorTextView extends FrameLayout {

        public FloatingDecorTextView(Context context) {
            this(context, null);
        }

        public FloatingDecorTextView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public FloatingDecorTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
    }
}
