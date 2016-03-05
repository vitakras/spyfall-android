package com.vitaliy.krasylovets.spyfall;

import android.os.CountDownTimer;

/**
 * Created by Vitaliy on 3/5/2016.
 */
public class SpyFallTimer {

    private static final long DEFAULT_TIMER_INTERVAL = 1000;

    // Instance Variables
    private CountDownTimer timer;
    private long millisInFuture;
    private long millisUntilFinished;
    private long countDownInterval;
    private boolean paused = false;

    // Instance Interface
    private onTimerListener onTimerListener;

    public SpyFallTimer(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.millisUntilFinished = millisInFuture;
        this.countDownInterval = countDownInterval;
        this.timer = this.createCountDownTimer(millisInFuture,countDownInterval);
    }

    public SpyFallTimer(long millisInFuture) {
        this(millisInFuture, DEFAULT_TIMER_INTERVAL);
    }

    public void setOnTimerListener(SpyFallTimer.onTimerListener onTimerListener) {
        this.onTimerListener = onTimerListener;
    }

    public synchronized void start() {
        this.timer.start();
    }

    public synchronized void pause() {
        // can only pause if the time interval is different
        if (!this.paused && this.millisInFuture != this.millisUntilFinished) {
            this.timer.cancel();
            this.paused = true;
        }
    }

    public synchronized void resume() {
        if (paused) {
            this.timer = createCountDownTimer(millisUntilFinished, countDownInterval);
            this.start();
        }
    }

    private CountDownTimer createCountDownTimer(long millisInFuture, long countDownInterval) {
        return new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long l) {
                millisUntilFinished = l;

                if (onTimerListener != null) {
                    onTimerListener.onTick(millisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                if (onTimerListener != null) {
                    onTimerListener.onFinish();
                }
            }
        };
    }

    /**
     * Interface used to interact with SpyFallTimer
     */
    public interface onTimerListener {

        void onTick(long millisUntilFinished);

        void onFinish();
    }
}
