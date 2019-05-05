package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.MyFootprintsCollectionModule;
import com.raddarapp.presentation.android.fragment.MyFootprintsCollectionFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyFootprintsCollectionActivity extends BaseNormalActivity {

    private static final int REQUEST_USER_PROFILE_CHANGES = 202;

    private MyFootprintsCollectionFragment myFootprintsCollectionFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprints_collection;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new MyFootprintsCollectionModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        myFootprintsCollectionFragment =
                (MyFootprintsCollectionFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (myFootprintsCollectionFragment == null) {
            myFootprintsCollectionFragment = new MyFootprintsCollectionFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    myFootprintsCollectionFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, MyFootprintsCollectionActivity.class);
        context.startActivity(intent);
    }

    public static void openNewTask(Context context) {
        Intent intent = new Intent(context, MyFootprintsCollectionActivity.class);
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
            if (myFootprintsCollectionFragment != null && myFootprintsCollectionFragment.isAdded() && data != null) {
                myFootprintsCollectionFragment.onActivityResult(requestCode, resultCode, data);
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
