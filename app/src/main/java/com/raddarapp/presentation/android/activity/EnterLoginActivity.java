package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.karumi.rosie.view.Presenter;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.CreateFootprintModule;
import com.raddarapp.presentation.android.di.module.EnterLoginModule;
import com.raddarapp.presentation.android.fragment.EnterLoginFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;
import com.raddarapp.presentation.general.presenter.LogoutEnterLoginPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterLoginActivity extends BaseNormalActivity implements LogoutEnterLoginPresenter.View {

    private EnterLoginFragment enterLoginFragment;

    @Inject
    LogoutEnterLoginPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_login;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new EnterLoginModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        enterLoginFragment =
                (EnterLoginFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (enterLoginFragment == null) {
            enterLoginFragment = new EnterLoginFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    enterLoginFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, EnterLoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        presenter.logout();
        finish();
    }
}
