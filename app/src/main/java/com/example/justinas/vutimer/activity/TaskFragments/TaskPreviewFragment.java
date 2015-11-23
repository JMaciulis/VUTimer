package com.example.justinas.vutimer.activity.TaskFragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.MainActivity;
import com.example.justinas.vutimer.activity.TaskFragments.TaskListFragment;
import com.example.justinas.vutimer.model.TaskListItem;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskPreviewFragment extends Fragment {

    private TaskListItem tItem;
    Button startButton, pauseButton, stopButton;
    long time = 0;
    Chronometer chronometer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        View view = inflater.inflate(R.layout.task_item_preview,null,false);
        startButton = (Button) view.findViewById(R.id.startButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        stopButton = (Button) view.findViewById(R.id.stopButton);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer2);

        TextView txtTaskName = (TextView) view.findViewById(R.id.task_name);
        final TextView txtTaskDescription = (TextView) view.findViewById(R.id.task_description);

        tItem = MainActivity.db.getTaskListItemOnPreview();

        txtTaskName.setText(tItem.getTitle());
        txtTaskDescription.setText(tItem.getDescription());
        startButton.setOnClickListener(new View.OnClickListener() {
            long time2;
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime() + time);
                chronometer.start();
                Toast.makeText(getActivity(), "Start", Toast.LENGTH_SHORT).show();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = chronometer.getBase()-SystemClock.elapsedRealtime();
                chronometer.stop();
                Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                long second = (elapsedMillis / 1000) % 60;
                long minute = (elapsedMillis / (1000 * 60)) % 60;
                long hour = (elapsedMillis / (1000 * 60 * 60)) % 24;

                String timeChronometer = String.format("%02d:%02d:%02d", hour, minute, second);
                txtTaskDescription.setText(timeChronometer);
                time = 0;
                Toast.makeText(getActivity(), "Stop", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_task_preview, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_edit:
                Toast.makeText(getActivity(), "Edit", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.action_delete:
                Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_SHORT);
                MainActivity.db.deleteTaskItem(tItem);
                goToTaskList();
                return true;
        }
        return false;
    }
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public void setItem(TaskListItem tItem){
        this.tItem = tItem;
    }


    private void goToTaskList(){
        ListFragment listFragment = new TaskListFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, listFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
