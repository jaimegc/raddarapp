package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.MyFootprintCollectionDetailsModule;
import com.raddarapp.presentation.android.fragment.MyFootprintCollectionDetailsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyFootprintCollectionDetailsActivity extends BaseNormalActivity {

    private static final String FOOTPRINT_KEY_EXTRA = "FootprintDetails.FootprintKey";
    private static final String FOOTPRINT_COMMENTS_EXTRA = "FootprintDetails.FootprintComments";
    private static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final int REQUEST_COMMENTS = 200;

    private MyFootprintCollectionDetailsFragment myFootprintDetailsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprint_collection_details;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new MyFootprintCollectionDetailsModule());
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
        myFootprintDetailsFragment =
                (MyFootprintCollectionDetailsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (myFootprintDetailsFragment == null) {
            myFootprintDetailsFragment = new MyFootprintCollectionDetailsFragment().newInstance(footprintMainKey, comments);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    myFootprintDetailsFragment, R.id.content_frame);
        }
    }

    public static void open(Activity activity, String footprintMainKey, long comments) {
        Intent intent = new Intent(activity, MyFootprintCollectionDetailsActivity.class);
        intent.putExtra(FOOTPRINT_KEY_EXTRA, footprintMainKey);
        intent.putExtra(FOOTPRINT_COMMENTS_EXTRA, comments);
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
}
