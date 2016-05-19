package com.webplus.jiangchao.iridescence.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.Model.AssignmentGroup;
import com.webplus.jiangchao.iridescence.Model.AssignmentModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class AssignmentGroupAdapter extends ArrayAdapter<AssignmentGroup> {
    private int resourceId;
    public AssignmentGroupAdapter(Context context, int textViewResourceId, List<AssignmentGroup> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    private int  selectId;
    public int getSelectId()
    {
        return selectId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final AssignmentGroup assignmentGroup=getItem(position);
        selectId=0;
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView createTimeView= (TextView) view.findViewById(R.id.homework_createtime);
        ListView dataView= (ListView) view.findViewById(R.id.homework_data);
        createTimeView.setText(assignmentGroup.getCreateTime());

        AssignmentAdapter adapter =new AssignmentAdapter(getContext(),R.layout.assignment_children_item,assignmentGroup.getData());
        dataView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(dataView);
        dataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssignmentModel homeWork =assignmentGroup.getData().get(position);

                selectId=assignmentGroup.getData().get(position).getId();
                for(int i=0;i<parent.getCount();i++)
                {
                    setDefaultTextColor(parent.getChildAt(i), Color.WHITE);
                }
                setDefaultTextColor(view,Color.BLUE);

            }
        });
        return view;
    }

    private void setDefaultTextColor(View view,int color)
    {
        ((TextView)view.findViewById(R.id.homework_content)).setTextColor(color);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
