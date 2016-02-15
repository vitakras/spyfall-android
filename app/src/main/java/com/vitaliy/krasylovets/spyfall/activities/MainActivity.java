package com.vitaliy.krasylovets.spyfall.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.vitaliy.krasylovets.spyfall.NewGameActivity;
import com.vitaliy.krasylovets.spyfall.R;

/**
 * MainActivity Responsible for Selecting Different game modes,
 * options and being able to exit the game
 */
public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupOnClickListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local_play_btn:
                Intent intent = new Intent(this, NewGameActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_btn:
                finish();
                break;
        }
    }

    private void setupOnClickListeners() {
        findViewById(R.id.local_play_btn).setOnClickListener(this);
        findViewById(R.id.exit_btn).setOnClickListener(this);
    }
}
