package com.raddarapp.presentation.android.activity.base;

import com.karumi.rosie.view.RosieActivity;

import butterknife.ButterKnife;

public abstract class BaseNormalActivity extends RosieActivity {
    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();
        ButterKnife.bind(this);
    }
}

