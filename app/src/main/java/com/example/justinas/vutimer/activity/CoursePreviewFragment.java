package com.example.justinas.vutimer.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;

/**
 * Created by Justinas on 11/12/2015.
 */
public class CoursePreviewFragment extends Fragment {

    private CourseListItem cItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStace){
        View view = inflater.inflate(R.layout.course_item_preview,null,false);

        TextView txtCourseName = (TextView) view.findViewById(R.id.course_name);
        TextView txtCourseDescription = (TextView) view.findViewById(R.id.course_description);

        cItem = MainActivity.db.getCourseListItemOnPreview();

        txtCourseName.setText(cItem.getTitle());
        txtCourseDescription.setText(cItem.getDescription());
        return view;
    }
    @Override
     public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_courses_preview, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_edit:
                Toast.makeText(getActivity(), "Edit", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.action_delete:
                Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_SHORT);
                MainActivity.db.deleteCourseItem(cItem);
                goToCourseList();
                return true;
        }
        return false;
    }
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public void setItem(CourseListItem cItem){
        this.cItem = cItem;
    }

    private void goToCourseList(){
        ListFragment listFragment = new CourseListFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, listFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
