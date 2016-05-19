package com.webplus.jiangchao.iridescence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.Model.CourseModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CourseModelAdapter extends ArrayAdapter<CourseModel> {
    private int resourceId;
    public CourseModelAdapter(Context context, int resource, List<CourseModel> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseModel cm =getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView courseName= (TextView) view.findViewById(R.id.course_name);
        courseName.setText(cm.getCourseName());
        TextView courseStartTime= (TextView) view.findViewById(R.id.course_starttime);
        courseStartTime.setText(cm.getStartTime());
        TextView courseEndTime= (TextView) view.findViewById(R.id.course_endtime);
        courseEndTime.setText(cm.getEndTime());
        TextView courseLocation=(TextView)view.findViewById(R.id.course_location);
        courseLocation.setText(cm.getLocation());

        return view;
    }
}
