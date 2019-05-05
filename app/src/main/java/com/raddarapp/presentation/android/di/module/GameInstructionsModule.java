package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.GameInstructionsActivity;
import com.raddarapp.presentation.android.fragment.GameInstructionsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            GameInstructionsActivity.class, GameInstructionsFragment.class
    })
public class GameInstructionsModule {
}