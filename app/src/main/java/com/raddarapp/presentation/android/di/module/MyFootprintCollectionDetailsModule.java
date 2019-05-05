package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.MyFootprintCollectionDetailsActivity;
import com.raddarapp.presentation.android.fragment.MyFootprintCollectionDetailsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            MyFootprintCollectionDetailsActivity.class, MyFootprintCollectionDetailsFragment.class
    })
public class MyFootprintCollectionDetailsModule {
}