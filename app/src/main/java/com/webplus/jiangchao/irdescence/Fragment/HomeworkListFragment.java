package com.webplus.jiangchao.irdescence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.webplus.jiangchao.irdescence.Adapter.AssignmentGroupAdapter;
import com.webplus.jiangchao.irdescence.Adapter.CourseModelAdapter;
import com.webplus.jiangchao.irdescence.DAO.AssignmentHelper;
import com.webplus.jiangchao.irdescence.DAO.CourseHelper;
import com.webplus.jiangchao.irdescence.Model.AssignmentGroup;
import com.webplus.jiangchao.irdescence.Model.AssignmentModel;
import com.webplus.jiangchao.irdescence.Model.CourseModel;
import com.webplus.jiangchao.irdescence.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeworkListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeworkListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
    AssignmentGroupAdapter adapter;
    private int selectId;

    public int getSelectId()
    {
        return adapter.getSelectId();
    }

    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<CourseModel> arrayAdapter;

    public HomeworkListFragment() {
        // Required empty public constructor
        selectId=-1;
    }

    public String getCourseName()
    {
        return this.getArguments().getString(ARG_PARAM1);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeworkListFragment newInstance(String param1, int param2) {
        HomeworkListFragment fragment = new HomeworkListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_assignmentlist, container, false);
        TextView courseListTitle= (TextView) rootView.findViewById(R.id.courselist_title);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
        courseListTitle.setText(mParam1);
        ListView courseListItem= (ListView) rootView.findViewById(R.id.courselist_item);
        final List<AssignmentModel> list=initAssignmentList(mParam1);

        adapter =new AssignmentGroupAdapter(getContext(),R.layout.assignment_itemgroup,initAssignmentGroupList(list));

        courseListItem.setAdapter(adapter);
        return rootView;
    }



    private List<AssignmentModel> initAssignmentList(String courseName)
    {
        AssignmentHelper helper=new AssignmentHelper(getContext(),getContext().getResources().getString(R.string.dbname),null,1);
        List<AssignmentModel> assignmentModelList=  helper.findAssignmentByCourse(courseName);//  new ArrayList<CourseModel>();
        return assignmentModelList;
    }

    private List<AssignmentGroup> initAssignmentGroupList(List<AssignmentModel> list)
    {
        List<AssignmentGroup> assignmentGroupList=new ArrayList<AssignmentGroup>() ;
        boolean flag=true;
        AssignmentGroup currentAg;
        for (AssignmentModel am :list)
        {
            flag=true;
            currentAg=new AssignmentGroup();
            for (AssignmentGroup ag :assignmentGroupList)
            {
                if(ag.getCreateTime().equals(DateFormat.getDateInstance().format(am.getStartDate())))
                {
                    flag=false;
                    currentAg=ag;
                }
            }
            if(flag)
            {
                AssignmentGroup ag=new AssignmentGroup();
                ag.setCreateTime(DateFormat.getDateInstance().format(am.getStartDate()));
                ag.getData().add(am);
                assignmentGroupList.add(ag);
            }else
            {
                currentAg.getData().add(am);
            }
        }
        return assignmentGroupList;
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
