package com.raddarapp.presentation.android.view.renderer;

import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView.ViewHolder extension created to be able to use Renderer classes in RecyclerView
 * widgets. This class will be completely hidden to the library clients.
 */
public class RendererViewHolderWithIndex extends RecyclerView.ViewHolder {

    private final RendererWithIndex renderer;

    public RendererViewHolderWithIndex(RendererWithIndex renderer) {
        super(renderer.getRootView());
        this.renderer = renderer;
    }

    public RendererWithIndex getRenderer() {
        return renderer;
    }
}
