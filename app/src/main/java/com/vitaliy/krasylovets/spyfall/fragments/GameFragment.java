package com.vitaliy.krasylovets.spyfall.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.SpyFallApplication;
import com.vitaliy.krasylovets.spyfall.Utils;
import com.vitaliy.krasylovets.spyfall.adapters.LocationAdapter;
import com.vitaliy.krasylovets.spyfall.resources.Location;

import java.util.List;

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
    private List<Location> locationList;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        setHasOptionsMenu(true);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle(R.string.title_play);

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadLocationList();

        GridView gridView = (GridView) getView().findViewById(R.id.location_grid);
        gridView.setAdapter(new LocationAdapter(inflater.getContext(),locationList));

        // Sets up the timer
        countDownView = (TextView) getView().findViewById(R.id.countdown_txtView);
        initializeCountDownTimer();

        countDownTimer.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.game_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

    private void loadLocationList() {
        SpyFallApplication spyFallApplication = (SpyFallApplication) getActivity().getApplication();
        this.locationList = spyFallApplication.getSpyfallLocationList();
    }
}
