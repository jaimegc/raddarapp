package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyPromoCodeRepository;
import com.raddarapp.domain.model.MyPromoCode;

import javax.inject.Inject;

public class GeMyPromoCode extends RosieUseCase {

    public static final String USE_CASE_GET_MY_PROMO_CODE = "getMyPromoCode";

    private MyPromoCodeRepository myPromoCodeRepository;

    @Inject
    public GeMyPromoCode(MyPromoCodeRepository myPromoCodeRepository) {
        this.myPromoCodeRepository = myPromoCodeRepository;
    }

    @UseCase(name = USE_CASE_GET_MY_PROMO_CODE)
    public void geyMyPromoCode(String promoCodeKey) throws Exception {
        MyPromoCode myPromoCode = myPromoCodeRepository.getMyPromoCode(promoCodeKey);
        notifySuccess(myPromoCode);
    }
}
