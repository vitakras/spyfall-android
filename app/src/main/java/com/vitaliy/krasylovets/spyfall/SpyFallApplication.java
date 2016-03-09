package com.vitaliy.krasylovets.spyfall;

import android.app.Application;
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

    private static final long TIMER_COUNTDOWN = 5 * 1000; //1000 * 60 * 8;

    // Instance Variables
    private List<Location> spyfallLocationList;
    private SpyProfession spyProfession;
    private long countdownTimer = TIMER_COUNTDOWN;
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
        return countdownTimer;
    }

    public void setCountdownTimer(long countdownTimer) {
        this.countdownTimer = countdownTimer;
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
