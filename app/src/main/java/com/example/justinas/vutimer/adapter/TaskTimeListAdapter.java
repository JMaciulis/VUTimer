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
import com.example.justinas.vutimer.model.TaskTimeListItem;

import java.util.List;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskTimeListAdapter extends BaseAdapter{
    Context context;
    List<TaskTimeListItem> taskTimeListItem;


    public TaskTimeListAdapter(Context context, List<TaskTimeListItem> rows){
        this.context = context;
        this.taskTimeListItem = rows;
    }

    @Override
    public int getCount() {
        return taskTimeListItem.size();
    }

    @Override
    public TaskTimeListItem getItem(int position) {
        return taskTimeListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_time_list_item,null);
        }

        TextView txtTaskTime = (TextView) convertView.findViewById(R.id.taskTime);
        TextView txtTaskDescription = (TextView) convertView.findViewById(R.id.taskName);
        TextView txtTaskPeriod = (TextView) convertView.findViewById(R.id.taskPeriod);

        TaskTimeListItem pos = taskTimeListItem.get(position);

        txtTaskTime.setText(pos.getTimeString());
        txtTaskDescription.setText(pos.getTitle());
        txtTaskPeriod.setText(pos.getStartDate()+" - "+pos.getEndDate());
        return convertView;
    }

}
