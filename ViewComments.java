package com.example.himan.todo;

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
        CommentList=fetchDataFromDb();
        ArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,CommentList );
        listView.setAdapter(ArrayAdapter);

    }

    private ArrayList<String> fetchDataFromDb() {
        ArrayList<String>Comments=new ArrayList<>();
        SQLiteDatabase database=openHelper.getReadableDatabase();

        Cursor cursor=database.query(Contract.Comment.TABLE_NAME,null,null,null,null,null,null);

        while(cursor.moveToNext()){
            String comment=cursor.getString(cursor.getColumnIndex(Contract.Comment.COMMENT));
            Comments.add(comment);
        }
        return Comments;
    }
}
