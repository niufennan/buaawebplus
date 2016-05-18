package com.webplus.jiangchao.irdescence;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.webplus.jiangchao.irdescence.DAO.AssignmentHelper;
import com.webplus.jiangchao.irdescence.DAO.CourseHelper;
import com.webplus.jiangchao.irdescence.Model.AssignmentModel;
import com.webplus.jiangchao.irdescence.Model.CourseModel;
import com.webplus.jiangchao.irdescence.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/17.
 */
public class AddOrUpdateAssignmentActivity  extends AppCompatActivity {
    private DatePickerDialog dialog;
    private Calendar c;
    private Calendar selCalender;
    private int type;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addorupdateassignment);
        getSupportActionBar().hide();


        final Intent intent = getIntent();
        final String courseName = intent.getStringExtra("courseName");
        final AssignmentHelper helper = new AssignmentHelper(AddOrUpdateAssignmentActivity.this, getApplicationContext().getResources().getString(R.string.dbname), null, 1);



        position=intent.getIntExtra("position",0);

        final Button dueDateBtn= (Button) findViewById(R.id.assignment_edit_duedate);
        c = Calendar.getInstance();
        dueDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(AddOrUpdateAssignmentActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dueDateBtn.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                                selCalender=  Calendar.getInstance();
                                selCalender.set(year,monthOfYear,dayOfMonth);
                            }
                        }, year, month,day).show();
            }
        });

        final EditText contentText= (EditText) findViewById(R.id.assignment_edit_content);
        final CheckBox isCompleted= (CheckBox) findViewById(R.id.assignment_edit_completed);
        TextView title= (TextView) findViewById(R.id.edittitle);
        if(intent.getIntExtra("type",0)==1)
        {
            //修改
            type=1;
            title.setText("编辑课程");

            AssignmentModel assignmentModel=helper.findAssignmentById(intent.getIntExtra("id",0));
            if (assignmentModel!=null&&assignmentModel.getId()!=0)
            {
                selCalender=  Calendar.getInstance();
                selCalender.setTime(assignmentModel.getDueDate());
                contentText.setText(assignmentModel.getContent());
                dueDateBtn.setText(DateFormat.getDateInstance().format(assignmentModel.getDueDate()));
                isCompleted.setChecked(assignmentModel.isCompleted()==1);
            }else {
                finish();
            }
        }

        Button okbtn= (Button) findViewById(R.id.addoreditassignmentokbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errorValue = "";
                boolean flag = false;
                AssignmentModel assignmentModel = new AssignmentModel();
                assignmentModel.setCourseName(courseName);
                assignmentModel.setContent(contentText.getText().toString());
                assignmentModel.setCompleted(isCompleted.isChecked() ? 1 : 0);
                assignmentModel.setStartDate(new Date());
                assignmentModel.setDueDate(selCalender.getTime());
                if ("".equals(assignmentModel.getDueDate())) {
                    errorValue += "请输入截止日期\n";
                } else if (assignmentModel.getDueDate().getTime() < (new Date()).getTime()) {
                    errorValue += "截止日期不能小于今天\n";
                }
                if ("".equals(assignmentModel.getContent())) {
                    errorValue += "请输入作业内容\n";
                }
                if (flag) {
                    //提示
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddOrUpdateAssignmentActivity.this);
                    builder.setMessage(errorValue);
                    builder.show();
                } else {


                    if(intent.getIntExtra("type",0)==1)
                    {
                        //修改
                        assignmentModel.setId(intent.getIntExtra("id",0));
                        helper.updateAssignment(assignmentModel);
                        Toast.makeText(AddOrUpdateAssignmentActivity.this,"作业编辑完成",Toast.LENGTH_SHORT).show();

                    }else {
                        //添加
                        helper.addAssignment(assignmentModel);
                        Toast.makeText(AddOrUpdateAssignmentActivity.this, "作业添加完成", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent1 =new Intent();
                    intent1.putExtra("success",true);
                    intent1.putExtra("position",position);
                    setResult(RESULT_OK,intent1);
                    finish();
                }

            }
        });

    }

}
