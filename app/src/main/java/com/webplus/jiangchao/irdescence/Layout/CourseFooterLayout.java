package com.webplus.jiangchao.irdescence.Layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.webplus.jiangchao.irdescence.AddOrUpdateCourseActivity;
import com.webplus.jiangchao.irdescence.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CourseFooterLayout extends LinearLayout {
    public CourseFooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.course_footer, this);
    }
}
