package com.infamous.pigeons.infamousSpy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.infamous.pigeons.infamousSpy.resources.Profession;
import com.infamous.pigeons.infamousSpy.R;

import java.util.List;

/**
 * Profession Adapter is Responsible for Displaying a List
 * of Professions
 * Created by vitaliy on 2016-02-09.
 */
public class ProfessionAdapter extends BaseAdapter {

    private Context context;
    final private List<Profession> professionList;

    public ProfessionAdapter(Context context, List<Profession> professionList) {
        this.context = context;
        this.professionList = professionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        // Get View from xml or use existing one
        if (convertView == null) {
            view = inflater.inflate(R.layout.profession_drawer_row, null);
        } else {
            view = convertView;
        }

        // set value into text view
        TextView textView = (TextView) view
                .findViewById(R.id.profession);

        textView.setText(professionList.get(position).getName());

        return view;
    }


    @Override
    public int getCount() {
        return this.professionList.size();
    }


    @Override
    public Object getItem(int position) {
        return this.professionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
