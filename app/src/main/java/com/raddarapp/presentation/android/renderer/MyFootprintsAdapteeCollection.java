package com.raddarapp.presentation.android.renderer;


import com.pedrogomez.renderers.ListAdapteeCollection;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

public class MyFootprintsAdapteeCollection extends ListAdapteeCollection<MyFootprintViewModelContract> {
    private boolean showLoadMore = false;

    public void setShowLoadMore(boolean showLoadMore) {
        this.showLoadMore = showLoadMore;
    }

    @Override public int size() {
        int size = super.size();
        return showLoadMore ? size + 1 : size;
    }

    @Override public MyFootprintViewModelContract get(int i) {
        MyFootprintViewModelContract item = null;
        if (i < super.size()) {
            item = super.get(i);
        }
        return item;
    }
}
