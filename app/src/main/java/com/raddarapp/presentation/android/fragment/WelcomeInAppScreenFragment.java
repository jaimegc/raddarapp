package com.raddarapp.presentation.android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.rosie.view.RosieFragment;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeInAppScreenFragment extends RosieFragment {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String MESSAGE_KEY = "WelcomeScreenFragment.Message";
    private static final String IMAGE_RESOURCE_KEY = "WelcomeScreenFragment.ImageResource";
    private static final String IMAGE_URL_KEY = "WelcomeScreenFragment.ImageUrl";

    @BindView(R.id.message) TextView messageView;
    @BindView(R.id.try_again) TextView tryAgainView;
    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.loading) AVLoadingIndicatorView linearLoading;

    private String message;
    private int imageResource;
    private String imageUrl;

    public static WelcomeInAppScreenFragment newInstance(String message, @DrawableRes int imageResource) {
        WelcomeInAppScreenFragment fragment = new WelcomeInAppScreenFragment();

        Bundle args = new Bundle();

        args.putString(MESSAGE_KEY, message);
        args.putInt(IMAGE_RESOURCE_KEY, imageResource);
        args.putString(IMAGE_URL_KEY, "");

        fragment.setArguments(args);

        return fragment;
    }

    public static WelcomeInAppScreenFragment newInstance(String message, String imageUrl) {
        WelcomeInAppScreenFragment fragment = new WelcomeInAppScreenFragment();

        Bundle args = new Bundle();

        args.putString(MESSAGE_KEY, message);
        args.putInt(IMAGE_RESOURCE_KEY, -1);
        args.putString(IMAGE_URL_KEY, imageUrl);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welcome_in_app_screen;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        ButterKnife.bind(this, view);

        message = getArguments().getString(MESSAGE_KEY);
        imageResource = getArguments().getInt(IMAGE_RESOURCE_KEY);
        imageUrl = getArguments().getString(IMAGE_URL_KEY);

        messageView.setText(Html.fromHtml(message));

        if (!imageUrl.isEmpty()) {
            loadImageUrl();
        } else {
            imageView.setImageResource(imageResource);
            linearLoading.setVisibility(View.GONE);
        }
    }

    private void onErrorMessage() {
        if (getActivity() != null && getView() != null) {
            FontUtils fontUtils = new FontUtils();
            Snackbar snackbarError = Snackbar.make(getView(), getString(R.string.error_load_image_welcome_screen), Snackbar.LENGTH_SHORT);
            View snackBarView = snackbarError.getView();
            snackBarView.setBackgroundColor(Color.WHITE);
            TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            message.setTextColor(Color.RED);
            fontUtils.applyFont(getActivity(), FONT_NAME, message);
            snackbarError.show();
        }
    }

    @OnClick(R.id.try_again)
    public void onTryAgainClicked() {
        linearLoading.setVisibility(View.VISIBLE);
        tryAgainView.setVisibility(View.GONE);
        loadImageUrl();
    }

    private void loadImageUrl() {
        if (getActivity() != null) {
            Picasso.with(getActivity())
                    .load(imageUrl)
                    .placeholder(R.drawable.welcome_screen_empty)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            linearLoading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            linearLoading.setVisibility(View.GONE);
                            tryAgainView.setVisibility(View.VISIBLE);
                            onErrorMessage();
                        }
                    });
        }
    }
}
