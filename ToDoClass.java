package com.example.himan.todo;

import java.util.ArrayList;

/**
 * Created by himan on 15-Feb-18.
 */

public class ToDoClass {
    private String taskName;
    private String content;
    private int id;

    public ToDoClass(String taskName, String content, int id) {
        this.taskName = taskName;
        this.content = content;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return this.content;
    }

    public String getTaskName() {
        return this.taskName;
    }


    public void setId(int id) {
        this.id = id;
    }
}
