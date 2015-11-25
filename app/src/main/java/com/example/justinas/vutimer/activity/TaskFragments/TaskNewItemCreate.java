package com.example.justinas.vutimer.activity.TaskFragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.justinas.vutimer.R;
import com.example.justinas.vutimer.activity.MainActivity;
import com.example.justinas.vutimer.model.TaskListItem;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskNewItemCreate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskNewItemCreate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskNewItemCreate extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button buttonAdd;
    Spinner spinner;
    String[] courses;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskNewItemCreate.
     */
    public static TaskNewItemCreate newInstance(String param1, String param2) {
        TaskNewItemCreate fragment = new TaskNewItemCreate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TaskNewItemCreate() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = MainActivity.db.getCourseTitles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_new_item_create, container, false);
        // Inflate the layout for this fragment
        spinner = (Spinner) view.findViewById(R.id.course_spinner);
        //spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,courses);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        buttonAdd = (Button)  view.findViewById(R.id.new_course_add_button);
        buttonAdd.setOnClickListener(this);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void saveNewCourse(View view){
        EditText editTaskNameText = (EditText) getActivity().findViewById(R.id.task_name_edit);
        String task_name = editTaskNameText.getText().toString();
        EditText  editTaskDescrText = (EditText) getActivity().findViewById(R.id.task_description_edit);
        String task_description = editTaskDescrText.getText().toString();
        String task_parent = spinner.getSelectedItem().toString(); //EDIT!!!
        TaskListItem taskListItem = new TaskListItem(task_name,task_description,task_parent);
        MainActivity.db.addTaskToCourse(task_parent,taskListItem);
        MainActivity.db.addTaskListItemToList(taskListItem);
        Toast.makeText(getActivity(), "Task Added", Toast.LENGTH_SHORT).show();
        goToTaskList();
    }

    private void goToTaskList() {
        ListFragment listFragment = new TaskListFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, listFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        saveNewCourse(v);
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        public void onFragmentInteraction(Uri uri);
    }

}
