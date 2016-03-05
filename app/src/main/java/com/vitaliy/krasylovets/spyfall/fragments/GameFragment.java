package com.vitaliy.krasylovets.spyfall.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.vitaliy.krasylovets.spyfall.SpyFallTimer;
import com.vitaliy.krasylovets.spyfall.TimerService;
import com.vitaliy.krasylovets.spyfall.Utils;
import com.vitaliy.krasylovets.spyfall.adapters.LocationAdapter;
import com.vitaliy.krasylovets.spyfall.resources.Location;

import java.util.List;

/**
 * This fragment is responsible for displaying the game
 * Created by vitaliy on 2016-02-20.
 */
public class GameFragment extends Fragment {

    private static final long TIMER_COUNTDOWN = 1000 * 60 * 8;

    private LayoutInflater inflater;
    private SpyFallTimer spyFallTimer;
    private TextView countDownView;
    private Menu menu;
    private List<Location> locationList;
    private TimerService timerService;
    private boolean serviceBound = false;

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

        Intent intent = new Intent(getContext(), TimerService.class);
        getActivity().bindService(intent, this.serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        inflater.inflate(R.menu.game_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pause_btn:
                MenuItem playBtn = menu.findItem(R.id.play_btn);
                playBtn.setVisible(true);
                item.setVisible(false);

                this.spyFallTimer.pause();
                return true;
            case R.id.play_btn:
                MenuItem pauseBtn = menu.findItem(R.id.pause_btn);
                pauseBtn.setVisible(true);
                item.setVisible(false);
                this.spyFallTimer.resume();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (this.serviceBound){
            getActivity().unbindService(this.serviceConnection);
        }
    }

    private void initializeSpyFallTimer() {
        this.spyFallTimer = new SpyFallTimer(TIMER_COUNTDOWN);
        this.spyFallTimer.setOnTimerListener(new SpyFallTimer.onTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownView.setText(Utils.timeFormat(millisUntilFinished));
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void loadLocationList() {
        SpyFallApplication spyFallApplication = (SpyFallApplication) getActivity().getApplication();
        this.locationList = spyFallApplication.getSpyfallLocationList();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TimerService.LocalBinder binder = (TimerService.LocalBinder) iBinder;
            timerService = binder.getService();
            serviceBound = true;

            // configures the timer for the Service
            SpyFallTimer timer = timerService.getSpyFallTimer();
            if (timer == null) {
                // Sets up the timer
                countDownView = (TextView) getView().findViewById(R.id.countdown_txtView);
                initializeSpyFallTimer();

                // Sets the timer in the service
                timerService.setSpyFallTimer(spyFallTimer);
                spyFallTimer.start();
            } else {
                spyFallTimer = timer;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBound = false;
        }
    };
}
