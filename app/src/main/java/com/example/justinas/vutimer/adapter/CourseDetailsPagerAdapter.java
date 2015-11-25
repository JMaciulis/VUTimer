package com.example.justinas.vutimer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.justinas.vutimer.activity.CourseFragments.CoursePreviewFragment;
import com.example.justinas.vutimer.activity.CourseFragments.CoursesListFragment;
import com.example.justinas.vutimer.activity.TaskFragments.TaskListFragment;

/**
 * Created by Justinas on 11/25/2015.
 */
public class CourseDetailsPagerAdapter extends FragmentPagerAdapter {

    public CourseDetailsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CoursePreviewFragment();
            case 1:
                return new TaskListFragment();
            case 2:
                return new CoursesListFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
