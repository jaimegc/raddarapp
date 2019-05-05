package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.UserFootprintDetailsModule;
import com.raddarapp.presentation.android.fragment.UserFootprintDetailsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserFootprintDetailsActivity extends BaseNormalActivity {

    private static final String FOOTPRINT_KEY_EXTRA = "UserProfile.FootprintKey";
    private static final String FOOTPRINT_COMMENTS_EXTRA = "FootprintDetails.FootprintComments";
    private static final String INDEX_SCREEN_EXTRA = "UserProfile.IndexScreen";
    private static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final int REQUEST_COMMENTS = 200;

    private UserFootprintDetailsFragment userFootprintDetailsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_footprint_details;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new UserFootprintDetailsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        String footprintKey = extras.getString(FOOTPRINT_KEY_EXTRA);
        int indexScreen = extras.getInt(INDEX_SCREEN_EXTRA);
        long comments = extras.getLong(FOOTPRINT_COMMENTS_EXTRA);

        initializeFragment(footprintKey, indexScreen, comments);
    }

    private void initializeFragment(String footprintKey, int indexScreen, long comments) {
        userFootprintDetailsFragment =
                (UserFootprintDetailsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (userFootprintDetailsFragment == null) {
            userFootprintDetailsFragment = new UserFootprintDetailsFragment().newInstance(footprintKey, indexScreen);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    userFootprintDetailsFragment, R.id.content_frame);
        }
    }

    public static void open(Context context, int indexScreen, String footprintKey, long comments) {
        Intent intent = new Intent(context, UserFootprintDetailsActivity.class);
        intent.putExtra(INDEX_SCREEN_EXTRA, indexScreen);
        intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(FOOTPRINT_COMMENTS_EXTRA, comments);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (userFootprintDetailsFragment != null && userFootprintDetailsFragment.isAdded()) {
            Intent data = new Intent();
            data.putExtra(COMMENTS_EXTRA, userFootprintDetailsFragment.getTotalComments());
            setResult(REQUEST_COMMENTS, data);
            userFootprintDetailsFragment.deleteCache();
        }

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (userFootprintDetailsFragment != null && userFootprintDetailsFragment.isAdded() && data != null) {
            userFootprintDetailsFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
