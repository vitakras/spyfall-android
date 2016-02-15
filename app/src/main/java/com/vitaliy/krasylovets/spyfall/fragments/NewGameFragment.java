package com.vitaliy.krasylovets.spyfall.fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.adapters.PlayerAdapter;
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

    public static NewGameFragment newInstace() {
        return new NewGameFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_new_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (playerList == null) {
            initializePlayerList();
        }

        RecyclerView recyclerView = (RecyclerView) getView()
               .findViewById(R.id.player_recycler_view);

        recyclerView.setAdapter(new PlayerAdapter(inflater.getContext(), playerList));
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
    }

    private void initializePlayerList() {
        playerList = new ArrayList<>(MAX_PLAYERS);

        for(int i = 1; i <= MIN_PLAYERS; i++) {
            Player player = new Player(getString(R.string.player) + " " + i);
            playerList.add(player);
        }
    }

}
