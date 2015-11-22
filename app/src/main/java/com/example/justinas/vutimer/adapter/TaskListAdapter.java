package com.example.justinas.vutimer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;
import com.example.justinas.vutimer.model.TaskListItem;

import java.util.List;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskListAdapter extends BaseAdapter{
    Context context;
    List<TaskListItem> taskListItem;


    public TaskListAdapter(Context context, List<TaskListItem> rows){
        this.context = context;
        this.taskListItem = rows;
    }

    @Override
    public int getCount() {
        return taskListItem.size();
    }

    @Override
    public TaskListItem getItem(int position) {
        return taskListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_list_item,null);
        }

        TextView txtCourseName = (TextView) convertView.findViewById(R.id.coursesTextTop);
        TextView txtCourseDescription = (TextView) convertView.findViewById(R.id.coursesTextBottom);

        TaskListItem pos = taskListItem.get(position);

        txtCourseName.setText(pos.getTitle());
        txtCourseDescription.setText(pos.getDescription());

        return convertView;
    }

}
