package com.vitaliy.krasylovets.spyfall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.adapters.PlayerAdapter;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This Fragment is responsible for creating a new Local Game
 * Created by vitaliy on 2016-02-15.
 */
public class NewGameFragment extends Fragment implements View.OnClickListener {

    // Static Variables
    private static final int MAX_PLAYERS = 8;
    private static final int MIN_PLAYERS = 4;

    // Instance Variables
    private List<Player> playerList;
    private LayoutInflater inflater;
    private PlayerAdapter playerAdapter;
    private OnNewGameListener mOnNewGameListener;

    public static NewGameFragment newInstance() {
        return new NewGameFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.mOnNewGameListener = (OnNewGameListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNewGameListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.new_game));

        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_new_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (playerList == null) {
            initializePlayerList();
        }

        // Setup Recycle View
        this.playerAdapter = new PlayerAdapter(inflater.getContext(), playerList);
        RecyclerView recyclerView = (RecyclerView) getView()
               .findViewById(R.id.player_recycler_view);
        recyclerView.setAdapter(this.playerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        initializeOnClickListeners();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    /**
     * Initializes the Player List in the fragment
     */
    private void initializePlayerList() {
        playerList = new ArrayList<>(MAX_PLAYERS);

        for(int i = 1; i <= MIN_PLAYERS; i++) {
            playerList.add(createNewPlayer(i));
        }
    }

    /**
     * Created a new Player based on the Position in the list
     * @param position
     * @return new Player Object
     */
    private Player createNewPlayer(int position) {
        return new Player(getString(R.string.player) + " " + position);
    }

    /**
     * Configure OnClickListers for this View
     */
    private void initializeOnClickListeners() {
        View view = getView();

        Button newGameBTN = (Button) view.findViewById(R.id.new_game_btn);
        Button addPlayerBTN = (Button) view.findViewById(R.id.add_player_btn);
        Button removePlayerBTN = (Button) view.findViewById(R.id.remove_player_btn);

        // Sets On click Listers
        newGameBTN.setOnClickListener(this);
        addPlayerBTN.setOnClickListener(this);
        removePlayerBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_player_btn:
                if (this.playerList.size() < MAX_PLAYERS) {
                    int newPosition = this.playerList.size();
                    this.playerList.add(createNewPlayer(newPosition + 1));
                    this.playerAdapter.notifyItemInserted(newPosition);
                }
                break;
            case R.id.remove_player_btn:
                if (this.playerList.size() > MIN_PLAYERS) {
                    this.playerList.remove(this.playerList.size() - 1);
                    this.playerAdapter.notifyItemRemoved(this.playerList.size());
                }
                break;
            case R.id.new_game_btn:
                this.mOnNewGameListener.onNewGame(this.playerList);
                break;
            default:
                break;
        }
    }

    /**
     * Interface for Handling when a new game gets created
     */
    public interface OnNewGameListener{
        void onNewGame(List<Player> playerList);
    }
}
