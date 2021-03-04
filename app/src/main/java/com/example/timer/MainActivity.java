package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main Activity
 * When in portrait, displays just timer, but when in landscape, displays both timer and lap list
 * @author Ian Wong (thewongian)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimerFragment.OnFragmentInteractionListener, TaskFragment.TimerTasks {

    private TimerFragment timerFragment;
    private ListFragment listFragment;
    private TaskFragment taskFragment;
    private Button toList;
    //key for transferring list information
    public final static String KEY = "laps";
    //tag for task fragment
    public final static String TASK_TAG = "task_fragment";

    /**
     * Run when created
     * displays list if in landscape
     * checks to see if async task from taskFragment is running
     * then updates buttons accordingly
     * @param savedInstanceState
     * previous state saved by device
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //set content to main activity
        setContentView(R.layout.activity_main);
        //get frags
        FragmentManager fm = getSupportFragmentManager();
        timerFragment = (TimerFragment) fm.findFragmentById(R.id.timerFrag);
        listFragment = (ListFragment) fm.findFragmentById(R.id.listFrag);
        taskFragment = (TaskFragment) fm.findFragmentByTag(TASK_TAG);

        //make sure ui is up to date
        if (listFragment != null && listFragment.isInLayout()) {
            listFragment.setText(taskFragment.timer.listToString());
        }

        if (taskFragment.isRunning()) {
            timerFragment.setStartText("Stop");
        }
        //make sure taskFragment is instantiated
        if (taskFragment == null) {
            taskFragment = new TaskFragment();
            fm.beginTransaction().add(taskFragment, TASK_TAG).commit();
        }
        //instantiate button for portrait mode
        toList = (Button) findViewById(R.id.toList);

    }

    /**
     * Brings app to list activity in portrait mode
     * @param v
     * view being clicked
     */
    @Override
    public void onClick(View v) {
        if (listFragment == null || !listFragment.isInLayout()) {
            Intent intent = new Intent(this, LapListActivity.class);
            intent.putExtra(this.KEY, taskFragment.timer.listToString());
            startActivity(intent);
        }
    }

    /**
     * Implementing interaction listener method
     * When a button in timerfrag is clicked, this activity will know and react accordingly
     * @param infoID
     * id of button pressed, decides the action
     */
    @Override
    public void onButtonClicked(int infoID) {
        // start/stop
        if (infoID == 0) {
            taskFragment.onStartStop();
        }
        // lap
        else if (infoID == 1) {
            taskFragment.onLap(timerFragment.getTimeText());
            if (listFragment != null && listFragment.isInLayout()) {
                listFragment.setText(taskFragment.timer.listToString());
            }
        }
        // reset
        else if (infoID == 2) {
            taskFragment.onReset();
            timerFragment.setTime(taskFragment.timer.getTime().toString());

            if (listFragment != null && listFragment.isInLayout()) {
                listFragment.setText(taskFragment.timer.listToString());
            }
        }
    }

    /**
     * Implementing TimerTasks method
     * sets timer text for taskFragment
     * @param time
     * time that is being set
     */
    @Override
    public void setTimerText(String time) {
        timerFragment.setTime(time);
    }

}