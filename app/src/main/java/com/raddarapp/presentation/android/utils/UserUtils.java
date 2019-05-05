package com.raddarapp.presentation.android.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.pkmmte.view.CircularImageView;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.UserRelationshipType;

public class UserUtils {

    public void setUserCircularImageBorderColor(Context context, int userRelationship, CircularImageView circularImageView) {
        if (userRelationship == UserRelationshipType.UNKNOWN.getValue()) {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_unknown));
        } else if (userRelationship == UserRelationshipType.FOLLOWING.getValue()) {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_following));
        } else if (userRelationship == UserRelationshipType.FRIEND.getValue()) {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_friend));
        } else {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_default));
        }
    }

    public void setUserCircularImageFlagBorderColor(Context context, int userRelationship, CircularImageView circularImageView, View flagBallView) {
        if (userRelationship == UserRelationshipType.UNKNOWN.getValue()) {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_unknown));
            flagBallView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.marker_unknown)));
        } else if (userRelationship == UserRelationshipType.FOLLOWING.getValue()) {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_following));
            flagBallView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.marker_following)));
        } else if (userRelationship == UserRelationshipType.FRIEND.getValue()) {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_friend));
            flagBallView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.marker_friend)));
        } else {
            circularImageView.setBorderColor(ContextCompat.getColor(context, R.color.marker_default));
            flagBallView.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.marker_default)));
        }
    }
}
