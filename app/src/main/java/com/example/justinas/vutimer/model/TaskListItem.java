package com.example.justinas.vutimer.model;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TaskListItem {
    private String title;
    private String description;
    private int icon;

    public TaskListItem(String title,String description) {
        this.title = title;
        this.description = description;

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
