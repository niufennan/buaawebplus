package com.webplus.jiangchao.irdescence.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webplus.jiangchao.irdescence.Model.AssignmentModel;
import com.webplus.jiangchao.irdescence.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class AssignmentAdapter extends ArrayAdapter<AssignmentModel> {
    private int resourceId;
    public AssignmentAdapter(Context context, int textViewResourceId, List<AssignmentModel> objects) {
        super(context, textViewResourceId, objects);
        Log.d("HomeWorkAdapter",String.valueOf(objects.size()));
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        AssignmentModel assignmentModel=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView contentView= (TextView) view.findViewById(R.id.homework_content);
        TextView monthView= (TextView) view.findViewById(R.id.homework_month);
        TextView dayView= (TextView) view.findViewById(R.id.homework_day);
        contentView.setText(assignmentModel.getContent());
        monthView.setText(getMonth(assignmentModel.getDueDate()));
        dayView.setText(getDay(assignmentModel.getDueDate()));
        LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.homework_date_layout);
        //Log.d("AssignmentAdapter",assignmentModel.getDueDate().getTime())
        if (assignmentModel.getDueDate().getTime()<(new Date()).getTime()+(2*24 * 60 * 60 * 1000))
        {
            linearLayout.setBackgroundColor(Color.argb(255,200,00,00));
        }

        if (assignmentModel.isCompleted()==1)
        {
            linearLayout.setBackgroundColor(Color.argb(255,00,80,00));
        }

        return view;
    }
    private String getDay(Date date)
    {
        Calendar c= Calendar.getInstance();
        c.setTime(date);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
    }
    private String getMonth(Date date)
    {
        Calendar c= Calendar.getInstance();
        c.setTime(date);
        int month=c.get(Calendar.MONTH);
        switch (month){
            case 0:
                return "一月";
            case 1:
                return "二月";
            case 2:
                return "三月";
            case 3:
                return "四月";
            case 4:
                return "五月";
            case 5:
                return "六月";
            case 6:
                return "七月";
            case 7:
                return "八月";
            case 8:
                return "九月";
            case 9:
                return "十月";
            case 10:
                return "十一月";
            case 11:
                return "十二月";
            default:
                return "";
        }
    }

}
