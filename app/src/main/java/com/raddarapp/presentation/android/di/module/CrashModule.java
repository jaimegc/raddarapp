package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.CrashActivity;
import com.raddarapp.presentation.android.fragment.CrashFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            CrashActivity.class, CrashFragment.class
    })
public class CrashModule {
}