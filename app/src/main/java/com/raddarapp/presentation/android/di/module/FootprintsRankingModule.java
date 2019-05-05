package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.FootprintsRankingActivity;
import com.raddarapp.presentation.android.fragment.FootprintsRankingFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            FootprintsRankingActivity.class, FootprintsRankingFragment.class
    })
public class FootprintsRankingModule {
}