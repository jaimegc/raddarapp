package com.raddarapp.presentation.android.fragment.base;

import android.view.View;

import com.karumi.rosie.view.RosieFragment;
import butterknife.ButterKnife;

public abstract class BaseSwipeFragment extends RosieFragment {

    protected static final String IS_SWIPE_REFRESHING = "isSwipeRefresh";

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        ButterKnife.bind(this, view);
    }
}
