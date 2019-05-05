package com.raddarapp.presentation.android.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FontUtils;

@SuppressLint("ValidFragment")
public class DialogActivateDesactivateUser extends DialogFragment {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String TOP_KEY_EXTRA = "DialogInfo.TopKeyExtra";
    private static final String DESCRIPTION_KEY_EXTRA = "DialogInfo.DescriptionKeyExtra";
    private static final String BUTTON_KEY_EXTRA = "DialogInfo.ButtonKeyExtra";
    private static final int WIDTH_DIALOG = 302;
    private static int HEIGHT_DIALOG;
    public static final int HEIGHT_BIG_DIALOG = 460;
    public static final int HEIGHT_NORMAL_DIALOG = 420;
    public static final int HEIGHT_SMALL_DIALOG = 380;
    public static String TAG = "DialogInfo";
    private Context context;

    private TextView topView;
    private TextView descriptionView;
    private TextView okView;
    private TextView exitView;
    private TextView termsConditionsAffirmativeOne;
    private TextView termsConditionsAffirmativeTwo;
    private TextView termsConditionsAffirmativeThree;
    private CheckBox checkboxAffirmativeOne;
    private CheckBox checkboxAffirmativeTwo;
    private CheckBox checkboxAffirmativeThree;

    private String top;
    private String okButton;
    private String description;

    private OnDialogDesactivateUserListener onDialogDesactivateUserListener = null;
    private OnDialogActivateUserListener onDialogActivateUserListener = null;

    private DimenUtils dimenUtils = new DimenUtils();

    public DialogActivateDesactivateUser() {}

    private DialogActivateDesactivateUser(String top, String description, String okButton, OnDialogDesactivateUserListener onDialogDesactivateUserListener) {
        this.top = top;
        this.description = description;
        this.okButton = okButton;
        this.onDialogDesactivateUserListener = onDialogDesactivateUserListener;
        this.onDialogActivateUserListener = null;
    }

    private DialogActivateDesactivateUser(String top, String description, String okButton, OnDialogActivateUserListener onDialogActivateUserListener) {
        this.top = top;
        this.description = description;
        this.okButton = okButton;
        this.onDialogDesactivateUserListener = null;
        this.onDialogActivateUserListener = onDialogActivateUserListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MenuDialogComingSoon);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) dimenUtils.dpToPx(context, WIDTH_DIALOG);
        params.height = (int) dimenUtils.dpToPx(context, HEIGHT_SMALL_DIALOG);

        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_card_white);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_activate_desactivate_user, null);
        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        topView = (TextView) view.findViewById(R.id.message_top);
        descriptionView = (TextView) view.findViewById(R.id.message_description);
        okView = (TextView) view.findViewById(R.id.message_ok);
        exitView = (TextView) view.findViewById(R.id.message_exit);
        checkboxAffirmativeOne = (CheckBox) view.findViewById(R.id.checkbox_affirmative_one);
        checkboxAffirmativeTwo = (CheckBox) view.findViewById(R.id.checkbox_affirmative_two);
        checkboxAffirmativeThree = (CheckBox) view.findViewById(R.id.checkbox_affirmative_three);
        termsConditionsAffirmativeOne = (TextView) view.findViewById(R.id.terms_conditions_affirmative_one);
        termsConditionsAffirmativeTwo = (TextView) view.findViewById(R.id.terms_conditions_affirmative_two);
        termsConditionsAffirmativeThree = (TextView) view.findViewById(R.id.terms_conditions_affirmative_three);

        topView.setText(top);
        descriptionView.setText(description);
        okView.setText(okButton);

        if (okButton.equals(R.string.dialog_desactivate)) {
            okView.setVisibility(View.GONE);
        } else {
            this.setCancelable(false);
        }

        initializeFonts();

        okView.setOnClickListener(v -> {
            if (checkboxAffirmativeOne.isChecked() && checkboxAffirmativeTwo.isChecked() && checkboxAffirmativeThree.isChecked()) {
                if (onDialogDesactivateUserListener != null) {
                    onDialogDesactivateUserListener.onDesactivatedUser();
                } else {
                    onDialogActivateUserListener.onActivatedUser();
                }

                dismiss();
            } else {
                selectAllCheckboxes();
            }
        });

        exitView.setOnClickListener(v -> {
            if (onDialogDesactivateUserListener != null) {
                dismiss();
            } else {
                onDialogActivateUserListener.onExitActivateUser();
            }
        });

        return dialog;
    }

    private void selectAllCheckboxes() {
        FontUtils fontUtils = new FontUtils();
        Snackbar snackbarError = Snackbar.make(getDialog().getWindow().getDecorView(), getString(R.string.dialog_select_all_checkboxes), Snackbar.LENGTH_SHORT);
        View snackBarView = snackbarError.getView();
        snackBarView.setBackgroundColor(Color.WHITE);
        TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        message.setTextColor(Color.RED);
        fontUtils.applyFont(context, FONT_NAME, message);
        snackbarError.show();
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, topView, descriptionView, okView, exitView, termsConditionsAffirmativeOne,
                termsConditionsAffirmativeTwo, termsConditionsAffirmativeThree);
    }

    public static void openActivateUser(Context context, FragmentManager fm, OnDialogActivateUserListener onDialogActivateUserListener) {
        DialogActivateDesactivateUser dialog = new DialogActivateDesactivateUser(context.getString(R.string.dialog_activate_top),
                context.getString(R.string.dialog_activate_description), context.getString(R.string.dialog_activate), onDialogActivateUserListener);

        dialog.show(fm, TAG);
    }

    public static void openDesactivateUser(Context context, FragmentManager fm, OnDialogDesactivateUserListener onDialogDesactivateUserListener) {
        DialogActivateDesactivateUser dialog = new DialogActivateDesactivateUser(context.getString(R.string.dialog_desactivate_top),
                context.getString(R.string.dialog_desactivate_description), context.getString(R.string.dialog_desactivate), onDialogDesactivateUserListener);

        dialog.show(fm, TAG);
    }

    public interface OnDialogDesactivateUserListener {
        void onDesactivatedUser();
    }

    public interface OnDialogActivateUserListener {
        void onActivatedUser();
        void onExitActivateUser();
    }
}