package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.MyFootprintsCollectionActivity;
import com.raddarapp.presentation.android.fragment.MyFootprintsCollectionFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            MyFootprintsCollectionActivity.class, MyFootprintsCollectionFragment.class
    })
public class MyFootprintsCollectionModule {
}