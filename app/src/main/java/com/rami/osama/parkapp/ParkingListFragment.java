package com.rami.osama.parkapp;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.List;

/**
 * Created by Osama on 2015-03-26.
 */
public final class ParkingListFragment extends ListFragment
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        List<Parking> parkings = ParkingStorage.getInstance(getActivity()).getAllParkings();
        ParkingListAdapter parkingListAdapter = new ParkingListAdapter(getActivity(), parkings);

        setListAdapter(parkingListAdapter);
    }

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Parking parking = (Parking) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), ParkingDetailsActivity.class);

        intent.putExtra(ParkingDetailsFragment.USER_DETAIL_ID_EXTRA, parking.mId);

        startActivity(intent);
    }*/
}