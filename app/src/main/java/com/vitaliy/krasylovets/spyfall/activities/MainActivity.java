package com.vitaliy.krasylovets.spyfall.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.Utils;
import com.vitaliy.krasylovets.spyfall.fragments.GameFragment;
import com.vitaliy.krasylovets.spyfall.fragments.NewGameFragment;
import com.vitaliy.krasylovets.spyfall.resources.Location;
import com.vitaliy.krasylovets.spyfall.resources.Player;
import com.vitaliy.krasylovets.spyfall.resources.SpyFall;
import com.vitaliy.krasylovets.spyfall.resources.SpyFallFactory;
import com.vitaliy.krasylovets.spyfall.resources.SpyProfession;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class MainActivity extends AppCompatActivity implements NewGameFragment.OnNewGameListener{

    // Instance Variables
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.fragmentManager = getSupportFragmentManager();
        NewGameFragment newGameFragment = NewGameFragment.newInstance();

        // Creates New Game Fragment
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, newGameFragment);
        ft.commit();

        backButtonHider();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (this.fragmentManager.getBackStackEntryCount() > 0) {
                    this.fragmentManager.popBackStackImmediate();
                    backButtonHider();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * NewGameFragment OnNewGameListener
     * @param playerList players for the new game
     */
    @Override
    public void onNewGame(List<Player> playerList) {
        InputStream stream = getResources().openRawResource(R.raw.locations);//Resources.getSystem().openRawResource(R.raw.locations);

        List<Location> locations = null;
        try {
            locations = SpyFallFactory.getLocationList(Utils.readResourceData(stream));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpyFall game = new SpyFall(playerList,locations, new SpyProfession("Spy"));
        game.newGame();

        for (Player player : playerList){
            Log.d("Player", "onNewGame: " + player.toString());
        }

        GameFragment gameFragment = GameFragment.newInstance();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, gameFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Checks to see if the FragmentManager stack is empty
     * hides the home button if its empty
     */
    private void backButtonHider() {
        if (this.fragmentManager.getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}
