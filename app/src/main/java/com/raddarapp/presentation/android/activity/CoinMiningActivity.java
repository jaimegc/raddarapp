package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.CoinMiningModule;
import com.raddarapp.presentation.android.fragment.CoinMiningFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;
import com.raddarapp.presentation.general.presenter.LogoutEnterLoginPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CoinMiningActivity extends BaseNormalActivity implements LogoutEnterLoginPresenter.View {

    private CoinMiningFragment coinMiningFragment;

    @Inject
    LogoutEnterLoginPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin_mining;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new CoinMiningModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        coinMiningFragment =
                (CoinMiningFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (coinMiningFragment == null) {
            coinMiningFragment = new CoinMiningFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    coinMiningFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, CoinMiningActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (coinMiningFragment != null && coinMiningFragment.isAdded()) {
            coinMiningFragment.saveRangeMinedLocalAccumulated();
        }
        finish();
    }
}
