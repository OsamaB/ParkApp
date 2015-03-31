package com.rami.osama.parkapp;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

class MyLocationListener implements LocationListener {

    private static final String TAG = "gps_tag";
    public static double longitude;
    public static double latitude;

    public Location myLocation(){
        Location location = new Location("My location");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

    @Override
    // Hämta GPS-position när man förflyttat sig (uppdaterad position)
    public void onLocationChanged(Location loc) {
        longitude = loc.getLongitude();
        latitude = loc.getLatitude();

        System.out.println(" --> " + longitude + " OnLocationChanged " + latitude + " <--- ");
    }

    @Override
    public void onProviderDisabled(String provider) {
        System.out.println(" !!! onProviderDisabled !!! ");
    }

    @Override
    public void onProviderEnabled(String provider) {
        System.out.println(" !!! onProviderEnabled !!! ");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        System.out.println(" !!! onStatusChanged !!! " + status);
    }

}
