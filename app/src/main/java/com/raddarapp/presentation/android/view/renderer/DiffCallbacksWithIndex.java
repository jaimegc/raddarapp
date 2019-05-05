package com.raddarapp.presentation.android.view.renderer;

import android.support.v7.util.DiffUtil;

import com.pedrogomez.renderers.AdapteeCollection;

import java.util.List;

public class DiffCallbacksWithIndex<T> extends DiffUtil.Callback {

    private final AdapteeCollection<T> oldList;
    private final List<T> newList;

    private int oldItemPosition;

    private boolean deep;

    DiffCallbacksWithIndex(AdapteeCollection<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override public int getOldListSize() {
        return oldList.size();
    }

    @Override public int getNewListSize() {
        return newList.size();
    }

    @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        this.deep = false;
        this.oldItemPosition = oldItemPosition;
        return equals(newList.get(newItemPosition));
    }

    @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        this.deep = true;
        this.oldItemPosition = oldItemPosition;
        return equals(newList.get(newItemPosition));
    }

    @Override public boolean equals(Object newItem) {
        Object current = oldList.get(oldItemPosition);
        if (deep) {
            return newItem.equals(current);
        } else {
            return newItem.getClass().equals(current.getClass());
        }
    }

    @Override public int hashCode() {
        return super.hashCode();
    }
}