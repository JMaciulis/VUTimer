package com.example.justinas.vutimer.activity.CourseFragments;


import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.CourseDetails;
import com.example.justinas.vutimer.activity.MainActivity;
import com.example.justinas.vutimer.adapter.CourseListAdapter;
import com.example.justinas.vutimer.model.CourseListItem;

import java.util.List;


public class CourseListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    OnHeadlineSelectedListener mCallback;
    CourseListAdapter adapter;
    private List<CourseListItem> courseListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        courseListItems = MainActivity.db.getCourseListItemList();
    }
    @Override
    public void onResume() {
        super.onResume();
        courseListItems = MainActivity.db.getCourseListItemList();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        return inflater.inflate(R.layout.course_list_fragment,null,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
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
        MainActivity.db.setCourseListItemOnPreview(courseListItems.get(position));
        //CoursePreviewFragment coursePreviewFragment = new CoursePreviewFragment();
        CourseDetails cdl = new CourseDetails();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, cdl);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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