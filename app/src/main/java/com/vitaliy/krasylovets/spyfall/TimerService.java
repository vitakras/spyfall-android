package com.vitaliy.krasylovets.spyfall;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;

import com.vitaliy.krasylovets.spyfall.resources.Location;

/**
 * This class is responsible for keeping track of the countdownTimer
 */
public class TimerService extends Service {

    private final LocalBinder localBinder = new LocalBinder();
    private CountDownTimer countDownTimer = null;

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.localBinder;
    }

    /**
     * Returns the Countdown timer used for keeping track of how much time there
     * is left in the game
     * @return
     */
    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    /**
     * Sets the timer to be used by the service that will run in the background
     * @param countDownTimer
     */
    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public TimerService getService() {
            // Return this instance of LocalService so clients can call public methods
            return TimerService.this;
        }
    }
}
