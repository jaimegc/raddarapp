package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.CrashModule;
import com.raddarapp.presentation.android.fragment.CrashFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CrashActivity extends BaseNormalActivity {

    private CrashFragment crashFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_crash;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new CrashModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        crashFragment =
                (CrashFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (crashFragment == null) {
            crashFragment = new CrashFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    crashFragment, R.id.content_frame);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
