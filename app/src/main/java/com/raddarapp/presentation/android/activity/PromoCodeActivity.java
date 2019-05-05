package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.PromoCodeModule;
import com.raddarapp.presentation.android.fragment.PromoCodeFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PromoCodeActivity extends BaseNormalActivity {

    private static final int REQUEST_PROMO_CODE = 333;
    private static final String REQUEST_PROMO_EXTRA = "PromoCode.Extra";
    private PromoCodeFragment promoCodeFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promo_code;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new PromoCodeModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        promoCodeFragment =
                (PromoCodeFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (promoCodeFragment == null) {
            promoCodeFragment = new PromoCodeFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    promoCodeFragment, R.id.content_frame);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static void open(Activity activity) {
        Intent intent = new Intent(activity, PromoCodeActivity.class);
        activity.startActivityForResult(intent, REQUEST_PROMO_CODE);
    }

    public void setResultPromoCodeExchanged() {
        Intent data = new Intent();
        data.putExtra(REQUEST_PROMO_EXTRA, true);
        setResult(REQUEST_PROMO_CODE, data);
        finish();
    }
}
