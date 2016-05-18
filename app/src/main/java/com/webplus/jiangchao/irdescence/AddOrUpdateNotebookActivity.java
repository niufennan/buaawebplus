package com.webplus.jiangchao.irdescence;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.webplus.jiangchao.irdescence.DAO.NotebookHelper;
import com.webplus.jiangchao.irdescence.Model.NotebookModel;
import com.webplus.jiangchao.irdescence.R;

/**
 * Created by Administrator on 2016/5/17.
 */
public class AddOrUpdateNotebookActivity extends AppCompatActivity {
    private DatePickerDialog dialog;

    private int type;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addorupdatenotebook);
        getSupportActionBar().hide();


        final Intent intent = getIntent();
        final String courseName = intent.getStringExtra("courseName");
        final NotebookHelper helper = new NotebookHelper(AddOrUpdateNotebookActivity.this, getApplicationContext().getResources().getString(R.string.dbname), null, 1);



        position=intent.getIntExtra("position",0);
        final EditText contentText= (EditText) findViewById(R.id.nodebook_content_edit);
        final EditText tagText= (EditText) findViewById(R.id.nodebook_tag_edit);

        TextView title= (TextView) findViewById(R.id.edittitle);
        if(intent.getIntExtra("type",0)==1)
        {
            //修改
            type=1;
            title.setText("编辑笔记");

            NotebookModel notebookModel =helper.findNodebookById(intent.getIntExtra("id",0));
            if (notebookModel !=null&& notebookModel.getId()!=0)
            {
                contentText.setText(notebookModel.getContent());
                tagText.setText(notebookModel.getTag());
            }else {
                finish();
            }
        }

        Button okbtn= (Button) findViewById(R.id.addoreditnodebookokbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errorValue = "";
                boolean flag = false;
                NotebookModel notebookModel = new NotebookModel();
                notebookModel.setCourseName(courseName);
                notebookModel.setContent(contentText.getText().toString());
                notebookModel.setTag(tagText.getText().toString());


                if ("".equals(notebookModel.getContent())) {
                    errorValue += "请输入作业内容\n";
                }
                if (flag) {
                    //提示
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddOrUpdateNotebookActivity.this);
                    builder.setMessage(errorValue);
                    builder.show();
                } else {


                    if(intent.getIntExtra("type",0)==1)
                    {
                        //修改
                        notebookModel.setId(intent.getIntExtra("id",0));
                        helper.updateNodebook(notebookModel);
                        Toast.makeText(AddOrUpdateNotebookActivity.this,"笔记编辑完成",Toast.LENGTH_SHORT).show();

                    }else {
                        //添加
                        helper.addNodebook(notebookModel);
                        Toast.makeText(AddOrUpdateNotebookActivity.this, "笔记添加完成", Toast.LENGTH_SHORT).show();
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
