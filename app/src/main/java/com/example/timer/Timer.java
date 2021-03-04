package com.example.timer;

import java.util.ArrayList;

/**
 * Timer class
 * Keeps track of the time and can increment by seconds
 * also keeps track of a list of times for laps
 * @author Ian Wong (thewongian)
 */
public class Timer {

    private ArrayList<String> timeList;
    private Time time;

    /**
     * constructor
     * makes a new timer at time 00:00:00 with an empty list of times
     */
    public Timer() {
        timeList = new ArrayList<String>();
        time = new Time(0, 0, 0);
    }

    /**
     * gets list of times stored
     * @return list of time strings
     */
    public ArrayList<String> getTimeList() {
        return timeList;
    }

    /**
     * Gets the time
     * @return Time object stored
     */
    public Time getTime() {
        return time;
    }

    /**
     * increments the time by one second
     */
    public void calc() {
        time.incrementSecond();
    }

    /**
     * adds a time to the times list
     * @param time
     * time to be added to list
     */
    public void addTime(String time) {
        timeList.add(time);
    }

    /**
     * Converts list to a readable string that numbers each entry in the list
     * @return list in string form
     */
    public String listToString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < timeList.size(); i++) {
            buffer.append(i + 1);
            buffer.append(". ");
            buffer.append(timeList.get(i));
            buffer.append("\n");
        }
        return buffer.toString();
    }

    //resets the time and list
    public void reset() {
        time.reset();
        timeList.clear();
    }
}
