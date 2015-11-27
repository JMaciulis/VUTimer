package com.example.justinas.vutimer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskListItem {
    private String title;
    private String description;
    private String parentCourse;
    private Date dateCreated;
    private Date deadline;
    private Boolean hasDeadline;

    List<TaskTimeListItem> timeList;

    private int icon;

    public TaskListItem(String title,String description,String parentCourse) {
        this.title = title;
        this.description = description;
        this.parentCourse = parentCourse;
        this.hasDeadline = false;
        dateCreated = new Date();
        timeList = new ArrayList<TaskTimeListItem>();

    }
    public void setDeadline(Date date){
        hasDeadline = true;
        deadline = date;
    }
    public Date getDeadline(){
        return deadline;
    }
    public Boolean getHasDealine(){
        return hasDeadline;
    }
    public void setParentCourse(String parentCourse){
        this.parentCourse = parentCourse;
    }
    public String getParentCourse(){
        return parentCourse;
    }
    public String getTitle() {
        return title;
    }
    public void addTime(long[] time){
        TaskTimeListItem item = new TaskTimeListItem(time);
        timeList.add(item);
    }
    public List<TaskTimeListItem> getTimeArr(){
        return timeList;
    }
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public String getTimeString(long hour, long minute, long second){
        String time = "";
        if(hour != 0) {
            if (hour < 10)
                time += "0" + hour + "h";
            else
                time += hour + "h";
        }
        if(minute < 10)
            time += "0"+minute+"m";
        else
            time += minute+"m";
        if(second < 10)
            time += "0"+second+"s";
        else
            time += second+"s";
        return time;
    }


    public String getTime(){
        long hour   = 0;
        long minute = 0;
        long second = 0;
        long[] time = {0,0,0};
        for(TaskTimeListItem t: timeList){
            long[] tmp = t.getTime();
            time[0] += tmp[0];
            time[1] += tmp[1];
            time[2] += tmp[2];

        }
        second += time[0];
        if(second > 59){
            minute += time[1] + second/60;
            second = second%60;
        }else {
            minute += time[1];
        }
        if (minute > 59){
            hour += time[2] + minute/60;
            minute = minute %60;
        }else {
            hour += time[2];
        }
        return getTimeString(hour,minute,second);
    }
}
