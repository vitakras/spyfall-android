package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Location;

import java.util.Collections;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-09.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {

    // Variables
    List<Location> locationList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public LocationAdapter(Context context, List<Location> locationList) {
        this.locationList = locationList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_drawer_row, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        holder.setLocation(this.locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.locationList.size();
    }
}
