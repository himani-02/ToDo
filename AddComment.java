package com.example.himan.todo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddComment extends AppCompatActivity {
    EditText comment;
    Bundle bundle;
    ToDoOpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        comment=findViewById(R.id.comment);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        if(bundle!=null){
            String Comment=bundle.getString(Contract.Comment.COMMENT);
            comment.setText(Comment);
        }
        else{
            bundle=new Bundle();
        }
    }

    public void save_comment(View view) {
        String add_comment=comment.getText().toString();
        comment.setText(Contract.Comment.ID+".) "+add_comment);
        if(add_comment==null){
            Toast.makeText(this,"Comment cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        bundle.putString(Contract.Comment.COMMENT,add_comment);
        Intent intent=new Intent();
        intent.putExtras(bundle);
        setResult(ConstantKeys.COMMENT_RESULT,intent);
        finish();
    }
}
