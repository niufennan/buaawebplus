package com.webplus.jiangchao.iridescence;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.webplus.jiangchao.iridescence.Adapter.MyFragmentPagerAdapter;
import com.webplus.jiangchao.iridescence.DAO.AssignmentHelper;
import com.webplus.jiangchao.iridescence.DAO.CourseHelper;
import com.webplus.jiangchao.iridescence.Fragment.NotebookListFragment;
import com.webplus.jiangchao.iridescence.Layout.CourseTitleLayout;
import com.webplus.jiangchao.iridescence.Layout.FooterLayout;
import com.webplus.jiangchao.iridescence.R;

import java.util.ArrayList;
import java.util.List;

public class NoteBookActivity extends AppCompatActivity   implements NotebookListFragment.OnFragmentInteractionListener {
    MyFragmentPagerAdapter fragmentPagerAdapter;
    ArrayList<Fragment> listFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        getSupportActionBar().hide();
        CourseTitleLayout titleLayout= (CourseTitleLayout) findViewById(R.id.nodebook_list_title_layout);
        titleLayout.setTitle("课堂笔记");

        AssignmentHelper assignmentHelper=new AssignmentHelper(NoteBookActivity.this,getApplicationContext().getResources().getString(R.string.dbname),null,1);
        CourseHelper courseHelper=new CourseHelper(NoteBookActivity.this,getApplicationContext().getResources().getString(R.string.dbname),null,1);
        List<String> courseNameList=courseHelper.findAllCourseName();


        listFragments=new ArrayList<Fragment>();
        for(String courseName :courseNameList)
        {
            listFragments.add(NotebookListFragment.newInstance(courseName,0));
        }
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),listFragments);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(fragmentPagerAdapter);



        FooterLayout courseFooterLayout= (FooterLayout) findViewById(R.id.homeworklist_footlayout);
        ImageButton addbtn= (ImageButton)courseFooterLayout.findViewById(R.id.btn_add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoteBookActivity.this, AddOrUpdateNotebookActivity.class);
                intent.putExtra("courseName",((NotebookListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getCourseName());
                intent.putExtra("position",fragmentPagerAdapter.getCurrentPosition());
                startActivityForResult(intent,1);
            }
        });

        ImageButton updatebtn= (ImageButton)courseFooterLayout.findViewById(R.id.btn_edit);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoteBookActivity.this,  AddOrUpdateNotebookActivity.class);
                intent.putExtra("courseName",((NotebookListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getCourseName());
                intent.putExtra("position",fragmentPagerAdapter.getCurrentPosition());
                intent.putExtra("id",((NotebookListFragment)fragmentPagerAdapter.getItem(fragmentPagerAdapter.getCurrentPosition())).getSelectId());
                intent.putExtra("type",1);
                startActivityForResult(intent,1);
            }
        });

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
                    }
                }
                break;
            default:
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
