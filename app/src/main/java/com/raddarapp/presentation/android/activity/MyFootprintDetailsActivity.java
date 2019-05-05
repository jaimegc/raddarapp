package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.karumi.rosie.view.Presenter;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.MyFootprintDetailsModule;
import com.raddarapp.presentation.android.fragment.MyFootprintDetailsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;
import com.raddarapp.presentation.general.presenter.MyFootprintDetailsNotificationsPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyFootprintDetailsActivity extends BaseNormalActivity implements MyFootprintDetailsNotificationsPresenter.View {

    private static final String FOOTPRINT_KEY_EXTRA = "FootprintDetails.FootprintKey";
    private static final String FOOTPRINT_COMMENTS_EXTRA = "FootprintDetails.FootprintComments";
    private static final String FOOTPRINT_FROM_NOTIFICATION_EXTRA = "FootprintDetails.FootprintFromNotification";
    private static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final String COMMENTS_SCREEN_LOADED_FROM_NOTIFICATION_EXTRA = "FootprintDetails.ScreenLoadedFromNotifictionExtra";
    private static final int REQUEST_COMMENTS = 201;
    private static final String NOTIFICATION_TOPIC = "notification_topic";
    private static final String NOTIFICATION_CONTENT = "content";

    private MyFootprintDetailsFragment myFootprintDetailsFragment;

    private boolean fromNotification;
    private boolean openSplashScreen = false;

    @Inject @Presenter
    MyFootprintDetailsNotificationsPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprint_details;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new MyFootprintDetailsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        String myFootprintKey = extras.getString(FOOTPRINT_KEY_EXTRA);
        long comments = extras.getLong(FOOTPRINT_COMMENTS_EXTRA);
        fromNotification = extras.getBoolean(FOOTPRINT_FROM_NOTIFICATION_EXTRA);

        if (myFootprintKey != null) {
            initializeFragment(myFootprintKey, comments);
        } else {
            // Enter here when we open this screen from a notification in foreground

            String notificationTopic = "";
            String notificationContent = "";

            for (String key : extras.keySet()) {
                Object value = extras.get(key);

                if (key.equals(NOTIFICATION_TOPIC)) {
                    notificationTopic = value.toString();
                } else if (key.equals(NOTIFICATION_CONTENT)) {
                    notificationContent = value.toString();
                }
            }

            if (!notificationTopic.isEmpty() && !notificationContent.isEmpty()) {
                presenter.handleNotifications(notificationTopic, notificationContent);
            } else {
                finish();
            }
        }

    }

    private void initializeFragment(String footprintMainKey, long comments) {
        myFootprintDetailsFragment =
                (MyFootprintDetailsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (myFootprintDetailsFragment == null) {
            myFootprintDetailsFragment = new MyFootprintDetailsFragment().newInstance(footprintMainKey, comments);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    myFootprintDetailsFragment, R.id.content_frame);
        }
    }

    public static void open(Activity activity, String footprintMainKey, long comments, boolean fromNotification) {
        Intent intent = new Intent(activity, MyFootprintDetailsActivity.class);
        intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintMainKey);
        intent.putExtra(FOOTPRINT_COMMENTS_EXTRA, comments);
        intent.putExtra(FOOTPRINT_FROM_NOTIFICATION_EXTRA, fromNotification);
        activity.startActivityForResult(intent, REQUEST_COMMENTS);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (myFootprintDetailsFragment != null && myFootprintDetailsFragment.isAdded()) {
            Intent data = new Intent();
            data.putExtra(COMMENTS_EXTRA, myFootprintDetailsFragment.getTotalComments());
            data.putExtra(COMMENTS_SCREEN_LOADED_FROM_NOTIFICATION_EXTRA, fromNotification);
            setResult(REQUEST_COMMENTS, data);
            myFootprintDetailsFragment.deleteCache();
        }

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (myFootprintDetailsFragment != null && myFootprintDetailsFragment.isAdded() && data != null) {
            myFootprintDetailsFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void initializeFragmentFromNotification(String myFootprintKey, long comments, boolean openSplashScreen) {
        this.openSplashScreen = openSplashScreen;
        initializeFragment(myFootprintKey, comments);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // See presenter for more information
        if (openSplashScreen) {
            SplashActivity.open(this);
        }
    }
}
