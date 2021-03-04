package com.example.timer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragment subclass that represents actions of timer
 * Takes care of user input in the timer fragment
 */
public class TimerFragment extends Fragment implements View.OnClickListener{

    private TimerAsyncTask asyncTask;
    boolean running;
    public Timer timer;
    private Button start, lap, reset;
    private TextView time;

    private OnFragmentInteractionListener mListener;
    /**
     * constructor, is empty
     */
    public TimerFragment() {
        // Required empty public constructor
    }

    /**
     * when view is created
     * @param inflater
     * inflater
     * @param container
     * container view group
     * @param savedInstanceState
     * last saved instance
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        //initialize buttons and textview
        start = (Button) view.findViewById(R.id.start);
        lap = (Button) view.findViewById(R.id.lap);
        reset = (Button) view.findViewById(R.id.reset);
        time = (TextView) view.findViewById(R.id.timer);
        timer = new Timer();
        start.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);

        asyncTask = new TimerAsyncTask();
        return view;
    }

    /**
     *
     * @param context
     * app context on attach
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString()+" must implement OnFragmentInteractionListener");
        }
    }

    /**
     * called when fragment is no longer attached
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Called when the three buttons are clicked
     * @param v
     * view being clicked
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == start.getId()) {
            if (asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
                running = true;
                asyncTask = new TimerAsyncTask();

                asyncTask.execute();


            }

            if (start.getText().toString().equals("Start")) {
                start.setText("Stop");

            }
            else {
                start.setText("Start");
                running = false;
            }
        }
        else if (v.getId() == lap.getId()) {
            timer.addTime(time.getText().toString());
            mListener.onButtonClicked(0);
        }
        else if(v.getId() == reset.getId()) {
            running = false;
            timer.reset();
            time.setText(timer.getTime().toString());
            start.setText("Start");

            mListener.onButtonClicked(1);



        }
    }


    private class TimerAsyncTask extends AsyncTask<Void, Time, Void> {

        /**
         *
         * @param times
         */
        @Override
        protected void onProgressUpdate(Time... times) {
            super.onProgressUpdate(times);
            String curr_time = times[0].toString();
            time.setText(curr_time);
        }
        /**
         *
         * @param
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            while (running) {
                timer.calc();
                publishProgress(timer.getTime());
                try {
                    Thread.sleep(1000); // sleep for 1 second (1000 milliseconds)
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            return null;
        }
    }
    public interface OnFragmentInteractionListener{
        void onButtonClicked(int infoID);
    }
}