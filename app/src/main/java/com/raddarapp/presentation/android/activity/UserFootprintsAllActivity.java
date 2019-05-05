package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.UserFootprintsAllModule;
import com.raddarapp.presentation.android.fragment.UserFootprintsAllFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserFootprintsAllActivity extends BaseNormalActivity {

    private static final String USER_KEY_EXTRA = "UserProfile.UserKey";
    private static final String FOOTPRINT_KEY_EXTRA = "UserProfile.FootprintKey";
    private static final String INDEX_SCREEN_EXTRA = "UserProfile.IndexScreen";

    private UserFootprintsAllFragment userFootprintsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_footprints_all;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new UserFootprintsAllModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        String footprintKey = extras.getString(FOOTPRINT_KEY_EXTRA);
        int indexScreen = extras.getInt(INDEX_SCREEN_EXTRA);
        String userKey = extras.getString(USER_KEY_EXTRA);

        initializeFragment(footprintKey, userKey, indexScreen);
    }

    private void initializeFragment(String footprintKey, String userKey, int indexScreen) {
        userFootprintsFragment =
                (UserFootprintsAllFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (userFootprintsFragment == null) {
            userFootprintsFragment = new UserFootprintsAllFragment().newInstance(footprintKey, userKey, indexScreen);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    userFootprintsFragment, R.id.content_frame);
        }
    }

    public static void open(Context context, int indexScreen, String userKey, String footprintKey) {
        Intent intent = new Intent(context, UserFootprintsAllActivity.class);
        intent.putExtra(INDEX_SCREEN_EXTRA, indexScreen);
        intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintKey);
        intent.putExtra(USER_KEY_EXTRA, userKey);
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
}
