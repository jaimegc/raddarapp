package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.FootprintsRankingRepository;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.usecase.GetFootprintRankingDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.FootprintRankingDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.FootprintRankingToFootprintRankingDetailsViewModelMapper;

import javax.inject.Inject;

public class FootprintRankingDetailsPresenter extends BasePresenterRefreshWithLoading<FootprintRankingDetailsPresenter.View> {

    private final FootprintRankingToFootprintRankingDetailsViewModelMapper mapper;
    private final GetFootprintRankingDetails getFootprintRankingDetails;
    private FootprintRanking footprintsRanking;
    private String footprintsRankingKey;
    private Long comments = null;

    @Inject
    FootprintsRankingRepository footprintsRankingRepository;

    @Inject
    public FootprintRankingDetailsPresenter(UseCaseHandler useCaseHandler,
            FootprintRankingToFootprintRankingDetailsViewModelMapper mapper, GetFootprintRankingDetails getFootprintRankingDetails) {
        super(useCaseHandler);

        this.mapper = mapper;
        this.getFootprintRankingDetails = getFootprintRankingDetails;
    }

    @Override public void update() {
        super.update();
        loadFootprintRankingDetails();
    }

    private void loadFootprintRankingDetails() {
        createUseCaseCall(getFootprintRankingDetails)
                .args(footprintsRankingKey)
                .useCaseName(GetFootprintRankingDetails.USE_CASE_GET_FOOTPRINT_RANKING_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getFootprintRankingDetails(FootprintRanking footprintsRanking) {
                        FootprintRankingDetailsPresenter.this.footprintsRanking = footprintsRanking;

                        if (comments != null) {
                            footprintsRanking.updateComments(comments);
                        }

                        showFootprintDetails(footprintsRanking);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showFootprintDetails(FootprintRanking footprintsRanking) {
        FootprintRankingDetailsViewModel footprintsRankingDetailsViewModel =
                (FootprintRankingDetailsViewModel) mapper.map(footprintsRanking);

        try {
            getView().showFootprintRankingDetails(footprintsRankingDetailsViewModel);
        } catch (Exception e) {}
    }

    public void updateUserRelationship(int userRelationship)  {
        footprintsRanking.updateUserRelationship(userRelationship);

        try {
            footprintsRankingRepository.addOrUpdate(footprintsRanking);
        } catch (Exception e) { getView().showErrorFollow(); }

    }

    public void updateComments()  {
        footprintsRanking.updateComments();
    }

    public void updateComments(long total)  {
        footprintsRanking.updateComments(total);
        comments = total;
    }

    public void setFootprintRankingKey(String footprintsRankingKey) {
        this.footprintsRankingKey = footprintsRankingKey;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showFootprintRankingDetails(FootprintRankingDetailsViewModel footprintsRankingDetailsViewModel);

        void showErrorFollow();
    }
}
