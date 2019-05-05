package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.FootprintMainDetailsActivity;
import com.raddarapp.presentation.android.fragment.FootprintMainDetailsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            FootprintMainDetailsActivity.class, FootprintMainDetailsFragment.class
    })
public class FootprintMainDetailsModule {
}