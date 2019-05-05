package com.raddarapp.presentation.android.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;

import java.io.ByteArrayOutputStream;

@SuppressLint("ValidFragment")
public class DialogSharePromoCode extends DialogFragment {

    private static final int METERS_SHARE_PROMO_CODE = 20000;
    private static final int WIN_METERS_SHARE_PROMO_CODE = 100000;
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String PROMO_CODE_KEY_EXTRA = "DialogInfo.DescriptionKeyExtra";
    private static final int WIDTH_DIALOG = 302;
    private static int HEIGHT_DIALOG = 420;
    public static String TAG = "DialogInfo";
    private Context context;
    private NumberUtils numberUtils = new NumberUtils();

    private TextView titleView;
    private TextView promoCodeView;
    private TextView messagePromoCodeView;
    private Button buttonCopyView;
    private Button buttonShareView;

    private String promoCode;

    private DimenUtils dimenUtils = new DimenUtils();

    public DialogSharePromoCode() {}

    public static DialogSharePromoCode newInstance(String promoCode) {
        DialogSharePromoCode fragment = new DialogSharePromoCode();

        Bundle args = new Bundle();

        args.putString(PROMO_CODE_KEY_EXTRA, promoCode);

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
        View view = inflater.inflate(R.layout.dialog_share_promo_code, null);
        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        titleView = (TextView) view.findViewById(R.id.message_title);
        promoCodeView = (TextView) view.findViewById(R.id.message_promo_code);
        messagePromoCodeView = (TextView) view.findViewById(R.id.message_share_promo_code);
        buttonCopyView = (Button) view.findViewById(R.id.button_copy);
        buttonShareView = (Button) view.findViewById(R.id.button_share);

        promoCode = getArguments().getString(PROMO_CODE_KEY_EXTRA);

        String extraMeters = numberUtils.rangeOrScopeToStringWithoutDecimals(this.METERS_SHARE_PROMO_CODE);
        String winExtraMeters = numberUtils.rangeOrScopeToStringWithoutDecimals(this.WIN_METERS_SHARE_PROMO_CODE);

        messagePromoCodeView.setText(getString(
                R.string.dialog_share_promo_code_message, extraMeters, winExtraMeters));

        promoCodeView.setText(promoCode);

        initializeFonts();

        buttonCopyView.setOnClickListener(v -> {
            final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            final ClipData clipData = ClipData.newPlainText(promoCode, promoCode);
            clipboardManager.setPrimaryClip(clipData);
            dismiss();
        });

        buttonShareView.setOnClickListener(v -> {
            dismiss();
            sharePromoCode();
        });

        return dialog;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, messagePromoCodeView, messagePromoCodeView, titleView, promoCodeView, buttonCopyView, buttonShareView);
    }

    public static void open(FragmentManager fm, String promoCode) {
        DialogSharePromoCode dialog = DialogSharePromoCode.newInstance(promoCode);

        dialog.show(fm, TAG);
    }

    private void sharePromoCode() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.share_promo_code);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 85, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm, getString(R.string.promo_code), getString(R.string.promo_code));
        Uri imageUri =  Uri.parse(path);
        String extraMeters = new NumberUtils().rangeOrScopeToStringWithoutDecimals(WIN_METERS_SHARE_PROMO_CODE);
        String text = getString(R.string.share_promo_code_text, promoCode, extraMeters);

        final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        final ClipData clipData = ClipData.newPlainText(text, text);
        clipboardManager.setPrimaryClip(clipData);

        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_promo_code_subject));
        intent.putExtra(Intent.EXTRA_TEXT, text);

        intent.setType("image/jpeg");
        startActivity(Intent.createChooser(intent, getString(R.string.share_promo_code_via)));
    }

    private void generateShare() {
        FontUtils fontUtils = new FontUtils();
        Snackbar snackbarError = Snackbar.make(getDialog().getWindow().getDecorView(), getString(R.string.dialog_share_promo_code_generating), Snackbar.LENGTH_INDEFINITE);
        View snackBarView = snackbarError.getView();
        snackBarView.setBackgroundColor(Color.WHITE);
        TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        message.setTextColor(Color.BLACK);
        fontUtils.applyFont(context, FONT_NAME, message);
        snackbarError.show();
    }
}