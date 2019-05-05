package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.MyFootprintsAllActivity;
import com.raddarapp.presentation.android.fragment.MyFootprintsAllFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            MyFootprintsAllActivity.class, MyFootprintsAllFragment.class
    })
public class MyFootprintsAllModule {
}