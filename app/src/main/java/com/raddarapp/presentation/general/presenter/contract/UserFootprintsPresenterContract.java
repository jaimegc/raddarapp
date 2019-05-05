package com.raddarapp.presentation.general.presenter.contract;

import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;

public interface UserFootprintsPresenterContract {

    void onUserFootprintClicked(UserFootprintViewModel userFootprint, int position);
}
