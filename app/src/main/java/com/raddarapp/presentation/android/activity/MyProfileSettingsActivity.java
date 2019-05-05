package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.MyProfileSettingsModule;
import com.raddarapp.presentation.android.fragment.MyProfileSettingsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyProfileSettingsActivity extends BaseNormalActivity {

    private MyProfileSettingsFragment myProfileSettingsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_profile_settings;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new MyProfileSettingsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        myProfileSettingsFragment =
                (MyProfileSettingsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (myProfileSettingsFragment == null) {
            myProfileSettingsFragment = new MyProfileSettingsFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    myProfileSettingsFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, MyProfileSettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (myProfileSettingsFragment != null && myProfileSettingsFragment.isAdded()) {
            myProfileSettingsFragment.getImagePicker().onActivityResult(this, requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (myProfileSettingsFragment != null && myProfileSettingsFragment.isAdded()) {
            myProfileSettingsFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
