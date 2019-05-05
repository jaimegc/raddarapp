package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.adapter.FragmentAdapter;
import com.raddarapp.presentation.android.di.module.WelcomeScreenModule;
import com.raddarapp.presentation.android.fragment.WelcomeScreenFragment;
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

public class WelcomeScreenActivity extends BaseNormalActivity {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE;
    private static final String IMAGE_FIRST = "welcome-screen1";
    private static final String IMAGE_SECOND = "welcome-screen2";
    private static final String IMAGE_THIRD = "welcome-screen3";
    private static final String IMAGE_FOURTH = "welcome-screen4";
    private static final String IMAGE_FIFTH = "welcome-screen5";

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.page_indicator_view) PageIndicatorView pageIndicatorView;
    @BindView(R.id.go) Button goView;

    private WelcomeScreenFragment welcomeScreenFragmentFirst;
    private WelcomeScreenFragment welcomeScreenFragmentSecond;
    private WelcomeScreenFragment welcomeScreenFragmentThird;
    private WelcomeScreenFragment welcomeScreenFragmentFourth;
    private WelcomeScreenFragment welcomeScreenFragmentFifth;

    private int actualScreen = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome_screen;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new WelcomeScreenModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();
        initializeFonts();
        initializeNavigation();
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(this, FONT_NAME, goView);
    }

    private void initializeNavigation() {
        String extensionDensityLanguage = new ImageUtils(this).getExtensionDensityLanguage();
        welcomeScreenFragmentFirst = new WelcomeScreenFragment().newInstance(getString(R.string.welcome_screen_first), R.drawable.welcome_screen1);
        welcomeScreenFragmentSecond = new WelcomeScreenFragment().newInstance(getString(R.string.welcome_screen_second), R.drawable.welcome_screen2);
        welcomeScreenFragmentThird = new WelcomeScreenFragment().newInstance(getString(R.string.welcome_screen_third), R.drawable.welcome_screen3);
        welcomeScreenFragmentFourth = new WelcomeScreenFragment().newInstance(getString(R.string.welcome_screen_fourth), R.drawable.welcome_screen4);
        welcomeScreenFragmentFifth = new WelcomeScreenFragment().newInstance(getString(R.string.welcome_screen_fifth), R.drawable.welcome_screen5);
        //welcomeScreenFragmentFifth = new WelcomeScreenFragment().newInstance(getString(R.string.welcome_screen_fifth), SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_FIFTH + extensionDensityLanguage);

        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager());

        adapter.addFragment(welcomeScreenFragmentFirst);
        adapter.addFragment(welcomeScreenFragmentSecond);
        adapter.addFragment(welcomeScreenFragmentThird);
        adapter.addFragment(welcomeScreenFragmentFourth);
        adapter.addFragment(welcomeScreenFragmentFifth);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);

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
        Intent intent = new Intent(context, WelcomeScreenActivity.class);
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
            preferencesUtils.setShowWelcomeScreen(false);

            if (preferencesUtils.showWelcomeScreen()) {
                LoginActivity.openNewTask(this);
            } else {
                SplashActivity.open(this);
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
