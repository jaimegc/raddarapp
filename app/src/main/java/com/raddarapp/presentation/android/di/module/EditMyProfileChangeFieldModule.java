package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.EditMyProfileChangeFieldActivity;
import com.raddarapp.presentation.android.fragment.EditMyProfileChangeFieldFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            EditMyProfileChangeFieldActivity.class, EditMyProfileChangeFieldFragment.class
    })
public class EditMyProfileChangeFieldModule {
}