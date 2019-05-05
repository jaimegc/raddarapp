package com.raddarapp.presentation.android.fragment;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.ImageUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.OnClick;

public class GameInstructionsFragment extends BaseNormalFragment {

    private static final String SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE;
    private static final String IMAGE_0 = "instructions-screen0";
    private static final String IMAGE_1 = "instructions-screen1";
    private static final String IMAGE_2 = "instructions-screen2";
    private static final String IMAGE_3 = "instructions-screen3";
    private static final String IMAGE_4 = "instructions-screen6";
    private static final String IMAGE_5 = "instructions-screen7";
    private static final String IMAGE_6 = "instructions-screen8";
    private static final String IMAGE_7 = "instructions-screen10";
    private static final String IMAGE_8 = "welcome-in-app-screen3";
    private static final String IMAGE_9 = "welcome-screen1";
    private static final String IMAGE_10 = "welcome-screen2";
    private static final String IMAGE_11 = "welcome-screen4";

    @BindView(R.id.instructions_screen0) ImageView instructionsScreen0;
    @BindView(R.id.instructions_screen1) ImageView instructionsScreen1;
    @BindView(R.id.instructions_screen2) ImageView instructionsScreen2;
    @BindView(R.id.instructions_screen3) ImageView instructionsScreen3;
    @BindView(R.id.instructions_screen4) ImageView instructionsScreen4;
    @BindView(R.id.instructions_screen5) ImageView instructionsScreen5;
    @BindView(R.id.instructions_screen6) ImageView instructionsScreen6;
    @BindView(R.id.instructions_screen7) ImageView instructionsScreen7;
    @BindView(R.id.instructions_screen8) ImageView instructionsScreen8;
    @BindView(R.id.instructions_screen9) ImageView instructionsScreen9;
    @BindView(R.id.instructions_screen10) ImageView instructionsScreen10;
    @BindView(R.id.instructions_screen11) ImageView instructionsScreen11;
    @BindView(R.id.instructions_initial) TextView instructionsInitial;
    @BindView(R.id.instructions_message0) TextView instructionsMessage0;
    @BindView(R.id.instructions_message1) TextView instructionsMessage1;
    @BindView(R.id.instructions_message2) TextView instructionsMessage2;
    @BindView(R.id.instructions_message3) TextView instructionsMessage3;
    @BindView(R.id.instructions_message4) TextView instructionsMessage4;
    @BindView(R.id.instructions_message5) TextView instructionsMessage5;
    @BindView(R.id.instructions_message6) TextView instructionsMessage6;
    @BindView(R.id.instructions_message7) TextView instructionsMessage7;
    @BindView(R.id.instructions_message8) TextView instructionsMessage8;
    @BindView(R.id.instructions_message9) TextView instructionsMessage9;
    @BindView(R.id.instructions_message10) TextView instructionsMessage10;
    @BindView(R.id.instructions_message11) TextView instructionsMessage11;
    @BindView(R.id.instructions_message12) TextView instructionsMessage12;
    @BindView(R.id.loading1) AVLoadingIndicatorView loading1;
    @BindView(R.id.loading2) AVLoadingIndicatorView loading2;
    @BindView(R.id.loading3) AVLoadingIndicatorView loading3;
    @BindView(R.id.loading4) AVLoadingIndicatorView loading4;
    @BindView(R.id.loading5) AVLoadingIndicatorView loading5;
    @BindView(R.id.loading6) AVLoadingIndicatorView loading6;
    @BindView(R.id.loading7) AVLoadingIndicatorView loading7;
    @BindView(R.id.loading8) AVLoadingIndicatorView loading8;
    @BindView(R.id.loading9) AVLoadingIndicatorView loading9;
    @BindView(R.id.loading10) AVLoadingIndicatorView loading10;
    @BindView(R.id.loading11) AVLoadingIndicatorView loading11;

    public static GameInstructionsFragment newInstance() {
        GameInstructionsFragment fragment = new GameInstructionsFragment();
        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        initializeImages();
        initializeMessages();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_instructions;
    }

    private void initializeImages() {
        String extensionDensityLanguage = new ImageUtils(getActivity()).getExtensionDensityLanguage();

        instructionsScreen0.setImageResource(R.drawable.instructions_screen0);

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_1 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen1, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading1.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_2 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen2, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading2.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_3 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen3, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading3.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading3.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_4 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen4, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading4.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading4.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_5 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen5, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading5.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading5.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_6 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen6, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading6.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading6.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_7 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen7, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading7.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading7.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_8 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen8, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading8.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading8.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_9 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen9, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading9.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading9.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_10 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen10, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading10.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading10.setVisibility(View.GONE);
                    }
                });

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_ON_BOARDING_API_BASE_URL_COMPLETE + IMAGE_11 + extensionDensityLanguage)
                .placeholder(R.drawable.welcome_screen_empty)
                .into(instructionsScreen11, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading11.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        loading11.setVisibility(View.GONE);
                    }
                });
    }

    private void initializeMessages() {
        instructionsInitial.setText(Html.fromHtml(getString(R.string.game_instructions_initial)));
        instructionsMessage0.setText(Html.fromHtml(getString(R.string.game_instructions_message0)));
        instructionsMessage1.setText(Html.fromHtml(getString(R.string.game_instructions_message1)));
        instructionsMessage2.setText(Html.fromHtml(getString(R.string.game_instructions_message2)));
        instructionsMessage3.setText(Html.fromHtml(getString(R.string.game_instructions_message3)));
        instructionsMessage4.setText(Html.fromHtml(getString(R.string.game_instructions_message4)));
        instructionsMessage5.setText(Html.fromHtml(getString(R.string.game_instructions_message5)));
        instructionsMessage6.setText(Html.fromHtml(getString(R.string.game_instructions_message6)));
        instructionsMessage7.setText(Html.fromHtml(getString(R.string.game_instructions_message7)));
        instructionsMessage8.setText(Html.fromHtml(getString(R.string.game_instructions_message8)));
        instructionsMessage9.setText(Html.fromHtml(getString(R.string.game_instructions_message9)));
        instructionsMessage10.setText(Html.fromHtml(getString(R.string.game_instructions_message10)));
        instructionsMessage11.setText(Html.fromHtml(getString(R.string.game_instructions_message11)));
        instructionsMessage12.setText(Html.fromHtml(getString(R.string.game_instructions_message12)));
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }
}
