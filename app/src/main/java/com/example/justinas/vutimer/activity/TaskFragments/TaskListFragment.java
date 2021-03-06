package com.example.justinas.vutimer.activity.TaskFragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.CourseFragments.CoursePreviewFragment;
import com.example.justinas.vutimer.activity.MainActivity;
import com.example.justinas.vutimer.activity.TaskDetails;
import com.example.justinas.vutimer.adapter.TaskListAdapter;
import com.example.justinas.vutimer.model.CourseListItem;
import com.example.justinas.vutimer.model.TaskListItem;

import java.util.List;


public class TaskListFragment extends ListFragment  implements AdapterView.OnItemClickListener {

    TaskListAdapter adapter;
    List<TaskListItem> taskListItems;
    String filter = "";
    boolean showAll = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setupTaskListItems();

    }
    private void setupTaskListItems(){
        if(showAll){
            taskListItems = MainActivity.db.getTaskListItemList();
        }else{
            if (MainActivity.db.getCourseListItemOnPreview() != null)
                taskListItems = MainActivity.db.getCourseListItemOnPreview().getTasks();
            else
                taskListItems = MainActivity.db.getTaskListItemList();
        }
        showAll = false;
    }
    @Override
    public void onResume() {
        super.onResume();
        //setupTaskListItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        return inflater.inflate(R.layout.task_list_fragment,null,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //setupTaskListItems();
        adapter = new TaskListAdapter(getActivity(), taskListItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.task_list_toolbar, menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(getActivity(), taskListItems.get(position).getTitle(), Toast.LENGTH_SHORT)
         //       .show();

        MainActivity.db.setTaskListItemOnPreview(taskListItems.get(position));
        TaskDetails taskDetails = new TaskDetails();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, taskDetails);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(taskListItems.get(position).getTitle());
    }

    public void setfilter(String courseTitle){
        this.filter = courseTitle;
    }
    public void showAll(boolean value){
        showAll = value;
    }

}