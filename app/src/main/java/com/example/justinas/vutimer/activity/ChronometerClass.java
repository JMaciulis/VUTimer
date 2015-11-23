package com.example.justinas.vutimer.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.view.View.OnClickListener;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.TaskFragments.TaskPreviewFragment;
import com.example.justinas.vutimer.model.TaskListItem;


public class ChronometerClass extends Fragment implements OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button startButton, pauseButton, stopButton;
    Chronometer chronometer;

    long time = 0;
    long second = 0;
    long minute = 0;
    long hour = 0;
    TaskListItem tItem;

    public long getTime(){
        return this.time;
    }

    private OnFragmentInteractionListener mListener;


    public ChronometerClass() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container, false);

        //Added 11/23 JM
        TextView txtTaskName = (TextView) view.findViewById(R.id.taskName);
        txtTaskName.setText(tItem.getTitle());

        startButton = (Button) view.findViewById(R.id.startButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        stopButton = (Button) view.findViewById(R.id.stopButton);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startButton:

                chronometer.setBase(SystemClock.elapsedRealtime() + time);
                Toast.makeText(getActivity(), "Start", Toast.LENGTH_SHORT).show();
                chronometer.start();
                break;
            case R.id.pauseButton:
                time = chronometer.getBase()-SystemClock.elapsedRealtime();
                Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
                chronometer.stop();
                break;
            case R.id.stopButton:
                chronometer.stop();
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                second = second +(elapsedMillis / 1000) % 60;
                if (second > 59){
                    minute = minute +(elapsedMillis / (1000 * 60)) % 60 + (second/60);
                    second = second % 60;
                }
                else{
                    minute = minute +(elapsedMillis / (1000 * 60)) % 60;
                }
                if (minute > 59){
                    hour = hour + (elapsedMillis / (1000 * 60 * 60)) % 24 + minute / 60;
                }
                else {
                    hour = hour + (elapsedMillis / (1000 * 60 * 60)) % 24;
                }


                long[] timeObj = {time,second,minute,hour};
                tItem.addDeltaTime(timeObj);
                Toast.makeText(getActivity(), "Stop", Toast.LENGTH_SHORT).show();
                goToTaskPreview();
                break;
        }
    }

    public void setTask(TaskListItem tItem){
        this.tItem = tItem;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    private void goToTaskPreview(){
        MainActivity.db.setTaskListItemOnPreview(tItem);
        TaskPreviewFragment taskPreviewFragment = new TaskPreviewFragment();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, taskPreviewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
