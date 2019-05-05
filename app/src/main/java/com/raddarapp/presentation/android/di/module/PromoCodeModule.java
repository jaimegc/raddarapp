package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.PromoCodeActivity;
import com.raddarapp.presentation.android.fragment.PromoCodeFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            PromoCodeActivity.class, PromoCodeFragment.class
    })
public class PromoCodeModule {
}