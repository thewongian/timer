package com.example.timer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * Task Fragment that runs the async timer task the whole time, allows for continue execution even when screen rotates
 * @author Ian Wong (thewongian)
 */
public class TaskFragment extends Fragment {

    private boolean running;
    private TimerTasks mTimerTasks;
    // timer that stores the time for the async task
    public Timer timer;
    private TimerAsyncTask asyncTask;

    /**
     * Class that does tasks that would otherwise be impossible for this class
     */
    interface TimerTasks {
        /**
         * sets Timer text of time TextView in TimerFragment
         * @param time
         * time you want to set text to
         */
        void setTimerText(String time);
    }

    /**
     * run on creation of fragment
     * initializes fields and setsRetainInstance to true, allowing it to continue running
     * @param savedInstanceState
     * state saved by device
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timer = new Timer();
        setRetainInstance(true);
        asyncTask = new TimerAsyncTask();
        asyncTask.execute();

    }
    /**
     * run when attached to a context/activity
     * @param context
     * context the fragment is being atteached to
     */
    public void onAttach(Context context) {
        super.onAttach(context);
        mTimerTasks = (TimerTasks) context;
    }

    /**
     * run when detached from activity
     */
    public void onDetach() {
        super.onDetach();
        mTimerTasks = null;
    }

    /**
     * For main activity to communicate with async task
     * run when start/stop button pressed
     */
    public void onStartStop() {
        if (asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
            running = true;
            asyncTask = new TimerAsyncTask();

            asyncTask.execute();
        }
        else {
            running = false;
        }
    }

    /**
     * run when lap button pressed
     * adds time to lap list
     * @param s
     * time you want to add to lap list
     */
    public void onLap(String s) {
        timer.addTime(s);
    }

    /**
     * run when reset button pressed
     */
    public void onReset() {
        running = false;
        timer.reset();
    }

    /**
     * checks if async task running
     * @return true if async task is running, else false
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Timer async task that takes care of the timer task.
     * @author Ian Wong (thewongian)
     *
     */
    private class TimerAsyncTask extends AsyncTask<Void, Time, Void> {

        /**
         * Runs when progress is updated
         * @param times
         * an array of times, but only one argument is passed through so its just the time that was updated
         */
        @Override
        protected void onProgressUpdate(Time... times) {
            super.onProgressUpdate(times);
            String curr_time = times[0].toString();
            mTimerTasks.setTimerText(curr_time);
        }

        /**
         * The task to do in background while other things are happening for app
         * Every second, it will increment the time by a second, like a stopwatch
         * Will update progress every time this happens
         * @param params
         * is void, nothing happens
         * @return is void, nothing happens
         */
        @Override
        protected Void doInBackground(Void... params) {
            while (running) {
                timer.calc();
                publishProgress(timer.getTime());
                try {
                    Thread.sleep(1000); // sleep for 1 second (1000 milliseconds)
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return null;
        }
    }

}
