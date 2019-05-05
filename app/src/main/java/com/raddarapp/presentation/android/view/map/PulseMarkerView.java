package com.raddarapp.presentation.android.view.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.presentation.android.utils.DimenUtils;

import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class PulseMarkerView extends MarkerView {
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int STROKE_DIMEN = 1;
    private static final int MARKER_DIMEN = 15;
    private static final int STROKE_CIRCLE_DIMEN = 14;

    private static final int FRAME_PARAMS_WIDTH = 17;
    private static final int FRAME_PARAMS_HEIGHT = 17;

    private static final int EAT_ANIMATION_DURATION = 500;
    private static final int EAT_ANIMATION_MARKER_POSITION = 35;

    private final Context context;
    private float size;
    private Animation eatAnimation;
    private Animation showMarkerAnimation;
    private Paint strokeBackgroundPaint;
    private Paint strokeBackgroundSelectedPaint;
    private Path heartPath;
    private Path strokeHeartPath;
    private Paint heart;
    private Paint strokeHeart;
    private Paint backgroundPaint;
    private String text;
    private Paint textPaint;
    private AnimatorSet showAnimatorSet, hideAnimatorSet;
    private int position = -1;
    private DimenUtils dimenUtils = new DimenUtils();
    private View thisMarkerView = this;
    private int typeFootprint;
    private int userRelationship;
    private boolean isLongClicked = false;
    private boolean visibleFootprint;

    private static final int USER_RELATIONSHIP_UNKNOWN = UserRelationshipType.UNKNOWN.getValue();
    private static final int USER_RELATIONSHIP_FOLLOWING = UserRelationshipType.FOLLOWING.getValue();
    private static final int USER_RELATIONSHIP_FRIEND = UserRelationshipType.FRIEND.getValue();

    public PulseMarkerView(final Context context, final LatLng latLng, final Point point,
            final int typeFootprint, final int userRelationship, boolean visibleFootprint) {
        super(context, latLng, point);
        this.context = context;
        this.typeFootprint = typeFootprint;
        this.userRelationship = userRelationship;
        this.visibleFootprint = visibleFootprint;

        setVisibility(View.INVISIBLE);
        setupSizes(context);
        setupShowMarkerAnimation();
        setupBackgroundPaint(context);

        setupStrokeBackgroundPaint(context);
        setupStrokeBackgroundSelectedPaint(context);
        setupHeart(context);
        setupStrokeHeart(context);
        setupTextPaint(context);

        setupShowAnimatorSet();
        setupHideAnimatorSet();
    }

    private void setupHeart(Context context) {
        heartPath = new Path();
        heart = new Paint(Paint.ANTI_ALIAS_FLAG);

        heart.setShader(null);

        float width = getContext().getResources().getDimension(R.dimen.default_layout_margin_halved);
        float height = getContext().getResources().getDimension(R.dimen.default_layout_margin_halved);

        // Starting point
        heartPath.moveTo(width / 2, height / 5);

        // Upper left path
        heartPath.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        heartPath.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        heartPath.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        heartPath.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);

        heart.setColor(getContext().getResources().getColor(R.color.blue_dark_raddar));
        heart.setStyle(Paint.Style.FILL);

        Matrix matrix = new Matrix();
        matrix.setTranslate(getContext().getResources().getDimension(R.dimen.default_layout_margin_halved_medium),
                getContext().getResources().getDimension(R.dimen.default_layout_margin_halved_medium));
        heartPath.transform(matrix);
    }

    private void setupStrokeHeart(Context context) {
        strokeHeartPath = new Path();
        strokeHeart = new Paint(Paint.ANTI_ALIAS_FLAG);

        strokeHeart.setShader(null);

        float width = getContext().getResources().getDimension(R.dimen.default_layout_margin_halved);
        float height = getContext().getResources().getDimension(R.dimen.default_layout_margin_halved);

        // Starting point
        strokeHeartPath.moveTo(width / 2, height / 5);

        // Upper left path
        strokeHeartPath.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        strokeHeartPath.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        strokeHeartPath.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        strokeHeartPath.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);

        strokeHeart.setColor(Color.WHITE);
        strokeHeart.setStyle(Paint.Style.FILL);

        Matrix mMatrix = new Matrix();
        RectF bounds = new RectF();
        strokeHeartPath.computeBounds(bounds, true);
        mMatrix.postRotate(180, bounds.centerX(), bounds.centerY());
        strokeHeartPath.transform(mMatrix);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        super.setBackgroundResource(R.drawable.ic_add_black_48dp_00);

    }

    public PulseMarkerView(final Context context, final LatLng latLng, final Point point, final int position,
            final int typeFootprint, final int userRelationship, boolean visibleFootprint) {
        this(context, latLng, point, typeFootprint, userRelationship, visibleFootprint);
        text = String.valueOf(position);
    }

    private void setupHideAnimatorSet() {
        Animator animatorScaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, 1.0f, 0.f);
        Animator animatorScaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1.0f, 0.f);
        Animator animator = ObjectAnimator.ofFloat(this, View.ALPHA, 1.f, 0.f).setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(final Animator animation) {
                super.onAnimationStart(animation);
                setVisibility(View.INVISIBLE);
                invalidate();
            }
        });
        hideAnimatorSet = new AnimatorSet();
        hideAnimatorSet.playTogether(animator, animatorScaleX, animatorScaleY);
    }

    private void setupSizes(final Context context) {
        size = dimenUtils.dpToPx(context, MARKER_DIMEN) / 2;
    }

    private void setupShowAnimatorSet() {
        Animator animatorScaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, 1.5f, 1.f);
        Animator animatorScaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1.5f, 1.f);
        Animator animator = ObjectAnimator.ofFloat(this, View.ALPHA, 0.f, 1.f).setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(final Animator animation) {
                super.onAnimationStart(animation);
                setVisibility(View.VISIBLE);
                invalidate();
            }
        });
        showAnimatorSet = new AnimatorSet();
        showAnimatorSet.playTogether(animator, animatorScaleX, animatorScaleY);
    }

    private void setupEatAnimation() {
        eatAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0.0f,
                TranslateAnimation.ABSOLUTE, -point.x + dimenUtils.dpToPx(context, EAT_ANIMATION_MARKER_POSITION),
                TranslateAnimation.ABSOLUTE, 0.0f,
                TranslateAnimation.ABSOLUTE, -point.y + dimenUtils.dpToPx(context, EAT_ANIMATION_MARKER_POSITION)
        );

        eatAnimation.setDuration(EAT_ANIMATION_DURATION);
        eatAnimation.setFillAfter(false);
        eatAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                thisMarkerView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setupShowMarkerAnimation() {
        showMarkerAnimation = AnimationUtils.loadAnimation(context, R.anim.pulse);
    }

    private void setupTextPaint(final Context context) {
        Typeface font = TypefaceUtils.load(context.getAssets(), "fonts/" + FONT_NAME);

        textPaint = new Paint();
        textPaint.setColor(ContextCompat.getColor(context, R.color.coin_map_text));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.textsize_mini_3));
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTypeface(font);
    }

    private void setupStrokeBackgroundPaint(final Context context) {
        strokeBackgroundPaint = new Paint();
        strokeBackgroundPaint.setColor(ContextCompat.getColor(context, R.color.coin_map_stroke));
        strokeBackgroundPaint.setStyle(Paint.Style.STROKE);
        strokeBackgroundPaint.setAntiAlias(true);
        strokeBackgroundPaint.setAlpha(221);
        strokeBackgroundPaint.setStrokeWidth(dimenUtils.dpToPx(context, STROKE_DIMEN));
        strokeBackgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    private void setupStrokeBackgroundSelectedPaint(final Context context) {
        strokeBackgroundSelectedPaint = new Paint();
        strokeBackgroundSelectedPaint.setColor(ContextCompat.getColor(context, R.color.black));
        strokeBackgroundSelectedPaint.setStyle(Paint.Style.STROKE);
        strokeBackgroundSelectedPaint.setAntiAlias(true);
        strokeBackgroundSelectedPaint.setAlpha(221);
        strokeBackgroundSelectedPaint.setStrokeWidth(dimenUtils.dpToPx(context, STROKE_DIMEN+20));
        strokeBackgroundSelectedPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    private void setupBackgroundPaint(final Context context) {
        backgroundPaint = new Paint();

        backgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        if (typeFootprint == FootprintType.FOOTPRINT.getValue()) {
            backgroundPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        } else if (typeFootprint == FootprintType.INSIGNE.getValue()){
            backgroundPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
        }

        backgroundPaint.setAntiAlias(true);
    }

    @Override
    public void setLayoutParams(final ViewGroup.LayoutParams params) {
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        frameParams.width = (int) dimenUtils.dpToPx(context, FRAME_PARAMS_WIDTH);
        frameParams.height = (int) dimenUtils.dpToPx(context, FRAME_PARAMS_HEIGHT);
        //frameParams.leftMargin = point.x - frameParams.width / 3;
        //frameParams.topMargin = point.y - frameParams.height / 3;
        frameParams.leftMargin = point.x - frameParams.width / 2;
        frameParams.topMargin = point.y - frameParams.height / 2;
        super.setLayoutParams(frameParams);
    }

    public void pulse(int position) {
        isLongClicked = true;
        this.position = -1;
        this.setElevation(5);
        startAnimation(showMarkerAnimation);
    }

    public void unPulse(int position) {
        isLongClicked = false;
        this.position = -1;
        this.setElevation(0);
        this.setAnimation(null);
        //startAnimation(eatAnimation);
    }

    public void remove(int position) {
        setupEatAnimation();
        startAnimation(eatAnimation);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        if (visibleFootprint) {
            drawBackground(canvas);
            drawStrokeBackground(canvas);
            drawHeart(canvas);
            //drawStrokeHeart(canvas);
            //drawText(canvas);
            //Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.common_google_signin_btn_icon_light);
            //canvas.drawBitmap(bm, 0f, 0f, null);
        }
        super.onDraw(canvas);
    }

    private void drawText(final Canvas canvas) {
        if(text != null && !TextUtils.isEmpty(text)) {
            canvas.drawText("m", size, (size - ((textPaint.descent() + textPaint.ascent()) / 2)), textPaint);
        }
    }

    private void drawStrokeBackground(final Canvas canvas) {
        canvas.drawCircle(size, size, dimenUtils.dpToPx(context, STROKE_CIRCLE_DIMEN) / 2, strokeBackgroundPaint);
    }

    private void drawBackground(final Canvas canvas) {
        /*if (userRelationship == USER_RELATIONSHIP_UNKNOWN) {
            backgroundPaint.setColor(ContextCompat.getColor(context, R.color.marker_unknown));
        } else if (userRelationship == USER_RELATIONSHIP_FOLLOWING) {
            backgroundPaint.setColor(ContextCompat.getColor(context, R.color.marker_following));
        } else if (userRelationship == USER_RELATIONSHIP_FRIEND) {
            backgroundPaint.setColor(ContextCompat.getColor(context, R.color.marker_friend));
        } else {
            backgroundPaint.setColor(ContextCompat.getColor(context, R.color.marker_default));
        }*/

        if (typeFootprint == FootprintType.FOOTPRINT.getValue()) {
            backgroundPaint.setColor(ContextCompat.getColor(context, R.color.coin_map));
        } else if (typeFootprint == FootprintType.INSIGNE.getValue()){
            backgroundPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        }

        canvas.drawCircle(size, size, size, backgroundPaint);
    }

    private void drawHeart(final Canvas canvas) {
        canvas.drawPath(heartPath, heart);
    }

    private void drawStrokeHeart(final Canvas canvas) {
        canvas.drawPath(strokeHeartPath, strokeHeart);
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    @Override
    public void hide() {
        hideAnimatorSet.start();
    }

    @Override
    public void refresh(final Point point) {
        this.point = point;
        updatePulseViewLayoutParams(point);
    }

    @Override
    public void show() {
        showAnimatorSet.start();
    }

    public void showWithDelay(final int delay) {
        showAnimatorSet.setStartDelay(delay);
        showAnimatorSet.start();
    }

    public void updatePulseViewLayoutParams(final Point point) {
        this.point = point;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.width = (int) dimenUtils.dpToPx(context, FRAME_PARAMS_WIDTH);
        params.height = (int) dimenUtils.dpToPx(context, FRAME_PARAMS_HEIGHT);
        params.leftMargin = point.x - params.width / 2;
        params.topMargin = point.y - params.height / 2;
        super.setLayoutParams(params);
        invalidate();
    }

    public boolean isLongClicked() {
        return isLongClicked;
    }
}
