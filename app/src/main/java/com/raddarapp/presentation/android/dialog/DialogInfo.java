package com.raddarapp.presentation.android.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FontUtils;

@SuppressLint("ValidFragment")
public class DialogInfo extends DialogFragment {

    private static final String RADDAR_VERSION = BuildConfig.VERSION_NAME;
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String IMAGE_RES_KEY_EXTRA = "DialogInfo.ImageResKeyExtra";
    private static final String TOP_KEY_EXTRA = "DialogInfo.TopKeyExtra";
    private static final String DIAGONAL_KEY_EXTRA = "DialogInfo.DiagonalKeyExtra";
    private static final String TITLE_KEY_EXTRA = "DialogInfo.TitleKeyExtra";
    private static final String DESCRIPTION_KEY_EXTRA = "DialogInfo.DescriptionKeyExtra";
    private static final String HEIGHT_KEY_EXTRA = "DialogInfo.HeightKeyExtra";
    private static final String DESCRIPTION_ONE_CUSTOM_VALUE_KEY_EXTRA = "DialogInfo.DescriptionOneCustomValueKeyExtra";
    private static final int WIDTH_DIALOG = 302;
    private static int HEIGHT_DIALOG;
    public static final int HEIGHT_BIG_DIALOG = 460;
    public static final int HEIGHT_NORMAL_DIALOG = 420;
    public static final int HEIGHT_SMALL_DIALOG = 380;
    public static String TAG = "DialogInfo";
    private Context context;

    private TextView diagonalView;
    private TextView topView;
    private TextView titleView;
    private TextView descriptionView;
    private TextView okView;
    private ImageView imageLogoView;

    private String descriptionWithOneCustomValue;

    @DrawableRes
    private int imageRes;
    private String top;
    private String diagonal;
    private String title;
    private String description;

    private DimenUtils dimenUtils = new DimenUtils();

    public DialogInfo() {}

    public static DialogInfo newInstance(@DrawableRes int imageRes, String top, String diagonal,
            String title, String description, int height) {
        DialogInfo fragment = new DialogInfo();

        Bundle args = new Bundle();

        args.putInt(IMAGE_RES_KEY_EXTRA, imageRes);
        args.putString(TOP_KEY_EXTRA, top);
        args.putString(DIAGONAL_KEY_EXTRA, diagonal);
        args.putString(TITLE_KEY_EXTRA, title);
        args.putString(DESCRIPTION_KEY_EXTRA, description);
        args.putString(DESCRIPTION_ONE_CUSTOM_VALUE_KEY_EXTRA, "");
        args.putInt(HEIGHT_KEY_EXTRA, height);

        fragment.setArguments(args);

        return fragment;
    }

    public static DialogInfo newInstance(@DrawableRes int imageRes, String top, String diagonal,
            String title, String description, String descriptionWithOneCustomValue, int height) {
        DialogInfo fragment = new DialogInfo();

        Bundle args = new Bundle();

        args.putInt(IMAGE_RES_KEY_EXTRA, imageRes);
        args.putString(TOP_KEY_EXTRA, top);
        args.putString(DIAGONAL_KEY_EXTRA, diagonal);
        args.putString(TITLE_KEY_EXTRA, title);
        args.putString(DESCRIPTION_KEY_EXTRA, description);
        args.putString(DESCRIPTION_ONE_CUSTOM_VALUE_KEY_EXTRA, descriptionWithOneCustomValue);
        args.putInt(HEIGHT_KEY_EXTRA, height);

        fragment.setArguments(args);

        return fragment;
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
        this.context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_info, null);
        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        diagonalView = (TextView) view.findViewById(R.id.message_diagonal);
        topView = (TextView) view.findViewById(R.id.message_top);
        titleView = (TextView) view.findViewById(R.id.message_title);
        descriptionView = (TextView) view.findViewById(R.id.message_description);
        okView = (TextView) view.findViewById(R.id.message_ok);
        imageLogoView = (ImageView) view.findViewById(R.id.image);

        imageRes = getArguments().getInt(IMAGE_RES_KEY_EXTRA);
        top = getArguments().getString(TOP_KEY_EXTRA);
        diagonal = getArguments().getString(DIAGONAL_KEY_EXTRA);
        title = getArguments().getString(TITLE_KEY_EXTRA);
        description = getArguments().getString(DESCRIPTION_KEY_EXTRA);
        descriptionWithOneCustomValue = getArguments().getString(DESCRIPTION_ONE_CUSTOM_VALUE_KEY_EXTRA);
        HEIGHT_DIALOG = getArguments().getInt(HEIGHT_KEY_EXTRA);

        imageLogoView.setImageResource(imageRes);
        topView.setText(top);
        diagonalView.setText(diagonal);
        titleView.setText(title);

        if (descriptionWithOneCustomValue.isEmpty()) {
            descriptionView.setText(description);
        } else {
            descriptionView.setText(String.format(description, descriptionWithOneCustomValue));
        }

        initializeFonts();

        okView.setOnClickListener(v -> dismiss());

        return dialog;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, diagonalView, topView, titleView, descriptionView, okView);
    }

    public static void open(Context context, FragmentManager fm, @DrawableRes int imageRes, String top,
            String diagonal, String title, String description, int height) {
        DialogInfo dialog = DialogInfo.newInstance(imageRes, top, diagonal, title, description, height);

        dialog.show(fm, TAG);
    }

    public static void openComingSoon(Context context, FragmentManager fm, @DrawableRes int imageRes, String title, String description, int height) {
        DialogInfo dialog = DialogInfo.newInstance(imageRes, context.getString(R.string.coming_soon_top), context.getString(R.string.coming_soon_diagonal_beta), title, description, height);

        dialog.show(fm, TAG);
    }

    public static void openInfo(Context context, FragmentManager fm, @DrawableRes int imageRes, String title, String description,  int height) {
        DialogInfo dialog = DialogInfo.newInstance(imageRes, context.getString(R.string.help_top), context.getString(R.string.help_diagonal), title, description, height);

        dialog.show(fm, TAG);
    }

    public static void openInfo(Context context, FragmentManager fm, @DrawableRes int imageRes, String title, String description,
            String descriptionWithOneCustomValue, int height) {
        DialogInfo dialog = DialogInfo.newInstance(imageRes, context.getString(R.string.help_top),
                context.getString(R.string.help_diagonal), title, description, !descriptionWithOneCustomValue.equals("1") ?
                descriptionWithOneCustomValue : "", height);

        dialog.show(fm, TAG);
    }

    public static void openWork(Context context, FragmentManager fm, @DrawableRes int imageRes, String title, String description,  int height) {
        DialogInfo dialog = DialogInfo.newInstance(imageRes, context.getString(R.string.work_top), context.getString(R.string.work_diagonal), title, description, height);

        dialog.show(fm, TAG);
    }

    public static void openNews(Context context, FragmentManager fm, int height) {
        DialogInfo dialog = DialogInfo.newInstance(R.drawable.dialog_raddar_beta, context.getString(R.string.news_raddar_top_mock),
            context.getString(R.string.news_raddar_diagonal_mock), String.format(context.getString(R.string.news_raddar_title_mock), RADDAR_VERSION),
            context.getString(R.string.news_raddar_description_mock), height);

        dialog.show(fm, TAG);
    }

    public static void openPromoCodeExchanged(Context context, FragmentManager fm, @DrawableRes int imageRes, String title, String description, int height) {
        DialogInfo dialog = DialogInfo.newInstance(imageRes, context.getString(R.string.promo_code_top),
                context.getString(R.string.promo_code_diagonal), title, description, height);

        dialog.show(fm, TAG);
    }
}