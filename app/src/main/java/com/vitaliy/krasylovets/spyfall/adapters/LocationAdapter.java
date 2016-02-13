package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.LinearListLayout;
import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Location;

import java.util.Collections;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-09.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    // Variables
    private List<Location> locationList = Collections.emptyList();
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private int selectedLocation;

    public LocationAdapter(Context context, List<Location> locationList) {
        this.locationList = locationList;
        this.inflater = LayoutInflater.from(context);
        this.resetSelectedLocation();
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_drawer_row, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        holder.setLocation(this.locationList.get(position));

        View view = holder.itemView.findViewById(R.id.professionList);
        if (this.selectedLocation == position){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.locationList.size();
    }

    public void resetSelectedLocation() {
        this.selectedLocation = -1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView;
        private LinearListLayout linearListLayout;
        private Context context;

        public LocationViewHolder(View itemView) {
            super(itemView);

            this.context = itemView.getContext();
            this.textView = (TextView) itemView.findViewById(R.id.title);
            this.linearListLayout = new LinearListLayout((ViewGroup) itemView.findViewById(R.id.professionList));
        }

        public void setLocation(Location location){
            this.textView.setText(location.getName());
            this.linearListLayout.setAdapter(new ProfessionAdapter(context,
                    location.getProfessionList()));
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
