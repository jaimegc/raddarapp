package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.CreateFootprintModule;
import com.raddarapp.presentation.android.fragment.CreateFootprintFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CreateFootprintActivity extends BaseNormalActivity {

    private CreateFootprintFragment createFootprintFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_footprint;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new CreateFootprintModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        createFootprintFragment =
                (CreateFootprintFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (createFootprintFragment == null) {
            createFootprintFragment = new CreateFootprintFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    createFootprintFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, CreateFootprintActivity.class);
        context.startActivity(intent);
    }

    public static void openNewTask(Context context) {
        Intent intent = new Intent(context, CreateFootprintActivity.class);
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

        if (createFootprintFragment != null && createFootprintFragment.isAdded()) {
            createFootprintFragment.getImagePicker().onActivityResult(this, requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (createFootprintFragment != null && createFootprintFragment.isAdded()) {
            createFootprintFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
