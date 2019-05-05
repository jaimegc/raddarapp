package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.model.MyPromoCode;
import com.raddarapp.domain.usecase.GeMyPromoCode;

import javax.inject.Inject;

public class MyPromoCodePresenter extends RosiePresenter<MyPromoCodePresenter.View> {

    private final GeMyPromoCode getMyPromoCode;

    @Inject
    public MyPromoCodePresenter(UseCaseHandler useCaseHandler, GeMyPromoCode getMyPromoCode) {
        super(useCaseHandler);
        this.getMyPromoCode = getMyPromoCode;
    }

    public void getMyPromoCode(String promoCodeKey) {
        createUseCaseCall(getMyPromoCode)
                .args(promoCodeKey)
                .useCaseName(GeMyPromoCode.USE_CASE_GET_MY_PROMO_CODE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyPromoCode(MyPromoCode myPromoCode) {
                        if (myPromoCode != null) {
                            saveMyPromoCode(myPromoCode.getCode());
                        }
                    }

                })
                .onError(error -> false).execute();
    }

    private void saveMyPromoCode(String myPromoCode) {
        try {
            getView().saveMyPromoCode(myPromoCode);
        } catch (Exception e) {}
    }

    public interface View extends RosiePresenter.View {
        void saveMyPromoCode(String myPromoCode);
    }
}
