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
 * @author Ian Wong (thewongian)
 */
public class TimerFragment extends Fragment implements View.OnClickListener{


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

        start.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);

        return view;
    }


    /**
     * run when attached to an activity
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
        // start/stop
        if(v.getId() == start.getId()) {

            if (start.getText().toString().equals("Start")) {
                setStartText("Stop");

            }
            else {
                setStartText("Start");

            }
            mListener.onButtonClicked(0);
        }
        // lap
        else if (v.getId() == lap.getId()) {

            mListener.onButtonClicked(1);
        }
        // reset
        else if(v.getId() == reset.getId()) {


            start.setText("Start");

            mListener.onButtonClicked(2);



        }
    }

    /**
     * gets text of time display for laps
     * @return the text of the time
     */
    public String getTimeText() {
        return time.getText().toString();
    }

    /**
     * sets text of start/stop button
     * @param s
     * text you want to set button to
     */
    public void setStartText(String s) {
        start.setText(s);
    }

    /**
     * sets test of Time TextView
     * @param s
     * text you want to set time to
     */
    public void setTime(String s) {
        time.setText(s);
    }


    /**
     * object that will do something when this class wants it to
     * @author Ian Wong (thewongian)
     */
    public interface OnFragmentInteractionListener{
        /**
         * Implementing interaction listener method
         * when a button in timerfrag is clicked, this activity will know and react accordingly
         * @param infoID
         * id of button pressed, decides the action
         */
        void onButtonClicked(int infoID);
    }
}