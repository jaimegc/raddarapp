package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.UserProfileModule;
import com.raddarapp.presentation.android.fragment.UserProfileFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserProfileActivity extends BaseNormalActivity {

    private static final String USER_KEY_EXTRA = "UserProfile.UserKey";
    private static final String FOOTPRINT_KEY_EXTRA = "UserProfile.FootprintKey";
    private static final String INDEX_SCREEN_EXTRA = "UserProfile.IndexScreen";
    protected static final String NEW_COMPLIMENTS_EXTRA = "UserProfile.NewComplimentsExtra";
    private static final int REQUEST_USER_PROFILE_CHANGES = 202;
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

    private static final int INDEX_FOOTPRINT_MAIN = 0;
    private static final int INDEX_MY_USERS_RANKING = 1;
    private static final int INDEX_MY_FOOTPRINT_COLLECTION = 2;
    private static final int INDEX_FOOTPRINT_RANKING = 3;

    private UserProfileFragment userProfileFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_profile;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new UserProfileModule());
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
        userProfileFragment =
                (UserProfileFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (userProfileFragment == null) {
            userProfileFragment = new UserProfileFragment().newInstance(footprintKey, userKey, indexScreen);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    userProfileFragment, R.id.content_frame);
        }
    }

    public static void open(Activity activity, String userKey) {
        Intent intent = new Intent(activity, UserProfileActivity.class);
        intent.putExtra(USER_KEY_EXTRA, userKey);
        activity.startActivityForResult(intent, REQUEST_USER_PROFILE_CHANGES);
    }

    public static void openFromFootprintMain(Activity activity, String footprintMainKey, String userKey) {
        open(activity, footprintMainKey, userKey, INDEX_FOOTPRINT_MAIN);
    }

    public static void openFromMyUsersRanking(Activity activity, String userKey, Fragment view) {
        if (!new PreferencesUtils(activity).getMyUserKey().equals(userKey)) {
            open(activity, userKey, INDEX_MY_USERS_RANKING);
        } else {
            showMessageMyUserProfile(activity, view);
        }
    }

    public static void openFromMyFootprintCollection(Activity activity, String myFootprintCollectionKey, String userKey) {
        if (!new PreferencesUtils(activity).getMyUserKey().equals(userKey)) {
            Intent intent = new Intent(activity, UserProfileActivity.class);
            intent.putExtra(FOOTPRINT_KEY_EXTRA, myFootprintCollectionKey);
            intent.putExtra(USER_KEY_EXTRA, userKey);
            intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_MY_FOOTPRINT_COLLECTION);
            activity.startActivityForResult(intent, REQUEST_USER_PROFILE_CHANGES);
        } else {
            showMessageMyUserProfile(activity, null);
        }
    }

    public static void openFromFootprintRanking(Activity activity, String footprintRankingKey, String userKey, Fragment view) {
        if (!new PreferencesUtils(activity).getMyUserKey().equals(userKey)) {
            Intent intent = new Intent(activity, UserProfileActivity.class);
            intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintRankingKey);
            intent.putExtra(USER_KEY_EXTRA, userKey);
            intent.putExtra(INDEX_SCREEN_EXTRA, INDEX_FOOTPRINT_RANKING);
            activity.startActivityForResult(intent, REQUEST_USER_PROFILE_CHANGES);
        } else {
            showMessageMyUserProfile(activity, view);
        }
    }

    private static void open(Activity activity, String footprintMainKey, String userKey, int indexScreen) {
        if (!new PreferencesUtils(activity).getMyUserKey().equals(userKey)) {
            Intent intent = new Intent(activity, UserProfileActivity.class);
            intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintMainKey);
            intent.putExtra(USER_KEY_EXTRA, userKey);
            intent.putExtra(INDEX_SCREEN_EXTRA, indexScreen);
            activity.startActivityForResult(intent, REQUEST_USER_PROFILE_CHANGES);
        } else {
            showMessageMyUserProfile(activity, null);
        }
    }

    private static void open(Activity activity, String userKey, int indexScreen) {
        if (!new PreferencesUtils(activity).getMyUserKey().equals(userKey)) {
            Intent intent = new Intent(activity, UserProfileActivity.class);
            intent.putExtra(USER_KEY_EXTRA, userKey);
            intent.putExtra(INDEX_SCREEN_EXTRA, indexScreen);
            activity.startActivityForResult(intent, REQUEST_USER_PROFILE_CHANGES);
        } else {
            showMessageMyUserProfile(activity, null);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (userProfileFragment != null && userProfileFragment.isAdded()) {
            if (userProfileFragment.getTotalNewCompliments() > 0) {
                Intent data = new Intent();
                data.putExtra(NEW_COMPLIMENTS_EXTRA, userProfileFragment.getTotalNewCompliments());
                setResult(REQUEST_USER_PROFILE_CHANGES, data);
            }
        }

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (userProfileFragment != null && userProfileFragment.isAdded() && data != null) {
            userProfileFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private static void showMessageMyUserProfile(Activity activity, Fragment view) {
        Snackbar snackbarError;
        FontUtils fontUtils = new FontUtils();
        snackbarError = Snackbar.make(view == null ? activity.getCurrentFocus() : view.getView(), activity.getString(R.string.profile_sms_my_own), Snackbar.LENGTH_SHORT);
        View snackBarView = snackbarError.getView();
        snackBarView.setBackgroundColor(Color.WHITE);
        TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        fontUtils.applyFont(activity, FONT_NAME, message);
        message.setTextColor(Color.BLACK);
        snackbarError.show();
    }
}
