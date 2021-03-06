package com.infamous.pigeons.infamousSpy.resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for Creating SpyFall Objects
 * Created by vitaliy on 2016-02-08.
 */
public class SpyFallFactory {

    private static final String KEY_LOCATIONS = "locations";
    private static final String KEY_NAME = "name";
    private static final String KEY_PROFESSION = "roles";
    private static final String KEY_SPY = "spy";

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
        JSONArray locationJsonArray = jsonObject.getJSONArray(KEY_LOCATIONS);
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
     * Creates a new Spy Object from a String that Represents a JSON Object
     * @param spy String representing JSON Object
     * @return a Spy Object
     * @throws JSONException
     */
    public static SpyProfession getSpy(String spy) throws JSONException {
        return getSpy(new JSONObject(spy));
    }

    /**
     * Creates a new Spy Object from JSON Object
     * @param spy JSON object representing a spy
     * @return a Spy Object
     * @throws JSONException
     */
    public static SpyProfession getSpy(JSONObject spy) throws JSONException {
        String name = spy.getJSONObject(KEY_SPY).getString(KEY_NAME);
        return new SpyProfession(name);
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
