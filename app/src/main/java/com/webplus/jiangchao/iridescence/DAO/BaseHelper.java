package com.webplus.jiangchao.iridescence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/17.
 */
public class BaseHelper extends SQLiteOpenHelper {
    public static final String AssignmentCreateSql = "create table Assignment ("
            + "id integer primary key autoincrement, "
            + "courseName text," +
            "dueDateTxt text," +
            "startDate long," +
            "dueDate long," +
            "content text," +
            "isCompleted integer)";
    public static final String CourseCreateSql = "create table Course ("
            + "id integer primary key autoincrement, "
            + "courseName text," +
            "startHour integer," +
            "startMinute integer," +
            "startTime text," +
            "endTime text," +
            "location text,"
            + "week integer)";

    public static final String NodebookCreateSql="create table Notebook ("
            + "id integer primary key autoincrement, "
            + "courseName text," +
            "content text," +
            "createtime text," +
            "tag text," +
            "state integer)";

    /*
    public static final String TagCreateSql="create table Tag ("
            + "id integer primary key autoincrement, "
            + "tag text)";
    */
    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CourseCreateSql);
        db.execSQL(AssignmentCreateSql);
        db.execSQL(NodebookCreateSql);
        //db.execSQL(TagCreateSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
