package com.example.timer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * ListFragment just displays a list of lap times
 * @author Ian Wong (thewongian)
 */
public class ListFragment extends Fragment {

    TextView lapList;

    /**
     * is empty *shrugs
     */
    public ListFragment() {
        // Required empty public constructor
    }


    /**
     * Run when fragment is created
     * @param inflater
     * the inflater
     * @param container
     * the container??
     * @param savedInstanceState
     * state saved by device
     * @return
     * this view i guess
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        lapList = view.findViewById(R.id.lapList);
        // Inflate the layout for this fragment
        return view;
    }

    /**
     * sets text of the textedit in the fragment
     * @param text
     * text you want to set textedit to
     */
    public void setText(String text) {
        lapList.setText(text);
    }
}