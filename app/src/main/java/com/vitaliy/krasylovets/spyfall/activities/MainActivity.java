package com.vitaliy.krasylovets.spyfall.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.fragments.GameFragment;
import com.vitaliy.krasylovets.spyfall.fragments.NewGameFragment;
import com.vitaliy.krasylovets.spyfall.fragments.PlayerRolesFragment;
import com.vitaliy.krasylovets.spyfall.resources.Player;
import com.vitaliy.krasylovets.spyfall.resources.SpyFall;

import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class MainActivity extends AppCompatActivity implements NewGameFragment.OnNewGameListener,
    PlayerRolesFragment.OnPlayerRolesListener, GameFragment.OnGameInterface {

    private static final String NEW_GAME_FRAGMENT = "NEW_GAME_FRAGMENT";
    private static final String ROLES_FRAGMENT = "ROLES_FRAGMENT";

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
                    this.fragmentManager.popBackStack();

                    //backButtonHider();
                }
                backButtonHider();
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
        PlayerRolesFragment fragment = PlayerRolesFragment.newInstance(playerList);

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(NEW_GAME_FRAGMENT);
        ft.commit();
    }

    /**
     * PlayerRolesFragment OnPlayerRolesListener
     * @param spyFall
     */
    @Override
    public void onGameStart(SpyFall spyFall) {
        GameFragment fragment = GameFragment.newInstance();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(ROLES_FRAGMENT);
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

    @Override
    public void onPlayAgain() {
        this.fragmentManager.popBackStack(ROLES_FRAGMENT,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onGameCancel() {
        this.fragmentManager.popBackStack(NEW_GAME_FRAGMENT,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onTimeIsUp() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_help_outline_white_24px); // TODO replace
        builder.setContentTitle(getText(R.string.app_name));
        builder.setContentText(getText(R.string.time_is_up));

        Intent notifyIntent = getIntent();

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setAutoCancel(true);
        notificationManager.notify(1,builder.build());
    }
}
