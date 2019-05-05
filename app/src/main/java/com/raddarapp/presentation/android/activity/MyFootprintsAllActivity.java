package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.MyFootprintsAllModule;
import com.raddarapp.presentation.android.fragment.MyFootprintsAllFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyFootprintsAllActivity extends BaseNormalActivity {

    private MyFootprintsAllFragment myFootprintsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprints_all;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new MyFootprintsAllModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        myFootprintsFragment =
                (MyFootprintsAllFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (myFootprintsFragment == null) {
            myFootprintsFragment = new MyFootprintsAllFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    myFootprintsFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, MyFootprintsAllActivity.class);
        context.startActivity(intent);
    }

    public static void openNewTask(Context context) {
        Intent intent = new Intent(context, MyFootprintsAllActivity.class);
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
}
