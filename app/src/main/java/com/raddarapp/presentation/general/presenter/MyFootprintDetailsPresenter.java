package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.usecase.GetMyFootprintDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.MyFootprintDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyFootprintToMyFootprintDetailsViewModelMapper;

import javax.inject.Inject;

public class MyFootprintDetailsPresenter extends BasePresenterRefreshWithLoading<MyFootprintDetailsPresenter.View> {

    private final MyFootprintToMyFootprintDetailsViewModelMapper mapper;
    private final GetMyFootprintDetails getMyFootprinDetails;
    private MyFootprint myFootprint;
    private String myFootprintKey;
    private Long comments = null;

    @Inject
    MyFootprintsRepository myFootprintsRepository;

    @Inject
    public MyFootprintDetailsPresenter(UseCaseHandler useCaseHandler,
            MyFootprintToMyFootprintDetailsViewModelMapper mapper, GetMyFootprintDetails getMyFootprinDetails) {
        super(useCaseHandler);

        this.mapper = mapper;
        this.getMyFootprinDetails = getMyFootprinDetails;
    }

    @Override public void update() {
        super.update();
        loadMyFootprintDetails();
    }

    private void loadMyFootprintDetails() {
        createUseCaseCall(getMyFootprinDetails)
                .args(myFootprintKey)
                .useCaseName(GetMyFootprintDetails.USE_CASE_GET_MY_FOOTPRINT_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintDetails(MyFootprint myFootprint) {
                        MyFootprintDetailsPresenter.this.myFootprint = myFootprint;

                        showMyFootprintDetails(myFootprint);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showMyFootprintDetails(MyFootprint myFootprint) {
        MyFootprintDetailsViewModel myFootprintDetailsViewModel =
                (MyFootprintDetailsViewModel) mapper.map(myFootprint);

        try {
            getView().showMyFootprintDetails(myFootprintDetailsViewModel);
        } catch (Exception e) {}
    }

    public void updateComments()  {
        myFootprint.updateComments();
    }

    public void updateComments(long total)  {
        myFootprint.updateComments(total);
        comments = total;
    }

    public void setMyFootprintKey(String myFootprintKey) {
        this.myFootprintKey = myFootprintKey;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showMyFootprintDetails(MyFootprintDetailsViewModel myFootprintDetailsViewModel);
    }
}
