package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.UserFootprintDetailsActivity;
import com.raddarapp.presentation.android.fragment.UserFootprintDetailsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            UserFootprintDetailsActivity.class, UserFootprintDetailsFragment.class
    })
public class UserFootprintDetailsModule {
}