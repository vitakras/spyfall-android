package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Location;

/**
 * Created by vitaliy on 2016-02-09.
 */
public class LocationViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;
    private GridView gridView;
    private Context context;

    public LocationViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();
        this.textView = (TextView) itemView.findViewById(R.id.title);
        this.gridView = (GridView) itemView.findViewById(R.id.gridView1);
    }

    public void setLocation(Location location){
        this.textView.setText(location.getName());
        this.gridView.setAdapter(new ProfessionAdapter(context,
                location.getProfessionList()));
    }

}
