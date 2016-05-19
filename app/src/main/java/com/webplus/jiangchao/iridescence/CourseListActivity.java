package com.webplus.jiangchao.iridescence;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.webplus.jiangchao.iridescence.Adapter.MyFragmentPagerAdapter;
import com.webplus.jiangchao.iridescence.Fragment.CourseListFragment;
import com.webplus.jiangchao.iridescence.Layout.CourseFooterLayout;
import com.webplus.jiangchao.iridescence.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CourseListActivity extends AppCompatActivity  implements CourseListFragment.OnFragmentInteractionListener {
    MyFragmentPagerAdapter fragmentPagerAdapter;
    ArrayList<Fragment> listFragments;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_courselist);
        getSupportActionBar().hide();

        listFragments=new ArrayList<Fragment>();
        listFragments.add(CourseListFragment.newInstance("星期一",1));
        listFragments.add(CourseListFragment.newInstance("星期二",2));
        listFragments.add(CourseListFragment.newInstance("星期三",3));
        listFragments.add(CourseListFragment.newInstance("星期四",4));
        listFragments.add(CourseListFragment.newInstance("星期五",5));
        listFragments.add(CourseListFragment.newInstance("星期六",6));
        listFragments.add(CourseListFragment.newInstance("星期日",0));
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),listFragments);
        // Set up the ViewPager with the sections adapter.
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        int week = (cal.get(Calendar.DAY_OF_WEEK)-1);


        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(week-1);
        //fragmentPagerAdapter.setPrimaryItem(mViewPager,2,listFragments.get(2));


        CourseFooterLayout courseFooterLayout= (CourseFooterLayout) findViewById(R.id.courselist_footlayout);
        ImageButton addbtn= (ImageButton)courseFooterLayout.findViewById(R.id.btn_add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CourseListActivity.this, AddOrUpdateCourseActivity.class);
                intent.putExtra("week",((CourseListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getWeek());
                startActivityForResult(intent,1);
            }
        });

        ImageButton updatebtn= (ImageButton)courseFooterLayout.findViewById(R.id.btn_edit);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CourseListActivity.this, AddOrUpdateCourseActivity.class);
                intent.putExtra("week",((CourseListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getWeek());
                intent.putExtra("type",1);
                intent.putExtra("id",((CourseListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getSelectId());
                startActivityForResult(intent,1);
            }
        });

        ImageButton tobtn= (ImageButton)courseFooterLayout.findViewById(R.id.btn_to);
        tobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CourseListActivity.this, CourseViewActivity.class);
                intent.putExtra("id",((CourseListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getSelectId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("CourseListActivity",uri.toString());
    }



    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case 1:
                if(resultCode==RESULT_OK)
                {
                    if(data.getBooleanExtra("success",false))
                    {
                        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),listFragments);
                        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
                        mViewPager.setAdapter(fragmentPagerAdapter);
                        mViewPager.setCurrentItem(data.getIntExtra("week",0)-1);
                    }
                }
                break;
            default:
        }
    }

}
