package com.vitaliy.krasylovets.spyfall.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.adapters.LocationAdapter;
import com.vitaliy.krasylovets.spyfall.resources.Location;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-11.
 */
public class LocationFragment extends Fragment {

    private static final String LOCATION_KEY="LOCATION";

    // Instance Variables
    private List<Location> locationList = Collections.emptyList();
    private LayoutInflater inflater;
    private LocationAdapter adapter;
    private View lastSelectedLocationView;

    public static LocationFragment newInstance(List<Location> locationList) {
        LocationFragment fragment = new LocationFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(LOCATION_KEY, (Serializable) locationList);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.locationList = (List<Location>) getArguments().getSerializable(LOCATION_KEY);
        this.inflater = inflater;

        // Configure Adapter
        this.adapter = new LocationAdapter(inflater.getContext(), this.locationList);
        this.adapter.setOnItemClickListener(new LocationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (lastSelectedLocationView != null) {
                    lastSelectedLocationView.findViewById(R.id.professionList)
                            .setVisibility(View.GONE);
                }

                if (adapter.getSelectedLocation() == position){
                    adapter.resetSelectedLocation();
                    view.findViewById(R.id.professionList).setVisibility(View.GONE);
                } else {
                    adapter.setSelectedLocation(position);
                    view.findViewById(R.id.professionList).setVisibility(View.VISIBLE);
                }

                lastSelectedLocationView = view;
            }
        });


        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) getView()
                .findViewById(R.id.location_recycler_view);

        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

    }
}
