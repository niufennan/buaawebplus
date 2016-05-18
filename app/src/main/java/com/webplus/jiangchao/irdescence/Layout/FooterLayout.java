package com.webplus.jiangchao.irdescence.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.webplus.jiangchao.irdescence.R;

/**
 * Created by Administrator on 2016/5/16.
 */
public class FooterLayout extends LinearLayout {
    public FooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.footer, this);
    }
}
