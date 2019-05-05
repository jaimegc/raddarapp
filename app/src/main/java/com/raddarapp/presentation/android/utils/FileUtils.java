package com.raddarapp.presentation.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;

import com.makeramen.roundedimageview.RoundedImageView;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.DownloadFileApiRest;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FileUtils {

    private MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    private static final int SMALL_SIZE_PX_IMAGE_PROFILE = 200;
    private static final int BIG_SIZE_PX_IMAGE_PROFILE = 1080;
    private static final String RADDAR_IMAGES_FOLDER = "/Raddar/Raddar Images";
    private static final String PROFILE_FOLDER = "/Profile";
    private static final String FOOTPRINT_IMAGE_NAME = "RADDAR-";
    private static final String IMAGES_EXTENSION = ".jpg";
    private static final String FOOTPRINT_IMAGE_CACHE = "footprintImageCache.jpg";
    private static final String SHARE_FOOTPRINT_IMAGE_CACHE = "MyFlag-";
    private static final String MY_USER_PROFILE_IMAGE_CACHE = "myUserProfileImage.jpg";
    private static final String MY_USER_PROFILE_AUDIO_CACHE = "myUserProfileAudio.opus";
    private static final String USER_PROFILE_AUDIO_CACHE = "userProfileAudio.opus";
    private static final String IMAGE_CROPPED = "cropped.jpg";
    private static final String DATA_ORIGIN = "API_FAKE";
    private static final String SERVER_AMAZON_API_BASE_URL = BuildConfig.SERVER_AMAZON_API_BASE_URL;
    private static final String SERVER_AUDIO_API_BASE_PATH = BuildConfig.SERVER_AUDIO_API_BASE_PATH;
    private String stamp;

    private Context context;

    public FileUtils(Context context) {
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(context);
        this.context = context;
    }

    public void moveFootprintImageCacheToRaddarImagesFolder() {
        stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String footprintImageCache = context.getExternalCacheDir() + File.separator + FOOTPRINT_IMAGE_CACHE;
        String generateFootprintImagePath = FOOTPRINT_IMAGE_NAME + stamp + IMAGES_EXTENSION;

        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + RADDAR_IMAGES_FOLDER);
        storageDir.mkdirs();

        generateFootprintImagePath = storageDir.getPath() + File.separator + generateFootprintImagePath;

        File from = new File(footprintImageCache);

        if (from.exists()) {
            File to = new File(generateFootprintImagePath);

            try {
                from.renameTo(to);
                userProfilePreferencesDataSource.setTotalFootprints(userProfilePreferencesDataSource.getTotalFootprints() + 1);
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
            }
        }
    }

    public void saveMyUserProfileImageAfterTakePhoto() {
        String imageProfileCropped = getPathImageCropped();
        String imageProfileCacheUri = getPathMyUserProfileImageCache();

        File from = new File(imageProfileCropped);

        if (from.exists()) {
            File to = new File(imageProfileCacheUri);

            try {
                from.renameTo(to);

                saveMyUserProfileImageCache();
                // To avoid the image cache
                Picasso.with(context).invalidate(to);
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
            }
        }
    }

    public void loadMyUserProfileImageCache(String baseUrl, final CircularImageView userImageView, boolean isSmall) {
        String imageUserProfile = getPathMyUserProfileImageCache();
        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + File.separator + PROFILE_FOLDER);
        File fileExists = new File(imageUserProfile);

        if (userProfilePreferencesDataSource.getImageCache() != null &&
                !userProfilePreferencesDataSource.getImageCache().isEmpty() && fileExists.exists()) {

            if (isSmall) {
                Picasso.with(context)
                        .load(new File(imageUserProfile))
                        .placeholder(R.drawable.placeholder_profile)
                        .resize(SMALL_SIZE_PX_IMAGE_PROFILE, SMALL_SIZE_PX_IMAGE_PROFILE)
                        .centerCrop()
                        .into(userImageView);
            } else {
                Picasso.with(context)
                        .load(new File(imageUserProfile))
                        .placeholder(R.drawable.placeholder_profile_big)
                        .resize(BIG_SIZE_PX_IMAGE_PROFILE, BIG_SIZE_PX_IMAGE_PROFILE)
                        .centerCrop()
                        .into(userImageView);
            }

        } else {
            if (userProfilePreferencesDataSource.getImage() != null && !userProfilePreferencesDataSource.getImage().isEmpty()) {
                Picasso.with(context)
                        .load(baseUrl + userProfilePreferencesDataSource.getImage())
                        .placeholder(isSmall ? R.drawable.placeholder_profile : R.drawable.placeholder_profile_big)
                        .resize(BIG_SIZE_PX_IMAGE_PROFILE, BIG_SIZE_PX_IMAGE_PROFILE) // For first download always big size
                        .centerCrop()
                        .into(userImageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Bitmap bitmap = ((BitmapDrawable) userImageView.getDrawable()).getBitmap();

                                try {
                                    storageDir.mkdirs();
                                    //fileExists.createNewFile();

                                    FileOutputStream ostream = new FileOutputStream(fileExists);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, ostream);
                                    ostream.flush();
                                    ostream.close();
                                    userProfilePreferencesDataSource.setImageCache(imageUserProfile);
                                } catch (IOException e) {}
                            }

                            @Override
                            public void onError() {}
                        });
            } else {
                if (isSmall) {
                    userImageView.setImageResource(R.drawable.placeholder_profile);
                } else {
                    userImageView.setImageResource(R.drawable.placeholder_profile_big);
                }
            }
        }
    }

    public String urlMyUserProfileImage(String baseUrl) {
        String imageUserProfile = getPathMyUserProfileImageCache();
        String urlFinal = "";
        File fileExists = new File(imageUserProfile);

        if (userProfilePreferencesDataSource.getImageCache() != null &&
                !userProfilePreferencesDataSource.getImageCache().isEmpty() && fileExists.exists()) {

            urlFinal = imageUserProfile;

        } else {
            if (userProfilePreferencesDataSource.getImage() != null && !userProfilePreferencesDataSource.getImage().isEmpty()) {
                urlFinal = baseUrl + userProfilePreferencesDataSource.getImage();
            }
        }

        return urlFinal;
    }

    // This method is only for Create Footprint because we need export the image profile and we
    // can't with the CircularImageView
    public void loadMyUserProfileImageCache(String baseUrl, final RoundedImageView userImageView) {
        String imageUserProfile = getPathMyUserProfileImageCache();
        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + File.separator + PROFILE_FOLDER);
        File fileExists = new File(imageUserProfile);

        if (userProfilePreferencesDataSource.getImageCache() != null &&
                !userProfilePreferencesDataSource.getImageCache().isEmpty() && fileExists.exists()) {
            Picasso.with(context)
                    .load(new File(imageUserProfile))
                    .placeholder(R.drawable.placeholder_profile)
                    .resize(SMALL_SIZE_PX_IMAGE_PROFILE, SMALL_SIZE_PX_IMAGE_PROFILE)
                    .centerCrop()
                    .into(userImageView);
        } else {
            if (userProfilePreferencesDataSource.getImage() != null && !userProfilePreferencesDataSource.getImage().isEmpty()) {
                Picasso.with(context)
                        .load(baseUrl + userProfilePreferencesDataSource.getImage())
                        .placeholder(R.drawable.placeholder_profile)
                        .resize(BIG_SIZE_PX_IMAGE_PROFILE, BIG_SIZE_PX_IMAGE_PROFILE) // For first download always big size
                        .centerCrop()
                        .into(userImageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Bitmap bitmap = ((BitmapDrawable) userImageView.getDrawable()).getBitmap();

                                try {
                                    storageDir.mkdirs();
                                    //fileExists.createNewFile();

                                    FileOutputStream ostream = new FileOutputStream(fileExists);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, ostream);
                                    ostream.flush();
                                    ostream.close();
                                    userProfilePreferencesDataSource.setImageCache(imageUserProfile);
                                } catch (IOException e) {}
                            }

                            @Override
                            public void onError() {}
                        });
            } else {
                userImageView.setImageResource(R.drawable.placeholder_profile);
            }
        }
    }

    public void removeMyUserProfileImageCache() {
        String imageUserProfile = context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER + File.separator + MY_USER_PROFILE_IMAGE_CACHE;
        File fileExists = new File(imageUserProfile);

        if (fileExists.exists()) {
            fileExists.delete();
        }
    }

    public String getPathMyUserProfileImageCache() {
        String path = context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER + File.separator + MY_USER_PROFILE_IMAGE_CACHE;

        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER);
        storageDir.mkdirs();

        return path;
    }

    public String getPathImageCropped() {
        String path = context.getExternalCacheDir().getAbsolutePath() + File.separator + IMAGE_CROPPED;

        return path;
    }

    public void saveMyUserProfileAudioCache() {
        userProfilePreferencesDataSource.setAudioCache(getPathMyUserProfileAudioCache());
    }

    public void saveMyUserProfileImageCache() {
        userProfilePreferencesDataSource.setImageCache(getPathMyUserProfileImageCache());
    }

    public void clearMyUserProfileAudio() {
        userProfilePreferencesDataSource.setAudio("");
    }

    public void clearMyUserProfileImage() {
        userProfilePreferencesDataSource.setImage("");
    }

    public String getPathMyUserProfileAudioCache() {
        String path = context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER + File.separator + MY_USER_PROFILE_AUDIO_CACHE;

        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER);
        storageDir.mkdirs();

        return path;
    }

    public String getPathUserProfileAudioCache() {
        String path = context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER + File.separator + USER_PROFILE_AUDIO_CACHE;

        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + PROFILE_FOLDER);
        storageDir.mkdirs();

        return path;
    }

    public File getCaptureScreenToShare(View view) {
        stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = null;
        Bitmap bitmap = getBitmapFromView(view);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        try {
            file = new File(context.getExternalCacheDir(), SHARE_FOOTPRINT_IMAGE_CACHE + stamp + IMAGES_EXTENSION);
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
        } catch (Exception e) {}

        return file;
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }

        view.draw(canvas);

        return bitmap;
    }

    public void loadMyUserProfileAudioCache(OnFilePathFinishListener onFilePathFinishListener) {
        String audioUserProfile = getPathMyUserProfileAudioCache();

        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + File.separator + PROFILE_FOLDER);
        File fileExists = new File(audioUserProfile);

        if (userProfilePreferencesDataSource.getAudioCache() != null &&
                !userProfilePreferencesDataSource.getAudioCache().isEmpty() && fileExists.exists()) {
            onFilePathFinishListener.filePath(userProfilePreferencesDataSource.getAudioCache());
        } else {

            if (userProfilePreferencesDataSource.getAudio() != null && !userProfilePreferencesDataSource.getAudio().isEmpty()) {
                Retrofit retrofit = new Retrofit.Builder().
                        baseUrl(SERVER_AMAZON_API_BASE_URL).
                        build();

                DownloadFileApiRest service = retrofit.create(DownloadFileApiRest.class);

                Call<ResponseBody> call = service.downloadFileByUrl(SERVER_AUDIO_API_BASE_PATH + userProfilePreferencesDataSource.getAudio());

                call.enqueue(new retrofit2.Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {

                            try {
                                storageDir.mkdir();
                                // Error on Android 5.x first time...
                                //fileExists.createNewFile();

                                File file = new File(fileExists.getAbsolutePath());

                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                IOUtils.write(response.body().bytes(), fileOutputStream);

                                userProfilePreferencesDataSource.setAudioCache(file.getPath());
                                onFilePathFinishListener.filePath(file.getPath());

                            } catch (IOException e) {
                                onFilePathFinishListener.onError();
                            } catch (Exception e) {
                                onFilePathFinishListener.onError();
                            }

                        } else {
                            onFilePathFinishListener.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        onFilePathFinishListener.onError();
                    }
                });
            }
        }
    }

    public void loadUserProfileAudioCache(OnFilePathFinishListener onFilePathFinishListener, String pathAudioProfile) {
        String audioUserProfile = getPathUserProfileAudioCache();

        File storageDir = new File(context.getExternalCacheDir().getAbsolutePath() + File.separator + PROFILE_FOLDER);
        File fileExists = new File(audioUserProfile);

        if (fileExists.exists()) {
            onFilePathFinishListener.filePath(fileExists.getAbsolutePath());
        } else {

            Retrofit retrofit;
            Call<ResponseBody> call;
            DownloadFileApiRest service;

            if (!BuildConfig.DATA_ORIGIN.equals(DATA_ORIGIN)) {
                retrofit = new Retrofit.Builder().
                        baseUrl(SERVER_AMAZON_API_BASE_URL).
                        build();

                service = retrofit.create(DownloadFileApiRest.class);
                call = service.downloadFileByUrl(SERVER_AUDIO_API_BASE_PATH + pathAudioProfile);
            } else {
                retrofit = new Retrofit.Builder().
                        baseUrl("https://raw.githubusercontent.com/").
                        build();

                service = retrofit.create(DownloadFileApiRest.class);
                call = service.downloadFileByUrl("jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus");
            }

            call.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            storageDir.mkdir();
                            // Error on Android 5.x first time...
                            //fileExists.createNewFile();

                            File file = new File(fileExists.getAbsolutePath());

                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            IOUtils.write(response.body().bytes(), fileOutputStream);
                            onFilePathFinishListener.filePath(file.getPath());

                        } catch (IOException e) {
                            onFilePathFinishListener.onError();
                        } catch (Exception e) {
                            onFilePathFinishListener.onError();
                        }

                    } else {
                        onFilePathFinishListener.onError();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    onFilePathFinishListener.onError();
                }
            });
        }
    }

    public void deleteCacheMyAudioProfile(boolean deleteAudioUrl) {
        File audioProfile = new File(getPathMyUserProfileAudioCache());
        userProfilePreferencesDataSource.setAudioCache("");

        if (deleteAudioUrl) {
            userProfilePreferencesDataSource.setAudio("");
        }

        if (audioProfile.exists()) {
            audioProfile.delete();
        }
    }

    public void deleteCacheAudioProfile() {
        File audioProfile = new File(getPathUserProfileAudioCache());

        if (audioProfile.exists()) {
            audioProfile.delete();
        }
    }

    public void deleteCacheImageProfile(boolean deleteImageUrl) {
        File imageProfile = new File(getPathMyUserProfileImageCache());
        userProfilePreferencesDataSource.setImageCache("");

        if (deleteImageUrl) {
            userProfilePreferencesDataSource.setImage("");
        }

        if (imageProfile.exists()) {
            imageProfile.delete();
        }
    }

    public void deleteAllCache() {
        File myProfileImage = new File(getPathMyUserProfileImageCache());
        File myProfileAudio = new File(getPathMyUserProfileAudioCache());
        File profileAudio = new File(getPathUserProfileAudioCache());
        File[] directory = context.getCacheDir().listFiles();

        if (myProfileImage.exists()) {
            Picasso.with(context).invalidate(myProfileImage);
        }

        if (myProfileAudio.exists()) {
            myProfileAudio.delete();
        }

        if (profileAudio.exists()) {
            profileAudio.delete();
        }

        if(directory != null){
            for (File file : directory ){
                file.delete();
            }
        }

        directory = context.getExternalCacheDir().listFiles();
        if(directory != null){
            for (File file : directory ){
                file.delete();
            }
        }

        try {
            // Delete all images cache
            Picasso.Builder builder = new Picasso.Builder(context);
            LruCache picassoCache = new LruCache(context);
            builder.memoryCache(picassoCache);
            picassoCache.clear();
        } catch (Exception e) {}

    }

    public interface OnFilePathFinishListener {
        void filePath(String path);
        void onError();
    }
}
