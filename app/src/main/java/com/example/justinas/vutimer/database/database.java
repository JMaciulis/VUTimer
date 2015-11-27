package com.example.justinas.vutimer.database;

import android.content.Context;

import com.example.justinas.vutimer.model.CourseListItem;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;
import com.example.justinas.vutimer.model.TaskListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinas on 11/13/2015.
 */
public class database {
    private Context context;

    List<CourseListItem> courseListItemList;
    CourseListItem courseListItemOnPreview;
    List<TaskListItem> taskListItemList;
    TaskListItem taskListItemOnPreview;

    public database(Context context){
        this.context = context;
        setCourseListItemList();
        setTaskListItemList();
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
    
    
    //----------TASK----------------
    private void setTaskListItemList() {
        List<TaskListItem> list;

        String[] taskNames;
        String[] taskDescr;
        String[] taskParentCourse;

        taskNames = context.getResources().getStringArray(R.array.taskNameArr);
        taskDescr = context.getResources().getStringArray(R.array.taskDescrArr);
        taskParentCourse = context.getResources().getStringArray(R.array.taskParentArr);

        list = new ArrayList<TaskListItem>();

        for (int i = 0; i < taskNames.length; i++) {
            TaskListItem items = new TaskListItem(taskNames[i],taskDescr[i],taskParentCourse[i]);
            //new
            findCourseItem(taskParentCourse[i]).addTask(items);

            //end
            //courseListItemList.get(0).addTask(items);
            list.add(items);
        }

        this.taskListItemList = list;
    }
    public void addTaskListItemToList(TaskListItem taskListItem){
        taskListItemList.add(taskListItem);
    }
    public List<TaskListItem> getTaskListItemList() {
        List<TaskListItem> list = new ArrayList<TaskListItem>();
        for(CourseListItem c: courseListItemList){
            for(int i = 0; i < c.getTasks().size(); i++){
                list.add(c.getTasks().get(i));
            }
        }
        return list;
        //return taskListItemList;
    }
    public void setTaskListItemOnPreview(TaskListItem taskListItem) {
        taskListItemOnPreview = taskListItem;
    }
    public TaskListItem getTaskListItemOnPreview(){
        return taskListItemOnPreview;
    }
    public void deleteTaskItem(TaskListItem tItem){
        taskListItemList.remove(tItem);
    }
    public CourseListItem findCourseItem(String filter){
        for(CourseListItem c : courseListItemList){
            if(c.getTitle() != null && c.getTitle().contains(filter)){
                return c;
            }
        }
        return null;
    }
    public void addTaskToCourse(String filter, TaskListItem item){
        findCourseItem(filter).addTask(item);
    }
    public String[] getCourseTitles(){
        List<String> arr = new ArrayList<String>();
        for(CourseListItem c: courseListItemList){
            arr.add(c.getTitle());
        }
        String[] tmp = new String[arr.size()];
        arr.toArray(tmp);
        return tmp;
    }
    public void setCPreviewNull(){
        courseListItemOnPreview = null;
    }
}
