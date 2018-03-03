package com.example.himan.todo;

/**
 * Created by himan on 18-Feb-18.
 */

public class Comments {
    private int id;
    private String comment;
    private int todo_id;

    public Comments(int id, String comment, int todo_id) {
        this.id = id;
        this.comment = comment;
        this.todo_id = todo_id;
    }

    public Comments(String comment, int todo_id) {
        this.comment = comment;
        this.todo_id = todo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }
}
