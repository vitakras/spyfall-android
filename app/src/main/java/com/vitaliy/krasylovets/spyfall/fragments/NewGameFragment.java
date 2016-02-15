package com.vitaliy.krasylovets.spyfall.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class NewGameFragment extends Fragment {

    private static final int MAX_PLAYERS = 8;
    private static final int MIN_PLAYERS = 4;

    // Instance Variables
    private List<Player> playerList;
    private LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;

        return inflater.inflate(R.layout.fragment_new_game, container);
    }

    @Override
    public void onStart() {
        if (playerList == null) {
            initializePlayerList();
        }
    }

    private void initializePlayerList() {
        playerList = new ArrayList<>(MAX_PLAYERS);

        for(int i = 0; i < MIN_PLAYERS; i++) {
            Player player = new Player(R.string.player + " " + (i + 1));
            playerList.add(player);
        }
    }

}
