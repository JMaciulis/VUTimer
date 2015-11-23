package com.example.justinas.vutimer.model;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskListItem {
    private String title;
    private String description;
    private String parentCourse;

    long time;
    long second;
    long minute;
    long hour;
    private int icon;

    public TaskListItem(String title,String description) {
        this.title = title;
        this.description = description;
        second = 0;
        minute = 0;
        hour = 0;

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

    public long[] getTime(){
        long[] time = {this.time,second,minute,hour};
        return time;
    }
    public String getTimeString(){
        String time = "";
        if(hour != 0) {
            if (hour < 10)
                time += "0" + hour + ":";
            else
                time += hour + ":";
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
    public void setTime(long[] time){
        this.time = time[0];
        second = time[1];
        minute = time[2];
        hour = time[3];
    }
    public void addDeltaTime(long[] time){
        this.time = time[0]; // ne += nes siuntinejam pilna
        second += time[1];
        minute += time[2];
        hour += time[3];
    }
}
