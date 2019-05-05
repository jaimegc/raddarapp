package com.raddarapp.presentation.android.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.VisibilityType;
import com.raddarapp.presentation.android.adapter.CategoriesAdapter;
import com.raddarapp.presentation.android.dialog.DialogCategories;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.android.utils.StringUtils;
import com.raddarapp.presentation.android.utils.TutorialUtils;
import com.raddarapp.presentation.android.utils.ViewUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.raddarapp.presentation.android.view.cropper.CropImage;
import com.raddarapp.presentation.android.view.cropper.CropImageView;
import com.raddarapp.presentation.android.view.cropper.ImagePicker;
import com.raddarapp.presentation.general.presenter.CreateFootprintPresenter;
import com.raddarapp.presentation.general.viewmodel.CreateFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.vanniktech.emoji.EmojiTextView;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.ghyeok.stickyswitch.widget.StickySwitch;

public class CreateFootprintFragment extends BaseNormalFragment implements CreateFootprintPresenter.View, CategoriesAdapter.EmojiCategoriesAdapterListener {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS = 0;
    private static final int MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS = 0;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 102;
    private static final int IMAGE_HEIGHT_COLLAPSED = 262;
    private static final int IMAGE_HEIGHT_EXPANDED = 295;
    private static final int TITLE_HEIGHT_EXPANDED = 303;
    private static final int TITLE_HEIGHT_COLLAPSED = 263;
    private static final int VIEW_TITLE_HEIGHT_EXPANDED = 310;
    private static final int VIEW_TITLE_HEIGHT_COLLAPSED = 270;
    private static final int SCREW_HEIGHT_EXPANDED = 317;
    private static final int SCREW_HEIGHT_COLLAPSED = 277;
    private static final int METERS_SHARE_PROMO_CODE = 100000;

    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final int MAX_WIDTH_TITLE_DP = 216;
    private float MAX_WIDTH_TITLE_PIXELS;

