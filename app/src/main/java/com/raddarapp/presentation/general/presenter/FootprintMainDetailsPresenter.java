package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.FootprintsMainRepository;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.usecase.GetFootprintMainDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.FootprintMainDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.FootprintMainToFootprintMainDetailsViewModelMapper;

import javax.inject.Inject;

public class FootprintMainDetailsPresenter extends BasePresenterRefreshWithLoading<FootprintMainDetailsPresenter.View> {

    private final FootprintMainToFootprintMainDetailsViewModelMapper mapper;
    private final GetFootprintMainDetails getFootprintDetails;
    private FootprintMain footprintMain;
    private String footprintMainKey;
    private Long comments = null;

    @Inject
    FootprintsMainRepository footprintsMainRepository;

    @Inject
    public FootprintMainDetailsPresenter(UseCaseHandler useCaseHandler,
            FootprintMainToFootprintMainDetailsViewModelMapper mapper, GetFootprintMainDetails getFootprintDetails) {
        super(useCaseHandler);

        this.mapper = mapper;
        this.getFootprintDetails = getFootprintDetails;
    }

    @Override public void update() {
        super.update();
        loadFootprintMainDetails();
    }

    private void loadFootprintMainDetails() {
        createUseCaseCall(getFootprintDetails)
                .args(footprintMainKey)
                .useCaseName(GetFootprintMainDetails.USE_CASE_GET_FOOTPRINT_MAIN_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getFootprintDetails(FootprintMain footprintMain) {
                        FootprintMainDetailsPresenter.this.footprintMain = footprintMain;

                        if (comments != null) {
                            footprintMain.updateComments(comments);
                        }

                        showFootprintDetails(footprintMain);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showFootprintDetails(FootprintMain footprintMain) {
        FootprintMainDetailsViewModel footprintMainDetailsViewModel =
                (FootprintMainDetailsViewModel) mapper.map(footprintMain);

        try {
            getView().showFootprintMainDetails(footprintMainDetailsViewModel);
        } catch (Exception e) {}
    }

    public void updateUserRelationship(int userRelationship)  {
        footprintMain.updateUserRelationship(userRelationship);

        try {
            footprintsMainRepository.addOrUpdate(footprintMain);
        } catch (Exception e) { getView().showErrorFollow(); }

    }

    public void updateComments()  {
        footprintMain.updateComments();
    }

    public void updateComments(long total)  {
        footprintMain.updateComments(total);
        comments = total;
    }

    public void setFootprintMainKey(String footprintMainKey) {
        this.footprintMainKey = footprintMainKey;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showFootprintMainDetails(FootprintMainDetailsViewModel footprintMainDetailsViewModel);

        void showErrorFollow();
    }
}
