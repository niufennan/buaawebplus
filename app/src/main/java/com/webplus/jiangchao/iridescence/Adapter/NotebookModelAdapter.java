package com.webplus.jiangchao.iridescence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.webplus.jiangchao.iridescence.Model.NotebookModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class NotebookModelAdapter extends ArrayAdapter<NotebookModel> {
    private int resourceId;
    public NotebookModelAdapter(Context context, int resource, List<NotebookModel> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotebookModel cm =getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView courseName= (TextView) view.findViewById(R.id.nodebook_content);
        courseName.setText(cm.getContent());
        return view;
    }
}
