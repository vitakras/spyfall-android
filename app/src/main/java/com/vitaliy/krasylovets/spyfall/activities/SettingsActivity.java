package com.vitaliy.krasylovets.spyfall.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.vitaliy.krasylovets.spyfall.R;

public class SettingsActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.vitaliy.krasylovets.spyfall.SETTINGS_ID";
    private static final int DEFAULT_ID = 0;
    public static final int RULES_ID = 1;
    public static final int SETTINGS_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Configure Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(EXTRA_MESSAGE, DEFAULT_ID);

        Log.d("Activity id:", "" + id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
