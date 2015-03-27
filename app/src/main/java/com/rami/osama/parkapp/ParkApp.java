package com.rami.osama.parkapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;



public final class ParkApp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_app);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.parking_container);

        if(fragment == null)
        {
            fm.beginTransaction().add(R.id.parking_container, new ParkingListFragment()).commit();
        }
    }
}
