package com.webplus.jiangchao.irdescence.Model;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CourseModel {
    private String courseName;
    private String startTime;
    private String endTime;
    private String location;
    private int week;


    private int id;
    public CourseModel() {

    }

    public CourseModel(String courseName, String startTime, String endTime, String location, int week) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.week = week;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartTime() {
        return startTime==null?"":startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime==null?"":endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
