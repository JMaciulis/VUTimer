package com.example.justinas.vutimer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.CourseFragments.CoursePreviewFragment;
import com.example.justinas.vutimer.activity.CourseFragments.CoursesListFragment;
import com.example.justinas.vutimer.activity.TaskFragments.TaskListFragment;
import com.example.justinas.vutimer.activity.TaskFragments.TaskPreviewFragment;
import com.example.justinas.vutimer.activity.TaskTimeFragment.TaskTimeListFragment;

/**
 * Created by Justinas on 11/26/2015.
 */
public class TaskDetails extends Fragment {
    private static final int THREE_FRAGMENTS = 2;
    private ViewPager mViewPager;
    private TabHost.TabContentFactory mFactory = new TabHost.TabContentFactory() {

        @Override
        public View createTabContent(String tag) {
            View v = new View(getActivity());
            v.setMinimumHeight(0);
            return v;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragContent = inflater.inflate(R.layout.course_details_view,
                container, false);
        mViewPager = (ViewPager) fragContent.findViewById(R.id.pager);
        mViewPager.setAdapter(new TransactionInnerPagerAdapter(getChildFragmentManager()));
        final TabHost tabHost = (TabHost) fragContent
                .findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("Details").setContent(mFactory));
        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("Time").setContent(mFactory));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("Tab1")) {
                    mViewPager.setCurrentItem(0);
                } else if (tabId.equals("Tab2")) {
                    mViewPager.setCurrentItem(1);
                }
            }
        });
        return fragContent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private class TransactionInnerPagerAdapter extends FragmentPagerAdapter {

        public TransactionInnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new TaskPreviewFragment();
            } else if (position == 1) {
                return new TaskTimeListFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return THREE_FRAGMENTS;
        }

    }
}
