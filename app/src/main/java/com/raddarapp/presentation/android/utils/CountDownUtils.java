package com.raddarapp.presentation.android.utils;

import android.widget.TextView;

import com.raddarapp.presentation.android.view.CountDown;

public class CountDownUtils {

    private static CountDown countDownTimer;
    private static final Long TIME_24_HOURS = 60 * 60 * 24 * 1000l;
    private CountDownListener listener;

    public void initializeCountDownCoinMining(TextView textTime, String dateString, CountDownListener listener) {
        this.listener = listener;

        if (countDownTimer != null) {
            countDownTimer.cancel();
        } else {
            countDownTimer = new CountDown();
        }

        countDownTimer.setMillisInFuture(new DateUtils().millisfromServerDateToMidnight(dateString));
        countDownTimer.setCountdownInterval(1000);
        countDownTimer.setCountDownListener(new CountDown.CountDownListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onFinish() {
                textTime.setText(getCountTimeByLong(0));
                onFinishCountDown(textTime, listener);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText(getCountTimeByLong(millisUntilFinished));

                if (millisUntilFinished < 1000) {
                    listener.onFinish();
                }
            }
        });

        if (countDownTimer != null) {
            countDownTimer.start();
        }

    }

    private void onFinishCountDown(TextView textTime, CountDownListener listener) {
        if (countDownTimer != null) {
            countDownTimer.cancel();

            countDownTimer.setMillisInFuture(TIME_24_HOURS);
            countDownTimer.setCountdownInterval(1000);
            countDownTimer.setCountDownListener(new CountDown.CountDownListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onFinish() {
                    textTime.setText(getCountTimeByLong(0));
                    onFinishCountDown(textTime, listener);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    textTime.setText(getCountTimeByLong(millisUntilFinished));

                    if (millisUntilFinished < 1000) {
                        listener.onFinish();
                    }
                }
            });

            countDownTimer.start();
        }
    }

    public void cancelCountDown() {
        listener = null;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }

        return sb.toString();
    }

    public interface CountDownListener {
        void onFinish();
    }
}
