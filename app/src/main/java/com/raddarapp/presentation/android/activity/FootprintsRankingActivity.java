package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.FootprintsRankingModule;
import com.raddarapp.presentation.android.fragment.FootprintsRankingFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FootprintsRankingActivity extends BaseNormalActivity {

    private static final int REQUEST_USER_PROFILE_CHANGES = 202;

    private FootprintsRankingFragment footprintsRankingFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_footprints_ranking;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new FootprintsRankingModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        footprintsRankingFragment =
                (FootprintsRankingFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (footprintsRankingFragment == null) {
            footprintsRankingFragment = new FootprintsRankingFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    footprintsRankingFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, FootprintsRankingActivity.class);
        context.startActivity(intent);
    }

    public static void openNewTask(Context context) {
        Intent intent = new Intent(context, FootprintsRankingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

        if (resultCode == REQUEST_USER_PROFILE_CHANGES) {
            if (footprintsRankingFragment != null && footprintsRankingFragment.isAdded() && data != null) {
                footprintsRankingFragment.onActivityResult(requestCode, resultCode, data);
            }
        }

        /*if (resultCode == REQUEST_NEW_COMMENTS_FROM_FOOTPRINT_MAIN_DETAILS) {
            if (footprintMainFragment != null && footprintMainFragment.isAdded() && data != null) {
                footprintMainFragment.onActivityResult(requestCode, resultCode, data);
            }
        } else if (resultCode == REQUEST_NEW_COMMENTS_FROM_MY_FOOTPRINT_DETAILS) {
            if (myProfileFragment != null && myProfileFragment.isAdded() && data != null) {
                myProfileFragment.onActivityResult(requestCode, resultCode, data);
            }
        } else if (resultCode == REQUEST_USER_PROFILE_CHANGES) {
            if (footprintMainFragment != null && footprintMainFragment.isAdded() && data != null) {
                footprintMainFragment.onActivityResult(requestCode, resultCode, data);
            }
        }*/
    }
}
