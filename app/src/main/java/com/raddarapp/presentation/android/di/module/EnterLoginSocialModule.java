package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.EnterLoginSocialActivity;
import com.raddarapp.presentation.android.fragment.EnterLoginSocialFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            EnterLoginSocialActivity.class, EnterLoginSocialFragment.class
    })
public class EnterLoginSocialModule {
}