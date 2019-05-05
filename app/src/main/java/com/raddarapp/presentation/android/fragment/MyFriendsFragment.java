package com.raddarapp.presentation.android.fragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.FontUtils;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFriendsFragment extends BaseNormalFragment {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

    private static final int REQUEST_STORAGE_PERMISSION = 102;

    @BindView(R.id.title_my_friends) TextView titleMyFriends;
    @BindView(R.id.invite_my_friends) Button inviteMyFriends;

    public static MyFriendsFragment newInstance() {
        MyFriendsFragment fragment = new MyFriendsFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_friends;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        initializeFonts();
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, titleMyFriends, inviteMyFriends);
    }

    @OnClick(R.id.invite_my_friends)
    public void onFlagsCollectionClicked() {
        if (checkStoragePermission()) {
            inviteMyFriends();
        } else {
            getStoragePermission();
        }
    }

    private boolean checkStoragePermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void getStoragePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_STORAGE_PERMISSION){
            if (grantResults.length== 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                inviteMyFriends();
            } else {
                showStoragePermissionInviteMyFriendsError();
            }
        }
    }

    private void inviteMyFriends() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.share_promo_code);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 85, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm, getString(R.string.my_friends_invite_code), getString(R.string.my_friends_invite_code));
        Uri imageUri =  Uri.parse(path);
        String text = getString(R.string.my_friends_invite_text);

        final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        final ClipData clipData = ClipData.newPlainText(text, text);
        clipboardManager.setPrimaryClip(clipData);

        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.my_friends_invite_subject));
        intent.putExtra(Intent.EXTRA_TEXT, text);

        intent.setType("image/jpeg");
        startActivity(Intent.createChooser(intent, getString(R.string.my_friends_invite_via)));
    }
}
