package com.raddarapp.presentation.android.fragment.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.android.activity.FootprintsRankingActivity;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.MapUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.TutorialUtils;
import com.raddarapp.presentation.android.view.map.MapOverlayLayout;
import com.raddarapp.presentation.android.view.map.PulseOverlayLayout;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public abstract class BaseMapNormalFragment extends BaseNormalFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        MapOverlayLayout.OnAnimateCameraMapFinishListener {

    protected static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    protected static final String COMPLIMENTS_EXTRA = "UserProfile.NewComplimentsExtra";
    protected static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

    @BindView(R.id.map_overlay_layout) protected PulseOverlayLayout mapOverlayLayoutView;
    @BindView(R.id.my_territory_position) protected ImageView myTerritoryPositionView;
    @BindView(R.id.view_center_compass) protected RelativeLayout centerCompassView;
    @BindView(R.id.view_center_gps) protected  RelativeLayout centerGpsView;
    @BindView(R.id.view_satellite_normal_map) protected  RelativeLayout satelliteNormalMapView;
    @BindView(R.id.image_satellite_normal_map) ImageView imageSatelliteNormalMapView;
    @BindView(R.id.circle_image_satellite_normal_map) View circleImageSatelliteMapView;
    @BindView(R.id.show_territory_info) protected RelativeLayout showTerritoryInfoView;
    @BindView(R.id.territory) protected TextView territoryView;
    @BindView(R.id.territory_parent) protected TextView territoryParent;
    @BindView(R.id.country_emoji) TextView countryEmojiView;
    @BindView(R.id.territory_title) protected TextView territoryTitleView;
    @BindView(R.id.territory_area) protected TextView territoryAreaView;
    @BindView(R.id.territory_total_footprints) protected TextView territoryTotalFootprintsView;
    @BindView(R.id.territory_total_footprints_title) protected TextView territoryTotalFootprintsTitleView;
    @BindView(R.id.linear_loading_territory_area) protected LinearLayout loadingTerritoryAreaView;
    @BindView(R.id.message_loading_territory_area) protected TextView messageLoadingTerritoryAreaView;
    @BindView(R.id.leader_username) protected TextView leaderUsernameView;
    @BindView(R.id.leader_level) protected TextView leaderLevelView;
    @BindView(R.id.leader_image) CircularImageView leaderImageView;
    @BindView(R.id.main_terrain) protected ImageView mainTerrainView;
    @BindView(R.id.raddar_button) ImageView raddarButtonView;
    @BindView(R.id.show_territory_parent) ImageView showTerritoryParent;
    @BindView(R.id.show_territory_son) ImageView showTerritorySon;
    @BindView(R.id.ic_level) ImageView icLevelView;
    @BindView(R.id.message_map_top) protected TextView messageMapTop;
    @BindView(R.id.message_map_bottom) protected TextView messageMapBottom;
    @BindView(R.id.message_map) protected LinearLayout linearMessageMapView;
    @BindView(R.id.searching_profile) protected RelativeLayout relativeSearchingProfileView;
    @BindView(R.id.animation_radar_profile) protected PulsatorLayout animationRadarProfile;
    @BindView(R.id.play_audio_leader) ImageView playAudioLeaderView;
    @BindView(R.id.view_scan_complete) RelativeLayout viewScanComplete;
    @BindView(R.id.top_rankings) ImageView topRankingsView;
    @BindView(R.id.github) ImageView githubView;

    // Minimum range for LEVEL 1
    private static final int RANGE_DEFAULT = 300000;
    private static final int TERRITORY_SIZE_MAP = 5;
    private static final int MARGIN_TOP_MAP = 30;
    private static final int MARGIN_BOTTOM_MAP_SMALL_FOOTPRINT_VIEW = 343;
    private static final int MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW = 2;
    private static final int SIZE_RADIUS_WITH_MARKERS = 9;
    private static final double SIZE_FIXED_SPHERICAL_RADIUS = 6;
    private static final double SIZE_FIXED_SPHERICAL_RADIUS_EMPTY_FOOTPRINTS_MAIN = 79;
    private static final double SIZE_SPHERICAL_RADIUS = 5.4;
    private static final int PADDING_TOP_MAP_TERRITORY_INFO = 82; // 82 + 52 (Raddar top menu)
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    protected static final int REQUEST_COMMENTS = 200;
    protected static final int REQUEST_USER_PROFILE_CHANGES = 202;
    private static final int MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS_FOR_FAKE = 0;
    private static final int MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS_FOR_FAKE = 0;
    private static final int MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS = 30;
    private static final int MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS = 1000;
    private static final int MINIMUM_FASTEST_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS = 5000;
    private static final float MAX_KM_PER_HOUR = 1000.0f;
    private static final float MAX_SPEED_UPDATE_LOCATION_IN_METERS_PER_SECOND = (MAX_KM_PER_HOUR * 10) / 36;
    protected static final String SPACE = " ";
    protected static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    protected MapFragment mapFragment;
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locRequest;
    //private Circle circleOne, circleTwo, circleThree, circleFour;
    private FusedLocationProviderApi fusedLocationProviderApi;
    private LocationManager locationManager;
    private android.location.LocationListener locationListener;
    protected Location lastLocation;
    private ImageView compassGoogleMapView;
    private ImageView gpsGoogleMapView;
    private DimenUtils dimenUtils = new DimenUtils();
    protected boolean cleanedMap = false;
    private List<Polygon> territoryAreaPolygon = new ArrayList<>();
    protected LatLngBounds lastLatLngBounds = null;
    protected Animation animationMessageTop;

    private boolean antiFakeGpsActivated = true;

    protected NumberUtils numberUtils = new NumberUtils();

    protected boolean firstMapLoad = true;


    protected long USER_RANGE = RANGE_DEFAULT;
    protected int USER_LEVEL = 1;

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        animationRadarProfile.start();

        getLocation();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeMap();
    }

    protected void initializeMap() {
        mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setPadding(0, 0, 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
        mapOverlayLayoutView.setupMap(googleMap, this);
        compassGoogleMapView = (ImageView) mapFragment.getView().findViewWithTag("GoogleMapCompass");
        gpsGoogleMapView = (ImageView) mapFragment.getView().findViewWithTag("GoogleMapMyLocationButton");
        compassGoogleMapView.setVisibility(View.GONE);
        gpsGoogleMapView.setImageDrawable(null);
        mapOverlayLayoutView.setupMap(this.googleMap, this);

        initGestures();
        drawMap();
    }

    public void onMapReadyAgain() {
        if (googleMap != null) {

            if (getActivity() != null) {
                if (!((FootprintMainActivity) getActivity()).isOnShowTerritoryFromMap()) {
                    ((FootprintMainActivity) getActivity()).onInfluencersTabClicked();
                }
            }

            googleMap.setPadding(0, 0, 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));

            if (lastLocation != null) {
                if (lastLocation.getSpeed() <= MAX_SPEED_UPDATE_LOCATION_IN_METERS_PER_SECOND) {

                    if (lastLatLngBounds != null) {
                        int width = this.getResources().getConfiguration().screenWidthDp;
                        googleMap.setPadding(0, (int) dimenUtils.dpToPx(context, MARGIN_TOP_MAP), 0, (int) dimenUtils.dpToPx(context, MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                                lastLatLngBounds, (int) dimenUtils.dpToPx(getActivity(), width / SIZE_RADIUS_WITH_MARKERS)));
                    } else {
                        animateCameraCenter();
                    }

                } else {
                    showSpeedError();
                }
            } else {
                showLocationError();
            }
        }
    }

    @OnClick(R.id.my_territory_position)
    public void myTerritoryPositionOnClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).resetAllTerritories();
            ((FootprintMainActivity) getActivity()).setOnShowTerritoryFromMap(true);

            onShowTerritoryMainClicked("", true);
        }
    }

    @OnClick(R.id.top_rankings)
    public void onTopClicked() {
        FootprintsRankingActivity.open(getActivity());
    }

    @OnClick(R.id.github)
    public void onGithubClicked() {
        Uri webpage = Uri.parse(getString(R.string.url_github));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    @OnClick(R.id.close)
    public void onCloseClicked() {
        myTerritoryPositionView.setVisibility(View.VISIBLE);
        topRankingsView.setVisibility(View.VISIBLE);
        showTerritoryParent.setVisibility(View.VISIBLE);
        showTerritorySon.setVisibility(View.VISIBLE);
        onCleanMapClicked();
        onMapReadyAgain();
    }

    @OnClick(R.id.view_satellite_normal_map)
    public void onSatelliteNormalMapClicked() {
        if (googleMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            circleImageSatelliteMapView.setBackgroundResource(R.drawable.circle_grey);
        } else {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            circleImageSatelliteMapView.setBackgroundResource(R.drawable.circle_blue);
        }
    }

    @OnClick(R.id.view_center_compass)
    public void onCenterCompassClicked() {
        centerCompass();
    }

    @OnClick(R.id.view_center_gps)
    public void onCenterGpsClicked() {
        centerGps();
    }

    private void drawMap() {
        try {
            this.googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.map_style));
        } catch (Resources.NotFoundException e) {}
    }

    private void undrawMap() {
        this.googleMap.setMapStyle(null);
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    private void onLocationChangedActions(Location location) {

        if (location != null) {
            if (!location.isFromMockProvider()) {
                lastLocation = location;
                updatePresenterLocation();

                if (location.getSpeed() <= MAX_SPEED_UPDATE_LOCATION_IN_METERS_PER_SECOND) {
                    updateCirclesRange(location);

                    if (firstMapLoad) {
                        firstMapLoad = false;
                        linearMessageMapView.setVisibility(View.GONE);
                        linearMessageMapView.startAnimation(animationMessageTop);
                        initializeShowTerritoryInfluencers(location);

                        firstAnimateCamera();
                    }
                } else {
                    showSpeedError();
                }
            } else {
                showLocationMockError();
            }

        } else {
            showLocationError();
        }
    }

    protected void animateCameraEmptyFootprintsMain() {
        animateCameraCenter();

        // Deprecated: Center map with raddar in meters
        //this.googleMap.setPadding(0, 0, 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
        //animateCameraCenterSpherical(SIZE_FIXED_SPHERICAL_RADIUS_EMPTY_FOOTPRINTS_MAIN);
    }

    // Deprecated: Center map with raddar in meters
    private void animateCameraCenterSpherical(double sizeFixedSphericalRadius) {
        MapUtils mapUtils = new MapUtils();
        int width = this.getResources().getConfiguration().screenWidthDp;

        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                mapUtils.calculateSphericalRadiusInMeters(lastLocation, numberUtils.calculateCircleRange(USER_RANGE)),
                (int) dimenUtils.dpToPx(context, (int) (width / SIZE_SPHERICAL_RADIUS + sizeFixedSphericalRadius))));
    }

    private void animateCameraCenter() {
        googleMap.setPadding(0, 0, 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
        LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 0));
    }

    private void animateCameraCenterWorld() {
        LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        googleMap.setPadding(0, (int) dimenUtils.dpToPx(getActivity(), PADDING_TOP_MAP_TERRITORY_INFO), 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 0));
    }

    // Deprecated: Center map with raddar in meters
    private void firstAnimateCameraCenterSpherical(double sizeFixedSphericalRadius) {
        MapUtils mapUtils = new MapUtils();
        googleMap.setPadding(0, 0, 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
        int width = this.getResources().getConfiguration().screenWidthDp;

        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                mapUtils.calculateSphericalRadiusInMeters(lastLocation, numberUtils.calculateCircleRange(USER_RANGE)),
                (int) dimenUtils.dpToPx(context, (int) (width / SIZE_SPHERICAL_RADIUS + sizeFixedSphericalRadius))), new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                onFirstRadarScanSimulateClicked();
            }

            @Override
            public void onCancel() {
                onFirstRadarScanSimulateClicked();
            }
        });
    }

    private void firstAnimateCamera() {
        LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        googleMap.setPadding(0, 0, 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 0), new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                onFirstRadarScanSimulateClicked();
            }

            @Override
            public void onCancel() {
                onFirstRadarScanSimulateClicked();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (googleApiClient != null && !googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    private void getLocation() {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // We use this because when we simulate and inactive the GPS, if we enter into the app
        // for several seconds we have the simulated coordinates and isFromMockeProvider() is
        // false. The idea is saving in preferences if the user sometimes tried to simulate the
        // the GPS. If true, will always have to wait LocationListener. LocationListener ALWAYS
        // has real GPS signal with no cache but it's a bit slower. FusedLocationProviderApi is
        // very fast but uses cache. So, we can use LocationListener first and when we receive
        // the first location remove this listener and permit to use the FusedLocationProviderApi
        // again
        /*locationListener = new android.location.LocationListener() {
            public void onLocationChanged(Location locationChanged) {
                if (!locationChanged.isFromMockProvider()) {
                    antiFakeGpsActivated = true;
                    locationManager.removeUpdates(locationListener);
                } else {
                    showLocationMockError();
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };*/

        locRequest = LocationRequest.create();
        locRequest.setInterval(MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS);
        locRequest.setFastestInterval(MINIMUM_FASTEST_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS);
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locRequest.setSmallestDisplacement(MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS);

        fusedLocationProviderApi = LocationServices.FusedLocationApi;

        //initializeProviders();

        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                //.enableAutoManage(context, this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if(googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    protected void moveMapAndAddMaker(LatLngBounds latLngBounds, List<LatLng> listLatLng, List<Integer> typeFootprints,
            List<Integer> usersRelationship, List<Boolean> visibleFootprints) {
        this.lastLatLngBounds = latLngBounds;
        mapOverlayLayoutView.moveCamera(latLngBounds);
        mapOverlayLayoutView.resetScaleAnimationDelay();
        mapOverlayLayoutView.setOnCameraIdleListener(() -> {
            for (int i = 0; i < listLatLng.size(); i++) {
                mapOverlayLayoutView.createAndShowMarker(i, listLatLng.get(i), typeFootprints.get(i), usersRelationship.get(i), visibleFootprints.get(i));
            }
            mapOverlayLayoutView.setOnCameraIdleListener(null);
        });
        mapOverlayLayoutView.setOnCameraMoveListener(mapOverlayLayoutView::refresh);
    }

    protected void centerGps() {
        gpsGoogleMapView.performClick();
    }

    protected void centerCompass() {
        compassGoogleMapView.performClick();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (!checkGpsPermission()) {
            getGpsPermission();
        } else {
            // Sometimes googleApiClient is null and produces crash
            if (googleApiClient != null) {
                fusedLocationProviderApi.requestLocationUpdates(googleApiClient, locRequest, location -> {
                    if (antiFakeGpsActivated) {
                        onLocationChangedActions(location);
                    } else {
                    }
                });

                googleMap.setMyLocationEnabled(true);
            } else {
                googleApiClient = new GoogleApiClient.Builder(context)
                        .addConnectionCallbacks(this)
                        //.enableAutoManage(context, this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();

                if(googleApiClient != null) {
                    googleApiClient.connect();
                }
            }
        }
    }

    private void initializeProviders() {
        if (checkGpsPermission()) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS_FOR_FAKE, MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS_FOR_FAKE, locationListener);
            }

            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        MINIMUM_DISTANCE_UPDATE_LOCATION_IN_METERS, MINIMUM_INTERVAL_UPDATE_LOCATION_IN_MILLISECONDS, locationListener);
            }
        }
    }

    @OnClick(R.id.show_territory_parent)
    public void onShowTerritoryParentClicked() {

        if (getActivity() != null) {
            String zoneKey;

            zoneKey = ((FootprintMainActivity) getActivity()).onShowTerritoryParentClicked();

            if (!zoneKey.isEmpty()) {
                onShowTerritoryMainClicked(zoneKey, true);
            }
        }
    }

    @OnClick(R.id.show_territory_son)
    public void onShowTerritorySonClicked() {

        if (getActivity() != null) {
            String zoneKey;

            zoneKey = ((FootprintMainActivity) getActivity()).onShowTerritorySonClicked();

            if (!zoneKey.isEmpty()) {
                onShowTerritoryMainClicked(zoneKey, true);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length == 1 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                showGpsPermissionError();
            } else {
                getLocation();
            }
        }
    }

    protected boolean checkGpsEnabledToRefresh() {
        boolean gpsEnabled = checkGpsEnabled();

        if (!gpsEnabled) {
            showGpsDisabledError();
        }

        return gpsEnabled;
    }

    protected boolean checkGpsPermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    protected void getGpsPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
    }

    protected boolean checkGpsEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    protected void uncleanMap() {
        drawMap();
        cleanedMap = false;
        loadingTerritoryAreaView.setVisibility(View.GONE);

        centerGpsView.setVisibility(View.GONE);
        centerCompassView.setVisibility(View.GONE);
        satelliteNormalMapView.setVisibility(View.GONE);
        mainTerrainView.setVisibility(View.VISIBLE);
        raddarButtonView.setVisibility(View.VISIBLE);

        if (googleMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            imageSatelliteNormalMapView.setImageResource(R.drawable.ic_satellite_map);
        }
    }

    protected void cleanMap() {
        undrawMap();
        cleanedMap = true;
        centerGpsView.setVisibility(View.VISIBLE);
        centerCompassView.setVisibility(View.VISIBLE);
        satelliteNormalMapView.setVisibility(View.VISIBLE);
        loadingTerritoryAreaView.setVisibility(View.GONE);
        mainTerrainView.setVisibility(View.GONE);
        raddarButtonView.setVisibility(View.GONE);
        circleImageSatelliteMapView.setBackgroundResource(R.drawable.circle_grey);
    }

    protected void removeTerritoryAreaPolygon() {
        if (territoryAreaPolygon != null) {
            for (Polygon polygon : territoryAreaPolygon) {
                polygon.remove();
            }

            territoryAreaPolygon.clear();
        }
    }

    protected void updateCirclesRange(Location location) {
        /*if (getActivity() != null && this.isAdded()) {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                float strokeWidth = dimenUtils.dpToPx(context, 6);
                int resourceColorTransparent = getResources().getColor(android.R.color.transparent);
                double circlesRange = numberUtils.calculateCircleRange(USER_RANGE / 4.0);

                clearAllCircles();

                circleOne = googleMap.addCircle(new CircleOptions()
                        .center(new LatLng(latitude, longitude))
                        .radius(circlesRange * 4.0)
                        .strokeColor(getResources().getColor(R.color.circle1))
                        .strokeWidth(strokeWidth)
                        .fillColor(resourceColorTransparent));

                circleTwo = googleMap.addCircle(new CircleOptions()
                        .center(new LatLng(latitude, longitude))
                        .radius(circlesRange * 3.0)
                        .strokeColor(getResources().getColor(R.color.circle2))
                        .strokeWidth(strokeWidth)
                        .fillColor(resourceColorTransparent));

                circleThree = googleMap.addCircle(new CircleOptions()
                        .center(new LatLng(latitude, longitude))
                        .radius(circlesRange * 2.0)
                        .strokeColor(getResources().getColor(R.color.circle3))
                        .strokeWidth(strokeWidth)
                        .fillColor(resourceColorTransparent));

                circleFour = googleMap.addCircle(new CircleOptions()
                        .center(new LatLng(latitude, longitude))
                        .radius(circlesRange)
                        .strokeColor(getResources().getColor(R.color.circle4))
                        .strokeWidth(strokeWidth)
                        .fillColor(resourceColorTransparent));
            }
        }*/
    }

    protected void showTerritoryMainArea(TerritoryArea territoryArea) {
        if (cleanedMap) {
            removeTerritoryAreaPolygon();
            DimenUtils dimenUtils = new DimenUtils();
            MapUtils mapUtils = new MapUtils();
            int width = this.getResources().getConfiguration().screenWidthDp;
            List<List<LatLng>> generatedLatLngs = mapUtils.generateLatLngs(territoryArea.getArea());
            List<LatLng> latLngsCenterMap = new ArrayList<>();
            territoryAreaPolygon = new ArrayList<>();

            for (List<LatLng> latLngs : generatedLatLngs) {
                PolygonOptions polygons = new PolygonOptions().addAll(latLngs);
                territoryAreaPolygon.add(googleMap.addPolygon(polygons
                        .strokeWidth(dimenUtils.dpToPx(getActivity(), 2))
                        .strokeColor(getResources().getColor(R.color.ranking_map_border_color))
                        .fillColor(getResources().getColor(R.color.ranking_map_area_color))));
                latLngsCenterMap.addAll(latLngs);
            }


            if (!generatedLatLngs.isEmpty()) {
                googleMap.setPadding(0, (int) dimenUtils.dpToPx(getActivity(), PADDING_TOP_MAP_TERRITORY_INFO), 0, (int) dimenUtils.dpToPx(getActivity(), MARGIN_BOTTOM_MAP_BIG_FOOTPRINT_VIEW));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(mapUtils.transformTerritoryAreaToLatLngBounds(latLngsCenterMap),
                        (int) dimenUtils.dpToPx(getActivity(), width / TERRITORY_SIZE_MAP)));
            } else {
                showWorldTerritoryMainArea();
            }

        } else {
            loadingTerritoryAreaView.setVisibility(View.GONE);
        }
    }

    public void showWorldTerritoryMainArea() {
        // The World
        loadingTerritoryAreaView.setVisibility(View.GONE);
        animateCameraCenterWorld();
    }

    protected void showTerritoryMainDetails(TerritoryViewModel territoryViewModel) {
        if (cleanedMap) {
            showTerritoryInfoView.setVisibility(View.VISIBLE);

            if (getActivity() != null) {

                if (((FootprintMainActivity) getActivity()).getFirstTerritoryKey().isEmpty() ||
                        ((FootprintMainActivity) getActivity()).getFirstTerritoryKey().equals(territoryViewModel.getKey())) {

                    ((FootprintMainActivity) getActivity()).setFirstTerritoryKey(territoryViewModel.getKey());

                    showTerritorySon.setVisibility(View.INVISIBLE);
                } else {
                    showTerritorySon.setVisibility(View.VISIBLE);
                }

                if (!((FootprintMainActivity) getActivity()).getActualTerritoryKeyList().contains(territoryViewModel.getKey())) {
                    ((FootprintMainActivity) getActivity()).getActualTerritoryKeyList().add(territoryViewModel.getKey());
                }

                if (territoryViewModel.getParentKey() != null && !territoryViewModel.getParentKey().isEmpty()) {

                    if (!((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().contains(territoryViewModel.getParentKey())) {
                        ((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().add(territoryViewModel.getParentKey());
                    }

                    showTerritoryParent.setVisibility(View.VISIBLE);
                } else {

                    // World Map
                    if (!((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().contains("")) {
                        ((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().add("");
                    }

                    removeTerritoryAreaPolygon();
                    showTerritoryParent.setVisibility(View.INVISIBLE);

                    animateCameraCenterWorld();
                }
            }

            myTerritoryPositionView.setVisibility(View.GONE);
            topRankingsView.setVisibility(View.GONE);
            territoryView.setText(territoryViewModel.getName());
            countryEmojiView.setText(territoryViewModel.getEmojiCountry());
            territoryParent.setText(((territoryViewModel.getParentName() != null && !
                    territoryViewModel.getParentName().isEmpty()) ? territoryViewModel.getParentName() : ""));
            territoryAreaView.setText(Html.fromHtml("&nbsp;" + territoryViewModel.getArea() +
                    context.getString(R.string.measure_square_meters_html)));
            territoryTotalFootprintsView.setText(SPACE + territoryViewModel.getTotalFootprints());

            leaderUsernameView.setText(territoryViewModel.getLeadername());
            leaderLevelView.setText(territoryViewModel.getLeaderLevel());

            if (territoryViewModel.getLeaderAudio() != null && !territoryViewModel.getLeaderAudio().isEmpty()) {
                playAudioLeaderView.setImageResource(R.drawable.ic_profile_audio);
            } else {
                playAudioLeaderView.setImageResource(R.drawable.ic_profile_audio_off);
            }

            if (!territoryViewModel.getLeaderLevel().isEmpty()) {
                icLevelView.setVisibility(View.VISIBLE);

                if (territoryViewModel.getLeaderImage() != null && !territoryViewModel.getLeaderImage().isEmpty()) {
                    Picasso.with(getActivity())
                            .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + territoryViewModel.getLeaderImage() + SERVER_IMAGES_SMALL_SUFFIX)
                            .placeholder(R.drawable.placeholder_profile)
                            .fit()
                            .centerCrop()
                            .into(leaderImageView);
                } else {
                    leaderImageView.setImageResource(R.drawable.placeholder_profile);
                }
            } else {
                icLevelView.setVisibility(View.GONE);
                leaderUsernameView.setText(getString(R.string.sms_empty_leaders));
                playAudioLeaderView.setVisibility(View.GONE);
                leaderImageView.setImageResource(R.drawable.placeholder_profile_empty_leader);
            }

        } else {
            loadingTerritoryAreaView.setVisibility(View.GONE);
        }
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

    private void initGestures() {
        this.googleMap.getUiSettings().setZoomControlsEnabled(false);
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
    }

    public void hideLoadingTerritoryMainArea() {
        loadingTerritoryAreaView.setVisibility(View.GONE);
    }

    public void showLoadingTerritoryMainDetails() {
        if (cleanedMap) {
            loadingTerritoryAreaView.setVisibility(View.VISIBLE);
            messageLoadingTerritoryAreaView.setText(getString(R.string.sms_loading_territory));
        } else {
            loadingTerritoryAreaView.setVisibility(View.GONE);
        }
    }

    public void showLoadingTerritoryArea() {
        if (cleanedMap) {
            loadingTerritoryAreaView.setVisibility(View.VISIBLE);
            messageLoadingTerritoryAreaView.setText(getString(R.string.sms_loading_territory_area));
        } else {
            loadingTerritoryAreaView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.territory)
    public void onTerritoryTextClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).onInfluencersTabClicked();
        }
    }

    @OnClick(R.id.territory_parent)
    public void onTerritoryParentTextClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).onInfluencersTabClicked();
        }
    }

    @OnClick(R.id.relative_start)
    public void onLeaderImageClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).onInfluencersTabClicked();
        }
    }

    @OnClick(R.id.leader_username)
    public void onLeaderUsernameClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).onInfluencersTabClicked();
        }
    }

    @OnClick(R.id.ic_level)
    public void onLeaderLevelClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).onInfluencersTabClicked();
        }
    }

    protected void initializeTutorialMap() {
        if (getActivity() != null) {
            new TutorialUtils(getActivity(), FONT_NAME, topRankingsView, myTerritoryPositionView, viewScanComplete, githubView,
                    ((FootprintMainActivity) getActivity()).getFootprintImage()).initializeTutorialMap();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (checkGpsPermission()) {
            if (!antiFakeGpsActivated) {
                locationManager.removeUpdates(locationListener);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (checkGpsPermission()) {
            if (!antiFakeGpsActivated) {
                locationManager.removeUpdates(locationListener);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!antiFakeGpsActivated) {
            initializeProviders();
        }
    }

    protected abstract void onCleanMapClicked();

    public abstract void onShowTerritoryMainClicked(String zoneKey, boolean fromMap);

    protected abstract void initializeShowTerritoryInfluencers(Location location);

    protected abstract void updatePresenterLocation();

    protected abstract void onFirstRadarScanSimulateClicked();
}
