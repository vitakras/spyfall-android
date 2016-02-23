package com.vitaliy.krasylovets.spyfall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.adapters.PlayerAdapter;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This Fragment is responsible for displaying player roles
 * Created by vitaliy on 2016-02-22.
 */
public class PlayRolesFragment extends Fragment {

    private static final String PLAYER_KEY = "PLAYER_KEY";

    private LayoutInflater inflater;
    private PlayerAdapter playerAdapter;
    private List<Player> playerList = Collections.emptyList();

    public static PlayRolesFragment newInstance(List<Player> playerList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER_KEY, (Serializable) playerList);

        PlayRolesFragment fragment = new PlayRolesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.playerList = (List<Player>) getArguments().getSerializable(PLAYER_KEY);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle(R.string.title_player_roles);

        return this.inflater.inflate(R.layout.fragment_player_roles, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Setup Recycle View
        this.playerAdapter = new PlayerAdapter(inflater.getContext(),
                this.playerList, PlayerAdapter.VIEW_TYPE_ROLES);

        RecyclerView recyclerView = (RecyclerView) getView()
                .findViewById(R.id.player_recycler_view);
        recyclerView.setAdapter(this.playerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
    }
}
