package com.vitaliy.krasylovets.spyfall.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.fragments.LocationFragment;
import com.vitaliy.krasylovets.spyfall.fragments.NewGameFragment;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class LocalPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play);

        FragmentManager fm = getFragmentManager();
        NewGameFragment ngf = NewGameFragment.newInstace();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, ngf);
        ft.commit();
    }
}
