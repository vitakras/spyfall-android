package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.LinearListLayout;
import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Location;

/**
 * Created by vitaliy on 2016-02-09.
 */
public class LocationViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;
    private LinearListLayout linearListLayout;
    private Context context;

    public LocationViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();
        this.textView = (TextView) itemView.findViewById(R.id.title);
        this.linearListLayout = new LinearListLayout((ViewGroup) itemView.findViewById(R.id.gridView1));
    }

    public void setLocation(Location location){
        this.textView.setText(location.getName());
        this.linearListLayout.setAdapter(new ProfessionAdapter(context,
                location.getProfessionList()));
    }

}
