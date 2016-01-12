package com.example.justinas.vutimer.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
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

import java.util.Date;


public class ChronometerClass extends Fragment implements OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button startButton, pauseButton, stopButton;
    Chronometer chronometer;

    private Date startDate;
    private Date endDate;
    boolean pause = false;
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
        tItem = MainActivity.db.getTaskListItemOnPreview();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container, false);

        //Added 11/23 JM
        TextView txtTaskName = (TextView) view.findViewById(R.id.taskName);
        txtTaskName.setText(tItem.getTitle());
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "DS-DIGII.TTF");
        startButton = (Button) view.findViewById(R.id.startButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        stopButton = (Button) view.findViewById(R.id.stopButton);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        chronometer.setTypeface(font, Typeface.NORMAL);
        chronometer.setTextSize(120);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);

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
                //Toast.makeText(getActivity(), "Start", Toast.LENGTH_SHORT).show();
                startDate = new Date();
                chronometer.start();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);
                pause = false;
                break;
            case R.id.pauseButton:
                time = chronometer.getBase()-SystemClock.elapsedRealtime();
                //Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
                pause = true;
                chronometer.stop();
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                break;
            case R.id.stopButton:
                chronometer.stop();
                endDate = new Date();
                long elapsedMillis = 0;
                if(!pause)
                elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                else
                    elapsedMillis = -time;
                /*if(time == 0)
                    elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                else
                    elapsedMillis = -time;
*/
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
                hour = hour + (elapsedMillis / (1000 * 60 * 60)) % 24;

                //String timeChronometer = String.format(tItem.getDescription()+ " Timer %02d:%02d:%02d", hour, minute, second);
                long[] timeObj = {second,minute,hour};
                //tItem.addDeltaTime(timeObj);
                tItem.addTime(timeObj,startDate,endDate);
                //txtTaskTime.setText(tItem.getTimeString());
                //Toast.makeText(getActivity(), "Stop", Toast.LENGTH_SHORT).show();
                goToTaskPreview();
                break;
        }
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
        //MainActivity.db.setTaskListItemOnPreview(tItem);
        TaskDetails taskDetails = new TaskDetails();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, taskDetails);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
