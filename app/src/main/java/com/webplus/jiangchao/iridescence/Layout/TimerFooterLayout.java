package com.webplus.jiangchao.iridescence.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.webplus.jiangchao.iridescence.R;

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
