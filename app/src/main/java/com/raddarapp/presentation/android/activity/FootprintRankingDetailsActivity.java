package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.FootprintRankingDetailsModule;
import com.raddarapp.presentation.android.fragment.FootprintRankingDetailsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FootprintRankingDetailsActivity extends BaseNormalActivity {

    private static final String FOOTPRINT_KEY_EXTRA = "FootprintDetails.FootprintKey";
    private static final String FOOTPRINT_COMMENTS_EXTRA = "FootprintDetails.FootprintComments";
    private static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final String POSITION_EXTRA = "FootprintDetails.PositionExtra";
    private static final int REQUEST_COMMENTS = 200;

    private FootprintRankingDetailsFragment footprintRankingDetailsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_footprint_ranking_details;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new FootprintRankingDetailsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        String footprintMainKey = extras.getString(FOOTPRINT_KEY_EXTRA);
        long comments = extras.getLong(FOOTPRINT_COMMENTS_EXTRA);
        int position = extras.getInt(POSITION_EXTRA);

        initializeFragment(footprintMainKey, comments, position);
    }

    private void initializeFragment(String footprintRankingKey, long comments, int position) {
        footprintRankingDetailsFragment =
                (FootprintRankingDetailsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (footprintRankingDetailsFragment == null) {
            footprintRankingDetailsFragment = new FootprintRankingDetailsFragment().newInstance(footprintRankingKey, comments, position);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    footprintRankingDetailsFragment, R.id.content_frame);
        }
    }

    public static void open(Activity activity, String footprintMainKey, long comments, int position) {
        Intent intent = new Intent(activity, FootprintRankingDetailsActivity.class);
        intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintMainKey);
        intent.putExtra(FOOTPRINT_COMMENTS_EXTRA, comments);
        intent.putExtra(POSITION_EXTRA, position);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (footprintRankingDetailsFragment != null && footprintRankingDetailsFragment.isAdded()) {
            Intent data = new Intent();
            data.putExtra(COMMENTS_EXTRA, footprintRankingDetailsFragment.getTotalComments());
            setResult(REQUEST_COMMENTS, data);
            footprintRankingDetailsFragment.deleteCache();
        }

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (footprintRankingDetailsFragment != null && footprintRankingDetailsFragment.isAdded() && data != null) {
            footprintRankingDetailsFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
