package com.example.himan.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by himan on 15-Feb-18.
 */

public class TodoAdapter extends BaseAdapter {
    ArrayList<ToDoClass> ToDoList;
    Context context;
    public TodoAdapter(ArrayList<ToDoClass>ToDoList,Context context){
        this.ToDoList=ToDoList;
        this.context=context;
    }
    @Override
    public int getCount() {
        return ToDoList.size();
    }

    @Override
    public Object getItem(int i) {
        return ToDoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView=view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(newView==null) {
            newView = inflater.inflate(R.layout.todo_list,viewGroup,false);
            ViewHolder holder=new ViewHolder(newView);
            newView.setTag(holder);
        }
        ViewHolder holder=(ViewHolder)newView.getTag();
        ToDoClass element=ToDoList.get(i);
        holder.taskname.setText(element.getTaskName());
        holder.content.setText(element.getContent());
        return newView;
    }
}
class ViewHolder{
    TextView taskname;
    TextView content;

    public ViewHolder(View rowlayout) {
        this.taskname = rowlayout.findViewById(R.id.taskname);
        this.content = rowlayout.findViewById(R.id.content);
    }
}