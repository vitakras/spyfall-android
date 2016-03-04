package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.resources.Location;

import java.util.Collection;
import java.util.List;

/**
 * Created by Vitaliy on 3/3/2016.
 */
public class LocationAdapter extends BaseAdapter {

    private Context context;
    private List<Location> locationList;

    public LocationAdapter(Context context, List<Location> locationList) {
        this.context = context;
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
            textView = new TextView(this.context);
            textView.setText(this.locationList.get(i).getName());
        }
        else {
            textView = (TextView) view;
        }

        return textView;
    }
}
