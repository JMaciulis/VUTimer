package com.example.justinas.vutimer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;
import com.example.justinas.vutimer.model.TaskListItem;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskPreviewFragment extends Fragment {
    private TaskListItem tItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        View view = inflater.inflate(R.layout.course_item_preview,null,false);

        //courseName.setText("title");
        //courseDescription.setText("descr");

        //courseName = (TextView) getView().findViewById(R.id.course_name);
        //courseDescription = (TextView) getView().findViewById(R.id.course_description);
        TextView txtCourseName = (TextView) view.findViewById(R.id.course_name);
        TextView txtCourseDescription = (TextView) view.findViewById(R.id.course_description);

        txtCourseName.setText(tItem.getTitle());
        txtCourseDescription.setText(tItem.getDescription());
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_courses_preview, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_edit:
                Toast.makeText(getActivity(), "Edit", Toast.LENGTH_SHORT)
                        .show();
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
}
