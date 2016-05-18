package com.webplus.jiangchao.irdescence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.webplus.jiangchao.irdescence.Model.AssignmentModel;
import com.webplus.jiangchao.irdescence.Model.CourseModel;
import com.webplus.jiangchao.irdescence.Model.NotebookModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class AssignmentHelper extends BaseHelper {

    private Context mContext;

    public AssignmentHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }
    public void addAssignment(AssignmentModel am)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("courseName",am.getCourseName());
        cv.put("startDate",am.getStartDate().getTime());
        cv.put("dueDate",am.getDueDate().getTime());
        cv.put("content",am.getContent());
        cv.put("isCompleted",am.isCompleted());
        cv.put("dueDateTxt", DateFormat.getDateInstance().format(am.getDueDate()));
        db.insert("Assignment",null,cv);
    }
    public void updateAssignment(AssignmentModel am)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("courseName",am.getCourseName());
        cv.put("startDate",am.getStartDate().getTime());
        cv.put("dueDate",am.getDueDate().getTime());
        cv.put("content",am.getContent());
        cv.put("isCompleted",am.isCompleted());
        cv.put("dueDateTxt", DateFormat.getDateInstance().format(am.getDueDate()));
        db.update("Assignment",cv,"id=?",new String[]{String.valueOf(am.getId())});
    }

    public void delAssignment(AssignmentModel am)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Assignment","id=?",new String[]{String.valueOf(am.getId())});
    }
    public  AssignmentModel findAssignmentById(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Assignment",null,"id=?",
                new String[]{String.valueOf(id)},null,null,null);
        AssignmentModel assignmentModel =new AssignmentModel();
        if (cursor.moveToFirst())
        {
            do{
                assignmentModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                assignmentModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                assignmentModel.setCompleted(cursor.getInt(cursor.getColumnIndex("isCompleted")));
                assignmentModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                assignmentModel.setStartDate( new Date(cursor.getLong(cursor.getColumnIndex("startDate"))));
                assignmentModel.setDueDate( new Date(cursor.getLong(cursor.getColumnIndex("dueDate"))));
            }while (cursor.moveToNext());
        }
        return assignmentModel;
    }
    public List<AssignmentModel> findAssignmentByCourse(String courseName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Assignment",null,"courseName=?",
                new String[]{courseName},null,null," startDate,dueDate");
        List<AssignmentModel> assignmentModelList =new ArrayList<AssignmentModel>();
        if (cursor.moveToFirst())
        {
            do{
                AssignmentModel assignmentModel=new AssignmentModel();
                assignmentModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                assignmentModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                assignmentModel.setCompleted(cursor.getInt(cursor.getColumnIndex("isCompleted")));
                assignmentModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                assignmentModel.setStartDate( new Date(cursor.getLong(cursor.getColumnIndex("startDate"))));
                assignmentModel.setDueDate( new Date(cursor.getLong(cursor.getColumnIndex("dueDate"))));
                assignmentModelList.add(assignmentModel);
            }while (cursor.moveToNext());
        }



        return assignmentModelList;
    }
    public List<AssignmentModel> findTodayAssignmentByCourse(String courseName)
    {
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.set(c1.get(Calendar.YEAR),c1.get(Calendar.MONTH),c1.get(Calendar.DAY_OF_MONTH),0,0,0);
        c2.set(c2.get(Calendar.YEAR),c2.get(Calendar.MONTH),c2.get(Calendar.DAY_OF_MONTH)+1,0,0,0);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Assignment",null,"courseName=? and startDate>=? and startDate<?",
                new String[]{courseName,String.valueOf(c1.getTimeInMillis()),String.valueOf(c2.getTimeInMillis())},null,null,"id desc");
        List<AssignmentModel> assignmentModelList =new ArrayList<AssignmentModel>();
        if (cursor.moveToFirst())
        {
            do{
                AssignmentModel assignmentModel=new AssignmentModel();
                assignmentModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                assignmentModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                assignmentModel.setCompleted(cursor.getInt(cursor.getColumnIndex("isCompleted")));
                assignmentModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                assignmentModel.setStartDate( new Date(cursor.getLong(cursor.getColumnIndex("startDate"))));
                assignmentModel.setDueDate( new Date(cursor.getLong(cursor.getColumnIndex("dueDate"))));
                assignmentModelList.add(assignmentModel);
            }while (cursor.moveToNext());
        }
        Log.d("AssignementHelper",courseName);
        Log.d("AssignementHelper",String.valueOf(assignmentModelList.size()));
        return assignmentModelList;
    }
}
