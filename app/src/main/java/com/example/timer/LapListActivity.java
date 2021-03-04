package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class LapListActivity extends AppCompatActivity implements View.OnClickListener {
    private ListFragment listFragment;
    private Button toTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_list);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        Bundle b1 = getIntent().getExtras();
        toTimer = (Button) findViewById(R.id.toTimer);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag2);

        String laps = b1.getString("laps");
        listFragment.setText(laps);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}