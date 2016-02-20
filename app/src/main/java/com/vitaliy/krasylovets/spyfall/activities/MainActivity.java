package com.vitaliy.krasylovets.spyfall.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.Utils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SpyFall");

        FragmentManager fm = getSupportFragmentManager();
        NewGameFragment ngf = NewGameFragment.newInstance();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, ngf);
        ft.commit();
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
    }
}
