package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.UserGenderType;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.adapter.FragmentAdapter;
import com.raddarapp.presentation.android.di.module.WelcomeInAppScreenModule;
import com.raddarapp.presentation.android.fragment.WelcomeInAppScreenFragment;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.ImageUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeInAppScreenActivity extends BaseNormalActivity {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE;
    private static final String IMAGE_FIRST = "welcome-in-app-screen1";
    private static final String IMAGE_SECOND = "welcome-in-app-screen2";
    private static final String IMAGE_THIRD = "welcome-in-app-screen3";

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.page_indicator_view) PageIndicatorView pageIndicatorView;
    @BindView(R.id.go) Button goView;

    private WelcomeInAppScreenFragment welcomeInAppScreenFragmentFirst;
    private WelcomeInAppScreenFragment welcomeInAppScreenFragmentSecond;
    private WelcomeInAppScreenFragment welcomeInAppScreenFragmentThird;

    private int actualScreen = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome_in_app_screen;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new WelcomeInAppScreenModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        initializeFonts();
        initializeNavigation();
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(this, FONT_NAME, goView);
    }

    private void initializeNavigation() {
        String extensionDensityLanguage = new ImageUtils(this).getExtensionDensityLanguage();
        welcomeInAppScreenFragmentFirst = new WelcomeInAppScreenFragment().newInstance(getString(R.string.welcome_in_app_screen_first), R.drawable.welcome_screen1);
        welcomeInAppScreenFragmentSecond = new WelcomeInAppScreenFragment().newInstance(getString(R.string.welcome_in_app_screen_second), SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_SECOND + extensionDensityLanguage);
        welcomeInAppScreenFragmentThird = new WelcomeInAppScreenFragment().newInstance(getString(R.string.welcome_in_app_screen_third), SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_THIRD + extensionDensityLanguage);

        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager());

        adapter.addFragment(welcomeInAppScreenFragmentFirst);
        adapter.addFragment(welcomeInAppScreenFragmentSecond);
        adapter.addFragment(welcomeInAppScreenFragmentThird);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                actualScreen = position;
                updateScreen();
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        pageIndicatorView.setCount(5);
        pageIndicatorView.setSelection(0);
        pageIndicatorView.setAnimationType(AnimationType.DROP);
        pageIndicatorView.setViewPager(viewPager);
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, WelcomeInAppScreenActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (actualScreen > 0) {
            actualScreen--;
            updateScreen();
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @OnClick(R.id.go)
    public void onGoClicked() {
        if (actualScreen == viewPager.getOffscreenPageLimit() - 1) {
            PreferencesUtils preferencesUtils = new PreferencesUtils(this);
            preferencesUtils.setShowWelcomeInAppScreen(false);
            finish();

            if (preferencesUtils.getGender() != UserGenderType.OTHER.getValue()) {
                FootprintMainActivity.open(this, false, false);
            } else {
                EnterCompleteProfileActivity.open(this);
            }
        } else {
            actualScreen++;
        }

        updateScreen();
    }

    private void updateScreen() {
        if (actualScreen == viewPager.getOffscreenPageLimit() - 1) {
            goView.setText(getString(R.string.welcome_screen_end));
        } else {
            goView.setText(getString(R.string.welcome_screen_go));
        }

        viewPager.setCurrentItem(actualScreen);
        pageIndicatorView.setSelection(actualScreen);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
