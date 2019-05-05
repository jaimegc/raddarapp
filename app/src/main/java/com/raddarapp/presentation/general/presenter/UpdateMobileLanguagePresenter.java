package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.builder.UpdateMobileLanguageBuilder;
import com.raddarapp.domain.usecase.UpdateMobileLanguage;
import com.raddarapp.presentation.general.presenter.base.BasePresenterNormal;

import javax.inject.Inject;

public class UpdateMobileLanguagePresenter extends BasePresenterNormal<UpdateMobileLanguagePresenter.View> {

    private final UpdateMobileLanguage updateMobileLanguage;

    @Inject
    public UpdateMobileLanguagePresenter(UseCaseHandler useCaseHandler, UpdateMobileLanguage updateMobileLanguage) {
        super(useCaseHandler);
        this.updateMobileLanguage = updateMobileLanguage;
    }

    public void updateMobileLanguage(String userKey, String mobileLanguage) {

        com.raddarapp.domain.model.UpdateMobileLanguage updateMobileLanguage = new UpdateMobileLanguageBuilder()
                .withMobileLanguage(mobileLanguage)
                .build();

        createUseCaseCall(this.updateMobileLanguage)
                .args(userKey, updateMobileLanguage)
                .useCaseName(this.updateMobileLanguage.USE_CASE_UPDATE_MOBILE_LANGUAGE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updatedLanguage(String updatedLanguage) {
                        try {
                            getView().showLanguageUpdated(updatedLanguage);
                        } catch (Exception e) {}
                    }
                })
                .onError(error -> {
                    return false;
                }).execute();
    }

    public interface View extends BasePresenterNormal.View {
        void showLanguageUpdated(String updatedLanguage);
    }
}
