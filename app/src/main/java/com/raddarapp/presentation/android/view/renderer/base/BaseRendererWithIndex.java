package com.raddarapp.presentation.android.view.renderer.base;

import android.view.View;

import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;

import butterknife.ButterKnife;

public abstract class BaseRendererWithIndex<T> extends RendererWithIndex<T> {

    @Override public void render() {
        ButterKnife.bind(this, getRootView());
    }

    @Override protected void setUpView(View view) {

    }

    @Override protected void hookListeners(View view) {

    }
}