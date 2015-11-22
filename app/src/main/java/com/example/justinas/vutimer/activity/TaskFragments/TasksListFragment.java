package com.example.justinas.vutimer.activity.TaskFragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justinas.vutimer.R;

/**
 * Created by Lukas on 22/11/2015.
 */
public class TasksListFragment extends Fragment {
    public TasksListFragment(){

    }
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.task_list_fragment,container,false);
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
