package com.webplus.jiangchao.iridescence.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.R;

/**
 * Created by jiangchao on 16/5/15.
 */
public class CourseTitleLayout extends LinearLayout {
    public CourseTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.course_title, this);
    }
    public void setTitle(String text)
    {
        TextView textView= (TextView) findViewById(R.id.course_title_text);
        textView.setText(text);
    }
}
