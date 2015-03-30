package com.rami.osama.parkapp;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class MyLocationListener implements LocationListener {

    private static final String TAG = "gps_tag";
    private double longitude;
    private double latitude;

    public final Location myLocation(){
        Location location = new Location("My location");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

    // Hämta senast kända GPS-position
    public final void getLastKnownLocation(Location loc){
        String longitude = "Longitude: " + loc.getLongitude();
        String latitude = "Latitude: " + loc.getLatitude();
    }

    @Override
    // Hämta GPS-position när man förflyttat sig (uppdaterad position)
    public void onLocationChanged(Location loc) {
        String longitude = "Longitude: " + loc.getLongitude();
        String latitude = "Latitude: " + loc.getLatitude();
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
