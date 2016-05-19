package com.webplus.jiangchao.iridescence.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.webplus.jiangchao.iridescence.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CourseFooterLayout extends LinearLayout {
    public CourseFooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.course_footer, this);
    }
}
