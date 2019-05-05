package com.raddarapp.presentation.general.presenter.contract;

import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;

public interface MyFootprintsPresenterContract {

    void onMyFootprintClicked(MyFootprintViewModel myFootprint, int position);

    void onMyFootprintsMoreClicked();

    void onMyFootprintDeleted(String footprintKey, boolean isDead, long scope, long likes, long dislikes);
}
