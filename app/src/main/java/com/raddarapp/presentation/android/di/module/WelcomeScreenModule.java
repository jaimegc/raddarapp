package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.WelcomeScreenActivity;
import com.raddarapp.presentation.android.fragment.WelcomeScreenFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            WelcomeScreenActivity.class, WelcomeScreenFragment.class
    })
public class WelcomeScreenModule {
}