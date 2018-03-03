package com.example.himan.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    private void populateDataFromBundle() {
        String task=bundle.getString(ConstantKeys.TASKNAME_KEY,"");
        String content=bundle.getString(ConstantKeys.CONTENT_KEY,"");
        editTaskName.setText(task);
        editContent.setText(content);
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
        if(requestCode==ConstantKeys.EDIT_REQUEST){
            if(resultCode==ConstantKeys.RESULT){
                bundle=data.getExtras();
                if(bundle!=null){
                    populateDataFromBundle();
                    setResult(resultCode,data);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void view_comment(View view) {
        Intent intent=new Intent(this,ViewComments.class);
        startActivity(intent);
    }
}
