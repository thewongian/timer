package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Lap List Activity that has a lap list fragment
 * just displays list of laps
 * @author Ian Wong (thewongian)
 */
public class LapListActivity extends AppCompatActivity implements View.OnClickListener {
    private ListFragment listFragment;
    private Button toTimer;

    /**
     * run when activity is created
     * @param savedInstanceState
     * state saved on device
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view to lap list
        setContentView(R.layout.activity_lap_list);

        //checks if in in landscape
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        //print list info
        Bundle b1 = getIntent().getExtras();
        toTimer = (Button) findViewById(R.id.toTimer);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag2);

        String laps = b1.getString("laps");
        listFragment.setText(laps);
    }

    /**
     * Goes back to the previous page
     * @param v
     * view clicked
     */
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}