package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.android.fragment.FootprintMainFragment;
import com.raddarapp.presentation.android.fragment.MyFriendsFragment;
import com.raddarapp.presentation.android.fragment.MyUsersRankingFragment;
import com.raddarapp.presentation.android.fragment.MyProfileFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            FootprintMainActivity.class, FootprintMainFragment.class,
            MyUsersRankingFragment.class, MyFriendsFragment.class, MyProfileFragment.class
    })
public class FootprintMainModule {
}