package com.webplus.jiangchao.iridescence.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.Adapter.NotebookModelAdapter;
import com.webplus.jiangchao.iridescence.DAO.NotebookHelper;
import com.webplus.jiangchao.iridescence.Model.CourseModel;
import com.webplus.jiangchao.iridescence.Model.NotebookModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotebookListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotebookListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;

    private int selectId;

    public int getSelectId()
    {
        return selectId;
    }

    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<CourseModel> arrayAdapter;

    public NotebookListFragment() {
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
    public static NotebookListFragment newInstance(String param1, int param2) {
        NotebookListFragment fragment = new NotebookListFragment();
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
        View rootView= inflater.inflate(R.layout.fragment_notebooklist, container, false);
        TextView courseListTitle= (TextView) rootView.findViewById(R.id.courselist_title);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
        courseListTitle.setText(mParam1);
        ListView nodebookListItem= (ListView) rootView.findViewById(R.id.nodebooklist_item);
        final List<NotebookModel> list=initNodebookList(mParam1);
        NotebookModelAdapter adapter =new NotebookModelAdapter(getContext(),R.layout.fragment_notebooklist_item,list);

        nodebookListItem.setAdapter(adapter);

        nodebookListItem.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

                selectId=list.get(arg2).getId();

                for(int i=0;i<arg0.getCount();i++)
                {
                    setDefaultTextColor(arg0.getChildAt(i),Color.WHITE);
                }

                // arg0.getChildAt()

               setDefaultTextColor(arg1,Color.BLUE);
            }

        });

        return rootView;
    }

    private void setDefaultTextColor(View view,int color)
    {
        ((TextView)view.findViewById(R.id.nodebook_content)).setTextColor(color);
        /*((TextView)view.findViewById(R.id.course_starttime)).setTextColor(color);
        ((TextView)view.findViewById(R.id.course_endtime)).setTextColor(color);
        ((TextView)view.findViewById(R.id.course_location)).setTextColor(color);
        ((TextView)view.findViewById(R.id.course_line)).setTextColor(color);*/
    }

    private List<NotebookModel> initNodebookList(String courseName)
    {
        NotebookHelper helper=new NotebookHelper(getContext(),getContext().getResources().getString(R.string.dbname),null,1);
        List<NotebookModel> notebookModelList =  helper.findNodebookByCourse(courseName);//  new ArrayList<CourseModel>();
        return notebookModelList;
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
