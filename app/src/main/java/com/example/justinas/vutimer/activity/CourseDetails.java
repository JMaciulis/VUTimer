package com.example.justinas.vutimer.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.CourseFragments.CoursePreviewFragment;
import com.example.justinas.vutimer.activity.CourseFragments.CoursesListFragment;
import com.example.justinas.vutimer.activity.TaskFragments.TaskListFragment;
import com.example.justinas.vutimer.adapter.CourseDetailsPagerAdapter;

/**
 * Created by Justinas on 11/25/2015.
 */
public class CourseDetails extends Fragment {
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_details_view,null,false);

        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);

        mTabHost.setup(getActivity(), getChildFragmentManager(),android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab 1").setIndicator(("Details"), null), CoursePreviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab 2").setIndicator(("Details"),null), TaskListFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("tab 3").setIndicator(("Details"),null), CoursesListFragment.class,null);
        return view;
    }
}
