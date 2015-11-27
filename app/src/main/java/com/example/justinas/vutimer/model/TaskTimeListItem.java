package com.example.justinas.vutimer.model;

import com.example.justinas.vutimer.activity.MainActivity;

import java.util.Date;
import java.util.List;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskTimeListItem {
    private String title ="";
    private String description ="";
    private List<TaskTimeListItem> timeList;
    private Date startDate;
    private Date endDate;

    long time;
    long second;
    long minute;
    long hour;
    private int icon;

    public TaskTimeListItem(long[] time, Date start, Date end) {
        title = MainActivity.db.getTaskListItemOnPreview().getTitle();
        second = time[0];
        minute = time[1];
        hour = time[2];
        this.startDate = start;
        this.endDate = end;

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
        long[] time = {second,minute,hour};
        return time;
    }
    public String getTimeString(){
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
    public String getStartDate(){
        return (String) android.text.format.DateFormat.format("HH:mm", startDate);
    }
    public String getEndDate(){
        return (String) android.text.format.DateFormat.format("HH:mm", endDate);
    }
}
