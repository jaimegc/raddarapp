package com.raddarapp.presentation.android.fragment;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.domain.model.enums.UserGenderType;
import com.raddarapp.presentation.android.activity.PromoCodeActivity;
import com.raddarapp.presentation.android.activity.SplashActivity;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.view.date.DatePickerPopWin;
import com.raddarapp.presentation.general.presenter.EnterCompleteProfilePresenter;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.ghyeok.stickyswitch.widget.StickySwitch;


public class EnterCompleteProfileFragment extends BaseNormalFragment implements EnterCompleteProfilePresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int REQUEST_PROMO_CODE = 333;
    private static final int METERS_SHARE_PROMO_CODE = 100000;
    private static final int WIN_METERS_SHARE_PROMO_CODE = 20000;
    private static final int REGISTER_INITIAL_METERS_WITH_PROMOCODE = 400000;
    private static final int REGISTER_INITIAL_LEVEL_WITH_PROMOCODE = 4;

    @BindView(R.id.register_username) EditText editRegisterUsernameView;
    @BindView(R.id.register_birthdate) TextView registerBirthdateView;
    @BindView(R.id.text_register_email) TextView textRegisterEmailView;
    @BindView(R.id.register_email) EditText editRegisterEmailView;
    @BindView(R.id.checkbox_terms_conditions) CheckBox checkboxTermsConditions;
    @BindView(R.id.terms_conditions) TextView termsConditions;
    @BindView(R.id.register_gender) StickySwitch gender;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.finish) Button finishView;
    @BindView(R.id.linear_promo_code) LinearLayout linearPromoCodeView;

    private AnimationUtils animationUtils = new AnimationUtils();
    private DateUtils dateUtils = new DateUtils();
    private MediaPlayer mediaPlayer;
    private static final int soundExchangeResource = R.raw.win_coin;
    private String lastDateSelected = "";

    @Inject @Presenter
    EnterCompleteProfilePresenter presenter;

    private MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    final Handler handlerSound = new Handler();

    final Runnable runnableSounds = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(context, soundExchangeResource);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSound.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    public static EnterCompleteProfileFragment newInstance() {
        EnterCompleteProfileFragment fragment = new EnterCompleteProfileFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enter_complete_profile;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());
        termsConditions.setText(Html.fromHtml(getString(R.string.register_terms_conditions)));
        lastDateSelected = dateUtils.actualDateForDateDialog();

        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, finishView, editRegisterEmailView, editRegisterUsernameView);
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
        enableFocusableView();
    }

    @Override
    public void showLoading() {
        animationUtils.alphaAnimationWithInitial(linearLoadingView, 10, View.VISIBLE, View.VISIBLE, false);
        disableFocusableView();
    }

    private void enableFocusableView() {
        finishView.setEnabled(true);
        finishView.setClickable(true);
        editRegisterUsernameView.setClickable(true);
        registerBirthdateView.setClickable(true);
        editRegisterEmailView.setClickable(true);
        editRegisterUsernameView.setFocusable(true);
        editRegisterUsernameView.setFocusable(true);
        editRegisterUsernameView.setFocusableInTouchMode(true);
        editRegisterUsernameView.setFocusableInTouchMode(true);
        checkboxTermsConditions.setClickable(true);
        gender.setClickable(true);
    }

    private void disableFocusableView() {
        finishView.setEnabled(false);
        finishView.setClickable(false);
        editRegisterUsernameView.setClickable(false);
        registerBirthdateView.setClickable(false);
        editRegisterEmailView.setClickable(false);
        editRegisterUsernameView.setFocusable(false);
        editRegisterUsernameView.setFocusable(false);
        editRegisterUsernameView.setFocusableInTouchMode(false);
        editRegisterUsernameView.setFocusableInTouchMode(false);
        checkboxTermsConditions.setClickable(false);
        gender.setClickable(false);
    }

    @Override
    public void showErrorLocalTermsConditions() {
        initSnackbarError(R.string.error_local_terms_conditions);
    }

    @Override
    public void showErrorLocalEmailPattern() {
        initSnackbarError(R.string.error_local_email);
    }

    @Override
    public void showErrorLocalEmptyEmail() {
        initSnackbarError(R.string.error_local_empty_email);
    }

    @Override
    public void showErrorLocalEmptyUsername() {
        initSnackbarError(R.string.error_local_empty_username);
    }

    @Override
    public void showErrorLocalEmptyBirthdate() {
        initSnackbarError(R.string.error_local_empty_birthdate);
    }

    @Override
    public void showErrorLocalBirthdatePattern() {
        initSnackbarError(R.string.error_local_empty_birthdate_pattern);
    }

    @Override
    public void showErrorLocalUnderFourteen() {
        initSnackbarError(R.string.error_local_empty_birthdate_under_fourteen);
    }

    @Override
    public void showErrorLocalMaximumAge() {
        initSnackbarError(R.string.error_local_empty_birthdate_maximum_age);
    }

    @Override
    public void showErrorLocalAgeFuture() {
        initSnackbarError(R.string.error_local_empty_birthdate_age_future);
    }

    @Override
    public void showErrorLocalUsernamePattern() {
        initSnackbarLongError(R.string.error_local_username_pattern);
    }

    @OnClick(R.id.terms_conditions)
    public void onTermsConditionsClicked() {
        Uri webpage = Uri.parse(getString(R.string.url_terms_conditions_mock));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    @OnClick(R.id.register_birthdate)
    public void onRegisterBirthdateClieck() {
        int actualYear = Integer.valueOf(lastDateSelected.substring(0, 4));

        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(getActivity(), new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                String dayString = "" + (day > 9 ? day : "0" + day);
                String monthString = "" + (month > 9 ? month : "0" + month);
                registerBirthdateView.setText(dayString + "/" + monthString + "/" + year);
                lastDateSelected = dateDesc;
            }
        }).textConfirm(getString(R.string.dialog_date_picker_confirm))
                .textCancel(getString(R.string.dialog_date_picker_cancel))
                .btnTextSize((int) getResources().getDimension(R.dimen.textsize_big1_px))
                .viewTextSize((int) getResources().getDimension(R.dimen.textsize_huge4_px))
                .colorCancel(getResources().getColor(R.color.grey1))
                .colorConfirm(getResources().getColor(R.color.enabled_green))
                .minYear(actualYear - 120)
                .maxYear(actualYear + 1)
                .dateChose(lastDateSelected)
                .build();

        pickerPopWin.showPopWin(getActivity());
    }

    @Override
    public void showMyUserProfile(MyUserProfileViewModel userProfileViewModel) {
        if (registerBirthdateView.getText().toString().isEmpty()) {
            registerBirthdateView.setText(dateUtils.stringDateToBirthdate(userProfileViewModel.getBirthdate()));
        }

        if (editRegisterUsernameView.getText().toString().isEmpty()) {
            editRegisterUsernameView.setText(userProfileViewModel.getUsername().substring(1, userProfileViewModel.getUsername().length()));
        }

        if (userProfileViewModel.getEmail().isEmpty()) {
            editRegisterEmailView.setVisibility(View.VISIBLE);
            textRegisterEmailView.setVisibility(View.VISIBLE);

            editRegisterUsernameView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            editRegisterEmailView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            editRegisterUsernameView.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editRegisterEmailView.setRawInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    @Override
    public void showCompletedProfileError() {
        initSnackbarError(R.string.error_update_profile);
    }

    @Override
    public void showCompletedProfile() {
        disableFocusableView();
        userProfilePreferencesDataSource.setUsername(editRegisterUsernameView.getText().toString());
        userProfilePreferencesDataSource.setGender(
                gender.getDirection() == StickySwitch.Direction.LEFT ? UserGenderType.MALE.getValue() : UserGenderType.FEMALE.getValue());
        userProfilePreferencesDataSource.setBirthdate(
                dateUtils.mapperDateProfileToDateComplete(registerBirthdateView.getText().toString()));

        if (textRegisterEmailView.getVisibility() == View.VISIBLE) {
            userProfilePreferencesDataSource.setEmail(editRegisterEmailView.getText().toString());
        }

        SplashActivity.open(getActivity());
    }

    @OnClick(R.id.linear_promo_code)
    public void onPromoCodeClicked() {
        PromoCodeActivity.open(getActivity());
    }

    @OnClick(R.id.finish)
    public void onFinishClicked() {
        if (textRegisterEmailView.getVisibility() == View.VISIBLE) {
            presenter.completeProfileWithEmail(editRegisterUsernameView.getText().toString(),
                    editRegisterEmailView.getText().toString(),
                    gender.getDirection() == StickySwitch.Direction.LEFT ? UserGenderType.MALE.getValue() : UserGenderType.FEMALE.getValue(),
                    registerBirthdateView.getText().toString(), checkboxTermsConditions.isChecked());
        } else {
            presenter.completeProfile(editRegisterUsernameView.getText().toString(),
                    gender.getDirection() == StickySwitch.Direction.LEFT ? UserGenderType.MALE.getValue() : UserGenderType.FEMALE.getValue(),
                    registerBirthdateView.getText().toString(), checkboxTermsConditions.isChecked());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        hideLoading();
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_PROMO_CODE) {
            handlerSound.postDelayed(runnableSounds, 10);
            NumberUtils numberUtils = new NumberUtils();
            String extraMeters = numberUtils.rangeOrScopeToStringWithoutDecimals(METERS_SHARE_PROMO_CODE);
            String winExtraMeters = numberUtils.rangeOrScopeToStringWithoutDecimals(WIN_METERS_SHARE_PROMO_CODE);
            userProfilePreferencesDataSource.setRange(userProfilePreferencesDataSource.getRange() + METERS_SHARE_PROMO_CODE);

            if (userProfilePreferencesDataSource.getRange() == REGISTER_INITIAL_METERS_WITH_PROMOCODE) {
                userProfilePreferencesDataSource.setLevel(REGISTER_INITIAL_LEVEL_WITH_PROMOCODE);
            }

            linearPromoCodeView.setClickable(false);
            linearPromoCodeView.setBackgroundResource(R.drawable.background_promo_code_disabled);
            DialogInfo.openPromoCodeExchanged(getActivity(), getFragmentManager(), R.drawable.dialog_promo_code_win,
                    getString(R.string.promo_code_title, extraMeters), getString(R.string.promo_code_description, extraMeters,
                            winExtraMeters, extraMeters), DialogInfo.HEIGHT_BIG_DIALOG);
        }
    }
}
