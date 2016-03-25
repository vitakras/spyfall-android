package com.infamous.pigeons.infamousSpy.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.infamous.pigeons.infamousSpy.R;
import com.infamous.pigeons.infamousSpy.fragments.RulesFragment;
import com.infamous.pigeons.infamousSpy.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.vitaliy.krasylovets.spyfall.SETTINGS_ID";
    private static final int DEFAULT_ID = 0;
    public static final int RULES_ID = 1;
    public static final int SETTINGS_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        // Configure Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(EXTRA_MESSAGE, DEFAULT_ID);

        setFragment(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets the fragment to start in the Settings activity
     * @param id
     */
    private void setFragment(int id){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;

        if (id == RULES_ID) {
            fragment = RulesFragment.newInstance();
        } else {
            fragment = SettingsFragment.newInstance();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}
