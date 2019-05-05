package com.raddarapp.presentation.general.presenter;

import android.net.Uri;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.builder.CreateFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.mapper.CreateFootprintToFootprintMainModelMapper;
import com.raddarapp.domain.model.mapper.CreateFootprintToMyFootprintModelMapper;
import com.raddarapp.domain.usecase.CreateFootprint;
import com.raddarapp.domain.usecase.GetMyUserProfile;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.validation.ValidationCreateFootprint;
import com.raddarapp.presentation.general.validation.view.ValidationCreateFootprintView;
import com.raddarapp.presentation.general.viewmodel.CreateFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.CreateFootprintToCreateFootprintViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class CreateFootprintPresenter extends RosiePresenterWithLoading<CreateFootprintPresenter.View> {

    private final CreateFootprintToCreateFootprintViewModelMapper mapperCreateFootprint;
    private final CreateFootprint createFootprintUseCase;
    private final GetMyUserProfile getUserProfile;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final ValidationCreateFootprint validationCreateFootprint;
    private final CreateFootprintToMyFootprintModelMapper mapperMyFootprintsForCache;
    private final CreateFootprintToFootprintMainModelMapper mapperFootprintMainForCache;
    private final MyFootprintsPresenter myFootprintsPresenter;
    private final FootprintsMainPresenter footprintsMainPresenter;
    private MyUserProfile userProfile = null;
    private com.raddarapp.domain.model.CreateFootprint createdFootprintLoaded = null;
    private boolean isCreatingFootprint = false;
    private boolean isFirstMessageFootprintUnclassified = true;

    @Inject
    public CreateFootprintPresenter(UseCaseHandler useCaseHandler, CreateFootprintToCreateFootprintViewModelMapper mapperCreateFootprint,
            CreateFootprint createFootprintUseCase, MyUserProfileToUserProfileViewModelMapper mapperUserProfile, GetMyUserProfile getUserProfile,
            ValidationCreateFootprint validationCreateFootprint, CreateFootprintToMyFootprintModelMapper mapperMyFootprintsForCache,
            MyFootprintsPresenter myFootprintsPresenter, FootprintsMainPresenter footprintsMainPresenter,
            CreateFootprintToFootprintMainModelMapper mapperFootprintMainForCache) {
        super(useCaseHandler);
        this.mapperCreateFootprint = mapperCreateFootprint;
        this.createFootprintUseCase = createFootprintUseCase;
        this.mapperUserProfile = mapperUserProfile;
        this.getUserProfile = getUserProfile;
        this.validationCreateFootprint = validationCreateFootprint;
        this.mapperMyFootprintsForCache = mapperMyFootprintsForCache;
        this.myFootprintsPresenter = myFootprintsPresenter;
        this.footprintsMainPresenter = footprintsMainPresenter;
        this.mapperFootprintMainForCache = mapperFootprintMainForCache;
    }

    @Override
    public void update() {
        super.update();
        loadUserProfile();
        loadCreatedFootprint();
    }

    private void loadUserProfile() {

        if (userProfile == null) {
            createUseCaseCall(getUserProfile)
                    .useCaseName(getUserProfile.USE_CASE_GET_USER_PROFILE_PREFERENCES)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void getUserProfilePreferences(MyUserProfile userProfilePreferences) {
                            userProfile = userProfilePreferences;
                            showUserProfilePreferences(userProfile);
                        }
                    })
                    .onError(error -> {
                        showError();

                        return false;
                    }).execute();
        } else {
            getView().showUserProfilePreferences(mapperUserProfile.map(userProfile));
        }
    }

    private void loadCreatedFootprint() {
        if (createdFootprintLoaded != null) {
            showCreatedFootprint(createdFootprintLoaded);
        } else {
            if (!isCreatingFootprint) {
                try {
                    getView().hideLoading();
                    getView().collapseFootprint();
                } catch (Exception e) {}
            }
        }
    }

    private void showUserProfilePreferences(MyUserProfile userProfilePreferences) {
        MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(userProfilePreferences);
        getView().showUserProfilePreferences(myUserProfileViewModel);
    }

    private void createCreateFootprint(com.raddarapp.domain.model.CreateFootprint createFootprint, String uriImage) {
        showLoading();

        createUseCaseCall(createFootprintUseCase)
                .args(createFootprint, uriImage)
                .useCaseName(CreateFootprint.USE_CASE_CREATE_FOOTPRINT)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void createFootprint(com.raddarapp.domain.model.CreateFootprint createdFootprint) {

                        isCreatingFootprint = false;

                        if (createdFootprint.getKey() != null && !createdFootprint.getKey().isEmpty()) {
                            createdFootprintLoaded = createdFootprint;
                            myFootprintsPresenter.addMyFootprintInCache(mapperMyFootprintsForCache.map(createdFootprint));
                            footprintsMainPresenter.addFootprintMainInCache(mapperFootprintMainForCache.map(createdFootprint));

                            showCreatedFootprint(createdFootprint);
                        } else {
                            showError();
                        }
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    private void showCreatedFootprint(com.raddarapp.domain.model.CreateFootprint createdFootprint) {
        try {
            CreateFootprintViewModel createdFootprintViewModel = mapperCreateFootprint.map(createdFootprint);
            getView().showCreatedFootprint(createdFootprintViewModel);
            hideLoading();
        } catch (Exception e) {}
    }

    public void onCreateFootprintClicked(String title, String description, int visibility, int aspectRatio,
            String longitude, String latitude, int footprintEmojiCategory, boolean footprintVisible, Uri uriImage) {

        isCreatingFootprint = true;

        ErrorLocalCode code = validationCreateFootprint.validateCreateFootprint(
                (uriImage != null) ? uriImage.getPath() : "", title, description, footprintEmojiCategory);

        if (code == ErrorLocalCode.UNCLASSIFIED_FOOTPRINT_EMOJI_CATEGORY) {
            if (!isFirstMessageFootprintUnclassified) {
                code = ErrorLocalCode.SUCCESS;
            }
        }

        if (code == ErrorLocalCode.SUCCESS) {
            try {
                getView().expandFootprint();
            } catch (Exception e) {}

            RaddarLocation raddarLocation = new RaddarLocationBuilder()
                    .withLatitude(Double.valueOf(latitude))
                    .withLongitude(Double.valueOf(longitude))
                    .build();

            com.raddarapp.domain.model.CreateFootprint createFootprint = new CreateFootprintBuilder()
                    .withTitle(title)
                    .withDescription(description)
                    .withVisibility(visibility)
                    .withAspectRatio(aspectRatio)
                    .withMediaType(FootprintMediaType.IMAGE.getValue())
                    .withType(FootprintType.FOOTPRINT.getValue())
                    .withCategory(footprintEmojiCategory)
                    .withVisible(footprintVisible)
                    .withRaddarLocation(raddarLocation)
                    .build();

            createCreateFootprint(createFootprint, uriImage.getPath());
        } else {
            showErrorLocal(code);
            isCreatingFootprint = false;
        }
    }

    private void showError() {
        hideLoading();
        getView().showError();
        getView().collapseFootprint();
    }

    private void showErrorLocal(ErrorLocalCode code) {
        try {

            switch (code) {
                case EMPTY_IMAGE:
                    getView().showErrorLocalImageEmpty();
                    break;
                case EMPTY_TITLE:
                    getView().showErrorLocalTitleEmpty();
                    break;
                case UNCLASSIFIED_FOOTPRINT_EMOJI_CATEGORY:
                    isFirstMessageFootprintUnclassified = false;
                    getView().showErrorLocalEmojiCategoryUnclassified();
                    break;
                case EMPTY_DESCRIPTION:
                    getView().showErrorLocalDescriptionEmpty();
                    break;
            }

            hideLoading();
        } catch (Exception e) {}
    }

    public interface View extends BasePresenterRefreshWithLoading.View, ValidationCreateFootprintView {
        void showCreatedFootprint(CreateFootprintViewModel createFootprintViewModel);

        void showError();

        void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel);

        void expandFootprint();

        void collapseFootprint();
    }
}
