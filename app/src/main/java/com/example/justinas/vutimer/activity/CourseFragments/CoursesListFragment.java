package com.example.justinas.vutimer.activity.CourseFragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justinas.vutimer.R;

/**
 * Created by Justinas on 11/6/2015.
 */
public class CoursesListFragment extends Fragment{
    public CoursesListFragment(){

    }
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_course_list,container,false);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
}
