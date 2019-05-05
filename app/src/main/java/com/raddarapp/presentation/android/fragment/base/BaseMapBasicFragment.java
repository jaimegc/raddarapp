package com.raddarapp.presentation.android.fragment.base;

import android.location.Location;
import android.location.LocationManager;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.raddarapp.R;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.List;

public abstract class BaseMapBasicFragment extends BaseNormalFragment implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {
    protected MapFragment mapFragment;
    protected GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locRequest;
    private FusedLocationProviderApi fusedLocationProviderApi;
    private LocationManager locationManager;
    protected Location lastLocation;
    protected MapUtils mapUtils = new MapUtils();

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        initializeMap();
    }

    protected void initializeMap() {
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setZoomControlsEnabled(false);
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onMapLoaded() {
        readyMapToGetTerritoryArea();
    }

    protected abstract void readyMapToGetTerritoryArea();
}
