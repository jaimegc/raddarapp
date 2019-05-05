package com.raddarapp.presentation.android.listener;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

public interface OnMoveMapAndAddMarkerForUpdateFootprintMainListener {
    void onMoveMapAndAddMarkerSuccess(
            LatLngBounds latLngBounds, List<LatLng> listLatLng, List<Integer> typeFootprints, List<Integer> usersRelationship, List<Boolean> visibleFootprints);
}
