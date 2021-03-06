package com.webplus.jiangchao.iridescence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TimePicker;

import com.webplus.jiangchao.iridescence.Layout.TimerFooterLayout;
import com.webplus.jiangchao.iridescence.R;

public class TimerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timer);
        final TimePicker timePicker= (TimePicker) findViewById(R.id.timer);
        timePicker.setIs24HourView(true);
        TimerFooterLayout footerLayout = (TimerFooterLayout) findViewById(R.id.timer_footer_layout);
        footerLayout.getOkBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour=timePicker.getHour();
                int minute=timePicker.getMinute();
                Intent in = new Intent();
                in.putExtra( "result", hour+":"+minute );
                setResult( RESULT_OK, in );
                finish();
            }
        });
    }
}
