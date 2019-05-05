package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.MyFootprintsCollectionRepository;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.usecase.GetMyFootprintCollectionDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyFootprintCollectionToMyFootprintCollectionDetailsViewModelMapper;

import javax.inject.Inject;

public class MyFootprintCollectionDetailsPresenter extends BasePresenterRefreshWithLoading<MyFootprintCollectionDetailsPresenter.View> {

    private final MyFootprintCollectionToMyFootprintCollectionDetailsViewModelMapper mapper;
    private final GetMyFootprintCollectionDetails getMyFootprintCollectionDetails;
    private MyFootprintCollection myFootprintCollection;
    private String myFootprintCollectionKey;
    private Long comments = null;

    @Inject
    MyFootprintsCollectionRepository myFootprintsCollectionRepository;

    @Inject
    public MyFootprintCollectionDetailsPresenter(UseCaseHandler useCaseHandler,
            MyFootprintCollectionToMyFootprintCollectionDetailsViewModelMapper mapper, GetMyFootprintCollectionDetails getMyFootprintCollectionDetails) {
        super(useCaseHandler);

        this.mapper = mapper;
        this.getMyFootprintCollectionDetails = getMyFootprintCollectionDetails;
    }

    @Override public void update() {
        super.update();
        loadMyFootprintCollectionDetails();
    }

    private void loadMyFootprintCollectionDetails() {
        createUseCaseCall(getMyFootprintCollectionDetails)
                .args(myFootprintCollectionKey)
                .useCaseName(GetMyFootprintCollectionDetails.USE_CASE_GET_MY_FOOTPRINT_COLLECTION_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintCollectionDetails(MyFootprintCollection myFootprintCollection) {
                        MyFootprintCollectionDetailsPresenter.this.myFootprintCollection = myFootprintCollection;

                        if (comments != null) {
                            myFootprintCollection.updateComments(comments);
                        }

                        showFootprintDetails(myFootprintCollection);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showFootprintDetails(MyFootprintCollection myFootprintCollection) {
        MyFootprintCollectionDetailsViewModel myFootprintCollectionDetailsViewModel =
                (MyFootprintCollectionDetailsViewModel) mapper.map(myFootprintCollection);

        try {
            getView().showMyFootprintCollectionDetails(myFootprintCollectionDetailsViewModel);
        } catch (Exception e) {}
    }

    public void updateUserRelationship(int userRelationship)  {
        myFootprintCollection.updateUserRelationship(userRelationship);

        try {
            myFootprintsCollectionRepository.addOrUpdate(myFootprintCollection);
        } catch (Exception e) { getView().showErrorFollow(); }

    }

    public void updateComments()  {
        myFootprintCollection.updateComments();
    }

    public void updateComments(long total)  {
        myFootprintCollection.updateComments(total);
        comments = total;
    }

    public void setMyFootprintCollectionKey(String myFootprintCollectionKey) {
        this.myFootprintCollectionKey = myFootprintCollectionKey;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showMyFootprintCollectionDetails(MyFootprintCollectionDetailsViewModel myFootprintCollectionDetailsViewModel);

        void showErrorFollow();
    }
}
