package com.raddarapp.presentation.general.application;

import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.util.List;

import javax.inject.Inject;

public class RaddarApplicationWrapper implements RaddarApplicationWrapperContract {

    private FootprintMainActivity footprintMainActivity;

    @Inject
    public RaddarApplicationWrapper(FootprintMainActivity footprintMainActivity) {
        this.footprintMainActivity = footprintMainActivity;
    }

    @Override
    public void updateMyFootprints(List<MyFootprintViewModelContract> myFootprintsViewModel) {
        footprintMainActivity.updateMyFootprints(myFootprintsViewModel);
    }

    @Override
    public void updateFootprintsMain(PaginatedCollection<FootprintMain> footprintsMainCollection,
            List<FootprintMainViewModelContract> footprintsMainViewModel) {
        footprintMainActivity.updateFootprintsMain(footprintsMainCollection, footprintsMainViewModel);
    }
}
