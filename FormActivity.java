package com.example.himan.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
 //to enter task name and comment
public class FormActivity extends AppCompatActivity {
    EditText taskname;
    EditText content;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        taskname=findViewById(R.id.taskname1);
        content=findViewById(R.id.content1);

        Intent intent=getIntent();
        bundle=intent.getExtras();
        if(bundle!=null){
            String taskName=bundle.getString(ConstantKeys.TASKNAME_KEY);
            String conTent=bundle.getString(ConstantKeys.CONTENT_KEY);
            //int pos =bundle.getInt(ConstantKeys.POSITION_KEY);
            taskname.setText(taskName);
            content.setText(conTent);
        }
        else{
            bundle=new Bundle();
        }
    }


    public void save(View view) {
        String TaskName=taskname.getText().toString();
        String Content=content.getText().toString();
        if(isNullOrEmpty(TaskName)){
            Toast.makeText(this,"Task name cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isNullOrEmpty(Content)){
            Toast.makeText(this,"Content cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        bundle.putString(ConstantKeys.TASKNAME_KEY,TaskName);
        bundle.putString(ConstantKeys.CONTENT_KEY,Content);
        Intent intent=new Intent();
        intent.putExtras(bundle);

        setResult(ConstantKeys.RESULT,intent);
        finish();
    }
    private boolean isNullOrEmpty(String s){
        return s == null || s.isEmpty();
    }
}
