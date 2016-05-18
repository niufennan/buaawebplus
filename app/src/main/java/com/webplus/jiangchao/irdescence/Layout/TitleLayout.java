package com.webplus.jiangchao.irdescence.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webplus.jiangchao.irdescence.R;

/**
 * Created by jiangchao on 16/5/15.
 */
public class TitleLayout extends LinearLayout{
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        /*
        Button preBtn = (Button) findViewById(R.id.title_pre);
        preBtn.setText("<");
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Pre button", Toast.LENGTH_SHORT).show();
            }
        });

        Button nextBtn = (Button) findViewById(R.id.title_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Next button", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}
