package com.vitaliy.krasylovets.spyfall.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.fragments.NewGameFragment;
import com.vitaliy.krasylovets.spyfall.fragments.PlayRolesFragment;
import com.vitaliy.krasylovets.spyfall.resources.Player;

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
        PlayRolesFragment fragment = PlayRolesFragment.newInstance(playerList);

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
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
