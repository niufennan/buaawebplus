package com.webplus.jiangchao.iridescence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.webplus.jiangchao.iridescence.Adapter.CourseAssignmentFragmentListViewAdapter;
import com.webplus.jiangchao.iridescence.DAO.AssignmentHelper;
import com.webplus.jiangchao.iridescence.Model.AssignmentModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseAssignmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseAssignmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseAssignmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseAssignmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseAssignmentFragment newInstance(String param1, String param2) {
        CourseAssignmentFragment fragment = new CourseAssignmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_course_assignment, container, false);

        final AssignmentHelper helper = new AssignmentHelper(getContext(), getContext().getResources().getString(R.string.dbname), null, 1);

        final ListView assignmentListView= (ListView) rootView.findViewById(R.id.course_view_today_assignment);

        List<String> contentlist=new ArrayList<String>();
        for (AssignmentModel am :helper.findAssignmentByCourse(mParam1));
        final CourseAssignmentFragmentListViewAdapter[] adapter = {new CourseAssignmentFragmentListViewAdapter(getContext(), R.layout.layout_course_assignment_listview_item, helper.findTodayAssignmentByCourse(mParam1))};
        assignmentListView.setAdapter(adapter[0]);

        Button addbtn= (Button) rootView.findViewById(R.id.course_view_today_assignment_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText= (EditText) rootView.findViewById(R.id.course_view_today_assignment_text);
                if(!"".equals(editText.getText().toString()))
                {
                    AssignmentModel assignmentModel = new AssignmentModel();
                    assignmentModel.setCourseName(mParam1);
                    assignmentModel.setContent(editText.getText().toString());
                    assignmentModel.setCompleted(0);
                    assignmentModel.setStartDate(new Date());
                    assignmentModel.setDueDate(new Date((new Date()).getTime()+2*24*60*60*1000));
                    helper.addAssignment(assignmentModel);
                    editText.setText("");

                    adapter[0] =new CourseAssignmentFragmentListViewAdapter(getContext(), R.layout.layout_course_assignment_listview_item, helper.findTodayAssignmentByCourse(mParam1));
                assignmentListView.setAdapter(adapter[0]);
                }
            }
        });

        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(Uri uri);
    }
}
