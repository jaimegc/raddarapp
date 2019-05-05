package com.raddarapp.presentation.android.utils;

import android.location.Location;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;
import com.raddarapp.domain.model.SimpleLocation;
import com.raddarapp.presentation.android.view.map.model.Bounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapUtils {

    private static final double LATITUDE_INCREASE_FACTOR = 10.5;
    public static int DEFAULT_MAP_PADDING = 30;

    public String increaseLatitude(final Bounds bounds) {
        double southwestLat = bounds.getSouthwest().getLatD();
        double northeastLat = bounds.getNortheast().getLatD();
        double updatedLat = LATITUDE_INCREASE_FACTOR * Math.abs(northeastLat - southwestLat);
        return String.valueOf(southwestLat - updatedLat);
    }

    public int calculateWidth(final WindowManager windowManager) {
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public int calculateHeight(final WindowManager windowManager, final int paddingBottom) {
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels - paddingBottom;
    }

    public double generateLatitude() {
        // World: Latitude(85, -85) Longitude(-180, 180)
        // Montequinto: Latitude(37.342794, 37.346035) Longitude(-5.930351, -5.934363)
        double minLatitude = 37.341794;
        double maxLatitude = 37.348035;
        Random randomGenerator = new Random();

        return minLatitude + (maxLatitude - minLatitude) * randomGenerator.nextDouble();
    }

    public double generateLongitude() {
        // World: Latitude(85, -85) Longitude(-180, 180)
        // Montequinto: Latitude(37.342794, 37.346035) Longitude(-5.930351, -5.934363)
        double minLongitude = -5.930351;
        double maxLongitude = -5.934363;
        Random randomGenerator = new Random();

        return minLongitude + (maxLongitude - minLongitude) * randomGenerator.nextDouble();
    }

    public LatLngBounds calculateSphericalRadiusInMeters(Location location, double radiusInMeters) {
        LatLng center = new LatLng(location.getLatitude(), location.getLongitude());
        double distanceFromCenterToCorner = radiusInMeters * Math.sqrt(2.0);
        LatLng southwestCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
    }

    public List<List<LatLng>> generateLatLngs(List<List<SimpleLocation>> area) {
        List<List<LatLng>> generatedLatLngs = new ArrayList<>();

        for (List<SimpleLocation> areaList : area) {
            List<LatLng> latLngs = new ArrayList<>();
            for (SimpleLocation areaSimple : areaList) {
                latLngs.add(new LatLng(areaSimple.getLatitude(), areaSimple.getLongitude()));
            }

            generatedLatLngs.add(latLngs);
        }

        return generatedLatLngs;
    }

    public LatLngBounds transformTerritoryAreaToLatLngBounds(List<LatLng> territory) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (LatLng latLng : territory) {
            builder.include(latLng);
        }

        return builder.build();
    }
}
