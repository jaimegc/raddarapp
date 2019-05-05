package com.raddarapp.presentation.android.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;

import top.oply.opuslib.OpusRecorder;

@SuppressLint("ValidFragment")
public class DialogRecordProfileAudio extends DialogFragment {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final long NUMBER_SECONDS = 10;
    private static final long NUMBER_MILLIS = NUMBER_SECONDS * 1000;
    private static final int DELAY = 10;
    private static final int WIDTH_DIALOG = 262;
    public static final int HEIGHT_DIALOG = 236;
    protected static final int REQUEST_DIALOG_PROFILE_AUDIO = 300;
    protected static final String DIALOG_PROFILE_AUDIO_EXTRA = "ProfileProfileAudioExtra";
    public static String TAG = "DialogComingSoon";
    private Context context;

    private ImageView recordView;
    private TextView messageProfileAudioTop;
    private TextView messageProfileAudioBottom;
    private TextView timeView;

    private DimenUtils dimenUtils = new DimenUtils();
    private FileUtils fileUtils;

    private boolean isRecording = false;
    private boolean hasAudio = false;
    private OpusRecorder opusRecorder;
    private CountDownTimer counter;

    public DialogRecordProfileAudio(Context context) {
        this.context = context;
        opusRecorder = OpusRecorder.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MenuDialogComingSoon);
        fileUtils = new FileUtils(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) dimenUtils.dpToPx(context, WIDTH_DIALOG);
        params.height = (int) dimenUtils.dpToPx(context, HEIGHT_DIALOG);

        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_record_profile_audio, null);
        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        recordView = (ImageView) view.findViewById(R.id.record);
        timeView = (TextView) view.findViewById(R.id.time);
        messageProfileAudioTop = (TextView) view.findViewById(R.id.message_profile_audio_top);
        messageProfileAudioBottom = (TextView) view.findViewById(R.id.message_profile_audio_bottom);

        initializeFonts();

        recordView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    record();
                    break;
                case MotionEvent.ACTION_UP:
                    stopRecording();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    stopRecording();
                    break;
                default:
                    break;
            }

            return true;
        });

        counter = new CountDownTimer(NUMBER_MILLIS, DELAY) {
            @Override
            public void onTick(long millisUntilFinished) {

                String time = String.valueOf(millisUntilFinished);

                if (time.length() == 4) {
                    timeView.setText("0" + time.substring(0, 1) + ":" + time.substring(1, 3));
                } else if (time.length() == 3) {
                    timeView.setText("00:" + time.substring(0, 2));
                } else if (time.length() == 2) {
                    if (millisUntilFinished < 60) {
                        // Max 9s
                        timeView.setText("0" + NUMBER_SECONDS + ":00");
                        recordView.setImageResource(R.drawable.ic_profile_audio_mic);
                    } else {
                        timeView.setText("00:0" + time.substring(0, 1));
                    }
                }
            }

            @Override
            public void onFinish() {
                stopRecording();
            }
        };

        return dialog;
    }

    public static void open(Context context, FragmentManager fm, Fragment fragment) {
        DialogRecordProfileAudio dialogRecordProfileAudio = new DialogRecordProfileAudio(context);
        dialogRecordProfileAudio.setTargetFragment(fragment, REQUEST_DIALOG_PROFILE_AUDIO);
        dialogRecordProfileAudio.show(fm, TAG);
    }

    private void stopRecording() {
        if (isRecording) {
            // Max 9s
            timeView.setText("0" + NUMBER_SECONDS + ":00");
            recordView.setImageResource(R.drawable.ic_profile_audio_mic);
            boolean error = false;
            try {
                counter.cancel();
                opusRecorder.stopRecording();
            } catch (Exception e) {
                error = true;
            }

            isRecording = false;

            if (!error) {
                fileUtils.saveMyUserProfileAudioCache();
            }

            dismiss();
        }
    }

    private void record() {
        if (!isRecording) {
            recordView.setImageResource(R.drawable.ic_profile_audio_recording);
            boolean error = false;

            try {
                opusRecorder.startRecording(fileUtils.getPathMyUserProfileAudioCache());
                counter.start();
            } catch (Exception e) {
                error = true;
            }

            if (!error) {
                hasAudio = true;
                isRecording = true;
                fileUtils.clearMyUserProfileAudio();
            }
        }
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, timeView, messageProfileAudioTop, messageProfileAudioBottom);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (opusRecorder.isWorking()) {
            stopRecording();
        }

        if (hasAudio) {
            Intent intent = new Intent();
            intent.putExtra(DIALOG_PROFILE_AUDIO_EXTRA, fileUtils.getPathMyUserProfileAudioCache());

            getTargetFragment().onActivityResult(REQUEST_DIALOG_PROFILE_AUDIO, Activity.RESULT_OK, intent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (opusRecorder.isWorking()) {
            stopRecording();
        }
    }
}