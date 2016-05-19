package com.webplus.jiangchao.iridescence;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.webplus.jiangchao.iridescence.DAO.CourseHelper;
import com.webplus.jiangchao.iridescence.Model.CourseModel;
import com.webplus.jiangchao.iridescence.R;

import java.util.Calendar;

public class AddOrUpdateCourseActivity extends AppCompatActivity {
    private  TimePickerDialog dialog;
    private Calendar c;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addorupdatecourse);
        getSupportActionBar().hide();

        final Intent intent = getIntent();
        final int data = intent.getIntExtra("week",0);

        TextView title= (TextView) findViewById(R.id.edittitle);
        final Button startTimeText= (Button) findViewById(R.id.courseeditstarttime);
        c = Calendar.getInstance();
        startTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(AddOrUpdateCourseActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);
                                startTimeText.setText((hourOfDay<10?("0"+hourOfDay):hourOfDay)+":" +
                                        ""+(minute<10?("0"+minute):minute));

                            }
                        }, hour, minute, true).show();
            }
        });

        final Button endTimeText= (Button) findViewById(R.id.courseeditendtime);
        c = Calendar.getInstance();
        endTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(AddOrUpdateCourseActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);
                                endTimeText.setText((hourOfDay<10?("0"+hourOfDay):hourOfDay)+":" +
                                        ""+(minute<10?("0"+minute):minute));

                            }
                        }, hour, minute, true).show();
            }
        });

        final TextView courseName= (TextView) findViewById(R.id.courseeditname);
        final TextView locationName= (TextView) findViewById(R.id.courseeditlocation);

        if(intent.getIntExtra("type",0)==1)
        {
            //修改
            type=1;
            title.setText("编辑课程");
            CourseHelper helper=new CourseHelper(AddOrUpdateCourseActivity.this,getApplicationContext().getResources().getString(R.string.dbname) ,null,1);
            CourseModel courseModel=helper.findCourseById(intent.getIntExtra("id",0));
            if (courseModel!=null)
            {
                startTimeText.setText(courseModel.getStartTime());
                endTimeText.setText(courseModel.getEndTime());
                courseName.setText(courseModel.getCourseName());
                locationName.setText(courseModel.getLocation());
            }else {
                finish();
            }
        }



        //提交
        Button okbtn= (Button) findViewById(R.id.addoreditcourseokbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CourseHelper helper=new CourseHelper();
                String errorValue="";
                boolean flag=false;

                CourseModel courseModel=new CourseModel();
                courseModel.setCourseName(courseName.getText().toString());
                courseModel.setStartTime(startTimeText.getText().toString());
                courseModel.setEndTime(endTimeText.getText().toString());

                courseModel.setLocation(locationName.getText().toString());
                courseModel.setWeek(data);
                if("".equals(courseModel.getCourseName()))
                {
                    errorValue+="请输入课程名称\n";
                    flag=true;
                }

                System.out.println(courseModel.getStartTime());
                if("".equals(courseModel.getStartTime()))
                {
                    errorValue+="请输入上课时间\n";
                    flag=true;
                }
                if("".equals(courseModel.getEndTime()))
                {
                    errorValue+="请输入下课时间\n";
                    flag=true;
                }
                if("".equals(courseModel.getLocation()))
                {
                    errorValue+="请输入上课地点\n";
                    flag=true;
                }
                Log.d("AddOrUpdateCourse2",errorValue);
                if(errorValue.length()==0) {
                    //下课时间不能>上课时间
                    if ((Integer.parseInt(courseModel.getStartTime().split(":")[0]) > Integer.parseInt(courseModel.getEndTime().split(":")[0])) ||
                            (Integer.parseInt(courseModel.getStartTime().split(":")[1]) > Integer.parseInt(courseModel.getEndTime().split(":")[1]))) {
                        errorValue += "上课时间不能大于下课时间";
                        flag = true;
                    }
                }
                if(flag)
                {
                    //提示
                    AlertDialog.Builder builder=new AlertDialog.Builder(AddOrUpdateCourseActivity.this);
                    builder.setMessage(errorValue);
                    builder.show();
                }else
                {
                    CourseHelper helper=new CourseHelper(AddOrUpdateCourseActivity.this, getApplicationContext().getResources().getString(R.string.dbname),null,1);

                    if(intent.getIntExtra("type",0)==1)
                    {
                        //修改
                        courseModel.setId(intent.getIntExtra("id",0));
                        helper.updateCourse(courseModel);
                        Toast.makeText(AddOrUpdateCourseActivity.this,"课程编辑完成",Toast.LENGTH_SHORT).show();

                    }else {
                        //添加
                        helper.addCourse(courseModel);
                        Toast.makeText(AddOrUpdateCourseActivity.this, "课程添加完成", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent1 =new Intent();
                    intent1.putExtra("success",true);
                    intent1.putExtra("week",data);
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            }
        });
    }

}
