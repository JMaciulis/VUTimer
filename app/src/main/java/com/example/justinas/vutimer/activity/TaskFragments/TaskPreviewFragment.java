package com.example.justinas.vutimer.activity.TaskFragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.justinas.vutimer.activity.ChronometerClass;
import com.example.justinas.vutimer.activity.MainActivity;
import com.example.justinas.vutimer.activity.TaskFragments.TaskListFragment;
import com.example.justinas.vutimer.model.TaskListItem;


public class TaskPreviewFragment extends Fragment{

    private TaskListItem tItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        View view = inflater.inflate(R.layout.task_item_preview,null,false);

        TextView txtTaskName = (TextView) view.findViewById(R.id.task_name);
        final TextView txtTaskDescription = (TextView) view.findViewById(R.id.task_description);
        TextView txtTaskParent = (TextView) view.findViewById(R.id.task_course_parent);

        final TextView txtTaskTime = (TextView) view.findViewById(R.id.taskTime);

        tItem = MainActivity.db.getTaskListItemOnPreview();

        txtTaskName.setText(tItem.getTitle());
        txtTaskDescription.setText(tItem.getDescription());
        txtTaskTime.setText(tItem.getTime());
        txtTaskParent.setText(tItem.getParentCourse());

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
            case R.id.action_edit_task:
                ChronometerClass ch = new ChronometerClass();
                Fragment fragment = ch;
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.action_delete:
                Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_SHORT);
                MainActivity.db.deleteTaskItem(tItem);
                MainActivity.db.findCourseItem(tItem.getParentCourse()).deleteTask(tItem);
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
        TaskListFragment listFragment = new TaskListFragment();
        listFragment.setfilter(tItem.getParentCourse());
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, listFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
