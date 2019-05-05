package com.raddarapp.presentation.android.view;

import com.pedrogomez.renderers.AdapteeCollection;
import com.raddarapp.presentation.android.view.renderer.RVRendererAdapterWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.swipe.ItemTouchHelperAdapter;

public class RVRendererAdapterRemovable<T> extends RVRendererAdapterWithIndex<T> implements ItemTouchHelperAdapter {

    public RVRendererAdapterRemovable(RendererBuilderWithIndex<T> rendererBuilder) {
        super(rendererBuilder);
    }

    public RVRendererAdapterRemovable(RendererBuilderWithIndex<T> rendererBuilder, AdapteeCollection<T> collection) {
        super(rendererBuilder, collection);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // Unused method
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        super.remove(super.getCollection().get(position));
        notifyItemRemoved(position);
    }
}
