package com.example.justinas.vutimer.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.adapter.CourseListAdapter;
import com.example.justinas.vutimer.model.CourseListItem;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Justinas on 11/12/2015.
 */
public class CourseListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    OnHeadlineSelectedListener mCallback;

    String[] courseNames;
    String[] courseDescr;

    CourseListAdapter adapter;
    private List<CourseListItem> courseListItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        return inflater.inflate(R.layout.course_list_fragment,null,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        /*
        courseNames = getResources().getStringArray(R.array.courseNameArr);
        courseDescr = getResources().getStringArray(R.array.courseDescrArr);

        courseListItems = new ArrayList<CourseListItem>();

        for (int i = 0; i < courseNames.length; i++) {
            CourseListItem items = new CourseListItem(courseNames[i],courseDescr[i]);

            courseListItems.add(items);
        }
        */
        adapter = new CourseListAdapter(getActivity(), courseListItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), courseListItems.get(position).getTitle(), Toast.LENGTH_SHORT)
                .show();

        mCallback.onCourseSelected(courseListItems.get(position));
    }


    public interface OnHeadlineSelectedListener {
        public void onCourseSelected(CourseListItem cItem);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public void setCourseListItems(List<CourseListItem> list){
        this.courseListItems = list;

    }

}
