package com.example.himan.todo;

/**
 * Created by himan on 17-Feb-18.
 */

public class Contract {
    public static final String DATABASE_NAME="todo_db";
    public static final int VERSION=1;
    static class ToDo{
        public static final String TABLE_NAME="ToDo";
        public static final String TASK_NAME="taskName";
        public static final String ID="id";
        public static final String CONTENT="content";
    }
    static class Comment{
        public static final String TABLE_NAME="Comments";
        public static final String COMMENT="comment";
        public static final String ID="id";
        public static final String TODO_ID="todo_id";
    }
}
