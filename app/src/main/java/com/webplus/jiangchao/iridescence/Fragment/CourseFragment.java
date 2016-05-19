package com.webplus.jiangchao.iridescence.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.DAO.CourseHelper;
import com.webplus.jiangchao.iridescence.Model.CourseModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);

        //item1
        CourseItem(rootView,R.id.course_item1);
        setCurrentCourseItemContent(rootView);
        CourseItem(rootView,R.id.course_item2);
        setNextCourseItemContent(rootView);
        CourseItem(rootView,R.id.course_item3);
        setTodayCourseItemContent(rootView);
        CourseItem(rootView,R.id.course_item4);
        setNextdayCourseItemContent(rootView);
        return  rootView;
    }

    private void setCurrentCourseItemContent(View rootView)
    {
        //当前节课
        CourseHelper helper=new CourseHelper(getContext(),getContext().getResources().getString(R.string.dbname) ,null,1);
        Calendar c=Calendar.getInstance();
        List<CourseModel> courseModelList=helper.findCourseByDay(c.get(Calendar.DAY_OF_WEEK)-1);
        Calendar newCalendar=Calendar.getInstance();
        newCalendar.set(0,0,0,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
        Calendar courseStartCalendar=Calendar.getInstance();
        Calendar courseEndCalendar=Calendar.getInstance();
        CourseModel currentCourse=new CourseModel();
        currentCourse.setId(-1);
        for (CourseModel cm :courseModelList)
        {
            courseStartCalendar.set(0,0,0,Integer.parseInt(cm.getStartTime().split(":")[0]),Integer.parseInt(cm.getStartTime().split(":")[1]));
            courseEndCalendar.set(0,0,0,Integer.parseInt(cm.getEndTime().split(":")[0]),Integer.parseInt(cm.getEndTime().split(":")[1]));
            if(newCalendar.getTimeInMillis()<courseEndCalendar.getTimeInMillis()&&newCalendar.getTimeInMillis()<courseEndCalendar.getTimeInMillis())
            {
                currentCourse=cm;
                break;
            }
        }

        TextView currentCourseName= (TextView) rootView.findViewById(R.id.current_course_name);
        TextView currentCourseEndTime= (TextView) rootView.findViewById(R.id.current_course_endtime);
        TextView currentCourseLocatione= (TextView) rootView.findViewById(R.id.current_course_location);
        if(currentCourse.getId()==-1)
        {
            if(courseModelList.size()==0)
                currentCourseName.setText("今天没课");//这个有问题
            else
                currentCourseName.setText("当前未上课");
            currentCourseEndTime.setText("");
            currentCourseLocatione.setText("");
        }else
        {
            currentCourseName.setText(currentCourse.getCourseName());
            currentCourseEndTime.setText(currentCourse.getEndTime());
            currentCourseLocatione.setText(currentCourse.getLocation());
        }
    }


    private void setNextCourseItemContent(View rootView)
    {
        //下一节课
        CourseHelper helper=new CourseHelper(getContext(),getContext().getResources().getString(R.string.dbname) ,null,1);
        Calendar c=Calendar.getInstance();
        List<CourseModel> courseModelList=helper.findCourseByDay(c.get(Calendar.DAY_OF_WEEK)-1);
        Calendar newCalendar=Calendar.getInstance();
        newCalendar.set(0,0,0,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
        Calendar courseCalendar=Calendar.getInstance();
        CourseModel nextCourse=new CourseModel();
        nextCourse.setId(-1);
        for (CourseModel cm :courseModelList)
        {
            courseCalendar.set(0,0,0,Integer.parseInt(cm.getStartTime().split(":")[0]),Integer.parseInt(cm.getStartTime().split(":")[1]));
            if(newCalendar.getTimeInMillis()<courseCalendar.getTimeInMillis())
            {
                nextCourse=cm;
                break;
            }
        }

        TextView currentCourseName= (TextView) rootView.findViewById(R.id.next_course_name);
        TextView currentCourseEndTime= (TextView) rootView.findViewById(R.id.next_course_starttime);
        TextView currentCourseLocatione= (TextView) rootView.findViewById(R.id.next_course_location);
        if(nextCourse.getId()==-1)
        {
            if(courseModelList.size()==0)
                currentCourseName.setText("今天没课");//这个有问题
            else
                currentCourseName.setText("今天已无课");
            currentCourseEndTime.setText("");
            currentCourseLocatione.setText("");
        }else
        {
            currentCourseName.setText(nextCourse.getCourseName());
            currentCourseEndTime.setText(nextCourse.getEndTime());
            currentCourseLocatione.setText(nextCourse.getLocation());
        }

    }
    private void setTodayCourseItemContent(View rootView)
    {
        //今天课程
        CourseHelper helper=new CourseHelper(getContext(),getContext().getResources().getString(R.string.dbname) ,null,1);
        Calendar c=Calendar.getInstance();
        List<CourseModel> courseModelList=helper.findCourseByDay(c.get(Calendar.DAY_OF_WEEK)-1);
        Calendar newCalendar=Calendar.getInstance();
        newCalendar.set(0,0,0,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
        Calendar courseCalendar=Calendar.getInstance();
        int index=0;
        for (CourseModel cm :courseModelList)
        {
            courseCalendar.set(0,0,0,Integer.parseInt(cm.getStartTime().split(":")[0]),Integer.parseInt(cm.getStartTime().split(":")[1]));
            if(newCalendar.getTimeInMillis()<courseCalendar.getTimeInMillis())
            {
                index++;
            }
        }
        String [] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

        TextView todayCourseDate= (TextView) rootView.findViewById(R.id.today_course_date);
        TextView todayCourseWeek= (TextView) rootView.findViewById(R.id.today_course_week);
        TextView todayCourseNum= (TextView) rootView.findViewById(R.id.today_course_num);

        todayCourseDate.setText((c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日");
        todayCourseWeek.setText(weeks[c.get(Calendar.DAY_OF_WEEK)-1]);
        if(index==0)
            todayCourseNum.setText("今天无课");
        else
            todayCourseNum.setText("一共"+index+"节课");

    }
    private void setNextdayCourseItemContent(View rootView)
    {
        //明天课程
        CourseHelper helper=new CourseHelper(getContext(),getContext().getResources().getString(R.string.dbname) ,null,1);
        Calendar c=Calendar.getInstance();
        List<CourseModel> courseModelList=helper.findCourseByDay(c.get(Calendar.DAY_OF_WEEK));
        Calendar newCalendar=Calendar.getInstance();
        newCalendar.set(0,0,0,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
        Calendar courseCalendar=Calendar.getInstance();
        CourseModel nextCourse=new CourseModel();
        int index=0;
        for (CourseModel cm :courseModelList)
        {
            courseCalendar.set(0,0,0,Integer.parseInt(cm.getStartTime().split(":")[0]),Integer.parseInt(cm.getStartTime().split(":")[1]));
            if(newCalendar.getTimeInMillis()<courseCalendar.getTimeInMillis())
            {
                index++;
            }
        }

        String [] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六",};

        TextView nextdayCourseDate= (TextView) rootView.findViewById(R.id.nextday_course_date);
        TextView nextdayCourseWeek= (TextView) rootView.findViewById(R.id.nextday_course_week);
        TextView nextdayCourseNum= (TextView) rootView.findViewById(R.id.nextday_course_num);
        c.add(Calendar.DAY_OF_MONTH,1);
        nextdayCourseDate.setText((c.get(Calendar.MONTH)+1)+"月"+(c.get(Calendar.DAY_OF_MONTH)+1)+"日");
        nextdayCourseWeek.setText(weeks[c.get(Calendar.DAY_OF_WEEK)-1]);
        if(index==0)
            nextdayCourseNum.setText("明天无课");
        else
            nextdayCourseNum.setText("一共"+index+"节课");

    }



    private void CourseItem(View rootView,int id) {
        FrameLayout courseItem1= (FrameLayout) rootView.findViewById(id);
        RelativeLayout.LayoutParams courseItem1Params= (RelativeLayout.LayoutParams) courseItem1.getLayoutParams();
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        courseItem1Params.width=((wm.getDefaultDisplay().getWidth()/2)-60);
        courseItem1Params.height=courseItem1Params.width;
        courseItem1.setLayoutParams(courseItem1Params);
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
