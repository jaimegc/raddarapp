package com.raddarapp.presentation.android.fragment;


import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.general.presenter.UpdatePasswordProfilePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class EditMyProfileChangeFieldFragment extends BaseNormalFragment implements UpdatePasswordProfilePresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String CHANGE_FIELD_TYPE_KEY_EXTRA = "EditMyProfileChangeFieldActivity.TypeChangeField";
    private static final String CHANGE_FIELD_TEXT_KEY_EXTRA = "EditMyProfileChangeFieldActivity.TypeChangeText";
    private static final int CHANGE_FIELD_TYPE_USERNAME = 0;
    private static final int CHANGE_FIELD_TYPE_NAME = 1;
    private static final int CHANGE_FIELD_TYPE_SURNAME = 2;
    private static final int CHANGE_FIELD_TYPE_EMAIL = 3;
    private static final int CHANGE_FIELD_TYPE_PASSWORD = 4;

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.edit_text) EditText editTextView;
    @BindView(R.id.edit_text_new) EditText editTextNewView;
    @BindView(R.id.edit_text_new_repeat) EditText editTextNewRepeatView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.relative_go) RelativeLayout relativeGo;

    @Inject @Presenter
    UpdatePasswordProfilePresenter updatePasswordProfilePresenter;

    private String text;
    private int actualChangeFieldType;

    private AnimationUtils animationUtils = new AnimationUtils();

    public static EditMyProfileChangeFieldFragment newInstance() {
        EditMyProfileChangeFieldFragment fragment = new EditMyProfileChangeFieldFragment();
        return fragment;
    }

    public static EditMyProfileChangeFieldFragment newInstance(int changeFieldType, String text) {
        EditMyProfileChangeFieldFragment fragment = new EditMyProfileChangeFieldFragment();

        Bundle args = new Bundle();

        args.putInt(CHANGE_FIELD_TYPE_KEY_EXTRA, changeFieldType);
        args.putString(CHANGE_FIELD_TEXT_KEY_EXTRA, text);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_my_profile_change_field;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        actualChangeFieldType = getArguments().getInt(CHANGE_FIELD_TYPE_KEY_EXTRA);
        text = getArguments().getString(CHANGE_FIELD_TEXT_KEY_EXTRA);

        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, editTextView, editTextNewView, editTextNewRepeatView);

        initializeFields(text);
    }

    private void initializeFields(String text) {

        switch (actualChangeFieldType) {
            case CHANGE_FIELD_TYPE_USERNAME:
                titleView.setText(getString(R.string.edit_my_profile_change_field_username));
                editTextView.setHint(getString(R.string.edit_my_profile_username_hint));
                text = text.substring(1, text.length());
                editTextNewView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                editTextNewRepeatView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case CHANGE_FIELD_TYPE_NAME:
                titleView.setText(getString(R.string.edit_my_profile_change_field_name));
                editTextView.setHint(getString(R.string.edit_my_profile_name_hint));
                editTextNewView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                editTextNewRepeatView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case CHANGE_FIELD_TYPE_SURNAME:
                titleView.setText(getString(R.string.edit_my_profile_change_field_surname));
                editTextView.setHint(getString(R.string.edit_my_profile_surname_hint));
                editTextNewView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                editTextNewRepeatView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case CHANGE_FIELD_TYPE_EMAIL:
                titleView.setText(getString(R.string.edit_my_profile_change_field_email));
                editTextView.setHint(getString(R.string.edit_my_profile_email_hint));
                editTextView.setEnabled(false);
                editTextView.setClickable(false);
                editTextNewView.setVisibility(View.VISIBLE);
                editTextNewRepeatView.setVisibility(View.VISIBLE);
                editTextNewView.setHint(getString(R.string.edit_my_profile_new_email_hint));
                editTextNewRepeatView.setHint(getString(R.string.edit_my_profile_repeat_email_hint));
                editTextNewView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                editTextNewView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                editTextNewRepeatView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                editTextNewRepeatView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case CHANGE_FIELD_TYPE_PASSWORD:
                titleView.setText(getString(R.string.edit_my_profile_change_field_password));
                editTextView.setHint(getString(R.string.edit_my_profile_actual_password_hint));
                editTextNewView.setVisibility(View.VISIBLE);
                editTextNewRepeatView.setVisibility(View.VISIBLE);
                editTextNewView.setHint(getString(R.string.edit_my_profile_new_password_hint));
                editTextNewRepeatView.setHint(getString(R.string.edit_my_profile_repeat_password_hint));
                editTextNewRepeatView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextNewView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editTextNewView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editTextNewRepeatView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editTextView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                editTextView.setRawInputType(InputType.TYPE_CLASS_TEXT);
                break;
        }

        editTextView.setText(text);
        editTextView.setSelection(editTextView.getText().length());
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.relative_go)
    public void onGoClicked() {

        if (actualChangeFieldType != CHANGE_FIELD_TYPE_PASSWORD) {
            DialogInfo.openComingSoon(getActivity(), getFragmentManager(), R.drawable.logo_coming_soon,
                    getString(R.string.coming_soon_edit_profile_title), getString(R.string.coming_soon_edit_profile_description), DialogInfo.HEIGHT_SMALL_DIALOG);
        } else {
            disableLoginFocusableView();
            updatePasswordProfilePresenter.onUpdatePasswordProfileClicked(editTextNewView.getText().toString(),
                    editTextNewRepeatView.getText().toString(), editTextView.getText().toString());
        }

        /*disableLoginFocusableView();

        switch (actualChangeFieldType) {
            case CHANGE_FIELD_TYPE_USERNAME:
                break;
            case CHANGE_FIELD_TYPE_NAME:
                break;
            case CHANGE_FIELD_TYPE_SURNAME:
                break;
            case CHANGE_FIELD_TYPE_EMAIL:
                break;
            case CHANGE_FIELD_TYPE_PASSWORD:
                updatePasswordProfilePresenter.onUpdatePasswordProfileClicked(editTextNewView.getText().toString(),
                        editTextNewRepeatView.getText().toString(), editTextView.getText().toString());
                break;
        }*/
    }

    @Override
    public void showErrorLocalFillAllPasswords() {
        initSnackbarError(R.string.error_local_fill_all_passwords);
        enableLoginFocusableView();
    }

    @Override
    public void showErrorLocalNewDifferentPasswords() {
        initSnackbarError(R.string.error_local_new_different_passwords);
        enableLoginFocusableView();
    }

    @Override
    public void showErrorLocalAllEqualsPasswords() {
        initSnackbarError(R.string.error_local_all_equals_passwords);
        enableLoginFocusableView();
    }

    @Override
    public void showUpdatedPasswordProfile() {
        initSnackbar(R.string.edit_my_profile_change_password_ok);
        enableLoginFocusableView();
        resetAllTexts();
    }

    @Override
    public void showUpdatePasswordProfileError() {
        enableLoginFocusableView();
    }

    @Override
    public void showUpdatePasswordProfileLoading() {
        animationUtils.alphaAnimationWithInitial(linearLoadingView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void hideUpdatePasswordProfileLoading() {
        linearLoadingView.setVisibility(View.GONE);
    }

    private void enableLoginFocusableView() {
        editTextNewView.setFocusable(true);
        editTextNewRepeatView.setFocusable(true);
        editTextView.setFocusable(true);
        editTextNewView.setFocusableInTouchMode(true);
        editTextNewRepeatView.setFocusableInTouchMode(true);
        editTextView.setFocusableInTouchMode(true);
        relativeGo.setEnabled(true);
        relativeGo.setClickable(true);
    }

    private void disableLoginFocusableView() {
        editTextNewView.setFocusable(false);
        editTextNewRepeatView.setFocusable(false);
        editTextView.setFocusable(false);
        editTextNewView.setFocusableInTouchMode(false);
        editTextNewRepeatView.setFocusableInTouchMode(false);
        editTextView.setFocusableInTouchMode(false);
        relativeGo.setEnabled(false);
        relativeGo.setClickable(false);
    }

    private void resetAllTexts() {
        editTextNewView.setText("");
        editTextNewRepeatView.setText("");
        editTextView.setText("");
    }

    public interface OnExchangedListener {
        void onExchanged(int coinMinedWon);
    }
}
