package com.raddarapp.presentation.android.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.github.piasy.rxandroidaudio.PlayConfig;
import com.github.piasy.rxandroidaudio.RxAudioPlayer;
import com.karumi.rosie.view.Presenter;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.presentation.android.activity.EditMyProfileChangeFieldActivity;
import com.raddarapp.presentation.android.activity.GameInstructionsActivity;
import com.raddarapp.presentation.android.activity.SplashActivity;
import com.raddarapp.presentation.android.dialog.DialogActivateDesactivateUser;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.dialog.DialogRecordProfileAudio;
import com.raddarapp.presentation.android.dialog.DialogSharePromoCode;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.view.cropper.CropImage;
import com.raddarapp.presentation.android.view.cropper.CropImageView;
import com.raddarapp.presentation.android.view.cropper.ImagePicker;
import com.raddarapp.presentation.general.presenter.DesactivateUserPresenter;
import com.raddarapp.presentation.general.presenter.EditMyProfilePresenter;
import com.raddarapp.presentation.general.presenter.EnterLoginPresenter;
import com.raddarapp.presentation.general.presenter.LogoutPresenter;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;

import java.io.File;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.oply.opuslib.OpusService;


public class MyProfileSettingsFragment extends BaseNormalFragment implements EditMyProfilePresenter.View,
        LogoutPresenter.View, DialogActivateDesactivateUser.OnDialogDesactivateUserListener, DesactivateUserPresenter.View {

    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;
    private static final String RADDAR_VERSION = BuildConfig.VERSION_NAME;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 103;
    private static final int REQUEST_STORAGE_PERMISSION = 102;
    private static final int REQUEST_STORAGE_PROMO_CODE_PERMISSION = 104;
    private static final int REQUEST_DIALOG_PROFILE_AUDIO = 300;
    protected static final String DIALOG_PROFILE_AUDIO_EXTRA = "ProfileProfileAudioExtra";
    private static final int METERS_SHARE_PROMO_CODE = 100000;
    private static final String NOTIFICATION_TOPIC_COMMENTS = "new_comment";
    private static final String NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS = "favourite_flag";
    private static final String NOTIFICATION_TOPIC_FOOTPRINTS_DEAD = "flag_dead";
    private static final String NOTIFICATION_TOPIC_FOOTPRINTS_DEAD_WARNING = "flag_dead_warning";
    private static final String NOTIFICATION_TOPIC_COMPLIMENTS = "compliments";

    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.name) TextView nameView;
    @BindView(R.id.surname) TextView surnameView;
    @BindView(R.id.email) TextView emailView;
    @BindView(R.id.raddar_version) TextView raddarVersionView;
    @BindView(R.id.user_image_mini) CircularImageView userImageMiniView;
    @BindView(R.id.progress_audio) ProgressBar progressAudio;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.background_kreomi3) ImageView backgroundKreomi3View;
    @BindView(R.id.switch_sounds) SwitchCompat switchSounds;
    @BindView(R.id.switch_notifications_comments) SwitchCompat switchNotificationsComments;
    @BindView(R.id.switch_notifications_footprints_favs) SwitchCompat switchNotificationsFootprintsFavs;
    @BindView(R.id.switch_notifications_footprints_dead) SwitchCompat switchNotificationsFootprintsDead;
    @BindView(R.id.switch_notifications_footprints_dead_warning) SwitchCompat switchNotificationsFootprintsDeadWarning;
    @BindView(R.id.switch_notifications_compliments) SwitchCompat switchNotificationsCompliments;

    private ImagePicker imagePicker;
    private Uri uriImageUpload;
    private int aspectRatio = -1;

    private FileUtils fileUtils;
    private DimenUtils dimenUtils;

    private RxAudioPlayer rxAudioPlayer = RxAudioPlayer.getInstance();

    private boolean hasAudioProfile = false;

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject @Presenter
    EnterLoginPresenter presenter;

    @Inject @Presenter
    EditMyProfilePresenter editMyProfilePresenter;

    @Inject @Presenter
    LogoutPresenter logoutPresenter;

    @Inject @Presenter
    DesactivateUserPresenter desactivateUserPresenter;

    public static MyProfileSettingsFragment newInstance() {
        MyProfileSettingsFragment fragment = new MyProfileSettingsFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_profile_settings;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        fileUtils = new FileUtils(getActivity());
        dimenUtils = new DimenUtils();
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());
        initializeSwitches();
        initializeImagePicker();
        initializeBackgroundKreomi();
        raddarVersionView.setText(String.format(getString(R.string.raddar_version), RADDAR_VERSION));
    }

    private void initializeSwitches() {
        switchSounds.setChecked(userProfilePreferencesDataSource.hasSounds());
        switchSounds.setOnCheckedChangeListener((buttonView, isChecked) -> userProfilePreferencesDataSource.setHasSounds(isChecked));
        switchNotificationsComments.setChecked(userProfilePreferencesDataSource.getNotificationTopics().contains(NOTIFICATION_TOPIC_COMMENTS));
        switchNotificationsFootprintsFavs.setChecked(userProfilePreferencesDataSource.getNotificationTopics().contains(NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS));
        switchNotificationsFootprintsDead.setChecked(userProfilePreferencesDataSource.getNotificationTopics().contains(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD));
        switchNotificationsFootprintsDeadWarning.setChecked(userProfilePreferencesDataSource.getNotificationTopics().contains(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD_WARNING));
        switchNotificationsCompliments.setChecked(userProfilePreferencesDataSource.getNotificationTopics().contains(NOTIFICATION_TOPIC_COMPLIMENTS));

        switchNotificationsComments.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editMyProfilePresenter.updateNotificationTopic(NOTIFICATION_TOPIC_COMMENTS);
            Set<String> notificationTopics = userProfilePreferencesDataSource.getNotificationTopics();

            if (isChecked) {
                notificationTopics.add(NOTIFICATION_TOPIC_COMMENTS);
            } else {
                notificationTopics.remove(NOTIFICATION_TOPIC_COMMENTS);
            }

            userProfilePreferencesDataSource.setNotificationTopics(notificationTopics);
        });

        switchNotificationsFootprintsFavs.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editMyProfilePresenter.updateNotificationTopic(NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS);
            Set<String> notificationTopics = userProfilePreferencesDataSource.getNotificationTopics();

            if (isChecked) {
                notificationTopics.add(NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS);
            } else {
                notificationTopics.remove(NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS);
            }

            userProfilePreferencesDataSource.setNotificationTopics(notificationTopics);
        });

        switchNotificationsFootprintsDead.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editMyProfilePresenter.updateNotificationTopic(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD);
            Set<String> notificationTopics = userProfilePreferencesDataSource.getNotificationTopics();

            if (isChecked) {
                notificationTopics.add(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD);
            } else {
                notificationTopics.remove(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD);
            }

            userProfilePreferencesDataSource.setNotificationTopics(notificationTopics);
        });

        switchNotificationsFootprintsDeadWarning.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editMyProfilePresenter.updateNotificationTopic(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD_WARNING);
            Set<String> notificationTopics = userProfilePreferencesDataSource.getNotificationTopics();

            if (isChecked) {
                notificationTopics.add(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD_WARNING);
            } else {
                notificationTopics.remove(NOTIFICATION_TOPIC_FOOTPRINTS_DEAD_WARNING);
            }

            userProfilePreferencesDataSource.setNotificationTopics(notificationTopics);
        });

        switchNotificationsCompliments.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editMyProfilePresenter.updateNotificationTopic(NOTIFICATION_TOPIC_COMPLIMENTS);
            Set<String> notificationTopics = userProfilePreferencesDataSource.getNotificationTopics();

            if (isChecked) {
                notificationTopics.add(NOTIFICATION_TOPIC_COMPLIMENTS);
            } else {
                notificationTopics.remove(NOTIFICATION_TOPIC_COMPLIMENTS);
            }

            userProfilePreferencesDataSource.setNotificationTopics(notificationTopics);
        });
    }

    private void initializeImagePicker() {
        imagePicker = new ImagePicker();
        imagePicker.setTitle(getString(R.string.select_photo_option));
        imagePicker.setCropImage(true);
    }

    private void initializeBackgroundKreomi() {
        int width = (int) dimenUtils.dpToPx(getActivity(), dimenUtils.calculateWidthDp(getActivity().getResources().getConfiguration()));

        backgroundKreomi3View.getLayoutParams().width = width;
        backgroundKreomi3View.getLayoutParams().height = width;
    }

    @OnClick(R.id.linear_profile_image)
    public void onLinearProfileImageClicked() {
        selectImage();
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.linear_record_profile_audio)
    public void onRecordAudioProfileClicked() {

        if (checkStoragePermission() && checkRecordAudioPermission()) {
            stopPlay();
            DialogRecordProfileAudio.open(getActivity(), getFragmentManager(), this);
        } else {
            if (!checkStoragePermission()) {
                getStoragePermission();
            } else {
                getRecordAudioPermission();
            }
        }
    }

    @OnClick(R.id.linear_username)
    public void onChangeUsernameClicked() {
        EditMyProfileChangeFieldActivity.openChangeUsername(getActivity(), usernameView.getText().toString());
    }

    @OnClick(R.id.linear_name)
    public void onChangeNameClicked() {
        EditMyProfileChangeFieldActivity.openChangeName(getActivity(), nameView.getText().toString());
    }

    @OnClick(R.id.linear_surname)
    public void onChangeSurnameClicked() {
        EditMyProfileChangeFieldActivity.openChangeSurname(getActivity(), surnameView.getText().toString());
    }

    @OnClick(R.id.linear_email)
    public void onChangeEmailClicked() {
        EditMyProfileChangeFieldActivity.openChangeEmail(getActivity(), emailView.getText().toString());
    }

    @OnClick(R.id.linear_password)
    public void onChangePasswordClicked() {
        EditMyProfileChangeFieldActivity.openChangePassword(getActivity());
    }

    @OnClick(R.id.password)
    public void onChangePasswordTextClicked() {
        EditMyProfileChangeFieldActivity.openChangePassword(getActivity());
    }

    @OnClick(R.id.linear_logout)
    public void onLogoutClicked() {
        Snackbar snackbarError = Snackbar.make(getView(), getString(R.string.edit_my_profile_logout_message), Snackbar.LENGTH_LONG);
        View snackBarView = snackbarError.getView();
        snackBarView.setBackgroundColor(Color.WHITE);
        TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        message.setTextColor(Color.BLACK);
        snackbarError.show();
    }

    @OnLongClick(R.id.linear_logout)
    public boolean onLogoutLongClicked() {
        LoginManager.getInstance().logOut();
        fileUtils.removeMyUserProfileImageCache();

        logoutPresenter.logout();

        return true;
    }

    @OnClick(R.id.play_audio_profile)
    public void onPlayAudioProfileClicked() {

        if (progressAudio.getVisibility() == View.GONE && hasAudioProfile) {
            progressAudio.setVisibility(View.VISIBLE);

            fileUtils.loadMyUserProfileAudioCache(new FileUtils.OnFilePathFinishListener() {
                @Override
                public void filePath(String path) {
                    progressAudio.setVisibility(View.GONE);

                    try {
                        rxAudioPlayer.play(PlayConfig.file(new File(path)).looping(false).build())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(final Disposable disposable) {}

                                    @Override
                                    public void onNext(final Boolean aBoolean) {}

                                    @Override
                                    public void onError(final Throwable throwable) {
                                        // On Android 5.x RxAudioPlayer fails, so we use OpusService
                                        OpusService.play(getActivity(), new FileUtils(getActivity()).getPathMyUserProfileAudioCache());
                                    }

                                    @Override
                                    public void onComplete() {}
                                });
                    } catch (Exception e) {
                        initSnackbarError(R.string.error_local_play_audio);
                        fileUtils.deleteCacheMyAudioProfile(false);
                        progressAudio.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onError() {
                    initSnackbarError(R.string.error_local_play_audio);
                    fileUtils.deleteCacheMyAudioProfile(false);
                    progressAudio.setVisibility(View.GONE);
                }
            });
        }

    }

    @OnClick(R.id.game_instructions)
    public void onGameInstructionsClicked() {
        //GameInstructionsActivity.open(getActivity());
        initSnackbarLong(R.string.mock_disable);
    }

    @OnClick(R.id.more_information_send_comments)
    public void onMoreInformationSendCommentsClicked() {
        //sendEmail(getString(R.string.send_email_comments_subject), getString(R.string.send_email_comments_email));
        sendEmail(getString(R.string.send_email_comments_subject), getString(R.string.mock_email));
    }

    @OnClick(R.id.more_information_propose_new_territories)
    public void onMoreInformationProposeNewTerritoriesClicked() {
        //sendEmail(getString(R.string.send_email_territories_subject), getString(R.string.send_email_territories_email));
        initSnackbarLong(R.string.mock_disable);
    }

    @OnClick(R.id.more_information_thanks)
    public void onMoreInformationThanksClicked() {
        //goWeb(getString(R.string.url_thanks));
        initSnackbarLong(R.string.mock_disable);
    }

    @OnClick(R.id.technical_terms_conditions)
    public void onMoreInformationTermsConditionsClicked() {
        goWeb(getString(R.string.url_terms_conditions_mock));
    }

    @OnClick(R.id.technical_privacity_policy)
    public void onMoreInformationPrivacityPolicyClicked() {
        goWeb(getString(R.string.url_privacity_policy_mock));
    }

    @OnClick(R.id.technical_remove_account)
    public void onRemoveAccountClicked() {
        //sendEmail(getString(R.string.send_email_remove_account_subject), getString(R.string.send_email_remove_account_email));
        initSnackbarLong(R.string.mock_disable);
    }

    @OnClick(R.id.technical_desactivate_account)
    public void onDesactivateAccountClicked() {
        DialogActivateDesactivateUser.openDesactivateUser(getActivity(), getFragmentManager(), this);
    }

    @OnClick(R.id.technical_licenses)
    public void onMoreInformationLicensesClicked() {
        /*DialogInfo.openComingSoon(getActivity(), getFragmentManager(), R.drawable.logo_coming_soon,
                getString(R.string.coming_soon_licenses_title), getString(R.string.coming_soon_licenses_description), DialogInfo.HEIGHT_SMALL_DIALOG);
        goWeb(getString(R.string.url_licenses));*/
        initSnackbarLong(R.string.mock_disable);
    }

    @OnClick(R.id.technical_work)
    public void onMoreInformationWorkClicked() {
        //goWeb(getString(R.string.url_work));
        initSnackbarLong(R.string.mock_disable);
    }

    @OnClick(R.id.technical_about_developers)
    public void onMoreInformationAboutDevelopersClicked() {
        //goWeb(getString(R.string.url_about_developers));
        initSnackbarLong(R.string.mock_disable);
    }

    private void goWeb(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    @Override
    public void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel) {
        usernameView.setText(userProfileViewModel.getUsername());
        nameView.setText(userProfileViewModel.getName());
        surnameView.setText(userProfileViewModel.getSurname());
        emailView.setText(userProfileViewModel.getEmail());

        if ((userProfileViewModel.getAudio() != null && !userProfileViewModel.getAudio().isEmpty()) ||
                userProfileViewModel.getAudioCache() != null && !userProfileViewModel.getAudioCache().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
            hasAudioProfile = true;
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
            hasAudioProfile = false;
        }

        fileUtils.loadMyUserProfileImageCache(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE, userImageMiniView, true);
    }

    @Override
    public void showUploadAudioProfileError() {
        super.showUploadAudioProfileError();
        fileUtils.deleteCacheMyAudioProfile(true);
        progressAudio.setVisibility(View.GONE);
        playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        hasAudioProfile = false;
    }

    @Override
    public void showUploadNotificationTopicError() {
        super.showUploadNotificationTopicError();
    }

    @Override
    public void showUploadImageProfileError() {
        super.showUploadImageProfileError();
        fileUtils.deleteCacheImageProfile(true);
    }

    private void selectImage() {
        new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.select_photo_from_remove))
                .setItems(new String[] {getString(R.string.select_photo_camera), getString(R.string.select_photo_gallery)}, (dialog, which) -> {
                    ImagePicker.Callback callback = new ImagePicker.Callback() {
                        @Override
                        public void cropConfig(CropImage.ActivityBuilder builder){
                            builder
                                    .setMultiTouchEnabled(false)
                                    .setGuidelines(CropImageView.Guidelines.OFF)
                                    .setCropShape(CropImageView.CropShape.OVAL)
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
                                    uriImageUpload = imageUri;
                                    userImageMiniView.setImageBitmap(bitmap);
                                    fileUtils.clearMyUserProfileImage();
                                    fileUtils.saveMyUserProfileImageAfterTakePhoto();
                                    editMyProfilePresenter.updateImageProfile(fileUtils.getPathMyUserProfileImageCache());
                                }
                            } else {
                                initSnackbarError(R.string.error_crop_image);
                            }
                        }
                    };

                    // With Gallery
                    if (which == 0) {
                        imagePicker.startCamera(getActivity(), callback);
                    } else if (which == 1){
                        imagePicker.startGallery(getActivity(), callback);
                    }
                })
                .show()
                .getWindow()
                .setGravity(Gravity.BOTTOM);

    }

    @Override
    public void hideLoading() {}

    @Override
    public void showLoading() {}

    public ImagePicker getImagePicker() {
        return imagePicker;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DIALOG_PROFILE_AUDIO) {
            String audioUri = data.getStringExtra(DIALOG_PROFILE_AUDIO_EXTRA);
            hasAudioProfile = true;
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
            editMyProfilePresenter.updateAudioProfile(audioUri);
        } else {
            imagePicker.onActivityResult(getActivity(), requestCode, resultCode, data);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);

        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length== 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeRecordAudioPermissions();
            } else {
                showRecordAudioPermissionError();
            }
        } else if (requestCode == REQUEST_STORAGE_PERMISSION){
            if (grantResults.length== 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                initializeStoragePermissions();
            } else {
                showStoragePermissionError();
            }
        } else if (requestCode == REQUEST_STORAGE_PROMO_CODE_PERMISSION){
            if (grantResults.length== 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                sharePromoCode();
            } else {
                showStoragePermissionPromoCodeError();
            }
        }
    }

    private void sharePromoCode() {

        if (!userProfilePreferencesDataSource.getMyPromoCode().isEmpty()) {
            DialogSharePromoCode.open(getFragmentManager(), userProfilePreferencesDataSource.getMyPromoCode());
        } else {
            initSnackbarLong(R.string.my_footprints_empty_my_promo_code_minutes);
        }
    }

    private void sendEmail(String subject, String email) {
        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(Intent.createChooser(intent, getString(R.string.send_email_chooser)));
    }

    private boolean checkRecordAudioPermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void getRecordAudioPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
    }

    private void getStoragePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
    }

    private void getStoragePromoCodePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PROMO_CODE_PERMISSION);
    }

    private boolean checkStoragePermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void initializeRecordAudioPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {

            if (checkStoragePermission()) {
                stopPlay();
                DialogRecordProfileAudio.open(getActivity(), getFragmentManager(), this);
            } else {
                getStoragePermission();
            }
        } else {
            getRecordAudioPermission();
        }
    }

    private void initializeStoragePermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                        WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            if (checkRecordAudioPermission()) {
                stopPlay();
                DialogRecordProfileAudio.open(getActivity(), getFragmentManager(), this);
            } else {
                getRecordAudioPermission();
            }
        } else {
            getStoragePermission();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlay();
    }

    private void stopPlay() {
        OpusService.stopPlaying(getActivity());
        if (rxAudioPlayer != null) {
            rxAudioPlayer.stopPlay();
        }

        progressAudio.setVisibility(View.GONE);
    }

    @Override
    public void goLogin() {
        fileUtils.deleteAllCache();
        SplashActivity.open(getActivity());
    }

    @Override
    public void onDesactivatedUser() {
        desactivateUserPresenter.desactivateUser(userProfilePreferencesDataSource.getUserKey());
    }

    @Override
    public void desactivateUserSuccess() {
        LoginManager.getInstance().logOut();
        fileUtils.removeMyUserProfileImageCache();

        logoutPresenter.logout();
    }

    @Override
    public void showDesactivateUserError() {
        initSnackbarError(R.string.error_desactivate_user);
    }
}
