package com.raddarapp.presentation.android.view.map;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;

public class MapOverlayLayout<V extends MarkerView> extends FrameLayout {
    private static final int MARGIN_TOP_MAP = 30;
    private static final int MARGIN_BOTTOM_MAP_SMALL_FOOTPRINT_VIEW = 343;
    private static final int MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW = 2;
    private static final int CIRCLE_STROKE_WIDTH = 6;
    private static final int SIZE_RADIUS_WITH_MARKERS = 9;
    protected List<V> markersList;
    protected GoogleMap googleMap;
    private DimenUtils dimenUtils = new DimenUtils();
    //private Circle circleOne, circleTwo, circleThree, circleFour;
    private static final String ORIGIN_FAKE = "API_FAKE";
    private static final double USER_RANGE = 420;
    private OnAnimateCameraMapFinishListener listener;

    public MapOverlayLayout(final Context context) {
        this(context, null);
    }

    public MapOverlayLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        markersList = new ArrayList<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    protected void addMarker(final V view) {
        markersList.add(view);
        addView(view);
    }

    protected void removeMarker(final V view) {
        markersList.remove(view);
        removeView(view);
    }

    public void showAllMarkers() {
        for (int i = 0; i < markersList.size(); i++) {
            markersList.get(i).show();
        }
    }

    public void hideAllMarkers() {
        for (int i = 0; i < markersList.size(); i++) {
            markersList.get(i).hide();
        }
    }

    public void removeAllMarkers() {
        for (int i = 0; i < markersList.size(); i++) {
            markersList.get(i).setAnimation(null);
        }
        removeAllViews();
        markersList.clear();
    }

    public void showMarker(final int position) {
        markersList.get(position).show();
    }

    private void refresh(final int position, final Point point) {
        markersList.get(position).refresh(point);
    }

    public void setupMap(final GoogleMap googleMap, OnAnimateCameraMapFinishListener listener) {
        this.googleMap = googleMap;
        this.listener = listener;
    }

    public void refresh() {
        Projection projection = googleMap.getProjection();
        for (int i = 0; i < markersList.size(); i++) {
            refresh(i, projection.toScreenLocation(markersList.get(i).latLng()));
        }
    }

    public void setOnCameraIdleListener(final GoogleMap.OnCameraIdleListener listener) {
        googleMap.setOnCameraIdleListener(listener);
    }

    public void setOnCameraMoveListener(final GoogleMap.OnCameraMoveListener listener) {
        googleMap.setOnCameraMoveListener(listener);
    }

    public void moveCamera(final LatLngBounds latLngBounds) {
        int width = this.getResources().getConfiguration().screenWidthDp;
        googleMap.setPadding(0, (int) dimenUtils.dpToPx(getContext(), MARGIN_TOP_MAP), 0, (int) dimenUtils.dpToPx(getContext(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                latLngBounds, (int) dimenUtils.dpToPx(getContext(), width / SIZE_RADIUS_WITH_MARKERS)), new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                listener.onAnimateCameraMapFinishListener();
                // 3D View
                /*CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(37.345119, -5.932235))      // Sets the center of the map to Mountain View
                        .zoom(googleMap.getCameraPosition().zoom)
                        .tilt(45)                   // Sets the tilt of the camera to 45 degrees
                        .build();                   // Creates a CameraPosition from the builder
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
            }

            @Override
            public void onCancel() {
                listener.onAnimateCameraMapCancelListener();
            }
        });

        /*if (BuildConfig.DATA_ORIGIN.equals(ORIGIN_FAKE)) {
            clearAllCircles();
            float strokeWidth = dimenUtils.dpToPx(getContext(), CIRCLE_STROKE_WIDTH);
            int resourceColorTransparent = getResources().getColor(android.R.color.transparent);
            double circlesRange = USER_RANGE / 4.0;
            LatLng myFakePosition = new LatLng(37.345119, -5.932235);

            circleOne = googleMap.addCircle(new CircleOptions()
                    .center(myFakePosition)
                    .radius(USER_RANGE)
                    .strokeColor(getResources().getColor(R.color.circle1))
                    .strokeWidth(strokeWidth)
                    .fillColor(resourceColorTransparent));

            circleTwo = googleMap.addCircle(new CircleOptions()
                    .center(myFakePosition)
                    .radius(circlesRange * 3.0)
                    .strokeColor(getResources().getColor(R.color.circle2))
                    .strokeWidth(strokeWidth)
                    .fillColor(resourceColorTransparent));

            circleThree = googleMap.addCircle(new CircleOptions()
                    .center(myFakePosition)
                    .radius(circlesRange * 2.0)
                    .strokeColor(getResources().getColor(R.color.circle3))
                    .strokeWidth(strokeWidth)
                    .fillColor(resourceColorTransparent));

            circleFour = googleMap.addCircle(new CircleOptions()
                    .center(myFakePosition)
                    .radius(circlesRange)
                    .strokeColor(getResources().getColor(R.color.circle4))
                    .strokeWidth(strokeWidth)
                    .fillColor(resourceColorTransparent));
        }*/
    }

    private LatLngBounds adjustBoundsForMaxZoomLevel(LatLngBounds bounds) {
        LatLng sw = bounds.southwest;
        LatLng ne = bounds.northeast;
        double deltaLat = Math.abs(sw.latitude - ne.latitude);
        double deltaLon = Math.abs(sw.longitude - ne.longitude);

        final double zoomN = 0.005; // minimum zoom coefficient
        if (deltaLat < zoomN) {
            sw = new LatLng(sw.latitude - (zoomN - deltaLat / 2), sw.longitude);
            ne = new LatLng(ne.latitude + (zoomN - deltaLat / 2), ne.longitude);
            bounds = new LatLngBounds(sw, ne);
        }
        else if (deltaLon < zoomN) {
            sw = new LatLng(sw.latitude, sw.longitude - (zoomN - deltaLon / 2));
            ne = new LatLng(ne.latitude, ne.longitude + (zoomN - deltaLon / 2));
            bounds = new LatLngBounds(sw, ne);
        }

        return bounds;
    }

    public void animateCamera(final LatLngBounds bounds) {
        int width = getWidth();
        int height = getHeight();
        int padding = MapUtils.DEFAULT_MAP_PADDING;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
    }

    public LatLng getCurrentLatLng() {
        return new LatLng(googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude);
    }

    /*private void clearAllCircles() {
        if (circleOne != null) {
            circleOne.remove();
        }

        if (circleTwo != null) {
            circleTwo.remove();
        }

        if (circleThree != null) {
            circleThree.remove();
        }

        if (circleFour != null) {
            circleFour.remove();
        }
    }*/

    public interface OnAnimateCameraMapFinishListener {
        void onAnimateCameraMapFinishListener();

        void onAnimateCameraMapCancelListener();
    }
}
