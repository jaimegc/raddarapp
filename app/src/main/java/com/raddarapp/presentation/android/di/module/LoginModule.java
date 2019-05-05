package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.LoginActivity;
import com.raddarapp.presentation.android.fragment.LoginFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            LoginActivity.class, LoginFragment.class
    })
public class LoginModule {
}