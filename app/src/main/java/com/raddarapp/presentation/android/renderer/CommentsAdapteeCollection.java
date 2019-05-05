package com.raddarapp.presentation.android.renderer;


import com.pedrogomez.renderers.ListAdapteeCollection;
import com.raddarapp.presentation.general.viewmodel.contract.CommentViewModelContract;

public class CommentsAdapteeCollection extends ListAdapteeCollection<CommentViewModelContract> {
    private boolean showLoadMore = true;

    public void setShowLoadMore(boolean showLoadMore) {
        this.showLoadMore = showLoadMore;
    }

    @Override public int size() {
        int size = super.size();
        return showLoadMore ? size + 1 : size;
    }

    @Override public CommentViewModelContract get(int i) {
        CommentViewModelContract item = null;
        if (i < super.size()) {
            item = super.get(i);
        }
        return item;
    }
}
