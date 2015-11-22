package com.example.justinas.vutimer.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinas on 11/11/2015.
 */
public class CourseListAdapter extends BaseAdapter {

    Context context;
    List<CourseListItem> courseItemList;

    public CourseListAdapter(Context context, List<CourseListItem> rows){
        this.context = context;
        this.courseItemList = rows;
    }

    @Override
    public int getCount() {
        return courseItemList.size();
    }

    @Override
    public CourseListItem getItem(int position) {
        return courseItemList.get(position);
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

        CourseListItem pos = courseItemList.get(position);

        txtCourseName.setText(pos.getTitle());
        txtCourseDescription.setText(pos.getDescription());

        return convertView;
    }

}
