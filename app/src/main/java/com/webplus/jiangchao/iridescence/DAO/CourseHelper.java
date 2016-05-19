package com.webplus.jiangchao.iridescence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.webplus.jiangchao.iridescence.Model.CourseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CourseHelper extends BaseHelper{

    /*
    *
    *  private String courseName;
    private String startTime;
    private String endTime;
    private String location;
    private int week;
    * */

    private Context mContext;

    public CourseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }
    public void addCourse(CourseModel cm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("courseName",cm.getCourseName().trim());
        cv.put("startHour",Integer.parseInt(cm.getStartTime().split(":")[0]));
        cv.put("startMinute",Integer.parseInt(cm.getStartTime().split(":")[1]));
        cv.put("startTime",cm.getStartTime().trim());
        cv.put("endTime",cm.getEndTime().trim());
        cv.put("location",cm.getLocation().trim());
        cv.put("week",cm.getWeek());
        db.insert("Course",null,cv);
    }
    public void updateCourse(CourseModel cm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("courseName",cm.getCourseName().trim());
        cv.put("startHour",Integer.parseInt(cm.getStartTime().split(":")[0]));
        cv.put("startMinute",Integer.parseInt(cm.getStartTime().split(":")[1]));
        cv.put("startTime",cm.getStartTime().trim());
        cv.put("endTime",cm.getEndTime().trim());
        cv.put("location",cm.getLocation().trim());
        cv.put("week",cm.getWeek());
        db.update("Course",cv,"id=?",new String[]{String.valueOf(cm.getId())});
    }

    public void delCourse(CourseModel cm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Course","id=?",new String[]{String.valueOf(cm.getId())});
    }
    public  CourseModel findCourseById(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Course",null,"id=?",
                new String[]{String.valueOf(id)},null,null,null);
        CourseModel courseModel =new CourseModel();
        if (cursor.moveToFirst())
        {
            do{
                courseModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                courseModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                courseModel.setStartTime(cursor.getString(cursor.getColumnIndex("startTime")));
                courseModel.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                courseModel.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
                courseModel.setWeek(cursor.getInt(cursor.getColumnIndex("week")));
            }while (cursor.moveToNext());
        }
        return courseModel;
    }
    public List<CourseModel> findCourseByDay(int week)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Course",null,"week=?",
                new String[]{String.valueOf(week)},null,null," startHour,startMinute");
        List<CourseModel> courseModelList =new ArrayList<CourseModel>();
        if (cursor.moveToFirst())
        {
            do{
                CourseModel courseModel=new CourseModel();
                courseModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                courseModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                courseModel.setStartTime(cursor.getString(cursor.getColumnIndex("startTime")));
                courseModel.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                courseModel.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
                courseModel.setWeek(cursor.getInt(cursor.getColumnIndex("week")));
                courseModelList.add(courseModel);
            }while (cursor.moveToNext());
        }
        return courseModelList;
    }

    public List<CourseModel> findCourseByCourseName(String courseName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Course",null,"courseName=?",
                new String[]{courseName},null,null," week");
        List<CourseModel> courseModelList =new ArrayList<CourseModel>();
        if (cursor.moveToFirst())
        {
            do{
                CourseModel courseModel=new CourseModel();
                courseModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                courseModel.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                courseModel.setStartTime(cursor.getString(cursor.getColumnIndex("startTime")));
                courseModel.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                courseModel.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
                courseModel.setWeek(cursor.getInt(cursor.getColumnIndex("week")));
                courseModelList.add(courseModel);
            }while (cursor.moveToNext());
        }
        return courseModelList;
    }

    public  List<String> findAllCourseName()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Course",new String[]{"courseName"},null,null,"courseName",null," startHour,startMinute");
        List<String> result=new ArrayList<String>();
        if(cursor.moveToFirst())
        {
            do{
                result.add(cursor.getString(cursor.getColumnIndex("courseName")));
            }while (cursor.moveToNext());
        }
        return result;
    }
}
