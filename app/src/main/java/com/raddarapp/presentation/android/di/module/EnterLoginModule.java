package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.EnterLoginActivity;
import com.raddarapp.presentation.android.fragment.EnterLoginFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            EnterLoginActivity.class, EnterLoginFragment.class
    })
public class EnterLoginModule {
}