package com.raddarapp.presentation.android.di.module;

import com.raddarapp.presentation.android.activity.CommentsActivity;
import com.raddarapp.presentation.android.fragment.CommentsFragment;

import dagger.Module;

@Module(
    library = true,
    complete = false,
    injects = {
            CommentsActivity.class, CommentsFragment.class
    })
public class CommentsModule {
}