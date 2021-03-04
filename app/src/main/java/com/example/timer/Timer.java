package com.example.timer;

import java.util.ArrayList;

public class Timer {

    private ArrayList<String> timeList;
    private Time time;

    public Timer() {
        timeList = new ArrayList<String>();
        time = new Time(0, 0, 0);
    }

    public ArrayList<String> getTimeList() {
        return timeList;
    }

    public Time getTime() {
        return time;
    }

    public void calc() {
        time.incrementSecond();
    }
    public void addTime(String time) {
        timeList.add(time);
    }

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
    public void reset() {
        time.reset();
        timeList.clear();
    }
}
