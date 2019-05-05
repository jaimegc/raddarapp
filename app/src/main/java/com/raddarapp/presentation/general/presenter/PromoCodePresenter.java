package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.usecase.PromoCode;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.validation.ValidationPromoCode;
import com.raddarapp.presentation.general.validation.view.ValidationPromoCodeView;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class PromoCodePresenter extends BasePresenterRefreshWithLoading<PromoCodePresenter.View> {

    private final PromoCode promoCode;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final ValidationPromoCode validationPromoCode;
    private boolean promoCodeAlreadyExchanged = false;

    @Inject
    public PromoCodePresenter(UseCaseHandler useCaseHandler, PromoCode promoCode,
            MyUserProfileToUserProfileViewModelMapper mapperUserProfile, ValidationPromoCode validationPromoCode) {
        super(useCaseHandler);
        this.promoCode = promoCode;
        this.mapperUserProfile = mapperUserProfile;
        this.validationPromoCode = validationPromoCode;
    }

    @Override
    public void update() {
        super.update();

        if (promoCodeAlreadyExchanged) {
            showPromoCodeExchanged();
        }
    }

    public void promoCode(String promoCode) {
        showLoading();

        ErrorLocalCode code = validationPromoCode.validatePromoCode(promoCode);

        if (code == ErrorLocalCode.SUCCESS) {
            createUseCaseCall(this.promoCode)
                    .args(promoCode)
                    .useCaseName(PromoCode.USE_CASE_PROMO_CODE)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void promoCode(boolean promoCodeExchanged) {
                            if (promoCodeExchanged) {
                                promoCodeAlreadyExchanged = true;
                                showPromoCodeExchanged();
                            } else {
                                showError();
                            }
                        }

                    })
                    .onError(error -> {
                        try {
                            getView().hideLoading();
                        } catch (Exception e) {}
                        return false;
                    }).execute();
        } else {
            showErrorLocal(code);
        }
    }

    private void showPromoCodeExchanged() {
        try {
            hideLoading();
            getView().showPromoCodeExchanged();
        } catch (Exception e) {}
    }

    private void showError() {
        try {
            getView().hideLoading();
            getView().showPromoCodeExchangedError();
        } catch (Exception e) {}
    }

    private void showErrorLocal(ErrorLocalCode code) {
        try {

            switch (code) {
                case EMPTY_PROMO_CODE:
                    getView().showErrorLocalPromoCodeEmpty();
                    break;
            }

            hideLoading();
        } catch (Exception e) {}
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View, ValidationPromoCodeView {
        void showPromoCodeExchanged();

        void showPromoCodeExchangedError();
    }
}
