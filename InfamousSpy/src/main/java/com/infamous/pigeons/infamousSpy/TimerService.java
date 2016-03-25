package com.infamous.pigeons.infamousSpy;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * This class is responsible for keeping track of the countdownTimer
 */
public class TimerService extends Service {

    private final LocalBinder localBinder = new LocalBinder();
    private SpyFallTimer spyFallTimer = null;

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.localBinder;
    }

    public SpyFallTimer getSpyFallTimer() {
        return spyFallTimer;
    }

    public void setSpyFallTimer(SpyFallTimer spyFallTimer) {
        this.spyFallTimer = spyFallTimer;
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
