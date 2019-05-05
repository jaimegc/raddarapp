package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.MyUsersRankingAllActivity;
import com.raddarapp.presentation.android.fragment.MyUsersRankingAllFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            MyUsersRankingAllActivity.class, MyUsersRankingAllFragment.class
    })
public class MyUsersRankingAllModule {
}