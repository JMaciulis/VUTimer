package com.example.justinas.vutimer.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.model.CourseListItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseNewItemCreate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseNewItemCreate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseNewItemCreate extends Fragment implements OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button buttonAdd;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseNewItemCreate.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseNewItemCreate newInstance(String param1, String param2) {
        CourseNewItemCreate fragment = new CourseNewItemCreate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CourseNewItemCreate() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_new_item_create, container, false);
        buttonAdd = (Button)  view.findViewById(R.id.new_course_add_button);
        buttonAdd.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void saveNewCourse(View view){
        EditText editCourseNameText = (EditText) getActivity().findViewById(R.id.course_name_edit);
        String course_name = editCourseNameText.getText().toString();
        EditText  editCourseDescrText = (EditText) getActivity().findViewById(R.id.course_name_edit);
        String course_description = editCourseDescrText.getText().toString();
        CourseListItem courseListItem = new CourseListItem(course_name,course_description);
        MainActivity.db.addCourseListItemToList(courseListItem);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        saveNewCourse(v);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
