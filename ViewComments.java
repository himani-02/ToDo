package com.example.himan.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewComments extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String>ArrayAdapter;
    ArrayList<String> CommentList;
    ToDoOpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comments);

        listView=findViewById(R.id.listView);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            int id=bundle.getInt(ConstantKeys.POSITION_KEY,-1);
            if(id>=0){
                CommentList=fetchDataFromDb(id);
            }
            else{
                CommentList=fetchAllComments();
            }
        }

        ArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,CommentList );
        listView.setAdapter(ArrayAdapter);

    }

    private ArrayList<String> fetchAllComments() {
        ArrayList<String>Comments=new ArrayList<>();
        SQLiteDatabase database=openHelper.getReadableDatabase();
        String[] columnName={Contract.Comment.COMMENT};
        Cursor cursor=database.query(Contract.Comment.TABLE_NAME,columnName,null,null,null,null,null);

        while(cursor.moveToNext()){
            String comment=cursor.getString(cursor.getColumnIndex(Contract.Comment.COMMENT));
            Comments.add(comment);
        }
        return Comments;
    }

    private ArrayList<String> fetchDataFromDb(int id) {
        ArrayList<String>Comments=new ArrayList<>();
        SQLiteDatabase database=openHelper.getReadableDatabase();
        String[] columnName={Contract.Comment.COMMENT};
        String[]selectionArg={id+""};
        Cursor cursor=database.query(Contract.Comment.TABLE_NAME,columnName,Contract.Comment.TODO_ID+" = ? ",selectionArg,null,null,null);

        while(cursor.moveToNext()){
            String comment=cursor.getString(cursor.getColumnIndex(Contract.Comment.COMMENT));
            Comments.add(comment);
        }
        return Comments;
    }
}
