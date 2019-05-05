package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.PromoCodeRepository;

import javax.inject.Inject;

public class PromoCode extends RosieUseCase {

    public static final String USE_CASE_PROMO_CODE = "promoCode";

    private PromoCodeRepository promoCodeRepository;

    @Inject
    public PromoCode(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    @UseCase(name = USE_CASE_PROMO_CODE)
    public void promoCode(String promoCode) throws Exception {
        boolean promoCodeExchanged = promoCodeRepository.promoCode(promoCode);
        notifySuccess(promoCodeExchanged);
    }
}
