package com.example.justinas.vutimer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinas on 11/11/2015.
 */
public class CourseListItem {

    private String title;
    private String description;
    private int icon;

    public CourseListItem(String title,String description) {
        this.title = title;
        this.description = description;
        //this.icon = icon;

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

}