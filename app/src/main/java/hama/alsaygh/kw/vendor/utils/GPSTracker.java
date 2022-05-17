package hama.alsaygh.kw.vendor.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import hama.alsaygh.kw.vendor.dialog.GPSSettingDialog;


public final class GPSTracker implements LocationListener {

    private final Context mContext;
    private LocationListener myLocationListener;

    // flag for GPS status
    private boolean isGPSEnabled = false;

    // flag for network status
    private boolean isNetworkEnabled = false;

    // flag for GPS status
    private boolean canGetLocation = false;

    private Location location; // location
    private double latitude; // latitude
    private double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 500; // 1 minute

    // Declaring a Location Manager
    private LocationManager locationManager;
    private static GPSSettingDialog dialog;

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public GPSTracker(Context context, LocationListener myLocationListener) {
        this.mContext = context;
        this.myLocationListener = myLocationListener;
        getLocation();
    }

    public LocationListener getMyLocationListener() {
        return myLocationListener;
    }

    public void setMyLocationListener(LocationListener myLocationListener) {
        this.myLocationListener = myLocationListener;
    }

    /**
     * Function to get the user's current location
     *
     * @return location
     */
    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            Log.v("GpsLocationReceiver", "isGPSEnabled=" + isGPSEnabled);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            Log.v("GpsLocationReceiver", "isNetworkEnabled=" + isNetworkEnabled);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                this.canGetLocation = false;

            } else {
                this.canGetLocation = true;

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    location = null;
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("GpsLocationReceiver", "GPS Enabled");

                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }


                if (isNetworkEnabled) {
                    if (location == null) {
                        if (locationManager != null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            Log.d("GpsLocationReceiver", "Network");
                        }

                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     */
    @SuppressLint("MissingPermission")
    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    /**
     * Function to get latitude
     */
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    public boolean canGetLocation() {
        return isCanGetLocation();
    }


    private boolean isCanGetLocation() {
        locationManager = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        Log.v("isGPSEnabled", "=" + isGPSEnabled);

        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Log.v("isNetworkEnabled", "=" + isNetworkEnabled);

        // no network provider is enabled
        canGetLocation = isGPSEnabled || isNetworkEnabled;

        return canGetLocation;
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    public void showSettingsAlert(final FragmentActivity context) {
        if (context == null)
            return;
        try {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    dialog = new GPSSettingDialog();
                    dialog.show(context.getSupportFragmentManager(), "checkout");

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;

        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        if (myLocationListener != null) {
            myLocationListener.onLocationChanged(location);
        }

        stopUsingGPS();

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
}
