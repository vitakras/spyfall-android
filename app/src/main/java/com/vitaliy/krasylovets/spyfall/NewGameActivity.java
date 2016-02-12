package com.vitaliy.krasylovets.spyfall;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.vitaliy.krasylovets.spyfall.adapters.LocationAdapter;
import com.vitaliy.krasylovets.spyfall.adapters.ProfessionAdapter;
import com.vitaliy.krasylovets.spyfall.fragments.LocationFragment;
import com.vitaliy.krasylovets.spyfall.resources.Location;
import com.vitaliy.krasylovets.spyfall.resources.SpyFallFactory;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

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

        FragmentManager fm = getFragmentManager();
        LocationFragment lf = LocationFragment.newInstance(locations);

        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.locationFragment, lf);
        ft.commit();

      // View view = getLayoutInflater().inflate(R.layout.profession_drawer_row, null);

       // ViewGroup viewGroup = (ViewGroup) findViewById(R.id.temp);
        //ProfessionAdapter test = new ProfessionAdapter(viewGroup.getContext(),locations.get(0).getProfessionList());

     //   for (int i = 0; i < test.getCount();i++){
       //     viewGroup.addView(test.getView(i,null,viewGroup));
        //}

        //viewGroup.addView(view);

      //  LinearListLayout temp = new LinearListLayout(viewGroup);
       // temp.setAdapter(new ProfessionAdapter(viewGroup.getContext(),locations.get(0).getProfessionList()));
        //viewGroup.

        //Log.d("Test", "onCreate: " + viewGroup.getChildCount());
        //Log.d("Test", "onCreate2: " + temp.getChildCount());
     //   RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawerList);
      //  recyclerView.setAdapter(new LocationAdapter(this, locations));
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
