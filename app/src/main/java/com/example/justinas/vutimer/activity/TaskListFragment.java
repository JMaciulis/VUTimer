package com.example.justinas.vutimer.activity;

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
import com.example.justinas.vutimer.adapter.CourseListAdapter;
import com.example.justinas.vutimer.adapter.TaskListAdapter;
import com.example.justinas.vutimer.model.CourseListItem;
import com.example.justinas.vutimer.model.TaskListItem;

import java.util.List;


public class TaskListFragment extends ListFragment  implements AdapterView.OnItemClickListener {
    OnHeadlineSelectedListener mCallback;
    TaskListAdapter adapter;
    private List<TaskListItem> taskListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        return inflater.inflate(R.layout.task_list_fragment,null,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        taskListItems = MainActivity.db.getTaskListItemList();
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

        Toast.makeText(getActivity(), taskListItems.get(position).getTitle(), Toast.LENGTH_SHORT)
                .show();


        //mCallback.onCourseSelected(courseListItems.get(position));

        TaskPreviewFragment taskPreviewFragment = new TaskPreviewFragment();
        taskPreviewFragment.setItem(taskListItems.get(position));

        //fragment.setText(cItem.getTitle(),cItem.getDescription());


        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, taskPreviewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //labas
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(taskListItems.get(position).getTitle());
    }


    public interface OnHeadlineSelectedListener {
        void onTaskSelected(TaskListItem tItem);
    }

    /*
    public void setCourseListItems(List<CourseListItem> list){
        this.courseListItems = list;
    }
    */
}
