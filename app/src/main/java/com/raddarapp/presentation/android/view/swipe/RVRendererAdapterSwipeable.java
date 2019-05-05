package com.raddarapp.presentation.android.view.swipe;

import com.pedrogomez.renderers.AdapteeCollection;
import com.raddarapp.presentation.android.view.renderer.RVRendererAdapterWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;

public class RVRendererAdapterSwipeable<T> extends RVRendererAdapterWithIndex<T> implements ItemTouchHelperAdapter {

    public RVRendererAdapterSwipeable(RendererBuilderWithIndex<T> rendererBuilder) {
        super(rendererBuilder);
    }

    public RVRendererAdapterSwipeable(RendererBuilderWithIndex<T> rendererBuilder, AdapteeCollection<T> collection) {
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
