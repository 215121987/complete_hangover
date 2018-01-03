package com.hangover.ashqures.hangover.listener;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hangover.ashqures.hangover.activity.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by ashqures on 9/10/16.
 */
public class AppLocationService extends Service implements LocationListener {

    private static final String TAG = "ApplicationService";

    protected LocationManager locationManager;
    private Location location;
    private Handler handler;
    protected Context context;

    private static final long MIN_DISTANCE_FOR_UPDATE = 1;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;


    public AppLocationService(Context context, final Handler handler) {
        locationManager = (LocationManager) context
                .getSystemService(LOCATION_SERVICE);
        this.context = context;
        this.handler = handler;
    }

    public boolean checkPermission(String provider) {
        return this.locationManager.isProviderEnabled(provider);
    }

    public void execute(String provider) {
        try {
            locationManager.requestLocationUpdates(provider,
                    MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
        } catch ( SecurityException e ) {
            Log.d(TAG,"Unable to request location", e);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LocationAddress.getAddressFromLocation(location.getLatitude(), location.getLongitude(), context, handler);
        try {
            locationManager.removeUpdates(this);
        } catch ( SecurityException e ) {
            Log.d(TAG,"Unable to remove updates", e);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
