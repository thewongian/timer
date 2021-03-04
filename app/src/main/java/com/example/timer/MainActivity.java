package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimerFragment.OnFragmentInteractionListener {

    TimerFragment timerFragment;
    ListFragment listFragment;
    Button toList;
    public final static String KEY = "laps";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerFragment = (TimerFragment) getSupportFragmentManager().findFragmentById(R.id.timerFrag);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);

        toList = (Button) findViewById(R.id.toList);

    }

    @Override
    public void onClick(View v) {
        if (listFragment == null || !listFragment.isInLayout()) {
            Intent intent = new Intent(this, LapListActivity.class);
            intent.putExtra(this.KEY, timerFragment.timer.listToString());
            startActivity(intent);
        }
    }

    @Override
    public void onButtonClicked(int infoID) {
        if (infoID == 0) {
            if (listFragment != null && listFragment.isInLayout()) {
                listFragment.setText(timerFragment.timer.listToString());
            }
        }
        else if (infoID == 1) {
            if (listFragment != null && listFragment.isInLayout()) {
                listFragment.setText(timerFragment.timer.listToString());
            }
        }
    }
}