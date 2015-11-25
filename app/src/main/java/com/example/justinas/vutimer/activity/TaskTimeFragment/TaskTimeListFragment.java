package com.example.justinas.vutimer.activity.TaskTimeFragment;

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
import com.example.justinas.vutimer.activity.MainActivity;
import com.example.justinas.vutimer.adapter.TaskListAdapter;
import com.example.justinas.vutimer.adapter.TaskTimeListAdapter;
import com.example.justinas.vutimer.model.TaskListItem;
import com.example.justinas.vutimer.model.TaskTimeListItem;

import java.util.List;


public class TaskTimeListFragment extends ListFragment  implements AdapterView.OnItemClickListener {
    public TaskTimePreviewFragment taskTimePreviewFragment;
    TaskTimeListAdapter adapter;
    private List<TaskTimeListItem> taskTimeListItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        taskTimeListItems = MainActivity.db.getTaskListItemOnPreview().getTimeArr();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        return inflater.inflate(R.layout.task_time_list_fragment,null,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        adapter = new TaskTimeListAdapter(getActivity(), taskTimeListItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.task_list_toolbar, menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*

        Toast.makeText(getActivity(), taskTimeListItems.get(position).getTitle(), Toast.LENGTH_SHORT)
                .show();


        taskTimePreviewFragment = new TaskTimePreviewFragment();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, taskTimePreviewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        */
    }

}
