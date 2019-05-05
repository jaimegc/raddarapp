package com.raddarapp.presentation.android.view.map;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class PulseOverlayLayout extends MapOverlayLayout {
    private static final int ANIMATION_DELAY_FACTOR = 0; // In old versions = 20

    private int scaleAnimationDelay = ANIMATION_DELAY_FACTOR;

    public PulseOverlayLayout(final Context context) {
        this(context, null);
    }

    public PulseOverlayLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    private PulseMarkerView createPulseMarkerView(final int position, final Point point,
            final LatLng latLng, final int typeFootprint, final int userRelationship, boolean visibleFootprint) {
        PulseMarkerView pulseMarkerView = new PulseMarkerView(getContext(), latLng, point, position, typeFootprint, userRelationship, visibleFootprint);
        addMarker(pulseMarkerView);
        return pulseMarkerView;
    }

    public void createAndShowMarker(final int position, final LatLng latLng, final Integer typeFootprint, final Integer userRelationship, boolean visibleFootprint) {
        PulseMarkerView marker = createPulseMarkerView(position, googleMap.getProjection().toScreenLocation(latLng), latLng, typeFootprint, userRelationship, visibleFootprint);

        marker.showWithDelay(scaleAnimationDelay);
        scaleAnimationDelay += ANIMATION_DELAY_FACTOR;
    }

    public void showMarker(final int position) {
        if (markersList.size() > position) {
            ((PulseMarkerView) markersList.get(position)).pulse(position);
        }

    }

    public void onClickedFootprintAnimationSelected(final int position) {
        if (markersList.size() > position) {
            if (!((PulseMarkerView) markersList.get(position)).isLongClicked()) {
                ((PulseMarkerView) markersList.get(position)).pulse(position);
            } else {
                ((PulseMarkerView) markersList.get(position)).unPulse(position);
            }
        }
    }

    public void unselectedMarker(final int position) {
        if (markersList.size() > position) {
            ((PulseMarkerView) markersList.get(position)).unPulse(position);
        }
    }

    public void removeSelectedMarker(final int position) {
        if (markersList.size() > position) {
            ((PulseMarkerView) markersList.remove(position)).remove(position);
        }
    }

    public void onBackPressed(final LatLngBounds latLngBounds) {
        moveCamera(latLngBounds);
        showAllMarkers();
        refresh();
    }

    public void resetScaleAnimationDelay() {
        scaleAnimationDelay = ANIMATION_DELAY_FACTOR;
    }
}
