package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.MyProfileSettingsActivity;
import com.raddarapp.presentation.android.fragment.MyProfileSettingsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            MyProfileSettingsActivity.class, MyProfileSettingsFragment.class
    })
public class MyProfileSettingsModule {
}