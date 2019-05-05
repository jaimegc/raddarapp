package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.CreateFootprintActivity;
import com.raddarapp.presentation.android.fragment.CreateFootprintFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            CreateFootprintActivity.class, CreateFootprintFragment.class
    })
public class CreateFootprintModule {
}