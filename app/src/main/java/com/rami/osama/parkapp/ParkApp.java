package com.rami.osama.parkapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public final class ParkApp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_app);
        System.out.println(" ---> ParkApp <--- ");
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        System.out.println("Criteria: " + criteria.toString());

       /* if (networkEnabledLocation)
        {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        else if (gpsEnabledLocation)
        {
            provider = LocationManager.GPS_PROVIDER;
        }
        this.locationManager.requestLocationUpdates(provider, 1000, 0, this.locationListener);
*/
        String provider = locationManager.getBestProvider(criteria, true);
        System.out.println(" !! Provider: " + provider);

        Location location = locationManager.getLastKnownLocation(provider);
        System.out.println(" ---> Get Last known Loc <-- ");

        if(location != null)
        {
            MyLocationListener.latitude = location.getLatitude();
            MyLocationListener.longitude = location.getLongitude();
            System.out.println(location + " " + location.toString());
        }

        MyLocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(
                provider, 30000, 10, locationListener);

        System.out.println(" ---> MyLocListener <--- ");

        /*while (!locationListener.Triggered)
        {
            Location lastLocation = this.locationManager.getLastKnownLocation(provider);
            if (lastLocation != null) {
                String lastLocationString = "Latitude: " + lastLocation.getLatitude() + ". Longitude: " + lastLocation.getLongitude() + ". Provided by: " + lastLocation.getProvider() + " at " + lastLocation.getTime();
                Log.i("locationManager", lastLocationString);
            }

            Thread.sleep(5 * 1000);
        }*/

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.parking_container);

        if(fragment == null)
        {
            fm.beginTransaction().add(R.id.parking_container, new ParkingListFragment()).commit();
            System.out.println( " ---> fragment != parking_container ");
        }
    }
}
