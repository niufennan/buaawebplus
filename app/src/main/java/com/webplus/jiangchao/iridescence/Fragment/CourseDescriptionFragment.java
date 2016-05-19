package com.webplus.jiangchao.iridescence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.DAO.CourseHelper;
import com.webplus.jiangchao.iridescence.Model.CourseModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDescriptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDescriptionFragment newInstance(String param1, String param2) {
        CourseDescriptionFragment fragment = new CourseDescriptionFragment();
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
        View rootView= inflater.inflate(R.layout.fragment_course_description, container, false);
        CourseHelper helper=new CourseHelper(getContext(),getContext().getResources().getString(R.string.dbname) ,null,1);
        List<CourseModel> courseModelList=helper.findCourseByCourseName(mParam1);

        List<Integer> weeklist=new ArrayList<Integer>();

        boolean flag;
        for(CourseModel cm :courseModelList)
        {
            flag=true;
            for (int week :weeklist)
            {
                if(week==cm.getWeek()){
                    flag=false;
                }
            }
            if(flag)
            {
                weeklist.add(cm.getWeek());
            }
        }
        String str="逢 ";
        for (int week :weeklist)
        {
            switch (week)
            {
                case 1:
                    str+="星期一 ";
                    break;
                case 2:
                    str+="星期二 ";
                    break;
                case 3:
                    str+="星期三 ";
                    break;
                case 4:
                    str+="星期四 ";
                    break;
                case 5:
                    str+="星期五 ";
                    break;
                case 6:
                    str+="星期六 ";
                    break;
                case 0:
                    str+="星期日 ";
                    break;
            }
        }
        str +="有课";
        TextView courseRemark= (TextView) rootView.findViewById(R.id.course_remark);
        courseRemark.setText(str);
        str="";
        CourseModel nextCourse=new CourseModel();

        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        for(CourseModel cm :courseModelList)
        {
            //如果星期相等，判断上课时间
            if(cm.getWeek()==c.get(Calendar.DAY_OF_WEEK))
            {
                if(c.get(Calendar.HOUR)<Integer.parseInt(cm.getStartTime().split(":")[0]))
                {
                    nextCourse=cm;
                    break;
                }
            }
            if(cm.getWeek()>c.get(Calendar.DAY_OF_WEEK))
                nextCourse=cm;
        }
        if(nextCourse==null||nextCourse.getId()==0)
            nextCourse=courseModelList.get(0);
        if(nextCourse!=null&&nextCourse.getId()!=0)
            str="下节课: "+ getNextDay(nextCourse.getWeek()) +nextCourse.getStartTime()+" - "+nextCourse.getEndTime() +","+nextCourse.getLocation();

        TextView nextRmearkText= (TextView) rootView.findViewById(R.id.course_next_remark);
        nextRmearkText.setText(str);

        return rootView;
    }

    public String getNextDay(int week)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        if(week==0)
            week=7;
        do {
            c.add(Calendar.DATE,1);
        }while (c.get(Calendar.DAY_OF_WEEK)!=week);
        return c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+(c.get(Calendar.DAY_OF_MONTH)+1)+"日";
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
