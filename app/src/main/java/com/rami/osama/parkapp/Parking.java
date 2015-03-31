package com.rami.osama.parkapp;

/**
 * Created by Osama on 2015-03-26.
 */

public final class Parking
{
    public final double mLatitude, mLongitude;
    public final String mAddress, mId;
    public final int mParkingSpots, mDistance;

    public Parking(String address, int parkingSpots, double latitude, double longitude, int distance, String id)
    {
        mAddress = address;
        mParkingSpots = parkingSpots;
        mLatitude = latitude;
        mLongitude = longitude;
        mDistance = distance;
        mId = id;
    }
}