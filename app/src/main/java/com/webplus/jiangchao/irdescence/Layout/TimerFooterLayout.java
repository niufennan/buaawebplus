package com.webplus.jiangchao.irdescence.Layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.webplus.jiangchao.irdescence.AddOrUpdateCourseActivity;
import com.webplus.jiangchao.irdescence.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class TimerFooterLayout  extends LinearLayout {
    public ImageButton getOkBtn()
    {
        return  (ImageButton) findViewById(R.id.btn_ok);
    }
    public TimerFooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.timer_footer, this);
        ImageButton okbtn = (ImageButton) findViewById(R.id.btn_ok);
    }
}
