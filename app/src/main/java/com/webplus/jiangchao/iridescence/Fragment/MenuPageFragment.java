package com.webplus.jiangchao.iridescence.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.webplus.jiangchao.iridescence.CourseListActivity;
import com.webplus.jiangchao.iridescence.DAO.CourseHelper;
import com.webplus.jiangchao.iridescence.HomeworkListActivity;
import com.webplus.jiangchao.iridescence.NoteBookActivity;
import com.webplus.jiangchao.iridescence.R;

public class MenuPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menupage, container, false);

        final CourseHelper helper=new CourseHelper(getContext(),getContext().getResources().getString(R.string.dbname) ,null,1);
        Button courseListBtn= (Button) rootView.findViewById(R.id.course_listbtn);
        courseListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CourseListActivity.class);
                startActivity(intent);
            }
        });

        Button homeworkListBtn= (Button) rootView.findViewById(R.id.homework_listbtn);
        homeworkListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.findAllCourseName().size()==0){
                    Toast.makeText(getContext(),"请先添加课程",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getContext(), HomeworkListActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button booklistBtn= (Button) rootView.findViewById(R.id.booklistBtn);
        booklistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.findAllCourseName().size()==0){
                    Toast.makeText(getContext(),"请先添加课程",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getContext(), NoteBookActivity.class);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }
}
