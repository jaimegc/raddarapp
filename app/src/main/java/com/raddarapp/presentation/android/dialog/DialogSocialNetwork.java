package com.raddarapp.presentation.android.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FontUtils;

@SuppressLint("ValidFragment")
public class DialogSocialNetwork extends DialogFragment {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    public static final int HEIGHT_DIALOG = 470;
    private static final int WIDTH_DIALOG = 200;
    public static String TAG = "DialogCategories";
    private Context context;

    private TextView selectSocialNetwork;
    private TextView closeView;

    private DimenUtils dimenUtils = new DimenUtils();

    private DialogSocialNetwork(Context context) {
        this.context = context;
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
        View view = inflater.inflate(R.layout.dialog_social_network, null);

        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        selectSocialNetwork = (TextView) view.findViewById(R.id.select_social_network);
        closeView = (TextView) view.findViewById(R.id.message_close);

        initializeFonts();

        closeView.setOnClickListener(v -> dismiss());

        return dialog;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, selectSocialNetwork);
    }

    public static void open(Context context, FragmentManager fm) {
        DialogSocialNetwork dialogComingSoon = new DialogSocialNetwork(context);
        dialogComingSoon.show(fm, TAG);
    }
}