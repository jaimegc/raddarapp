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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FontUtils;

@SuppressLint("ValidFragment")
public class DialogMyFootprintCollection extends DialogFragment {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int WIDTH_DIALOG = 302;
    private static int HEIGHT_DIALOG = 180;
    public static String TAG = "DialogMyFootprintCollection";
    private Context context;

    private TextView topView;
    private TextView textDiscardFlagView;
    private TextView cancelView;
    private LinearLayout linearDiscardFlagView;

    private OnDialogMyFootprintsListener onDialogMyFootprintsListener;

    private DimenUtils dimenUtils = new DimenUtils();

    private DialogMyFootprintCollection(Context context, OnDialogMyFootprintsListener onDialogMyFootprintsListener) {
        this.context = context;
        this.onDialogMyFootprintsListener = onDialogMyFootprintsListener;
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
        params.height = (int) dimenUtils.dpToPx(context, HEIGHT_DIALOG);

        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_card_white);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_my_footprint_collection, null);
        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        topView = (TextView) view.findViewById(R.id.message_top);
        textDiscardFlagView = (TextView) view.findViewById(R.id.text_discard_flag);
        cancelView = (TextView) view.findViewById(R.id.message_cancel);
        linearDiscardFlagView = (LinearLayout) view.findViewById(R.id.linear_discard_flag);

        initializeFonts();

        cancelView.setOnClickListener(v -> dismiss());

        linearDiscardFlagView.setOnClickListener(v -> onOptionClicked());

        linearDiscardFlagView.setOnLongClickListener(v -> deleteMyFootprint());

        return dialog;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, topView, textDiscardFlagView, cancelView);
    }

    public static void open(Context context, FragmentManager fm, OnDialogMyFootprintsListener onDialogMyFootprintsListener) {
        DialogMyFootprintCollection dialogComingSoon = new DialogMyFootprintCollection(context, onDialogMyFootprintsListener);
        dialogComingSoon.show(fm, TAG);
    }

    private void onOptionClicked() {
        FontUtils fontUtils = new FontUtils();
        Snackbar snackbarError = Snackbar.make(getDialog().getWindow().getDecorView(), getString(R.string.dialog_option_clicked), Snackbar.LENGTH_SHORT);
        View snackBarView = snackbarError.getView();
        snackBarView.setBackgroundColor(Color.WHITE);
        TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        message.setTextColor(Color.BLACK);
        fontUtils.applyFont(context, FONT_NAME, message);
        snackbarError.show();
    }

    private boolean deleteMyFootprint() {
        onDialogMyFootprintsListener.onMyFootprintDelete();
        dismiss();

        return true;
    }

    public interface OnDialogMyFootprintsListener {
        void onMyFootprintDelete();
    }
}