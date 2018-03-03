package com.example.himan.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by himan on 17-Feb-18.
 */

public class ToDoOpenHelper extends SQLiteOpenHelper {
    private static ToDoOpenHelper openHelper;
    public static ToDoOpenHelper getOpenHelper(Context context){
        if(openHelper==null){
            openHelper=new ToDoOpenHelper(context.getApplicationContext()); //to reduce memory leaks when main activity closes
        }
        return openHelper;
    }
    private ToDoOpenHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, Contract.VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create="CREATE TABLE "+ Contract.ToDo.TABLE_NAME+ " ( "+ Contract.ToDo.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Contract.ToDo.TASK_NAME+" TEXT, "+ Contract.ToDo.CONTENT+" TEXT )";
        sqLiteDatabase.execSQL(create);
        String create_Comment="CREATE TABLE "+Contract.Comment.TABLE_NAME+" ( "+Contract.Comment.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Contract.Comment.COMMENT+" TEXT, "+Contract.Comment.TODO_ID+" INTEGER, " +
                "FOREIGN KEY ( "+Contract.Comment.TODO_ID+ " ) REFERENCES "+Contract.ToDo.TABLE_NAME+" ( "+Contract.ToDo.ID+" ) ON DELETE CASCADE )" ;
        sqLiteDatabase.execSQL(create_Comment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
