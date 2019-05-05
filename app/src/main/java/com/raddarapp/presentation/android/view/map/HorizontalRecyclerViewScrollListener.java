package com.raddarapp.presentation.android.view.map;

import android.support.v7.widget.RecyclerView;

public class HorizontalRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private static final int OFFSET_RANGE = 50;
    private static final double COVER_FACTOR = 0.7;
    private static int unSelected = 0;

    private int[] itemBounds = null;
    private final OnItemCoverListener listener;

    public HorizontalRecyclerViewScrollListener(final OnItemCoverListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (itemBounds == null) fillItemBounds(recyclerView.getAdapter().getItemCount(), recyclerView);
        for (int i = 0; i < itemBounds.length; i++) {
            if (isInChildItemsRange(recyclerView.computeHorizontalScrollOffset(), itemBounds[i], OFFSET_RANGE)) {

                if (unSelected != i) {
                    listener.onItemUnselected(unSelected);
                    unSelected = i;
                }

                listener.onItemCover(i);
            }
        }
    }

    private void fillItemBounds(final int placesCount, final RecyclerView recyclerView) {
        itemBounds = new int[placesCount];
        int childWidth = (recyclerView.computeHorizontalScrollRange() - recyclerView.computeHorizontalScrollExtent()) / placesCount;
        for (int i = 0; i < placesCount; i++) {
            itemBounds[i] = (int) (((childWidth * i + childWidth * (i + 1)) / 2) * COVER_FACTOR);
        }
    }

    private boolean isInChildItemsRange(final int offset, final int itemBound, final int range) {
        int rangeMin = itemBound - range;
        int rangeMax = itemBound + range;
        return (Math.min(rangeMin, rangeMax) <= offset) && (Math.max(rangeMin, rangeMax) >= offset);
    }

    public interface OnItemCoverListener {
        void onItemUnselected(final int position);
        void onItemCover(final int position);
    }
}
