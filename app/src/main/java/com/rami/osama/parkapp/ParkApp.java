package com.rami.osama.parkapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;



public final class ParkApp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_app);

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);


        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.parking_container);

        if(fragment == null)
        {
            fm.beginTransaction().add(R.id.parking_container, new ParkingListFragment()).commit();
        }
    }
}
