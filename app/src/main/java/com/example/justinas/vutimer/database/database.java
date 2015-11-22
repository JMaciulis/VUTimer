package com.example.justinas.vutimer.database;

import android.content.Context;

import com.example.justinas.vutimer.model.CourseListItem;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinas on 11/13/2015.
 */
public class database {
    private Context context;


    List<CourseListItem> courseListItemList;
    CourseListItem courseListItemOnPreview;

    public database(Context context){
        this.context = context;
        setCourseListItemList();

    }


    public void setCourseListItemList(){

        List<CourseListItem> list;

        String[] courseNames;
        String[] courseDescr;

        courseNames = context.getResources().getStringArray(R.array.courseNameArr);
        courseDescr = context.getResources().getStringArray(R.array.courseDescrArr);

        list = new ArrayList<CourseListItem>();

        for (int i = 0; i < courseNames.length; i++) {
            CourseListItem items = new CourseListItem(courseNames[i],courseDescr[i]);

            list.add(items);
        }

        this.courseListItemList = list;
    }
    public void addCourseListItemToList(CourseListItem courseListItem){
        courseListItemList.add(courseListItem);
    }
    public List<CourseListItem> getCourseListItemList(){
        return courseListItemList;
    }

    public void setCourseListItemOnPreview(CourseListItem courseListItemOnPreview) {
        this.courseListItemOnPreview = courseListItemOnPreview;
    }

    public CourseListItem getCourseListItemOnPreview() {
        return courseListItemOnPreview;
    }
    public void deleteCourseItem(CourseListItem cItem){
        courseListItemList.remove(cItem);
    }
}
