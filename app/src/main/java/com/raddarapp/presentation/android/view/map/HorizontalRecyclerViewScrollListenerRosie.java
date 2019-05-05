package com.raddarapp.presentation.android.view.map;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HorizontalRecyclerViewScrollListenerRosie extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager layoutManager;
    private OnItemCoverListener onItemCoverListener = null;
    private boolean isEnabled;
    private boolean isProcessing;

    private static final int OFFSET_RANGE = 50;
    private static final double COVER_FACTOR = 0.7;
    private static int unSelected = 0;
    private static int selected = -1;
    private final OnLoadMoreListener onLoadMoreListener;

    private int[] itemBounds = null;

    public HorizontalRecyclerViewScrollListenerRosie(LinearLayoutManager layoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.layoutManager = layoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public HorizontalRecyclerViewScrollListenerRosie(LinearLayoutManager layoutManager, OnItemCoverListener onItemCoverListener, OnLoadMoreListener onLoadMoreListener) {
        this.layoutManager = layoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
        this.onItemCoverListener = onItemCoverListener;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setIsProcessing(boolean isProcessing) {
        this.isProcessing = isProcessing;
    }

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

        if (isEnabled && !isProcessing) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onScrolledToBottom();
                    isProcessing = true;
                }
            }
        }

        /*if (itemBounds == null) {
            fillItemBounds(recyclerView.getAdapter().getItemCount(), recyclerView);
        }

        /**
         * Check cards inside range
         */
        /*for (int i = 0; i < itemBounds.length-1; i++) {
            if (isInChildItemsRange(recyclerView.computeHorizontalScrollOffset(), itemBounds[i], OFFSET_RANGE)) {

                if (unSelected != i) {
                    onItemCoverListener.onItemUnselected(unSelected);
                    unSelected = i;
                }

                if (selected != i) {
                    onItemCoverListener.onItemCover(i);
                    selected = i;
                }

            }
        }*/
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

    public interface OnLoadMoreListener {
        void onScrolledToBottom();
    }

    public interface OnItemCoverListener {
        void onItemUnselected(final int position);
        void onItemCover(final int position);
    }
}
