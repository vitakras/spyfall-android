package com.vitaliy.krasylovets.spyfall.resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-08.
 */
public class SpyFallFactory {

    private static final String LOCATION_ARRAY = "locations";
    private static final String KEY_NAME = "name";
    private static final String KEY_PROFESSION = "roles";

    /**
     * Returns a List of a SpyFall Locations
     * @param locations a json String with locations
     * @return List of SpyFall locations
     * @throws JSONException
     */
    public static List<Location> getLocationList(String locations) throws JSONException {
        JSONObject jsonObject = new JSONObject(locations);
        return getLocationList(jsonObject);
    }

    /**
     * Returns a List of a SpyFall Locations
     * @param jsonObject a json Object representing the location list
     * @return List of SpyFall locations
     * @throws JSONException
     */
    public static List<Location> getLocationList(JSONObject jsonObject) throws JSONException {
        JSONArray locationJsonArray = jsonObject.getJSONArray(LOCATION_ARRAY);
        List<Location> locations = new ArrayList<>
                (locationJsonArray.length());

        // Gets the Location
        for (int i = 0; i < locationJsonArray.length(); i++) {
            JSONObject location = locationJsonArray.getJSONObject(i);
            locations.add(getLocationFromJson(location));
        }

        return locations;
    }

    /**
     * Created a Location Object from a JSON String
     * @param jsonLocationObj JSON representing a location
     * @return a new Location Object
     * @throws JSONException
     */
    private static Location getLocationFromJson(JSONObject jsonLocationObj) throws JSONException {
        String name = jsonLocationObj.getString(KEY_NAME);
        JSONArray professions = jsonLocationObj.getJSONArray(KEY_PROFESSION);

        // Goes through the JSON Array and gets a List of Professions
        List<Profession> professionList = new ArrayList<>(professions.length());
        for (int i = 0; i < professions.length(); i++) {
            Profession newProfession = new Profession(professions.getString(i));
            professionList.add(newProfession);
        }

        return new Location(name, professionList);
    }
}
