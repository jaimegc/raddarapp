package com.raddarapp.presentation.android.fragment;

import android.view.View;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.SplashActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;

import butterknife.OnClick;

public class CrashFragment extends BaseNormalFragment {

    public static CrashFragment newInstance() {
        CrashFragment fragment = new CrashFragment();
        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_crash;
    }

    @OnClick(R.id.restart_app)
    public void onRestartAppClicked() {
        SplashActivity.open(getActivity());
    }
}
