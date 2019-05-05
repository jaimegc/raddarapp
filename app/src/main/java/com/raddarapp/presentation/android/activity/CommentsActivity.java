package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.CommentsModule;
import com.raddarapp.presentation.android.fragment.CommentsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CommentsActivity extends BaseNormalActivity {

    private static final String COMMENTS_FOOTPRINT_KEY_EXTRA = "CommentsFootprintDetails.FootprintKey";
    private static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final String INDEX_SCREEN_EXTRA = "FootprintDetails.IndexScreen";
    private static final int INDEX_COMMENTS_FOOTPRINT_MAIN_DETAILS = 0;
    private static final int INDEX_COMMENTS_MY_FOOTPRINT_DETAILS = 1;
    private static final int INDEX_COMMENTS_USER_FOOTPRINT_DETAILS = 2;
    private static final int INDEX_COMMENTS_MY_FOOTPRINT_COLLECTION_DETAILS = 3;
    private static final int INDEX_COMMENTS_FOOTPRINT_RANKING_DETAILS = 4;
    private static final int REQUEST_COMMENTS = 200;

    private CommentsFragment commentsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comments;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new CommentsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        String footprintMainKey = extras.getString(COMMENTS_FOOTPRINT_KEY_EXTRA);
        int indexScreen = extras.getInt(INDEX_SCREEN_EXTRA);

        initializeFragment(footprintMainKey, indexScreen);
    }

    private void initializeFragment(String footprintMainKey, int indexScreen) {
        commentsFragment =
                (CommentsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (commentsFragment == null) {
            commentsFragment = new CommentsFragment().newInstance(footprintMainKey, indexScreen);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    commentsFragment, R.id.content_frame);
        }
    }

    public static void openFromFootprintMainDetails(Activity activity, String footprintKey) {
        Intent intent = new Intent(activity, CommentsActivity.class);
        intent.putExtra(COMMENTS_FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_COMMENTS_FOOTPRINT_MAIN_DETAILS);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    public static void openFromMyFootprintDetails(Activity activity, String footprintKey) {
        Intent intent = new Intent(activity, CommentsActivity.class);
        intent.putExtra(COMMENTS_FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_COMMENTS_MY_FOOTPRINT_DETAILS);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    public static void openFromUserFootprintDetails(Activity activity, String footprintKey) {
        Intent intent = new Intent(activity, CommentsActivity.class);
        intent.putExtra(COMMENTS_FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_COMMENTS_USER_FOOTPRINT_DETAILS);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    public static void openFromMyFootprintCollectionDetails(Activity activity, String footprintKey) {
        Intent intent = new Intent(activity, CommentsActivity.class);
        intent.putExtra(COMMENTS_FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_COMMENTS_MY_FOOTPRINT_COLLECTION_DETAILS);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    public static void openFromFootprintRankingDetails(Activity activity, String footprintKey) {
        Intent intent = new Intent(activity, CommentsActivity.class);
        intent.putExtra(COMMENTS_FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_COMMENTS_FOOTPRINT_RANKING_DETAILS);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (commentsFragment != null && commentsFragment.isAdded()) {
            Intent data = new Intent();
            data.putExtra(COMMENTS_EXTRA, commentsFragment.getTotalComments());
            setResult(REQUEST_COMMENTS, data);
        }

        finish();
    }
}
