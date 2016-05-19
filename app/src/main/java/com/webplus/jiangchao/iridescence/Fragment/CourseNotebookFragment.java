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

import com.webplus.jiangchao.iridescence.Adapter.NotebookModelAdapter;
import com.webplus.jiangchao.iridescence.DAO.NotebookHelper;
import com.webplus.jiangchao.iridescence.Model.NotebookModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseNotebookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseNotebookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseNotebookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseNotebookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseNotebookFragment newInstance(String param1, String param2) {
        CourseNotebookFragment fragment = new CourseNotebookFragment();
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
        final View rootView= inflater.inflate(R.layout.fragment_course_notebook, container, false);

        final NotebookHelper helper = new NotebookHelper(getContext(), getContext().getResources().getString(R.string.dbname), null, 1);

        final ListView noteListView= (ListView) rootView.findViewById(R.id.course_view_today_notebook);

        List<String> contentlist=new ArrayList<String>();
        for (NotebookModel nbm :helper.findTodayNodebookByCourse(mParam1));
        final NotebookModelAdapter[] adapter = {new NotebookModelAdapter(getContext(), R.layout.fragment_notebooklist_item, helper.findTodayNodebookByCourse(mParam1))};
        noteListView.setAdapter(adapter[0]);

        Button addbtn= (Button) rootView.findViewById(R.id.course_view_today_notebook_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText= (EditText) rootView.findViewById(R.id.course_view_today_notebook_text);
                if(!"".equals(editText.getText().toString()))
                {
                    NotebookModel notebookModel = new NotebookModel();
                    notebookModel.setCourseName(mParam1);
                    notebookModel.setContent(editText.getText().toString());
                    notebookModel.setTag("");
                    helper.addNodebook(notebookModel);
                    editText.setText("");

                    adapter[0] =new NotebookModelAdapter(getContext(),R.layout.fragment_notebooklist_item,helper.findTodayNodebookByCourse(mParam1));
                    noteListView.setAdapter(adapter[0]);
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
