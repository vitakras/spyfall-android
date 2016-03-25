package com.infamous.pigeons.infamousSpy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.infamous.pigeons.infamousSpy.R;
import com.infamous.pigeons.infamousSpy.resources.Location;

import java.util.List;

/**
 * Created by Vitaliy on 3/3/2016.
 */
public class LocationAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Location> locationList;

    public LocationAdapter(Context context, List<Location> locationList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.locationList = locationList;
    }


    @Override
    public int getCount() {
        return this.locationList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.locationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = null;

        if (view == null) {
            textView = (TextView) inflater.inflate(R.layout.profession_drawer_row, viewGroup, false);
            textView.setText(this.locationList.get(i).getName());
        }
        else {
            textView = (TextView) view;
        }

        return textView;
    }
}
