package com.example.justinas.vutimer.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.adapter.CourseListAdapter;
import com.example.justinas.vutimer.model.CourseListItem;

import java.util.List;
import java.util.ArrayList;


public class CourseListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    OnHeadlineSelectedListener mCallback;
    CourseListAdapter adapter;
    private List<CourseListItem> courseListItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        return inflater.inflate(R.layout.course_list_fragment,null,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        courseListItems = MainActivity.db.getCourseListItemList();
        adapter = new CourseListAdapter(getActivity(), courseListItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.courses_list_toolbar, menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), courseListItems.get(position).getTitle(), Toast.LENGTH_SHORT)
                .show();


        //mCallback.onCourseSelected(courseListItems.get(position));

        CoursePreviewFragment coursePreviewFragment = new CoursePreviewFragment();
        coursePreviewFragment.setItem(courseListItems.get(position));

        //fragment.setText(cItem.getTitle(),cItem.getDescription());


        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, coursePreviewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //labas
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(courseListItems.get(position).getTitle());
    }


    public interface OnHeadlineSelectedListener {
        void onCourseSelected(CourseListItem cItem);
    }

    /*
    public void setCourseListItems(List<CourseListItem> list){
        this.courseListItems = list;
    }
    */

}
