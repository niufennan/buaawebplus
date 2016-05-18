package com.webplus.jiangchao.irdescence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.webplus.jiangchao.irdescence.Model.NotebookModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class NotebookHelper extends BaseHelper {

    private Context mContext;

    public NotebookHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }
    public void addNodebook(NotebookModel nbm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("courseName",nbm.getCourseName());
        cv.put("content",nbm.getContent());
        cv.put("tag",nbm.getTag());
        cv.put("state",nbm.getState());
        cv.put("createtime", DateFormat.getDateInstance().format(new Date()));
        db.insert("Notebook",null,cv);
        //标签
        /*
        for(String tag :nbm.getTag().split(",")) {
            Cursor cursor = db.query("Tag", null, "tag=?",
                    new String[]{tag}, null, null, null);
            if(cursor.getCount()==0)
            {
                cv.clear();;
                cv.put("tag",tag);
                db.insert("Tag",null,cv);
            }
        }*/
    }
    public void updateNodebook(NotebookModel nbm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("courseName",nbm.getCourseName());
        cv.put("content",nbm.getContent());
        cv.put("tag",nbm.getTag());
        cv.put("state",nbm.getState());
        cv.put("createtime", DateFormat.getDateInstance().format(new Date()));
        db.update("Notebook",cv,"id=?",new String[]{String.valueOf(nbm.getId())});
    }

    public void delNodebook(NotebookModel nbm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Notebook","id=?",new String[]{String.valueOf(nbm.getId())});
    }
    public NotebookModel findNodebookById(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Notebook",null,"id=?",
                new String[]{String.valueOf(id)},null,null,null);
        NotebookModel notebookModel =new NotebookModel();
        if (cursor.moveToFirst())
        {
            do{
                notebookModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                notebookModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                notebookModel.setTag(cursor.getString(cursor.getColumnIndex("tag")));
                notebookModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                notebookModel.setState( cursor.getInt(cursor.getColumnIndex("state")));
                notebookModel.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
            }while (cursor.moveToNext());
        }
        return notebookModel;
    }
    public List<NotebookModel> findNodebookByCourse(String courseName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Notebook",null,"courseName=?",
                new String[]{courseName},null,null,null);
        List<NotebookModel> notebookModelList =new ArrayList<NotebookModel>();
        if (cursor.moveToFirst())
        {
            do{
                NotebookModel notebookModel =new NotebookModel();
                notebookModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                notebookModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                notebookModel.setTag(cursor.getString(cursor.getColumnIndex("tag")));
                notebookModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                notebookModel.setState( cursor.getInt(cursor.getColumnIndex("state")));
                notebookModel.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
                notebookModelList.add(notebookModel);
                Log.d("Notebook",notebookModel.getCreateTime());
                Log.d("Notebook",DateFormat.getDateInstance().format(new Date()));
            }while (cursor.moveToNext());
        }
        return notebookModelList;
    }

    public List<NotebookModel> findTodayNodebookByCourse(String courseName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Notebook",null,"courseName=? and createtime=?",
                new String[]{courseName, DateFormat.getDateInstance().format(new Date())},null,null,"id desc");
        List<NotebookModel> notebookModelList =new ArrayList<NotebookModel>();
        if (cursor.moveToFirst())
        {
            do{
                NotebookModel notebookModel =new NotebookModel();
                notebookModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                notebookModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                notebookModel.setTag(cursor.getString(cursor.getColumnIndex("tag")));
                notebookModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                notebookModel.setState( cursor.getInt(cursor.getColumnIndex("state")));
                notebookModel.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
                notebookModelList.add(notebookModel);

                Log.d("Notebook",notebookModel.getCreateTime());
                Log.d("Notebook",DateFormat.getDateInstance().format(new Date()));

            }while (cursor.moveToNext());
        }
        return notebookModelList;
    }
}
