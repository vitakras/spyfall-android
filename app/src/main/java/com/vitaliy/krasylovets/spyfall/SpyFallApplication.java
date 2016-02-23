package com.vitaliy.krasylovets.spyfall;

import android.app.Application;

import com.vitaliy.krasylovets.spyfall.resources.Location;
import com.vitaliy.krasylovets.spyfall.resources.SpyFallFactory;
import com.vitaliy.krasylovets.spyfall.resources.SpyProfession;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Used for Storing Common Data among the whole aplication
 * Created by vitaliy on 2016-02-22.
 */
public class SpyFallApplication extends Application {

    // Instance Variables
    private List<Location> spyfallLocationList;
    private SpyProfession spyProfession;

    /**
     * Gets a List of Locations from the JSON file
     * @return a List of Locations
     */
    public List<Location> getSpyfallLocationList() {
        if (this.spyfallLocationList == null) {
           loadLocationData();
        }

        return spyfallLocationList;
    }

    /**
     * Gets the Spy Profession from the JSON file
     * @return Spy Profession
     */
    public SpyProfession getSpyProfession() {
        if (this.spyProfession == null) {
            loadLocationData();
        }

        return spyProfession;
    }


    /**
     * Loads the Location and Spy data from the JSON
     */
    private void loadLocationData() {
        InputStream stream = getResources().openRawResource(R.raw.locations);

        try {
            this.spyfallLocationList = SpyFallFactory.getLocationList(
                    Utils.readResourceData(stream));
            this.spyProfession = SpyFallFactory.getSpy(Utils.readResourceData(stream));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
