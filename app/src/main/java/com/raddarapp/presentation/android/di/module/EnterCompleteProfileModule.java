package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.EnterCompleteProfileActivity;
import com.raddarapp.presentation.android.fragment.EnterCompleteProfileFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            EnterCompleteProfileActivity.class, EnterCompleteProfileFragment.class
    })
public class EnterCompleteProfileModule {
}