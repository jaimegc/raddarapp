package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.MyFootprintDetailsActivity;
import com.raddarapp.presentation.android.fragment.MyFootprintDetailsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            MyFootprintDetailsActivity.class, MyFootprintDetailsFragment.class
    })
public class MyFootprintDetailsModule {
}