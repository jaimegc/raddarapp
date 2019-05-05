package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.CoinMiningActivity;
import com.raddarapp.presentation.android.fragment.CoinMiningFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            CoinMiningActivity.class, CoinMiningFragment.class
    })
public class CoinMiningModule {
}