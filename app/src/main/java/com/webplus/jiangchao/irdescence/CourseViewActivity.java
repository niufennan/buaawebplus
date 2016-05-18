package com.webplus.jiangchao.irdescence;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.webplus.jiangchao.irdescence.Adapter.MyFragmentPagerAdapter;
import com.webplus.jiangchao.irdescence.DAO.CourseHelper;
import com.webplus.jiangchao.irdescence.Fragment.CourseAssignmentFragment;
import com.webplus.jiangchao.irdescence.Fragment.CourseDescriptionFragment;
import com.webplus.jiangchao.irdescence.Fragment.CourseNotebookFragment;
import com.webplus.jiangchao.irdescence.Layout.CourseTitleLayout;
import com.webplus.jiangchao.irdescence.Model.CourseModel;
import com.webplus.jiangchao.irdescence.R;

import java.util.ArrayList;

public class CourseViewActivity extends AppCompatActivity  implements CourseDescriptionFragment.OnFragmentInteractionListener,
        CourseNotebookFragment.OnFragmentInteractionListener,
        CourseAssignmentFragment.OnFragmentInteractionListener {

    MyFragmentPagerAdapter fragmentPagerAdapter;
    ArrayList<Fragment> listFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_course_view);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);
        if(id==-1) {
            finish();
        }
        CourseHelper helper=new CourseHelper(CourseViewActivity.this,getApplicationContext().getResources().getString(R.string.dbname) ,null,1);
        CourseModel courseModel=helper.findCourseById(id);
        if(courseModel==null)
        {
            finish();
        }
        CourseTitleLayout titleLayout= (CourseTitleLayout) findViewById(R.id.course_view_title);
        titleLayout.setTitle("课程全景");
        TextView courseViewNameTxt= (TextView) findViewById(R.id.course_view_coursename);
        courseViewNameTxt.setText(courseModel.getCourseName());
        listFragments=new ArrayList<Fragment>();
        listFragments.add(CourseDescriptionFragment.newInstance(courseModel.getCourseName(),""));
        listFragments.add(CourseNotebookFragment.newInstance(courseModel.getCourseName(),""));
        listFragments.add(CourseAssignmentFragment.newInstance(courseModel.getCourseName(),""));
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),listFragments);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(fragmentPagerAdapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
