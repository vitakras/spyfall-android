package com.vitaliy.krasylovets.spyfall;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;

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

    private static final String SHARED_PREFERENCE_KEY = "Spyfall_preference_key";
    private static final String TIMER_KEY = "TIMER_KEY";
    private static final long TIMER_COUNTDOWN = 1000 * 60 * 8;

    // Instance Variables
    private List<Location> spyfallLocationList;
    private SpyProfession spyProfession;
    private Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

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

    public long getCountdownTimer() {
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        long countdownTimer = sharedPreferences.getLong(TIMER_KEY, TIMER_COUNTDOWN);
        return countdownTimer;
    }

    /**
     * Sets the Countdown timer of the application and saves it the preferences
     * @param countdownTimer time in milliseconds
     */
    public void setCountdownTimer(long countdownTimer) {
        Context context = getApplicationContext();

        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_KEY,
                Context.MODE_PRIVATE);

        // Edits the property
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(TIMER_KEY, countdownTimer);
        editor.commit();
    }

    public Uri getNotification() {
        return notification;
    }

    public void setNotification(Uri notification) {
        this.notification = notification;
    }

    /**
     * Loads the Location and Spy data from the JSON
     */
    private void loadLocationData() {
        InputStream stream = getResources().openRawResource(R.raw.locations);

        try {
            String data = Utils.readResourceData(stream);

            this.spyfallLocationList = SpyFallFactory.getLocationList(data);
            this.spyProfession = SpyFallFactory.getSpy(data);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
