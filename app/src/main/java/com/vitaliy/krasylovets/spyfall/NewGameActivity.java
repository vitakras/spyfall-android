package com.vitaliy.krasylovets.spyfall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.vitaliy.krasylovets.spyfall.adapters.LocationAdapter;
import com.vitaliy.krasylovets.spyfall.adapters.ProfessionAdapter;
import com.vitaliy.krasylovets.spyfall.resources.Location;
import com.vitaliy.krasylovets.spyfall.resources.SpyFallFactory;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NewGameActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        InputStream stream = getResources().openRawResource(R.raw.locations);//Resources.getSystem().openRawResource(R.raw.locations);

        List<Location> locations = null;
        try {
            locations = SpyFallFactory.getLocationList(Utils.readResourceData(stream));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        recyclerView.setAdapter(new LocationAdapter(this, locations));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
