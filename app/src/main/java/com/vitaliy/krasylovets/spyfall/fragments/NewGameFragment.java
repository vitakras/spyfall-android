package com.vitaliy.krasylovets.spyfall.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.DividerItemDecorator;
import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.activities.SettingsActivity;
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
    private View addPlayerButton;

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
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.title_new_game);

        return inflater.inflate(R.layout.fragment_new_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (playerList == null) {
            initializePlayerList();
        }

        // Setup Recycle View
        this.playerAdapter = new PlayerAdapter(inflater.getContext(),
                playerList, PlayerAdapter.VIEW_TYPE_ADD_PLAYER);
        this.playerAdapter.setOnItemFocusChangeListener(new PlayerAdapter
                .OnItemFocusChangeListener() {
            @Override
            public void onItemFocusChange(View view, boolean hasFocus, int position) {
                if(!hasFocus) {
                    if (position == -1) {
                        return;
                    }

                    String txt = ((TextView) view).getText().toString();
                    txt = txt.trim(); // remove blank spaces

                    if (!txt.isEmpty()){
                        playerList.get(position).setName(txt);
                    }
                }
            }
        });

        this.playerAdapter.setOnItemClickListener(new PlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (playerList.size() > MIN_PLAYERS) {
                    playerList.remove(position);
                    playerAdapter.notifyItemRemoved(position);

                    if ((playerList.size() < MAX_PLAYERS) && addPlayerButton != null) {
                        addPlayerButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) getView()
               .findViewById(R.id.player_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.setAdapter(this.playerAdapter);

        initializeOnClickListeners();
    }

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.setting_btn:
                startSettingsActivity(SettingsActivity.SETTINGS_ID);
                break;
            case R.id.rules_btn:
                startSettingsActivity(SettingsActivity.RULES_ID);
                break;
        }

        return super.onOptionsItemSelected(item);
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

        FloatingActionButton newGameBTN = (FloatingActionButton) view.findViewById(R.id.new_game_btn);
        FloatingActionButton addPlayerBTN = (FloatingActionButton) view.findViewById(R.id.add_player_btn);

        // Sets On click Listers
        newGameBTN.setOnClickListener(this);
        addPlayerBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_player_btn:
                addPlayerButton = v;

                if (this.playerList.size() < MAX_PLAYERS) {
                    int newPosition = this.playerList.size();
                    this.playerList.add(createNewPlayer(newPosition + 1));
                    this.playerAdapter.notifyItemInserted(newPosition);

                    if (this.playerList.size() == MAX_PLAYERS) {
                        v.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.new_game_btn:
                this.mOnNewGameListener.onNewGame(this.playerList);
                break;
            default:
                break;
        }
    }

    private void startSettingsActivity(int msgType) {
        Intent intent = new Intent(getContext(), SettingsActivity.class);
        intent.putExtra(SettingsActivity.EXTRA_MESSAGE, msgType);
        startActivity(intent);
    }

    /**
     * Interface for Handling when a new game gets created
     */
    public interface OnNewGameListener{
        void onNewGame(List<Player> playerList);
    }
}
