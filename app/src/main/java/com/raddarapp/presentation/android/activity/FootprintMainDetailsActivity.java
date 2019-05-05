package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.FootprintMainDetailsModule;
import com.raddarapp.presentation.android.fragment.FootprintMainDetailsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FootprintMainDetailsActivity extends BaseNormalActivity {

    private static final String FOOTPRINT_KEY_EXTRA = "FootprintDetails.FootprintKey";
    private static final String FOOTPRINT_COMMENTS_EXTRA = "FootprintDetails.FootprintComments";
    private static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final int REQUEST_NEW_COMMENTS = 200;

    private FootprintMainDetailsFragment footprintMainDetailsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_footprint_main_details;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new FootprintMainDetailsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        String footprintMainKey = extras.getString(FOOTPRINT_KEY_EXTRA);
        long comments = extras.getLong(FOOTPRINT_COMMENTS_EXTRA);

        initializeFragment(footprintMainKey, comments);
    }

    private void initializeFragment(String footprintMainKey, long comments) {
        footprintMainDetailsFragment =
                (FootprintMainDetailsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (footprintMainDetailsFragment == null) {
            footprintMainDetailsFragment = new FootprintMainDetailsFragment().newInstance(footprintMainKey, comments);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    footprintMainDetailsFragment, R.id.content_frame);
        }
    }

    public static void open(Activity activity, String footprintMainKey, long comments) {
        Intent intent = new Intent(activity, FootprintMainDetailsActivity.class);
        intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintMainKey);
        intent.putExtra(FOOTPRINT_COMMENTS_EXTRA, comments);
        activity.startActivityForResult(intent, REQUEST_NEW_COMMENTS);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (footprintMainDetailsFragment != null && footprintMainDetailsFragment.isAdded()) {
            Intent data = new Intent();
            data.putExtra(COMMENTS_EXTRA, footprintMainDetailsFragment.getTotalComments());
            setResult(REQUEST_NEW_COMMENTS, data);

            footprintMainDetailsFragment.deleteCache();
        }

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (footprintMainDetailsFragment != null && footprintMainDetailsFragment.isAdded() && data != null) {
            footprintMainDetailsFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
