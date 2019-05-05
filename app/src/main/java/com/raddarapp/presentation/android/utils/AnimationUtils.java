package com.raddarapp.presentation.android.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {

    public void scaleAnimation(int startOffset, int delay, int duration, View... views) {
        ScaleAnimation scaleAnimation;
        ScaleAnimation animations[] = new ScaleAnimation[views.length];
        int msAnimation = startOffset;

        for (int i = 0; i < views.length; i++) {
            scaleAnimation = new ScaleAnimation(
                    0, (float) 1.0, 0, (float) 1.0, Animation.ABSOLUTE, 50, Animation.ABSOLUTE, 50);
            scaleAnimation.setDuration(duration);
            scaleAnimation.setStartOffset(msAnimation);
            animations[i] = scaleAnimation;
            msAnimation += delay;
        }

        for (int i = 0; i < animations.length; i++) {
            views[i].startAnimation(animations[i]);
        }
    }

    public void alphaAnimationWithInitial(View view, long duration, int initialVisibility, int finalVisibility, boolean fillAfter) {
        view.setVisibility(initialVisibility);
        AlphaAnimation alphaAnimation = (finalVisibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(fillAfter);
        view.startAnimation(alphaAnimation);
    }

    public void alphaAnimation(View view, long duration, int finalVisibility, boolean fillAfter) {
        AlphaAnimation alphaAnimation = (finalVisibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(fillAfter);
        view.startAnimation(alphaAnimation);
    }

    public void recyclerViewAnimation(RecyclerView recyclerView, int dp, long duration) {
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(duration);
        set.addAnimation(animation);

        TranslateAnimation tanim = new TranslateAnimation(dp, 0, 0, 0);
        tanim.setFillAfter(true);
        tanim.setFillBefore(true);

        tanim.setDuration(duration);
        set.addAnimation(tanim);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        recyclerView.setLayoutAnimation(controller);
    }
}
