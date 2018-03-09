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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
 // where data is displayed and actions are performed
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ToDoClass>ToDoList;
    ToDoOpenHelper openHelper;
    TodoAdapter TodoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);

        openHelper=ToDoOpenHelper.getOpenHelper(this);

        ToDoList=fetchToDoListFromDb();
        //Fetching from Database
        TodoAdapter=new TodoAdapter(ToDoList,this);
        listView.setAdapter(TodoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToDoClass element=ToDoList.get(i);
                String taskname=element.getTaskName();
                String content=element.getContent();

                Intent intent=new Intent(MainActivity.this,EditActivity2.class);
                Bundle bundle=new Bundle();
                bundle.putString(ConstantKeys.TASKNAME_KEY,taskname);
                bundle.putString(ConstantKeys.CONTENT_KEY,content);
                bundle.putInt(ConstantKeys.POSITION_KEY,i);
                intent.putExtras(bundle);
                startActivityForResult(intent,ConstantKeys.EDIT_ACTIVITY);
            }
        });

    }

    private ArrayList<ToDoClass> fetchToDoListFromDb() {
        ArrayList<ToDoClass> ToDoList=new ArrayList<>();
        SQLiteDatabase database=openHelper.getReadableDatabase();
        Cursor cursor=database.query(Contract.ToDo.TABLE_NAME,null,null,null,null,null,null);

        while(cursor.moveToNext()){
            String taskName=cursor.getString(cursor.getColumnIndex(Contract.ToDo.TASK_NAME));
            String content=cursor.getString(cursor.getColumnIndex(Contract.ToDo.CONTENT));
            int id=cursor.getInt(cursor.getColumnIndex(Contract.ToDo.ID));
            ToDoClass element=new ToDoClass(taskName,content,id);
            ToDoList.add(element);

        }
        return ToDoList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Bundle bundle=data.getExtras();
            switch(requestCode){
                case ConstantKeys.EDIT_ACTIVITY:
                    if(resultCode==ConstantKeys.RESULT){
                        if(bundle!=null) {
                            int position = bundle.getInt(ConstantKeys.POSITION_KEY, -1);
                            if (position >= 0) {
                                ToDoClass element = getDataFromBundle(bundle);
                                ToDoList.add(position, element);
                                TodoAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    break;
                case ConstantKeys.ADD_REQUEST:
                    if(resultCode==ConstantKeys.RESULT){
                        if(bundle!=null) {
                            ToDoClass element = getDataFromBundle(bundle);
                            SQLiteDatabase database = openHelper.getWritableDatabase();
                            ContentValues contentValue = new ContentValues();
                            contentValue.put(Contract.ToDo.TASK_NAME, element.getTaskName());
                            contentValue.put(Contract.ToDo.CONTENT, element.getContent());
                            //setting id
                            long id = database.insert(Contract.ToDo.TABLE_NAME, null, contentValue);
                            element.setId((int) id);
                            ToDoList.add(element);
                            TodoAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    }

    private ToDoClass getDataFromBundle(Bundle bundle) {
        if(bundle!=null) {
            String taskname = bundle.getString(ConstantKeys.TASKNAME_KEY, "");
            String content = bundle.getString(ConstantKeys.CONTENT_KEY, "");
            return new ToDoClass(taskname,content);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add) {
            Intent intent = new Intent(this, FormActivity.class);
            startActivityForResult(intent, ConstantKeys.ADD_REQUEST);
            return true;
        }
        return onOptionsItemSelected(item);
    }


}
