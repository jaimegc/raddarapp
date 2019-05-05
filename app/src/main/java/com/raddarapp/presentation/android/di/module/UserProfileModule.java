package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.UserProfileActivity;
import com.raddarapp.presentation.android.fragment.UserProfileFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            UserProfileActivity.class, UserProfileFragment.class
    })
public class UserProfileModule {
}