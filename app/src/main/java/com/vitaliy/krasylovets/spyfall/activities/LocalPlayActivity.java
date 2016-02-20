package com.vitaliy.krasylovets.spyfall.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.fragments.NewGameFragment;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class LocalPlayActivity extends Activity implements NewGameFragment.OnNewGameListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SpyFall");

        FragmentManager fm = getFragmentManager();
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

    }
}
