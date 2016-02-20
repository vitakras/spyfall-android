package com.vitaliy.krasylovets.spyfall.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.Utils;

/**
 * This fragment is responsible for displaying the game
 * Created by vitaliy on 2016-02-20.
 */
public class GameFragment extends Fragment {

    private static final long TIMER_INTERVAL = 1000;
    private static final long TIMER_COUNTDOWN = 1000 * 60 * 8;

    private LayoutInflater inflater;
    private CountDownTimer countDownTimer;
    private TextView countDownView;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        countDownView = (TextView) getView().findViewById(R.id.countdown_txtView);

        initializeCountDownTimer();

        countDownTimer.start();
    }

    private void initializeCountDownTimer() {
        this.countDownTimer = new CountDownTimer(TIMER_COUNTDOWN, TIMER_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownView.setText(Utils.timeFormat(millisUntilFinished));
            }

            @Override
            public void onFinish() {

            }
        };
    }
}
