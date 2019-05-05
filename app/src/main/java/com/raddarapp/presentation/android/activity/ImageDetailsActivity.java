package com.raddarapp.presentation.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.appeaser.imagetransitionlibrary.ImageTransitionUtil;
import com.github.chrisbanes.photoview.PhotoView;
import com.raddarapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageDetailsActivity extends AppCompatActivity {

    private static final String IMAGE_URL_KEY_EXTRA = "ImageDetails.ImageUrlKey";
    private static final String IMAGE_PLACEHOLDER_KEY_EXTRA = "ImagePlaceHolder.ImageUrlKey";
    private static final String MY_USER_PROFILE_IMAGE_CACHE = "myUserProfileImage.jpg";

    private int placeHolderResource;

    @BindView(R.id.footprint_image) PhotoView footprintImageView;

    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        imageUrl = extras.getString(IMAGE_URL_KEY_EXTRA);
        placeHolderResource = extras.getInt(IMAGE_PLACEHOLDER_KEY_EXTRA);

        supportPostponeEnterTransition();
        setEnterSharedElementCallback(ImageTransitionUtil.DEFAULT_SHARED_ELEMENT_CALLBACK);

        if (!imageUrl.contains(MY_USER_PROFILE_IMAGE_CACHE)) {
            Picasso.with(this)
                    .load(imageUrl)
                    .noFade()
                    .placeholder(placeHolderResource)
                    .into(footprintImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        } else {
            Picasso.with(this)
                    .load(new File(imageUrl))
                    .placeholder(placeHolderResource)
                    .noFade()
                    .into(footprintImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        }


    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        onBackPressed();
    }

    public static void open(Context context, String imageUrl, ActivityOptionsCompat options) {
        Intent intent = new Intent(context, ImageDetailsActivity.class);
        intent.putExtra(IMAGE_URL_KEY_EXTRA, imageUrl);
        intent.putExtra(IMAGE_PLACEHOLDER_KEY_EXTRA, R.drawable.placeholder_card);
        context.startActivity(intent, options.toBundle());
    }

    public static void openProfileImage(Context context, String imageUrl) {
        Intent intent = new Intent(context, ImageDetailsActivity.class);
        intent.putExtra(IMAGE_URL_KEY_EXTRA, imageUrl);
        intent.putExtra(IMAGE_PLACEHOLDER_KEY_EXTRA, R.drawable.placeholder_profile_big);
        context.startActivity(intent);
    }
}
