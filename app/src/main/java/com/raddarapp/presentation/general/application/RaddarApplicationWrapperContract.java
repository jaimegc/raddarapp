package com.raddarapp.presentation.general.application;

import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.util.List;

public interface RaddarApplicationWrapperContract {

    void updateMyFootprints(List<MyFootprintViewModelContract> myFootprintsViewModel);

    void updateFootprintsMain(PaginatedCollection<FootprintMain> footprintsMainCollection,
        List<FootprintMainViewModelContract> footprintsMainViewModel);
}
