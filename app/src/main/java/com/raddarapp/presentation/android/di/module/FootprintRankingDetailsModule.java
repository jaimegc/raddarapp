package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.FootprintRankingDetailsActivity;
import com.raddarapp.presentation.android.fragment.FootprintRankingDetailsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            FootprintRankingDetailsActivity.class, FootprintRankingDetailsFragment.class
    })
public class FootprintRankingDetailsModule {
}