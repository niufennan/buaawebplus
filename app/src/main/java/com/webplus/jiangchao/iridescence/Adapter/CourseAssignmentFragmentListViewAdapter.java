package com.webplus.jiangchao.iridescence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.webplus.jiangchao.iridescence.DAO.AssignmentHelper;
import com.webplus.jiangchao.iridescence.Model.AssignmentModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.List;

/**
 * Created by jiangchao on 16/5/17.
 */

public class CourseAssignmentFragmentListViewAdapter extends ArrayAdapter<AssignmentModel>
{
    private int resourceId;
    public CourseAssignmentFragmentListViewAdapter(Context context, int resource, List<AssignmentModel> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AssignmentModel am =getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);

        final CheckBox assignmenetCompleted= (CheckBox) view.findViewById(R.id.assignment_view_completed);
        assignmenetCompleted.setChecked(am.isCompleted()==1);
        assignmenetCompleted.setText(am.getContent());

        assignmenetCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssignmentHelper helper = new AssignmentHelper(getContext(), getContext().getResources().getString(R.string.dbname), null, 1);
                if(assignmenetCompleted.isChecked())
                {
                    am.setCompleted(1);
                    helper.updateAssignment(am);
                }else
                {
                    am.setCompleted(0);
                    helper.updateAssignment(am);
                }

            }
        });

        return view;
    }
}