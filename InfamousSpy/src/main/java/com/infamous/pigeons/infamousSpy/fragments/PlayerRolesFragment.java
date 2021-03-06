package com.infamous.pigeons.infamousSpy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.infamous.pigeons.infamousSpy.DividerItemDecorator;
import com.infamous.pigeons.infamousSpy.R;
import com.infamous.pigeons.infamousSpy.SpyFallApplication;
import com.infamous.pigeons.infamousSpy.adapters.PlayerAdapter;
import com.infamous.pigeons.infamousSpy.resources.Location;
import com.infamous.pigeons.infamousSpy.resources.Player;
import com.infamous.pigeons.infamousSpy.resources.Profession;
import com.infamous.pigeons.infamousSpy.resources.SpyFall;
import com.infamous.pigeons.infamousSpy.resources.SpyProfession;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * This Fragment is responsible for displaying player roles
 * Created by vitaliy on 2016-02-22.
 */
public class PlayerRolesFragment extends Fragment implements View.OnClickListener {

    private static final String PLAYER_KEY = "PLAYER_KEY";

    private LayoutInflater inflater;
    private OnPlayerRolesListener mPlayerRolesListener;
    private PlayerAdapter playerAdapter;
    private List<Player> playerList = Collections.emptyList();
    private SpyFall spyFall;

    public static PlayerRolesFragment newInstance(List<Player> playerList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER_KEY, (Serializable) playerList);

        PlayerRolesFragment fragment = new PlayerRolesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.mPlayerRolesListener = (OnPlayerRolesListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnPlayerRolesListener");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_roles, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        this.inflater = inflater;
        this.playerList = (List<Player>) getArguments().getSerializable(PLAYER_KEY);

        // Setup Toolbar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle(R.string.title_player_roles);

        return this.inflater.inflate(R.layout.fragment_player_roles, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createSpyFallGameInstance();
        this.spyFall.newGame();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Setup Recycle View
        this.playerAdapter = new PlayerAdapter(inflater.getContext(),
                this.playerList);
        this.playerAdapter.setOnItemClickListener(new PlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Profession profession = playerList.get(position).getProfession();
                Location location = spyFall.getLocation();


                // Configure Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.player_role);

                builder.setItems(new String[] {
                        getString(R.string.profession) + ": " + profession.getName(),
                        getString(R.string.location) + ": "
                                + (profession.isSpy() ? "???" : location.getName())}, null);
                builder.setPositiveButton(R.string.ok, null);

                // Create and show Dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) getView()
                .findViewById(R.id.player_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecorator(getContext(),R.drawable.divider));
        recyclerView.setAdapter(this.playerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        setOnClickListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.refresh_btn:
                this.spyFall.newGame();

                // Configure Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.assigned_new_roles);
                builder.setPositiveButton(R.string.ok, null);

                // Create and show Dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_game_btn){
            this.mPlayerRolesListener.onGameStart(this.spyFall);
        }
    }

    /**
     * Created a new SpyFall game Instance
     */
    private void createSpyFallGameInstance() {
        SpyFallApplication spyFallApplication = (SpyFallApplication) getActivity().getApplication();
        List<Location> locationList = spyFallApplication.getSpyfallLocationList();
        SpyProfession spyProfession = spyFallApplication.getSpyProfession();

        this.spyFall = new SpyFall(this.playerList, locationList, spyProfession);
    }

    private void setOnClickListeners() {
        View view = getView();
        FloatingActionButton startGameBTN = (FloatingActionButton)
                view.findViewById(R.id.start_game_btn);
        startGameBTN.setOnClickListener(this);
    }

    public interface OnPlayerRolesListener {
        void onGameStart(SpyFall spyFall);
    }
}
