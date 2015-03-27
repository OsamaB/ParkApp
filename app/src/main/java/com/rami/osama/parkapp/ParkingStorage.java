package com.rami.osama.parkapp;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Osama on 2015-03-26.
 */
public final class ParkingStorage {
    private static ParkingStorage INSTANCE;
    private final Map<String, Parking> mParkings;
    private final Context mContext;

    private ParkingStorage(Context context) {
        mParkings = new HashMap<>();
        mContext = context;

        try {
            getParkings(100);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ParkingStorage getInstance(Context context) {
        if (INSTANCE == null)
        {
            INSTANCE = new ParkingStorage(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private int radius;
// TODO: Byta ut lat och lng mot mobilens värden
    public void getParkings(final int radius) throws JSONException {
        this.radius = radius; ParkRestClient.get("within?radius=" + radius + "&lat=59.293802&lng=18.078095&maxFeatures=10&outputFormat=json&apiKey=a7984ad9-3548-420b-a0d6-071ae94f462b", null, new JsonHttpResponseHandler() {

            private String streetname, id;
            private int parkingSpots;
            private double lati, longi;
            private JSONArray features, coordinates, firstCoordinates;
            private JSONObject feature, geometry, properties;

            /*@Override
            public void onStart() {
                // called before request is started
            }*/

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Tar in alla objekt (parkeringsområden) som skickats in
                try {
                    features = response.getJSONArray("features");


                    System.out.println("featuresLength: " + features.length());

                    if(features.length() < 10){
                        int newRadius = radius + 100;
                        getParkings(newRadius);
//                        return;
                    }

                    for (int i = 0; i < features.length(); i++) {
                        feature = (JSONObject) features.get(i);

                        id = feature.getString("id");
                        geometry = feature.getJSONObject("geometry");

                        // Sparar alla koordinater
                        coordinates = geometry.getJSONArray("coordinates");
                        firstCoordinates = coordinates.getJSONArray(0);
                        // Sparar Lat och Long från första koordinat-paret i "coordinates"
                        longi = firstCoordinates.getDouble(0);
                        lati = firstCoordinates.getDouble(1);
                        // Sparar antalet parkeringsplatser i "parkingsSpots"
                        parkingSpots = coordinates.length();
                        // TEST!
                        System.out.println("spots: " + parkingSpots + "coordinates: " + coordinates);
                        properties = feature.getJSONObject("properties");
                        // Sparar gatunamnet
                        streetname = properties.getString("STREET_NAME");
                        // TEST!
                        System.out.println("streetname: " + streetname);


                        Parking parking = new Parking(streetname, parkingSpots, lati, longi, id);
                        mParkings.put(id, parking);
                    }
                        System.out.println("mParkings: " + mParkings.keySet());
                        System.out.println("mParkings: " + mParkings.values());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

                /*@Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }*/
        });
    }


    public List<Parking> getAllParkings() {
        return new ArrayList<Parking>(mParkings.values());
    }
}