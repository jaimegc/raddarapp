package com.raddarapp.presentation.android.activity.base;

import com.karumi.rosie.view.RosieActivity;
import com.raddarapp.presentation.general.presenter.base.contract.BasePresenterErrorsContract;

import butterknife.ButterKnife;

public abstract class BaseNormalWithErrorsActivity extends RosieActivity implements BasePresenterErrorsContract {
    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();
        ButterKnife.bind(this);
    }
}

