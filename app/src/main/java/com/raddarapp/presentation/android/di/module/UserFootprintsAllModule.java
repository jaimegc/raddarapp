package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.UserFootprintsAllActivity;
import com.raddarapp.presentation.android.fragment.UserFootprintsAllFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            UserFootprintsAllActivity.class, UserFootprintsAllFragment.class
    })
public class UserFootprintsAllModule {
}