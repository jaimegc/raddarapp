package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.WelcomeInAppScreenActivity;
import com.raddarapp.presentation.android.fragment.WelcomeInAppScreenFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            WelcomeInAppScreenActivity.class, WelcomeInAppScreenFragment.class
    })
public class WelcomeInAppScreenModule {
}