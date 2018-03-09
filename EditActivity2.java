package com.example.himan.todo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 // where u can edit existed tasks and comments and add comments and view all comments
public class EditActivity2 extends AppCompatActivity {
    TextView editTaskName;
    TextView editContent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

        editContent=findViewById(R.id.editContent);
        editTaskName=findViewById(R.id.editTaskName);

        Intent intent=getIntent();
        bundle=intent.getExtras();
        if(bundle!=null){
            populateDataFromBundle();
        }
        else{
            bundle=new Bundle();
        }
    }

    private void populateDataFromBundle() {  //samajh nahi aya
        int id=bundle.getInt(ConstantKeys.POSITION_KEY,-1);

            ToDoOpenHelper openhelper=ToDoOpenHelper.getOpenHelper(this); //ye kya kia
            SQLiteDatabase database=openhelper.getReadableDatabase();

            String[] selectionArg={id+""};
            Cursor cursor=database.query(Contract.ToDo.TABLE_NAME,null,Contract.ToDo.ID+ " =?",selectionArg,null,null,null);
            if(cursor.moveToFirst()){
                String taskname=cursor.getString(cursor.getColumnIndex(ConstantKeys.TASKNAME_KEY));
                String content=cursor.getString(cursor.getColumnIndex(ConstantKeys.CONTENT_KEY));
                editTaskName.setText(taskname);
                editContent.setText(content);
            }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.edit){
            Intent intent=new Intent(this,FormActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,ConstantKeys.EDIT_REQUEST);
            return true;
        }
        if(item.getItemId()==R.id.add_comment){
            Intent intent=new Intent(this,AddComment.class);
            startActivityForResult(intent,ConstantKeys.COMMENT_KEY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ConstantKeys.EDIT_REQUEST){
            if(resultCode==ConstantKeys.RESULT){
                bundle=data.getExtras();
                if(bundle!=null){
                    populateDataFromBundle();
                    setResult(resultCode, data);
                }
            }
        }
        if(requestCode==ConstantKeys.COMMENT_KEY){
            if(resultCode==ConstantKeys.COMMENT_RESULT){
                if(bundle!=null){
                    bundle=data.getExtras();
                    String comment=bundle.getString(Contract.Comment.COMMENT,"");
                    ToDoOpenHelper openhelper=ToDoOpenHelper.getOpenHelper(this);
                    SQLiteDatabase database=openhelper.getWritableDatabase();
                    int id=bundle.getInt(ConstantKeys.POSITION_KEY,-1);

                    ContentValues contentValues=new ContentValues();
                    contentValues.put(Contract.Comment.COMMENT,comment);
                    contentValues.put(Contract.Comment.TODO_ID,id);
                    database.insert(Contract.Comment.TABLE_NAME,null,contentValues);
                }
            }
        }
    }

    public void view_comment(View view) {
        Intent intent=new Intent(this,ViewComments.class);
        startActivity(intent);
    }
}