    @BindView(R.id.title) EditText titleView;
    @BindView(R.id.description) EditText descriptionView;
    @BindView(R.id.create_footprint) FloatingActionButton createFootprintView;
    @BindView(R.id.linear_loading) LinearLayout loadingView;
    @BindView(R.id.image) RoundedImageView imageView;
    @BindView(R.id.accuracy) TextView accuracyView;
    @BindView(R.id.accuracy_text) TextView accuracyTextView;
    @BindView(R.id.create_your_flag) TextView createYourFlagView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.level) TextView levelView;
    @BindView(R.id.scope) TextView scopeView;
    @BindView(R.id.user_image) RoundedImageView userImageView;
    @BindView(R.id.remove_image) ImageView removeImageView;
    @BindView(R.id.close) ImageView closeView;
    @BindView(R.id.back) ImageView backView;
    @BindView(R.id.help) ImageView helpView;
    @BindView(R.id.ic_create_footprint_previsualization) ImageView createFootprintPrevisualizationView;
    @BindView(R.id.login_image_button) ImageView loginImageButtonView;
    @BindView(R.id.relative_base) RelativeLayout relativeBaseView;
    @BindView(R.id.relative_share_screen) RelativeLayout relativeShareScreenView;
    @BindView(R.id.linear_bottom_share) LinearLayout linearBottomShareView;
    @BindView(R.id.share_footprint) Button shareFootprintView;
    @BindView(R.id.linear_territory_details) RelativeLayout linearTerritoryDetails;
    @BindView(R.id.territory_parent) TextView territoryParentView;
    @BindView(R.id.territory) TextView territoryView;
    @BindView(R.id.zone_footprint) TextView zoneFootprintView;
    @BindView(R.id.country_emoji) TextView countryEmojiView;
    @BindView(R.id.country_emoji_footprint) TextView countryEmojiFootprintView;
    @BindView(R.id.background_image) RelativeLayout backgroundImageView;
    @BindView(R.id.confetti) ImageView confettiView;
    @BindView(R.id.view_confetti) View viewConfettiView;
    @BindView(R.id.view_main_layout) View viewMainLayoutView;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.ic_emoji_category) EmojiTextView icEmojiCategory;
    @BindView(R.id.emoji_category) TextView emojiCategory;
    @BindView(R.id.footprint_visible) StickySwitch footprintVisibleView;
    @BindView(R.id.flag_ball_background) View flagBallBackgroundView;
    @BindView(R.id.ic_footprint_location) ImageView icFootprintLocationView;
    @BindView(R.id.ic_comments) ImageView icCommentsView;
    @BindView(R.id.comments) TextView commentsView;

    @Inject @Presenter
    CreateFootprintPresenter presenter;

    private ImagePicker imagePicker;
    private Uri uriImageUpload;
    private StringUtils stringUtils = new StringUtils();
    private DimenUtils dimenUtils = new DimenUtils();
    private NumberUtils numberUtils = new NumberUtils();
    private AnimationUtils animationUtils = new AnimationUtils();
    private ViewUtils viewUtils = new ViewUtils();
    private PreferencesUtils preferencesUtils;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location = null;

    private int aspectRatio = -1;
    private boolean isCameraButtonClicked = false;
    private boolean photoTaken = false;
    private boolean isExpandedPhoto = false;
    private boolean isPrevisualizationActive = false;
    private int footprintEmojiCategory = FootprintCategory.CATEGORY_EMOJI_UNCLASSIFIED.getValue();

    private Resources resources;

    private Bitmap photoBitmap;

    public static CreateFootprintFragment newInstance() {
        CreateFootprintFragment fragment = new CreateFootprintFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_footprint;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        initializeImagePicker();
        initializeEditTexts();
        initializeFonts();

        preferencesUtils = new PreferencesUtils(getActivity());

        scopeView.setText(numberUtils.rangeOrScopeToString(0));

        MAX_WIDTH_TITLE_PIXELS = dimenUtils.dpToPx(getActivity(), MAX_WIDTH_TITLE_DP);
        accuracyView.setTextColor(Color.RED);
        accuracyView.setText(getString(R.string.default_accuracy).substring(1, getString(R.string.default_accuracy).length()));

        Criteria criterio = new Criteria();
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(false);
        criterio.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.getBestProvider(criterio, true);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location locationChanged) {
                if (!locationChanged.isFromMockProvider()) {
                    updateBestLocation(locationChanged);
                } else {
                    showLocationMockError();
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        if (checkStoragePermission() && checkGpsEnabled()) {
            onImageClicked();
        } else {
            initializeGpsPermission();
        }

        icEmojiCategory.setBackgroundDrawable(context.getDrawable(R.drawable.emoji_unclassified));

        footprintVisibleView.setOnSelectedChangeListener((direction, s) -> {
            if (direction == StickySwitch.Direction.LEFT) {
                flagBallBackgroundView.setBackgroundResource(R.drawable.circle_coin);
                icFootprintLocationView.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.footprint_location)));
                icFootprintLocationView.setImageResource(R.drawable.ic_footprint_location);
            } else {
                flagBallBackgroundView.setBackgroundResource(R.drawable.circle_coin_invisible);
                icFootprintLocationView.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.footprint_location_invisible)));
                icFootprintLocationView.setImageResource(R.drawable.ic_footprint_location_invisible);
            }
        });
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, titleView, descriptionView, zoneFootprintView, commentsView, shareFootprintView);
    }

    private void initializeGpsPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (!checkStoragePermission()) {
                getStoragePermission();
            }
        } else {
            getGpsPermission();
        }
    }

    private void initializeStoragePermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                        WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            getStoragePermission();
        }
    }

    private void initializeEditTexts() {
        titleView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                boolean freeSpace = dimenUtils.maxCharactersByPx(titleView, MAX_WIDTH_TITLE_PIXELS);

                if (!freeSpace) {
                    titleView.setText(titleView.getText().toString().substring(0, titleView.getText().toString().length()-1));
                    titleView.setSelection(titleView.getText().length());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        titleView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    if (isExpandedPhoto) {
                        titleView.setSingleLine(true);
                        titleView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        descriptionView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        titleView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                        descriptionView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                    }
                }
            }
        });

        // To use multiline and action done
        descriptionView.setHorizontallyScrolling(false);
        descriptionView.setMaxLines(Integer.MAX_VALUE);

    }

    private void initializeImagePicker() {
        imagePicker = new ImagePicker();
        imagePicker.setTitle(getString(R.string.select_photo_option));
        imagePicker.setCropImage(true);
    }

    @Override
    public void showCreatedFootprint(CreateFootprintViewModel createFootprintViewModel) {
        territoryView.setText(createFootprintViewModel.getCurrentZoneName());
        zoneFootprintView.setText(createFootprintViewModel.getCurrentZoneName());
        countryEmojiView.setText(createFootprintViewModel.getCountryEmoji());
        countryEmojiFootprintView.setText(createFootprintViewModel.getCountryEmoji());
        territoryParentView.setText(((createFootprintViewModel.getCurrentZoneParentName() != null &&
                !createFootprintViewModel.getCurrentZoneParentName().isEmpty()) ? createFootprintViewModel.getCurrentZoneParentName() : ""));
        disableViewsCreatedFootprint();
        FileUtils fileUtils = new FileUtils(getActivity());
        fileUtils.moveFootprintImageCacheToRaddarImagesFolder();
    }

    private void disableViewsCreatedFootprint() {
        relativeBaseView.setBackgroundResource(R.color.background_dark);
        relativeShareScreenView.setBackgroundResource(R.color.background_dark);

        viewUtils.visibilityVisible(linearTerritoryDetails, closeView, zoneFootprintView, countryEmojiFootprintView,
                shareFootprintView, confettiView, viewConfettiView, viewMainLayoutView, icCommentsView, commentsView);

        // To show this view except for hdpi screens
        if (resources.getDisplayMetrics().densityDpi != DisplayMetrics.DENSITY_HIGH) {
            viewUtils.visibilityVisible(linearBottomShareView);
        }

        viewUtils.visibilityGone(removeImageView, accuracyView, accuracyTextView, createFootprintView, loginImageButtonView,
                createYourFlagView, helpView, backView, createFootprintPrevisualizationView, footprintVisibleView);
    }

    @Override
    public void showError() {
        enableViews();
        showGenericError();
    }

    @Override
    public void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel) {
        usernameView.setText(userProfileViewModel.getUsername());
        levelView.setText(userProfileViewModel.getLevel());

        if (userProfileViewModel.getAudio() != null && !userProfileViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        new FileUtils(getActivity()).loadMyUserProfileImageCache(SERVER_IMAGES_API_BASE_URL_COMPLETE, userImageView);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        animationUtils.alphaAnimationWithInitial(loadingView, 10, View.VISIBLE, View.VISIBLE, false);
        createYourFlagView.setVisibility(View.GONE);
    }

    private void disableViews() {
        createFootprintView.setEnabled(false);
        createFootprintView.setClickable(false);
        titleView.setEnabled(false);
        titleView.setClickable(false);
        descriptionView.setEnabled(false);
        descriptionView.setClickable(false);
        imageView.setClickable(false);
        titleView.setFocusable(false);
        descriptionView.setFocusable(false);
        titleView.setFocusableInTouchMode(false);
        descriptionView.setFocusableInTouchMode(false);
        removeImageView.setVisibility(View.GONE);
        createYourFlagView.setVisibility(View.GONE);
        createFootprintPrevisualizationView.setEnabled(false);
        createFootprintPrevisualizationView.setClickable(false);
        footprintVisibleView.setEnabled(false);
        footprintVisibleView.setClickable(false);
    }

    private void enableViews() {
        createFootprintView.setEnabled(true);
        createFootprintView.setClickable(true);
        titleView.setEnabled(true);
        titleView.setClickable(true);
        descriptionView.setEnabled(true);
        descriptionView.setClickable(true);
        imageView.setClickable(true);
        titleView.setFocusable(true);
        descriptionView.setFocusable(true);
        titleView.setFocusableInTouchMode(true);
        descriptionView.setFocusableInTouchMode(true);
        createFootprintPrevisualizationView.setEnabled(true);
        createFootprintPrevisualizationView.setClickable(true);
        footprintVisibleView.setEnabled(true);
        footprintVisibleView.setClickable(true);
        createYourFlagView.setVisibility(View.VISIBLE);

        if (photoTaken) {
            removeImageView.setVisibility(View.VISIBLE);

            if (new PreferencesUtils(getActivity()).showTutorialCreateFootprint()) {
                new TutorialUtils(
                    getActivity(), FONT_NAME, createFootprintPrevisualizationView, icEmojiCategory, footprintVisibleView).initializeTutorialCreateFlag();
            }
        }
    }

    @Override
    public void collapseFootprint() {
        if (!isPrevisualizationActive) {
            collapseFootprintViews();
        }

        enableViews();
    }

    private void collapseFootprintViews() {
        isExpandedPhoto = false;
        RelativeLayout.LayoutParams lpTitleView = (RelativeLayout.LayoutParams) titleView.getLayoutParams();
        RelativeLayout.LayoutParams lpBackgroundImageView = (RelativeLayout.LayoutParams) backgroundImageView.getLayoutParams();
        RelativeLayout.LayoutParams lpRoundedImageView = (RelativeLayout.LayoutParams) imageView.getLayoutParams();

        lpTitleView.topMargin = (int) dimenUtils.dpToPx(getActivity(), TITLE_HEIGHT_COLLAPSED);
        lpBackgroundImageView.height =  (int) dimenUtils.dpToPx(getActivity(), IMAGE_HEIGHT_COLLAPSED);
        lpRoundedImageView.height =  (int) dimenUtils.dpToPx(getActivity(), IMAGE_HEIGHT_COLLAPSED);

        descriptionView.setVisibility(View.VISIBLE);

        titleView.setLayoutParams(lpTitleView);
        backgroundImageView.setLayoutParams(lpBackgroundImageView);
        imageView.setLayoutParams(lpRoundedImageView);

        if (photoBitmap != null) {
            imageView.setImageBitmap(photoBitmap);
        }

        titleView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        descriptionView.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    @Override
    public void expandFootprint() {
        expandFootprintViews();
        disableViews();
    }

    private void expandFootprintViews() {
        isExpandedPhoto = true;
        RelativeLayout.LayoutParams lpTitleView = (RelativeLayout.LayoutParams) titleView.getLayoutParams();
        RelativeLayout.LayoutParams lpBackgroundImageView = (RelativeLayout.LayoutParams) backgroundImageView.getLayoutParams();
        RelativeLayout.LayoutParams lpRoundedImageView = (RelativeLayout.LayoutParams) imageView.getLayoutParams();

        lpTitleView.topMargin = (int) dimenUtils.dpToPx(getActivity(), TITLE_HEIGHT_EXPANDED);
        lpBackgroundImageView.height = (int) dimenUtils.dpToPx(getActivity(), IMAGE_HEIGHT_EXPANDED);
        lpRoundedImageView.height =  (int) dimenUtils.dpToPx(getActivity(), IMAGE_HEIGHT_EXPANDED);

        descriptionView.setVisibility(View.GONE);

        titleView.setLayoutParams(lpTitleView);
        backgroundImageView.setLayoutParams(lpBackgroundImageView);
        imageView.setLayoutParams(lpRoundedImageView);

        if (photoBitmap != null) {
            imageView.setImageBitmap(photoBitmap);
        }

        titleView.setSingleLine(true);
        titleView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        descriptionView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        titleView.setRawInputType(InputType.TYPE_CLASS_TEXT);
        descriptionView.setRawInputType(InputType.TYPE_CLASS_TEXT);
    }

    @OnClick(R.id.create_footprint)
    public void onCreateFootprintClicked() {

        if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
            if (checkGpsEnabled()) {
                if (location != null) {
                    if (checkStoragePermission()) {
                        presenter.onCreateFootprintClicked(titleView.getText().toString(), descriptionView.getText().toString(),
                                checkVisibilityType(), aspectRatio, String.valueOf(location.getLongitude()),
                                String.valueOf(location.getLatitude()), footprintEmojiCategory,
                                footprintVisibleView.getDirection()  == StickySwitch.Direction.LEFT ? true : false, uriImageUpload);
                    } else {
                        getStoragePermission();
                    }
                } else {
                    if (checkGpsPermission()) {
                        showLocationError();
                    } else {
                        getGpsPermission();
                    }
                }

            } else {
                showGpsDisabledError();
            }
        } else {
            initSnackbarError(R.string.error_local_create_footprint_admin);
        }
    }

    // In the future will change
    private int checkVisibilityType() {
        return VisibilityType.PUBLIC.getValue();
    }

    private void updateBestLocation(Location newLocation) {
        if (newLocation != null && (location == null || newLocation.getAccuracy() < location.getAccuracy())) {
            location = newLocation;
            showAccuracy(location);
        }
    }

    @OnClick(R.id.image)
    public void onImageClicked() {
        if (checkStoragePermission() && checkGpsEnabled()) {
            isCameraButtonClicked = true;
            selectImage();
        } else {
            initializeGpsPermission();
        }
    }

    @OnClick(R.id.ic_create_footprint_previsualization)
    public void onCreateFootprintPrevisualizationClicked() {
        if (isExpandedPhoto) {
            collapseFootprintViews();
            createFootprintPrevisualizationView.setImageTintList(ColorStateList.valueOf(resources.getColor(R.color.grey3)));
            isPrevisualizationActive = false;
        } else {
            expandFootprintViews();
            createFootprintPrevisualizationView.setImageTintList(ColorStateList.valueOf(resources.getColor(R.color.marker_me)));
            isPrevisualizationActive = true;
        }
    }

    private void selectImage() {
        if (!photoTaken) {

            new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.select_photo_from_remove))
                    .setItems(new String[] {getString(R.string.select_photo_gallery), getString(R.string.select_photo_camera)}, (dialog, which) -> {
                        ImagePicker.Callback callback = new ImagePicker.Callback() {
                            @Override
                            public void cropConfig(CropImage.ActivityBuilder builder){
                                builder
                                        .setMultiTouchEnabled(false)
                                        .setGuidelines(CropImageView.Guidelines.OFF)
                                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                                        .setOutputCompressQuality(75)
                                        .setAspectRatio(1, 1);
                            }
                            @Override
                            public void onPickImage(Uri imageUri) {
                            }

                            @Override
                            public void onCropImage(Uri imageUri, int aspectRatioX, int aspectRatioY) {
                                File imageFile = new File(imageUri.getPath());

                                if (imageFile != null) {
                                    BitmapFactory.Options o2 = new BitmapFactory.Options();
                                    Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath(), o2);

                                    if (bitmap != null) {
                                        int resizedValues[] = dimenUtils.ratioResizeImageByAspectRatio(aspectRatioX, aspectRatioY);
                                        aspectRatio = resizedValues[2];

                                        uriImageUpload = imageUri;
                                        imageView.setImageBitmap(bitmap);
                                        photoTaken = true;
                                        photoBitmap = bitmap;
                                        isCameraButtonClicked = false;
                                        removeImageView.setVisibility(View.VISIBLE);
                                        createFootprintPrevisualizationView.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    clearImage();
                                }
                            }
                        };

                        if (which == 0) {
                            imagePicker.startGallery(getActivity(), callback);
                        } else if (which == 1){
                            imagePicker.startCamera(getActivity(), callback);
                        }
                     })
                     .show()
                     .getWindow()
                     .setGravity(Gravity.BOTTOM);

        } else {
            new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.select_photo_from_remove))
                    .setItems(new String[] {getString(R.string.select_photo_gallery), getString(R.string.select_photo_camera), getString(R.string.select_photo_remove)}, (dialog, which) -> {
                        ImagePicker.Callback callback = new ImagePicker.Callback() {
                            @Override
                            public void cropConfig(CropImage.ActivityBuilder builder){
                                builder
                                        .setMultiTouchEnabled(false)
                                        .setGuidelines(CropImageView.Guidelines.OFF)
                                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                                        .setAspectRatio(1, 1);
                            }
                            @Override public void onPickImage(Uri imageUri) {
                            }

                            @Override public void onCropImage(Uri imageUri, int aspectRatioX, int aspectRatioY) {
                                File imageFile = new File(imageUri.getPath());

                                if (imageFile != null) {
                                    BitmapFactory.Options o2 = new BitmapFactory.Options();
                                    Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath(), o2);

                                    if (bitmap != null) {
                                        int resizedValues[] = dimenUtils.ratioResizeImageByAspectRatio(aspectRatioX, aspectRatioY);
                                        aspectRatio = resizedValues[2];

                                        uriImageUpload = imageUri;
                                        imageView.setImageBitmap(bitmap);
                                        photoTaken = true;
                                        photoBitmap = bitmap;
                                        isCameraButtonClicked = false;
                                        removeImageView.setVisibility(View.VISIBLE);
                                        createFootprintPrevisualizationView.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    clearImage();
                                }
                            }
                        };

                        if (which == 0) {
                            imagePicker.startGallery(getActivity(), callback);
                        } else if (which == 1){
                            imagePicker.startCamera(getActivity(), callback);
                        } else if (which == 2){
                            clearImage();
                        }
                    })
                    .show()
                    .getWindow()
                    .setGravity(Gravity.BOTTOM);
        }

    }

    private void clearImage() {
        uriImageUpload = null;
        photoTaken = false;
        photoBitmap = null;
        removeImageView.setVisibility(View.GONE);
        createFootprintPrevisualizationView.setVisibility(View.GONE);
        isPrevisualizationActive = false;
        createFootprintPrevisualizationView.setImageTintList(ColorStateList.valueOf(resources.getColor(R.color.grey3)));
        collapseFootprintViews();
        Bitmap backgroundCreateFootprint = BitmapFactory.decodeResource(resources, R.drawable.background_create_footprint);
        imageView.setImageBitmap(backgroundCreateFootprint);
    }

    @OnClick(R.id.remove_image)
    public void onRemovedImageClicked() {
        clearImage();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(getActivity(), requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length== 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeStoragePermissions();
                initializeProviders();
            } else {
                showGpsPermissionError();
            }
        } else if (requestCode == REQUEST_STORAGE_PERMISSION){
            if (grantResults.length== 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                initializeGpsPermission();
            } else {
                showStoragePermissionError();
            }
        }
    }

    private boolean checkGpsPermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void getGpsPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
    }

    private void getStoragePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
    }

    private boolean checkStoragePermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public ImagePicker getImagePicker() {
        return imagePicker;
    }

    private void showAccuracy(Location location) {
        if (location != null) {
            accuracyView.setTextColor(resources.getColor(R.color.marker_following));
            accuracyView.setText(stringUtils.accuracyFormat(location.getAccuracy()));
        } else {
            accuracyView.setTextColor(Color.RED);
            accuracyView.setText(getString(R.string.default_accuracy).substring(1, getString(R.string.default_accuracy).length()));
        }
    }

    private boolean checkGpsEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() != null && resources == null) {
            resources = getResources();
        }

        initializeProviders();
    }

    private void initializeProviders() {
        if (checkGpsPermission()) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS, MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS, locationListener);
            }
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS, MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS, locationListener);
            }
        }
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.close)
    public void onCloseClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.help)
    public void onHelpClicked() {
        DialogInfo.openInfo(getActivity(), getFragmentManager(), R.drawable.dialog_flags_example,
                getString(R.string.help_create_footprint_title), getString(R.string.help_create_footprint_description),
                DialogInfo.HEIGHT_BIG_DIALOG);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (checkGpsPermission()) {
            if (!isCameraButtonClicked) {
                locationManager.removeUpdates(locationListener);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (checkGpsPermission()) {
            locationManager.removeUpdates(locationListener);
        }
    }

    @OnClick(R.id.share_footprint)
    public void onShareFootprintClicked() {
        FileUtils fileUtils = new FileUtils(getActivity());
        File file = fileUtils.getCaptureScreenToShare(relativeShareScreenView);
        String metersSharePromoCode = numberUtils.rangeOrScopeToStringWithoutDecimals(METERS_SHARE_PROMO_CODE);

        if (file != null) {
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_flag_subject));
            //intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_flag_text,
              //      new PreferencesUtils(getActivity()).getMyPromoCode(), metersSharePromoCode));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_flag_text));

            intent.setType("image/jpeg");
            startActivity(Intent.createChooser(intent, getString(R.string.share_flag_via)));
        }
    }

    @OnClick(R.id.ic_emoji_category)
    public void onCategoriesImageClicked() {
        DialogCategories.open(getActivity(), getFragmentManager(), this);
    }

    @OnClick(R.id.emoji_category)
    public void onCategoriesNameClicked() {
        DialogCategories.open(getActivity(), getFragmentManager(), this);
    }
    @OnClick(R.id.linear_category_clicked)
    public void onLinearCategoryClicked() {
        DialogCategories.open(getActivity(), getFragmentManager(), this);
    }

    @Override
    public void showErrorLocalImageEmpty() {
        initSnackbarError(R.string.error_local_empty_image);
        enableViews();
    }

    @Override
    public void showErrorLocalTitleEmpty() {
        initSnackbarError(R.string.error_local_empty_title);
        enableViews();
    }

    @Override
    public void showErrorLocalEmojiCategoryUnclassified() {
        initSnackbarLong(R.string.error_local_unclassified_emoji_category);
        enableViews();
    }

    @Override
    public void showErrorLocalDescriptionEmpty() {
        initSnackbarError(R.string.error_local_empty_description);
        enableViews();
    }

    @Override
    public void onEmojiCategorySelected(FootprintEmojiCategory emojiCategory) {
        this.icEmojiCategory.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
        this.emojiCategory.setText(emojiCategory.getName());
        this.footprintEmojiCategory = emojiCategory.getCode();
    }
}
