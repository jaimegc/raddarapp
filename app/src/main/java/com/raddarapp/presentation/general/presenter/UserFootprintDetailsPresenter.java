package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.UserFootprintsRepository;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.domain.usecase.GetUserFootprintDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.UserFootprintDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.UserFootprintToUserFootprintDetailsViewModelMapper;

import javax.inject.Inject;

public class UserFootprintDetailsPresenter extends BasePresenterRefreshWithLoading<UserFootprintDetailsPresenter.View> {

    private final UserFootprintToUserFootprintDetailsViewModelMapper mapper;
    private final GetUserFootprintDetails getUserFootprinDetails;
    private UserFootprint userFootprint;
    private String userFootprintKey;
    private Long comments = null;

    @Inject
    UserFootprintsRepository userFootprintsRepository;

    @Inject
    public UserFootprintDetailsPresenter(UseCaseHandler useCaseHandler,
            UserFootprintToUserFootprintDetailsViewModelMapper mapper, GetUserFootprintDetails getUserFootprinDetails) {
        super(useCaseHandler);

        this.mapper = mapper;
        this.getUserFootprinDetails = getUserFootprinDetails;
    }

    @Override public void update() {
        super.update();
        loadUserFootprintDetails();
    }

    private void loadUserFootprintDetails() {
        createUseCaseCall(getUserFootprinDetails)
                .args(userFootprintKey)
                .useCaseName(GetUserFootprintDetails.USE_CASE_GET_USER_FOOTPRINT_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getUserFootprintDetails(UserFootprint userFootprint) {
                        UserFootprintDetailsPresenter.this.userFootprint = userFootprint;

                        if (comments != null) {
                            userFootprint.updateComments(comments);
                        }

                        showUserFootprintDetails(userFootprint);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showUserFootprintDetails(UserFootprint userFootprint) {
        UserFootprintDetailsViewModel userFootprintDetailsViewModel =
                (UserFootprintDetailsViewModel) mapper.map(userFootprint);

        try {
            getView().showUserFootprintDetails(userFootprintDetailsViewModel);
        } catch (Exception e) {}
    }

    public void updateComments()  {
        userFootprint.updateComments();
    }

    public void updateComments(long total)  {
        userFootprint.updateComments(total);
        comments = total;
    }

    public void setUserFootprintKey(String userFootprintKey) {
        this.userFootprintKey = userFootprintKey;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showUserFootprintDetails(UserFootprintDetailsViewModel userFootprintDetailsViewModel);
    }
}
