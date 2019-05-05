package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.builder.CoinMiningBuilder;
import com.raddarapp.domain.usecase.CoinMining;
import com.raddarapp.domain.usecase.GetCoinMining;
import com.raddarapp.domain.usecase.GetMyUserProfile;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.validation.ValidationCoinMining;
import com.raddarapp.presentation.general.validation.view.ValidationCoinMiningView;
import com.raddarapp.presentation.general.viewmodel.CoinMiningViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.CoinMiningToCoinMiningViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class CoinMiningPresenter extends BasePresenterRefreshWithLoading<CoinMiningPresenter.View> {

    private static final int MAXIMUM_RANGE_MINED_PER_DAY = 20000;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final ValidationCoinMining validationCoinMining;
    private final GetMyUserProfile getUserProfile;
    private MyUserProfile userProfile = null;
    private final CoinMining coinMiningUseCase;
    private final GetCoinMining getCoinMiningUseCase;
    private final CoinMiningToCoinMiningViewModelMapper mapper;
    private boolean maximumRangeMinedPerDayFirsTime = true;
    private boolean exchangeSent = false;
    private com.raddarapp.domain.model.CoinMining coinMinedCache;

    @Inject
    public CoinMiningPresenter(UseCaseHandler useCaseHandler, MyUserProfileToUserProfileViewModelMapper mapperUserProfile,
            ValidationCoinMining validationCoinMining, GetMyUserProfile getUserProfile, CoinMining coinMiningUseCase,
            CoinMiningToCoinMiningViewModelMapper mapper, GetCoinMining getCoinMiningUseCase) {
        super(useCaseHandler);
        this.mapperUserProfile = mapperUserProfile;
        this.validationCoinMining = validationCoinMining;
        this.getUserProfile = getUserProfile;
        this.coinMiningUseCase = coinMiningUseCase;
        this.mapper = mapper;
        this.getCoinMiningUseCase = getCoinMiningUseCase;
    }

    @Override
    public void update() {
        super.update();
        loadUserProfile();
        loadGetCoinMining();

        if (exchangeSent && coinMinedCache != null) {
            showRangeMined(coinMinedCache);
        }
    }

    private void loadUserProfile() {

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
    }

    private void showUserProfilePreferences(MyUserProfile userProfilePreferences) {
        MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(userProfilePreferences);
        getView().showUserProfilePreferences(myUserProfileViewModel);
    }

    private void showError() {
        hideLoading();
        getView().enableViews();
    }

    private void loadGetCoinMining() {
        try {
            showLoading();
            getView().disableViews();

            createUseCaseCall(getCoinMiningUseCase)
                    .useCaseName(GetCoinMining.USE_CASE_GET_COIN_MINING)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void getCoinMining(com.raddarapp.domain.model.CoinMining coinMined) {
                            initializeCountDown(coinMined);
                        }

                    })
                    .onError(error -> {
                        showError();
                        return false;
                    }).execute();
        } catch (Exception e) {}
    }

    public void coinMining(int rangeMinedNow) {
        try {
            showLoading();
            getView().disableViews();

            ErrorLocalCode code = validationCoinMining.validateCoinMined(rangeMinedNow);

            if (code == ErrorLocalCode.SUCCESS) {
                exchangeSent = true;
                com.raddarapp.domain.model.CoinMining coinMining = new CoinMiningBuilder()
                        .withRangeMined(rangeMinedNow)
                        .build();

                createUseCaseCall(coinMiningUseCase)
                        .args(coinMining)
                        .useCaseName(CoinMining.USE_CASE_COIN_MINING)
                        .onSuccess(new OnSuccessCallback() {
                            @Success
                            public void coinMining(com.raddarapp.domain.model.CoinMining coinMined) {
                                coinMinedCache = coinMined;
                                showRangeMined(coinMined);
                            }

                        })
                        .onError(error -> {
                            showError();
                            return false;
                        }).execute();
            } else {
                showErrorLocal(code);
            }
        } catch (Exception e) {}
    }

    private void showRangeMined(com.raddarapp.domain.model.CoinMining coinMined) {
        try {
            boolean maximumRangeMinedPerDay = coinMined.getRangeMined() == MAXIMUM_RANGE_MINED_PER_DAY;
            hideLoading();
            getView().enableViews();
            CoinMiningViewModel coinMiningViewModel = mapper.map(coinMined);

            if (maximumRangeMinedPerDay) {
                getView().showMaximumRangeMinedPerDay();
            }

            if (maximumRangeMinedPerDay) {
                if (maximumRangeMinedPerDayFirsTime) {
                    getView().showRangeMined(coinMiningViewModel, false);
                    maximumRangeMinedPerDayFirsTime = false;
                } else {
                    getView().showRangeMined(coinMiningViewModel, true);
                }
            } else {
                getView().showRangeMined(coinMiningViewModel, false);
            }
        } catch (Exception e) {}
    }

    private void initializeCountDown(com.raddarapp.domain.model.CoinMining coinMined) {
        try {
            boolean maximumRangeMinedPerDay = coinMined.getRangeMined() == MAXIMUM_RANGE_MINED_PER_DAY;
            hideLoading();
            getView().enableViews();

            if (maximumRangeMinedPerDay) {
                maximumRangeMinedPerDayFirsTime = false;
            }

            CoinMiningViewModel coinMiningViewModel = mapper.map(coinMined);
            getView().initializeCountDown(coinMiningViewModel);
        } catch (Exception e) {}
    }

    private void showErrorLocal(ErrorLocalCode code) {
        switch (code) {
            case MINIMUM_RANGE_MINED:
                getView().showErrorLocalMinimumRangeMined();
                break;
        }

        hideLoading();
        getView().enableViews();
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public void setExchangeSent(boolean exchangeSent) {
        this.exchangeSent = exchangeSent;
    }

    public interface View extends BasePresenterRefreshWithLoading.View, ValidationCoinMiningView {
        void disableViews();

        void enableViews();

        void showUserProfilePreferences(MyUserProfileViewModel myUserProfileViewModel);

        void showRangeMined(CoinMiningViewModel coinMiningViewModel, boolean maximumRangeMinedPerDay);

        void showMaximumRangeMinedPerDay();

        void initializeCountDown(CoinMiningViewModel coinMiningViewModel);
    }
}
